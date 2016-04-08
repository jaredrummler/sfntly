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

package com.google.typography.font.sfntly.table.opentype;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.component.OffsetRecordTable;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class LookupListTable extends OffsetRecordTable<LookupTable> {

  private static final int FIELD_COUNT = 0;

  LookupListTable(ReadableFontData data, boolean dataIsCanonical) {
    super(data, dataIsCanonical);
  }

  @Override protected LookupTable readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new LookupTable(data, base, dataIsCanonical);
  }

  static class Builder extends OffsetRecordTable.Builder<LookupListTable, LookupTable> {

    @Override protected LookupListTable readTable(
        ReadableFontData data, int baseUnused, boolean dataIsCanonical) {
      return new LookupListTable(data, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<LookupTable> createSubTableBuilder() {
      return new LookupTable.Builder();
    }

    @Override protected VisibleSubTable.Builder<LookupTable> createSubTableBuilder(
        ReadableFontData data, boolean dataIsCanonical) {
      return new LookupTable.Builder(data, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<LookupTable> createSubTableBuilder(LookupTable subTable) {
      return new LookupTable.Builder(subTable);
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
