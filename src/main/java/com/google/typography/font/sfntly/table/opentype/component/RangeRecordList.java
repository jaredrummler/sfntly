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
import com.google.typography.font.sfntly.data.WritableFontData;

public final class RangeRecordList extends RecordList<RangeRecord> {

  public RangeRecordList(WritableFontData data) {
    super(data);
  }

  public RangeRecordList(ReadableFontData data) {
    super(data);
  }

  public static int sizeOfListOfCount(int count) {
    return RecordList.DATA_OFFSET + count * RangeRecord.RECORD_SIZE;
  }

  @Override
  protected RangeRecord getRecordAt(ReadableFontData data, int offset) {
    return new RangeRecord(data, offset);
  }

  @Override
  protected int recordSize() {
    return RangeRecord.RECORD_SIZE;
  }
}
