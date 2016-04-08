/*
 * Copyright 2010 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

