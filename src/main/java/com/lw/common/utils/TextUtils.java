package com.lw.common.utils;
public class TextUtils {
  public static String camelToUnderline(final String input) {
    final StringBuilder output = new StringBuilder();
    final int length = input.length();
    for (int index = 0; index < length; index++) {
      final char charAt = input.charAt(index);
      if (Character.isUpperCase(charAt)) {
        output.append('_');
        output.append(Character.toLowerCase(charAt));
      } else {
        output.append(charAt);
      }
    }

    return output.toString();
  }
}