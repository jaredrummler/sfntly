/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.opentype.component;

import com.google.typography.font.sfntly.data.ReadableFontData;

public class RangeRecordTable extends RecordsTable<RangeRecord> {

  public RangeRecordTable(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  @Override
  protected RecordList<RangeRecord> createRecordList(ReadableFontData data) {
    return new RangeRecordList(data);
  }

  @Override
  public int fieldCount() {
    return 0;
  }

  public static class Builder extends RecordsTable.Builder<RangeRecordTable, RangeRecord> {

    public Builder(ReadableFontData data, int base, boolean dataIsCanonical) {
      super(data, base, dataIsCanonical);
    }

    @Override
    protected RangeRecordTable readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      if (base != 0) {
        throw new UnsupportedOperationException();
      }
      return new RangeRecordTable(data, base, dataIsCanonical);
    }

    @Override
    protected RecordList<RangeRecord> readRecordList(ReadableFontData data, int base) {
      if (base != 0) {
        throw new UnsupportedOperationException();
      }
      return new RangeRecordList(data);
    }

    @Override
    protected int fieldCount() {
      return 0;
    }

    @Override
    protected void initFields() {
    }
  }
}
