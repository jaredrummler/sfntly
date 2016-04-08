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
import com.google.typography.font.sfntly.table.opentype.component.OffsetRecordTable;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class LigatureSet extends OffsetRecordTable<Ligature> {

  LigatureSet(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  static class Builder extends OffsetRecordTable.Builder<LigatureSet, Ligature> {

    Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    Builder() {
      super();
    }

    Builder(LigatureSet table) {
      super(table);
    }

    @Override protected LigatureSet readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      return new LigatureSet(data, base, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<Ligature> createSubTableBuilder() {
      return new Ligature.Builder();
    }

    @Override protected VisibleSubTable.Builder<Ligature> createSubTableBuilder(
        ReadableFontData data, boolean dataIsCanonical) {
      return new Ligature.Builder(data, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<Ligature> createSubTableBuilder(Ligature subTable) {
      return new Ligature.Builder(subTable);
    }

    @Override protected void initFields() {
    }

    @Override protected int fieldCount() {
      return 0;
    }
  }

  @Override protected Ligature readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new Ligature(data, base, dataIsCanonical);
  }

  @Override public int fieldCount() {
    return 0;
  }

}
