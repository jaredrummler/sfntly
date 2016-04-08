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

package com.google.typography.font.sfntly.table.opentype.contextsubst;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.component.OffsetRecordTable;

public abstract class SubGenericRuleSet<T extends DoubleRecordTable> extends OffsetRecordTable<T> {

  protected SubGenericRuleSet(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  @Override public int fieldCount() {
    return 0;
  }

  protected abstract static class Builder<T extends SubGenericRuleSet<S>,
      S extends DoubleRecordTable>
      extends OffsetRecordTable.Builder<T, S> {

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    protected Builder() {
      super();
    }

    protected Builder(T table) {
      super(table);
    }

    @Override protected void initFields() {
    }

    @Override protected int fieldCount() {
      return 0;
    }
  }

}
