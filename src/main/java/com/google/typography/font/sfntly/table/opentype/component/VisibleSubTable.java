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
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.SubTable;

public abstract class VisibleSubTable extends SubTable {

  private VisibleSubTable(ReadableFontData data) {
    super(data);
  }

  public abstract static class Builder<T extends SubTable> extends SubTable.Builder<T> {

    protected int serializedLength;

    protected Builder() {
      super(null);
    }

    protected Builder(ReadableFontData data) {
      super(data);
    }

    @Override
    public abstract int subSerialize(WritableFontData newData);

    /**
     * Even though public, not to be used by the end users. Made public only
     * make it available to packages under
     * {@code com.google.typography.font.sfntly.table.opentype}.
     */
    @Override
    public abstract int subDataSizeToSerialize();

    @Override
    protected abstract void subDataSet();

    @Override
    protected abstract T subBuildTable(ReadableFontData data);
  }
}