/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.math;

/**
 * Fixed 16.16 integer utilities.
 *
 * @author Stuart Gill
 */
public final class Fixed1616 {

  public static int integral(int fixed) {
    return (fixed >> 16) & 0xffff;
  }

  public static int fractional(int fixed) {
    return fixed & 0xffff;
  }

  public static int fixed(int integral, int fractional) {
    return ((integral & 0xffff) << 16) | (fractional & 0xffff);
  }

  /**
   * @param fixed
   *     the number to convert
   * @return a double representing the 16-16 floating point number
   */
  public static double doubleValue(int fixed) {
    return fixed / 65536.0; // shift the decimal 16 bits
  }

  public static String toString(int fixed) {
    StringBuilder sb = new StringBuilder();
    sb.append(Fixed1616.integral(fixed));
    sb.append(".");
    sb.append(Fixed1616.fractional(fixed));
    return sb.toString();
  }
}
