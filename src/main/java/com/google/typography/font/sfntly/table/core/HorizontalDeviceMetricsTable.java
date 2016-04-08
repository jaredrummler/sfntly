/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.core;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.Header;
import com.google.typography.font.sfntly.table.Table;
import com.google.typography.font.sfntly.table.TableBasedTableBuilder;

/**
 * A Horizontal Device Metrics table - 'hdmx'.
 *
 * @author raph@google.com (Raph Levien)
 */
public class HorizontalDeviceMetricsTable extends Table {

  private int numGlyphs;

  private enum Offset {
    version(0),
    numRecords(2),
    sizeDeviceRecord(4),
    records(8),

    // Offsets within a device record
    deviceRecordPixelSize(0),
    deviceRecordMaxWidth(1),
    deviceRecordWidths(2);

    private final int offset;

    private Offset(int offset) {
      this.offset = offset;
    }
  }

  private HorizontalDeviceMetricsTable(Header header, ReadableFontData data, int numGlyphs) {
    super(header, data);
    this.numGlyphs = numGlyphs;
  }

  public int version() {
    return data.readUShort(Offset.version.offset);
  }

  public int numRecords() {
    return data.readShort(Offset.numRecords.offset);
  }

  public int recordSize() {
    return data.readLong(Offset.sizeDeviceRecord.offset);
  }

  public int pixelSize(int recordIx) {
    if (recordIx < 0 || recordIx >= numRecords()) {
      throw new IndexOutOfBoundsException();
    }
    return data.readUByte(Offset.records.offset + recordIx * recordSize() +
        Offset.deviceRecordPixelSize.offset);
  }

  public int maxWidth(int recordIx) {
    if (recordIx < 0 || recordIx >= numRecords()) {
      throw new IndexOutOfBoundsException();
    }
    return data.readUByte(Offset.records.offset + recordIx * recordSize() +
        Offset.deviceRecordMaxWidth.offset);
  }

  public int width(int recordIx, int glyphNum) {
    if (recordIx < 0 || recordIx >= numRecords() || glyphNum < 0 || glyphNum >= numGlyphs) {
      throw new IndexOutOfBoundsException();
    }
    return data.readUByte(Offset.records.offset + recordIx * recordSize() +
        Offset.deviceRecordWidths.offset + glyphNum);
  }

  /**
   * Builder for a Horizontal Device Metrics Table - 'hdmx'.
   */
  public static class
  Builder extends TableBasedTableBuilder<HorizontalDeviceMetricsTable> {

    private int numGlyphs = -1;

    public static Builder createBuilder(Header header, WritableFontData data) {
      return new Builder(header, data);
    }

    protected Builder(Header header, WritableFontData data) {
      super(header, data);
    }

    protected Builder(Header header, ReadableFontData data) {
      super(header, data);
    }

    @Override
    protected HorizontalDeviceMetricsTable subBuildTable(ReadableFontData data) {
      return new HorizontalDeviceMetricsTable(this.header(), data, this.numGlyphs);
    }

    public void setNumGlyphs(int numGlyphs) {
      if (numGlyphs < 0) {
        throw new IllegalArgumentException("Number of glyphs can't be negative.");
      }
      this.numGlyphs = numGlyphs;
      this.table().numGlyphs = numGlyphs;
    }
  }
}
