package com.lw.common;
import java.util.regex.Pattern;

public enum Regexes {

  sqlOrder(Regexes.SQL_ORDER), phone(Regexes.PHONE), pin(Regexes.PIN), password(Regexes.PASSWORD), // NOSONAR
  userNickname(Regexes.USER_NICKNAME), uuid(Regexes.UUID), fundCode(Regexes.FUND_CODE), fofName(Regexes.FOF_NAME); // NOSONAR

  public static final String SQL_ORDER = "^(?: *\\w+(?: +asc| +desc)? *)(?:, *\\w+(?: +asc| +desc)? *)*$";

  public static final String PHONE = "^1\\d{10}$";
  public static final String PIN = "^[1-9]\\d{5}$";
  public static final String PASSWORD = "^[\\x21-\\x7e]{6,20}$"; // NOSONAR
  public static final String USER_NICKNAME = "^[0-9a-zA-Z\\u4e00-\\u9fff]{2,10}$";

  public static final String UUID = "^[0-9a-f]{32}$";

  public static final String FUND_CODE = "^\\d{6}$";
  public static final String FOF_NAME = "^[0-9a-zA-Z\\u4e00-\\u9fff]{2,12}$";

  private Pattern pattern;

  private Regexes(final String regex) {
    this.pattern = Pattern.compile(regex);
  }

  public boolean match(final String input) {
    return this.pattern.matcher(input).matches();
  }

}
