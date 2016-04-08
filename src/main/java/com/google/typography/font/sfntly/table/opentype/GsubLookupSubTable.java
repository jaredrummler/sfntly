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
import com.google.typography.font.sfntly.table.opentype.component.GsubLookupType;

/**
 * @author dougfelt@google.com (Doug Felt)
 */
abstract class GsubLookupSubTable extends LookupSubTable {

  protected GsubLookupSubTable(ReadableFontData data, boolean dataIsCanonical) {
    super(data, dataIsCanonical);
  }

  @Override public abstract Builder<? extends GsubLookupSubTable> builder();

  @Override public abstract GsubLookupType lookupType();

  static abstract class Builder<T extends GsubLookupSubTable>
      extends LookupSubTable.Builder<T> {

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    protected Builder(T table) {
      super(table);
    }

    @Override public abstract GsubLookupType lookupType();
  }

}
