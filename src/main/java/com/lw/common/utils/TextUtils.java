package com.lw.common.utils;

import java.lang.reflect.Proxy;

public class TextUtils {
  public static String camelToUnderline(final String input) {
    final StringBuilder output = new StringBuilder();
    final int length = input.length();
    for (int index = 0; index < length; index++) {
      final char charAt = input.charAt(index);
      if (Character.isUpperCase(charAt)) {
        Proxy proxy;
        output.append('_');
        output.append(Character.toLowerCase(charAt));
      } else {
        output.append(charAt);
      }
    }

    return output.toString();
  }
}