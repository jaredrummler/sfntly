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
import com.google.typography.font.sfntly.table.opentype.CoverageTable;
import com.google.typography.font.sfntly.table.opentype.SubstSubtable;
import com.google.typography.font.sfntly.table.opentype.multiplesubst.GlyphIds;

import java.util.Iterator;

public class OneToManySubst extends SubstSubtable implements Iterable<NumRecordTable> {

  private final GlyphIds array;

  // //////////////
  // Constructors

  protected OneToManySubst(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
    if (format != 1) {
      throw new IllegalStateException("Subt format value is " + format + " (should be 1).");
    }
    array = new GlyphIds(data, headerSize(), dataIsCanonical);
  }

  // //////////////////////////////////
  // Methods redirected to the array

  public NumRecordList recordList() {
    return array.recordList;
  }

  public NumRecordTable subTableAt(int index) {
    return array.subTableAt(index);
  }

  @Override
  public Iterator<NumRecordTable> iterator() {
    return array.iterator();
  }

  // //////////////////////////////////
  // Methods specific to this class

  public CoverageTable coverage() {
    return array.coverage;
  }

  public static class Builder extends SubstSubtable.Builder<SubstSubtable> {

    private final GlyphIds.Builder arrayBuilder;

    protected Builder() {
      super();
      arrayBuilder = new GlyphIds.Builder();
    }

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
      arrayBuilder = new GlyphIds.Builder(data, dataIsCanonical);
    }

    protected Builder(SubstSubtable subTable) {
      OneToManySubst multiSubst = (OneToManySubst) subTable;
      arrayBuilder = new GlyphIds.Builder(multiSubst.array);
    }

    @Override
    public int subDataSizeToSerialize() {
      return arrayBuilder.subDataSizeToSerialize();
    }

    @Override
    public int subSerialize(WritableFontData newData) {
      return arrayBuilder.subSerialize(newData);
    }

    // /////////////////////////////////
    // must implement abstract methods

    @Override
    protected boolean subReadyToSerialize() {
      return true;
    }

    @Override
    public void subDataSet() {
      arrayBuilder.subDataSet();
    }

    @Override
    public OneToManySubst subBuildTable(ReadableFontData data) {
      return new OneToManySubst(data, 0, true);
    }
  }
}
