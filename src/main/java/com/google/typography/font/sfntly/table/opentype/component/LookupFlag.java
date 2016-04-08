/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.opentype.component;

enum LookupFlag {
  RIGHT_TO_LEFT(1),
  IGNORE_BASE_GLYPHS(2),
  IGNORE_LIGATURES(4),
  IGNORE_MARKS(8);

  boolean isSet(int flags) {
    return isFlagSet(flags, mask);
  }

  int set(int flags) {
    return setFlag(flags, mask);
  }

  int clear(int flags) {
    return clearFlag(flags, mask);
  }

  private final int mask;

  private LookupFlag(int mask) {
    this.mask = mask;
  }

  static boolean isFlagSet(int flags, int mask) {
    return (flags & mask) != 0;
  }

  static int setFlag(int flags, int mask) {
    return flags | mask;
  }

  static int clearFlag(int flags, int mask) {
    return flags & ~mask;
  }
}

