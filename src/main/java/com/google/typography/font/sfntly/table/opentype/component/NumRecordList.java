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

import java.util.Iterator;

public class NumRecordList extends RecordList<NumRecord> {

  public NumRecordList(WritableFontData data) {
    super(data);
  }

  public NumRecordList(ReadableFontData data) {
    super(data);
  }

  public NumRecordList(ReadableFontData data, int countDecrement) {
    super(data, countDecrement);
  }

  public NumRecordList(ReadableFontData data, int countDecrement, int countOffset) {
    super(data, countDecrement, countOffset);
  }

  public NumRecordList(
      ReadableFontData data, int countDecrement, int countOffset, int valuesOffset) {
    super(data, countDecrement, countOffset, valuesOffset);
  }

  public NumRecordList(NumRecordList other) {
    super(other);
  }

  public static int sizeOfListOfCount(int count) {
    return RecordList.DATA_OFFSET + count * NumRecord.RECORD_SIZE;
  }

  public boolean contains(int value) {
    Iterator<NumRecord> iterator = iterator();
    while (iterator.hasNext()) {
      NumRecord record = iterator.next();
      if (record.value == value) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected NumRecord getRecordAt(ReadableFontData data, int offset) {
    return new NumRecord(data, offset);
  }

  @Override
  protected int recordSize() {
    return NumRecord.RECORD_SIZE;
  }
}
