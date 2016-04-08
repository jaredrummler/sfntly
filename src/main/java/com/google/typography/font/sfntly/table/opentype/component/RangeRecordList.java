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

public final class RangeRecordList extends RecordList<RangeRecord> {

  public RangeRecordList(WritableFontData data) {
    super(data);
  }

  public RangeRecordList(ReadableFontData data) {
    super(data);
  }

  public static int sizeOfListOfCount(int count) {
    return RecordList.DATA_OFFSET + count * RangeRecord.RECORD_SIZE;
  }

  @Override
  protected RangeRecord getRecordAt(ReadableFontData data, int offset) {
    return new RangeRecord(data, offset);
  }

  @Override
  protected int recordSize() {
    return RangeRecord.RECORD_SIZE;
  }
}
