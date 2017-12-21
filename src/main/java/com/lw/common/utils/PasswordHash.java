package com.lw.common.utils;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordHash {
  private static final Logger log = LoggerFactory.getLogger(PasswordHash.class);

  private static final int SALT_LENGTH = 16;
  private static final int PAD_LENGTH = 16;
  private static final String PAD_STRING = "7zQ~";
  private static final SecureRandomNumberGenerator SecureRandomNumberGenerator = new SecureRandomNumberGenerator();
  private static final Function<String, SimpleHash> InnerHash = Md5Hash::new;
  private static final BiFunction<SimpleHash, ByteSource, SimpleHash> OuterHash = Sha256Hash::new;

  public static String createSalt() {
    return PasswordHash.createSaltByte().toHex();
  }

  private static ByteSource createSaltByte() {
    return PasswordHash.SecureRandomNumberGenerator.nextBytes(PasswordHash.SALT_LENGTH);
  }

  public static boolean validate(final String password, final String salt, final String hash) {
    return hash.equals(PasswordHash.hashPassword(password, salt));
  }

  public static String hashPassword(final String password, final String salt) {
    return PasswordHash.hashPassword(password, new SimpleByteSource(Hex.decode(salt)));
  }

  private static String hashPassword(final String password, final ByteSource salt) {
    return PasswordHash.hashOuter(PasswordHash.hashInner(PasswordHash.pad(password)), salt).toHex();
  }

  private static String pad(final String password) {
    return StringUtils.center(password, PasswordHash.PAD_LENGTH, PasswordHash.PAD_STRING);
  }

  private static SimpleHash hashInner(final String password) {
    return PasswordHash.InnerHash.apply(password);
  }

  private static SimpleHash hashOuter(final SimpleHash hash1, final ByteSource salt) {
    return PasswordHash.OuterHash.apply(hash1, salt);
  }

  private static void test() {
    PasswordHash.log.debug("START");
    final ByteSource salt = PasswordHash.createSaltByte();
    PasswordHash.log.debug("SALT:{}", salt.toHex());
    final String password = "123456";
    PasswordHash.log.debug("PASS:{}", password);
    final String passcode = PasswordHash.pad(password);
    PasswordHash.log.debug("CODE:{}", passcode);

    final SimpleHash hash1 = PasswordHash.hashInner(passcode);
    PasswordHash.log.debug("HASH1:{}", hash1.toHex());
    final SimpleHash hash2 = PasswordHash.hashOuter(hash1, salt);
    PasswordHash.log.debug("HASH2:{}", hash2.toString());

    final String hashb = PasswordHash.hashPassword(password, salt);
    PasswordHash.log.debug("HASHB:{}", hashb.toString());
    final String hashs = PasswordHash.hashPassword(password, salt.toHex());
    PasswordHash.log.debug("HASHS:{}", hashs.toString());
  }

  public static void main(final String args[]) {
    PasswordHash.test();
  }

}
