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
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public final class NullTable extends SubstSubtable {

  private static final int RECORD_SIZE = 0;

  NullTable(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  private NullTable(ReadableFontData data) {
    super(data, 0, false);
  }

  private NullTable() {
    super(null, 0, false);
  }

  public final static class Builder extends VisibleSubTable.Builder<NullTable> {

    private Builder() {
    }

    private Builder(ReadableFontData data, boolean dataIsCanonical) {
    }

    private Builder(NullTable table) {
    }

    @Override public int subDataSizeToSerialize() {
      return NullTable.RECORD_SIZE;
    }

    @Override public int subSerialize(WritableFontData newData) {
      return NullTable.RECORD_SIZE;
    }

    @Override public NullTable subBuildTable(ReadableFontData data) {
      return new NullTable(data);
    }

    @Override public void subDataSet() {
    }

    @Override protected boolean subReadyToSerialize() {
      return true;
    }
  }

}
