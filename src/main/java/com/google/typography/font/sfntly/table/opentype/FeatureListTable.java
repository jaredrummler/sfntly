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
import com.google.typography.font.sfntly.table.opentype.component.TagOffsetsTable;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class FeatureListTable extends TagOffsetsTable<FeatureTable> {

  FeatureListTable(ReadableFontData data, boolean dataIsCanonical) {
    super(data, dataIsCanonical);
  }

  @Override protected FeatureTable readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new FeatureTable(data, dataIsCanonical);
  }

  static class Builder extends TagOffsetsTable.Builder<FeatureListTable, FeatureTable> {

    protected Builder() {
      super();
    }

    protected Builder(ReadableFontData data, int base, boolean dataIsCanonical) {
      super(data, 0, false);
    }

    @Override protected VisibleSubTable.Builder<FeatureTable> createSubTableBuilder(
        ReadableFontData data, int tag, boolean dataIsCanonical) {
      return new FeatureTable.Builder(data, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<FeatureTable> createSubTableBuilder() {
      return new FeatureTable.Builder();
    }

    @Override protected FeatureListTable readTable(
        ReadableFontData data, int baseUnused, boolean dataIsCanonical) {
      return new FeatureListTable(data, dataIsCanonical);
    }

    @Override protected void initFields() {
    }

    @Override public int fieldCount() {
      return 0;
    }
  }

  @Override public int fieldCount() {
    return 0;
  }

}
