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

package com.google.typography.font.sfntly.table.opentype.ligaturesubst;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.CoverageTable;
import com.google.typography.font.sfntly.table.opentype.component.OffsetRecordTable;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class InnerArrayFmt1 extends OffsetRecordTable<LigatureSet> {

  private static final int FIELD_COUNT = 1;

  private static final int COVERAGE_INDEX = 0;
  private static final int COVERAGE_DEFAULT = 0;
  public final CoverageTable coverage;

  public InnerArrayFmt1(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
    int coverageOffset = getField(COVERAGE_INDEX);
    coverage = new CoverageTable(data.slice(coverageOffset), 0, dataIsCanonical);
  }

  @Override public LigatureSet readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new LigatureSet(data, 0, dataIsCanonical);
  }

  public static class Builder extends OffsetRecordTable.Builder<InnerArrayFmt1, LigatureSet> {

    public Builder() {
      super();
    }

    public Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    public Builder(InnerArrayFmt1 table) {
      super(table);
    }

    @Override protected InnerArrayFmt1 readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      return new InnerArrayFmt1(data, base, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<LigatureSet> createSubTableBuilder() {
      return new LigatureSet.Builder();
    }

    @Override protected VisibleSubTable.Builder<LigatureSet> createSubTableBuilder(
        ReadableFontData data, boolean dataIsCanonical) {
      return new LigatureSet.Builder(data, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<LigatureSet> createSubTableBuilder(LigatureSet subTable) {
      return new LigatureSet.Builder(subTable);
    }

    @Override protected void initFields() {
      setField(COVERAGE_INDEX, COVERAGE_DEFAULT);
    }

    @Override protected int fieldCount() {
      return FIELD_COUNT;
    }
  }

  @Override public int fieldCount() {
    return FIELD_COUNT;
  }

}
