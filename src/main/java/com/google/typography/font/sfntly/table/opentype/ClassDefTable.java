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
import com.google.typography.font.sfntly.table.opentype.classdef.InnerArrayFmt1;
import com.google.typography.font.sfntly.table.opentype.component.RangeRecordTable;
import com.google.typography.font.sfntly.table.opentype.component.RecordsTable;

public class ClassDefTable extends SubstSubtable {

  public final RecordsTable<?> array;
  private boolean dataIsCanonical;

  // //////////////
  // Constructors

  public ClassDefTable(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
    this.dataIsCanonical = dataIsCanonical;

    switch (format) {
      case 1:
        array = new InnerArrayFmt1(data, headerSize(), dataIsCanonical);
        break;
      case 2:
        array = new RangeRecordTable(data, headerSize(), dataIsCanonical);
        break;
      default:
        throw new IllegalArgumentException("class def format " + format + " unexpected");
    }
  }

  // ////////////////////////////////////////
  // Utility methods specific to this class

  public InnerArrayFmt1 fmt1Table() {
    switch (format) {
      case 1:
        return (InnerArrayFmt1) array;
      default:
        throw new IllegalArgumentException("unexpected format table requested: " + format);
    }
  }

  public RangeRecordTable fmt2Table() {
    switch (format) {
      case 2:
        return (RangeRecordTable) array;
      default:
        throw new IllegalArgumentException("unexpected format table requested: " + format);
    }
  }

  public static class Builder extends SubstSubtable.Builder<ClassDefTable> {

    private final RecordsTable.Builder<?, ?> arrayBuilder;

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
      switch (format) {
        case 1:
          arrayBuilder = new InnerArrayFmt1.Builder(data, headerSize(), dataIsCanonical);
          break;
        case 2:
          arrayBuilder = new RangeRecordTable.Builder(data, headerSize(), dataIsCanonical);
          break;
        default:
          throw new IllegalArgumentException("class def format " + format + " unexpected");
      }
    }

    protected Builder(ClassDefTable table) {
      this(table.readFontData(), table.dataIsCanonical);
    }

    @Override
    public int subDataSizeToSerialize() {
      return super.subDataSizeToSerialize() + arrayBuilder.subDataSizeToSerialize();
    }

    @Override
    public int subSerialize(WritableFontData newData) {
      int newOffset = super.subSerialize(newData);
      return arrayBuilder.subSerialize(newData.slice(newOffset));
    }

    // ///////////////////
    // Overriden methods

    @Override
    public ClassDefTable subBuildTable(ReadableFontData data) {
      return new ClassDefTable(data, 0, false);
    }

    @Override
    protected boolean subReadyToSerialize() {
      return super.subReadyToSerialize() && true;
    }

    @Override
    public void subDataSet() {
      super.subDataSet();
      arrayBuilder.subDataSet();
    }
  }
}
