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
import com.google.typography.font.sfntly.table.opentype.component.NumRecord;
import com.google.typography.font.sfntly.table.opentype.component.NumRecordList;
import com.google.typography.font.sfntly.table.opentype.component.RecordList;
import com.google.typography.font.sfntly.table.opentype.component.RecordsTable;

public class Ligature extends RecordsTable<NumRecord> {

  private static final int FIELD_COUNT = 1;

  public static final int LIG_GLYPH_INDEX = 0;
  private static final int LIG_GLYPH_DEFAULT = 0;

  Ligature(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  static class Builder extends RecordsTable.Builder<Ligature, NumRecord> {

    Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    Builder() {
      super();
    }

    Builder(Ligature table) {
      super(table);
    }

    @Override protected Ligature readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      return new Ligature(data, base, dataIsCanonical);
    }

    @Override protected void initFields() {
      setField(LIG_GLYPH_INDEX, LIG_GLYPH_DEFAULT);
    }

    @Override protected int fieldCount() {
      return FIELD_COUNT;
    }

    @Override protected RecordList<NumRecord> readRecordList(ReadableFontData data, int base) {
      return new NumRecordList(data);
    }
  }

  @Override public int fieldCount() {
    return FIELD_COUNT;
  }

  @Override protected RecordList<NumRecord> createRecordList(ReadableFontData data) {
    return new NumRecordList(data, 1);
  }

}
