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

final class TagOffsetRecord implements Record {

  static final int RECORD_SIZE = 6;
  private static final int TAG_POS = 0;
  private static final int OFFSET_POS = 4;
  final int tag;
  final int offset;

  TagOffsetRecord(ReadableFontData data, int base) {
    this.tag = data.readULongAsInt(base + TAG_POS);
    this.offset = data.readUShort(base + OFFSET_POS);
  }

  TagOffsetRecord(int tag, int offset) {
    this.tag = tag;
    this.offset = offset;
  }

  @Override
  public int writeTo(WritableFontData newData, int base) {
    newData.writeULong(base + TAG_POS, tag);
    newData.writeUShort(base + OFFSET_POS, offset);
    return RECORD_SIZE;
  }
}
