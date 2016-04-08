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

import java.util.Iterator;

public abstract class RecordsTable<R extends Record> extends HeaderTable implements Iterable<R> {

  public final RecordList<R> recordList;

  // ///////////////
  // constructors

  protected RecordsTable(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
    recordList = createRecordList(data.slice(base + headerSize()));
  }

  protected RecordsTable(ReadableFontData data, boolean dataIsCanonical) {
    this(data, 0, dataIsCanonical);
  }

  protected RecordsTable(RecordList<R> records) {
    super(records.readData, records.base, false);
    recordList = records;
  }

  @Override public Iterator<R> iterator() {
    return recordList.iterator();
  }

  // ////////////////////////////////////
  // implementations pushed to subclasses

  protected abstract RecordList<R> createRecordList(ReadableFontData data);

  public abstract static class Builder<T extends HeaderTable, R extends Record>
      extends HeaderTable.Builder<T> {

    protected RecordList<R> records;
    private int serializedLength;
    private final int base;

    protected Builder() {
      super();
      base = 0;
    }

    protected Builder(RecordsTable<R> table) {
      this(table.readFontData(), table.base, table.dataIsCanonical);
    }

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      this(data, 0, dataIsCanonical);
    }

    protected Builder(ReadableFontData data, int base, boolean dataIsCanonical) {
      super(data);
      this.base = base;
      if (!dataIsCanonical) {
        prepareToEdit();
      }
    }

    protected Builder(Builder<T, R> other) {
      super();
      base = other.base;
      records = other.records;
    }

    // ////////////////
    // private methods

    public RecordList<R> records() {
      return records;
    }

    public int count() {
      initFromData(internalReadData(), base);
      return records.count();
    }

    // ////////////////////////////////////
    // overriden methods

    @Override public int subDataSizeToSerialize() {
      if (records != null) {
        serializedLength = records.limit();
      } else {
        computeSizeFromData(internalReadData().slice(base + headerSize()));
      }
      return serializedLength;
    }

    @Override public int subSerialize(WritableFontData newData) {
      if (serializedLength == 0) {
        return 0;
      }

      if (records == null) {
        return serializeFromData(newData);
      }

      return records.writeTo(newData);
    }

    @Override public T subBuildTable(ReadableFontData data) {
      return readTable(data, 0, true);
    }

    @Override protected boolean subReadyToSerialize() {
      return true;
    }

    @Override public void subDataSet() {
      records = null;
    }

    // ////////////////////////////////////
    // implementations pushed to subclasses

    protected abstract T readTable(ReadableFontData data, int base, boolean dataIsCanonical);

    protected abstract RecordList<R> readRecordList(ReadableFontData data, int base);

    // ////////////////////////////////////
    // private methods

    private void prepareToEdit() {
      initFromData(internalReadData(), base + headerSize());
      setModelChanged();
    }

    private void initFromData(ReadableFontData data, int base) {
      if (records == null) {
        records = readRecordList(data, base);
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
      ReadableFontData data = internalReadData().slice(base + headerSize());
      data.copyTo(newData);
      return data.length();
    }
  }

}
