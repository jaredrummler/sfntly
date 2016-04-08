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
import com.google.typography.font.sfntly.table.opentype.component.HeaderTable;

public abstract class SubstSubtable extends HeaderTable {

  private static final int FIELD_COUNT = 1;
  private static final int FORMAT_INDEX = 0;
  private static final int FORMAT_DEFAULT = 0;
  public final int format;

  protected SubstSubtable(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
    format = getField(FORMAT_INDEX);
  }

  @Override
  public int fieldCount() {
    return FIELD_COUNT;
  }

  public abstract static class Builder<T extends SubstSubtable> extends HeaderTable.Builder<T> {

    protected int format;

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data);
      format = getField(FORMAT_INDEX);
    }

    protected Builder() {
      super();
    }

    @Override
    protected void initFields() {
      setField(FORMAT_INDEX, FORMAT_DEFAULT);
    }

    @Override
    public int fieldCount() {
      return FIELD_COUNT;
    }
  }
}
