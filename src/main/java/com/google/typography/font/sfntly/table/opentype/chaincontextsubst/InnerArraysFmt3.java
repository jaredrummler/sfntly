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

package com.google.typography.font.sfntly.table.opentype.chaincontextsubst;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.SubTable;
import com.google.typography.font.sfntly.table.opentype.component.NumRecordList;
import com.google.typography.font.sfntly.table.opentype.component.SubstLookupRecordList;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class InnerArraysFmt3 extends SubTable {

  public final CoverageArray backtrackGlyphs;
  public final CoverageArray inputGlyphs;
  public final CoverageArray lookAheadGlyphs;
  public final SubstLookupRecordList lookupRecords;

  // //////////////
  // Constructors

  public InnerArraysFmt3(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data);
    NumRecordList records = new NumRecordList(data, 0, base);
    backtrackGlyphs = new CoverageArray(records);

    records = new NumRecordList(data, 0, records.limit());
    inputGlyphs = new CoverageArray(records);

    records = new NumRecordList(data, 0, records.limit());
    lookAheadGlyphs = new CoverageArray(records);

    lookupRecords = new SubstLookupRecordList(data, records.limit());
  }

  public static class Builder extends VisibleSubTable.Builder<InnerArraysFmt3> {

    private CoverageArray.Builder backtrackGlyphsBuilder;
    private CoverageArray.Builder inputGlyphsBuilder;
    private CoverageArray.Builder lookAheadGlyphsBuilder;
    private SubstLookupRecordList lookupRecordsBuilder;

    protected Builder() {
      super();
    }

    protected Builder(InnerArraysFmt3 table) {
      this(table.readFontData(), 0, false);
    }

    protected Builder(ReadableFontData data, int base, boolean dataIsCanonical) {
      super(data);
      if (!dataIsCanonical) {
        prepareToEdit();
      }
    }

    protected Builder(Builder other) {
      super();
      backtrackGlyphsBuilder = other.backtrackGlyphsBuilder;
      inputGlyphsBuilder = other.inputGlyphsBuilder;
      lookAheadGlyphsBuilder = other.lookAheadGlyphsBuilder;
      lookupRecordsBuilder = other.lookupRecordsBuilder;
    }

    @Override
    public int subDataSizeToSerialize() {
      if (lookupRecordsBuilder != null) {
        serializedLength = lookupRecordsBuilder.limit();
      } else {
        computeSizeFromData(internalReadData());
      }
      return serializedLength;
    }

    @Override
    public int subSerialize(WritableFontData newData) {
      if (serializedLength == 0) {
        return 0;
      }

      if (backtrackGlyphsBuilder == null || inputGlyphsBuilder == null
          || lookAheadGlyphsBuilder == null || lookupRecordsBuilder == null) {
        return serializeFromData(newData);
      }

      int tableOnlySize = 0;
      tableOnlySize += backtrackGlyphsBuilder.tableSizeToSerialize();
      tableOnlySize += inputGlyphsBuilder.tableSizeToSerialize();
      tableOnlySize += lookAheadGlyphsBuilder.tableSizeToSerialize();
      int subTableWriteOffset = tableOnlySize
          + lookupRecordsBuilder.writeTo(newData.slice(tableOnlySize));

      backtrackGlyphsBuilder.subSerialize(newData, subTableWriteOffset);
      subTableWriteOffset += backtrackGlyphsBuilder.subTableSizeToSerialize();
      int tableWriteOffset = backtrackGlyphsBuilder.tableSizeToSerialize();

      inputGlyphsBuilder.subSerialize(newData.slice(tableWriteOffset), subTableWriteOffset);
      subTableWriteOffset += inputGlyphsBuilder.subTableSizeToSerialize();
      tableWriteOffset += inputGlyphsBuilder.tableSizeToSerialize();

      lookAheadGlyphsBuilder.subSerialize(newData.slice(tableWriteOffset), subTableWriteOffset);
      subTableWriteOffset += lookAheadGlyphsBuilder.subTableSizeToSerialize();

      return subTableWriteOffset;
    }

    @Override
    public InnerArraysFmt3 subBuildTable(ReadableFontData data) {
      return new InnerArraysFmt3(data, 0, true);
    }

    @Override
    protected boolean subReadyToSerialize() {
      return true;
    }

    @Override
    public void subDataSet() {
      backtrackGlyphsBuilder = null;
      inputGlyphsBuilder = null;
      lookupRecordsBuilder = null;
      lookAheadGlyphsBuilder = null;
    }

    // ////////////////////////////////////
    // private methods

    private void prepareToEdit() {
      initFromData(internalReadData());
      setModelChanged();
    }

    private void initFromData(ReadableFontData data) {
      if (backtrackGlyphsBuilder == null || inputGlyphsBuilder == null
          || lookAheadGlyphsBuilder == null || lookupRecordsBuilder == null) {
        NumRecordList records = new NumRecordList(data);
        backtrackGlyphsBuilder = new CoverageArray.Builder(records);

        records = new NumRecordList(data, 0, records.limit());
        inputGlyphsBuilder = new CoverageArray.Builder(records);

        records = new NumRecordList(data, 0, records.limit());
        lookAheadGlyphsBuilder = new CoverageArray.Builder(records);

        lookupRecordsBuilder = new SubstLookupRecordList(data, records.limit());
      }
    }

    private void computeSizeFromData(ReadableFontData data) {
      // This assumes canonical data.
      int len = 0;
      if (data != null) {
        len = data.length();
      }
      serializedLength = len;
    }

    private int serializeFromData(WritableFontData newData) {
      // The source data must be canonical.
      ReadableFontData data = internalReadData();
      data.copyTo(newData);
      return data.length();
    }
  }
}
