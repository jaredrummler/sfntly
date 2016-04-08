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

public enum GsubLookupType implements LookupType {
  GSUB_SINGLE,
  GSUB_MULTIPLE,
  GSUB_ALTERNATE,
  GSUB_LIGATURE,
  GSUB_CONTEXTUAL,
  GSUB_CHAINING_CONTEXTUAL,
  GSUB_EXTENSION,
  GSUB_REVERSE_CHAINING_CONTEXTUAL_SINGLE;

  @Override public int typeNum() {
    return ordinal() + 1;
  }

  @Override public String toString() {
    return super.toString().toLowerCase();
  }

  public static GsubLookupType forTypeNum(int typeNum) {
    if (typeNum <= 0 || typeNum > values.length) {
      System.err.format("unknown gsub lookup typeNum: %d\n", typeNum);
      return null;
    }
    return values[typeNum - 1];
  }

  private static final GsubLookupType[] values = values();
}
