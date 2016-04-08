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

public class RangeRecordTable extends RecordsTable<RangeRecord> {

  public RangeRecordTable(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  @Override protected RecordList<RangeRecord> createRecordList(ReadableFontData data) {
    return new RangeRecordList(data);
  }

  @Override public int fieldCount() {
    return 0;
  }

  public static class Builder extends RecordsTable.Builder<RangeRecordTable, RangeRecord> {

    public Builder(ReadableFontData data, int base, boolean dataIsCanonical) {
      super(data, base, dataIsCanonical);
    }

    @Override protected RangeRecordTable readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      if (base != 0) {
        throw new UnsupportedOperationException();
      }
      return new RangeRecordTable(data, base, dataIsCanonical);
    }

    @Override protected RecordList<RangeRecord> readRecordList(ReadableFontData data, int base) {
      if (base != 0) {
        throw new UnsupportedOperationException();
      }
      return new RangeRecordList(data);
    }

    @Override protected int fieldCount() {
      return 0;
    }

    @Override protected void initFields() {
    }
  }

}
