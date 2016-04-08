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

package com.google.typography.font.sfntly.table.opentype.chaincontextsubst;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.CoverageTable;
import com.google.typography.font.sfntly.table.opentype.component.NumRecordList;
import com.google.typography.font.sfntly.table.opentype.component.OffsetRecordTable;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class CoverageArray extends OffsetRecordTable<CoverageTable> {

  private static final int FIELD_COUNT = 0;

  private CoverageArray(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  public CoverageArray(NumRecordList records) {
    super(records);
  }

  @Override protected CoverageTable readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new CoverageTable(data, 0, dataIsCanonical);
  }

  public static class Builder extends OffsetRecordTable.Builder<CoverageArray, CoverageTable> {

    public Builder(NumRecordList records) {
      super(records);
    }

    @Override protected CoverageArray readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      return new CoverageArray(data, base, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<CoverageTable> createSubTableBuilder() {
      return new CoverageTable.Builder();
    }

    @Override protected VisibleSubTable.Builder<CoverageTable> createSubTableBuilder(
        ReadableFontData data, boolean dataIsCanonical) {
      return new CoverageTable.Builder(data, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<CoverageTable> createSubTableBuilder(CoverageTable subTable) {
      return new CoverageTable.Builder(subTable);
    }

    @Override protected void initFields() {
    }

    @Override public int fieldCount() {
      return FIELD_COUNT;
    }
  }

  @Override public int fieldCount() {
    return FIELD_COUNT;
  }

}
