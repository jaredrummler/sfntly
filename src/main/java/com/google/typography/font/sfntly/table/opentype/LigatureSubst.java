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
import com.google.typography.font.sfntly.table.opentype.ligaturesubst.InnerArrayFmt1;
import com.google.typography.font.sfntly.table.opentype.ligaturesubst.LigatureSet;

import java.util.Iterator;

public class LigatureSubst extends SubstSubtable implements Iterable<LigatureSet> {

  private final InnerArrayFmt1 array;

  // //////////////
  // Constructors

  LigatureSubst(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
    if (format != 1) {
      throw new IllegalStateException("Subt format value is " + format + " (should be 1).");
    }
    array = new InnerArrayFmt1(data, headerSize(), dataIsCanonical);
  }

  // //////////////////////////////////
  // Methods redirected to the array

  public int subTableCount() {
    return array.recordList.count();
  }

  public LigatureSet subTableAt(int index) {
    return array.subTableAt(index);
  }

  @Override
  public Iterator<LigatureSet> iterator() {
    return array.iterator();
  }

  // //////////////////////////////////
  // Methods specific to this class

  public CoverageTable coverage() {
    return array.coverage;
  }

  // //////////////////////////////////
  // Builder

  static class Builder extends SubstSubtable.Builder<SubstSubtable> {

    private final InnerArrayFmt1.Builder arrayBuilder;

    // //////////////
    // Constructors

    Builder() {
      super();
      arrayBuilder = new InnerArrayFmt1.Builder();
    }

    Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
      arrayBuilder = new InnerArrayFmt1.Builder(data, dataIsCanonical);
    }

    Builder(SubstSubtable subTable) {
      LigatureSubst ligSubst = (LigatureSubst) subTable;
      arrayBuilder = new InnerArrayFmt1.Builder(ligSubst.array);
    }

    // /////////////////////////////
    // private methods for builders

    // ///////////////////////////////
    // private methods to serialize

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
    public LigatureSubst subBuildTable(ReadableFontData data) {
      return new LigatureSubst(data, 0, true);
    }
  }
}
