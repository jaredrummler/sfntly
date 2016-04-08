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

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;

final class RangeRecord implements Record {

  static final int RECORD_SIZE = 6;
  private static final int START_OFFSET = 0;
  private static final int END_OFFSET = 2;
  private static final int PROPERTY_OFFSET = 4;
  final int start;
  final int end;
  final int property;

  RangeRecord(ReadableFontData data, int base) {
    this.start = data.readUShort(base + START_OFFSET);
    this.end = data.readUShort(base + END_OFFSET);
    this.property = data.readUShort(base + PROPERTY_OFFSET);
  }

  @Override
  public int writeTo(WritableFontData newData, int base) {
    newData.writeUShort(base + START_OFFSET, start);
    newData.writeUShort(base + END_OFFSET, end);
    return RECORD_SIZE;
  }
}
