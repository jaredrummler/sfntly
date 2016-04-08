/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.truetype;

import com.google.typography.font.sfntly.data.FontData;
import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.ByteArrayTableBuilder;
import com.google.typography.font.sfntly.table.Header;
import com.google.typography.font.sfntly.table.Table;

/**
 * A Control Program table.
 *
 * @author Stuart Gill
 */
public final class ControlProgramTable extends Table {

  /**
   * @param header
   *     header data
   * @param data
   *     the font data block for this table
   */
  protected ControlProgramTable(Header header, ReadableFontData data) {
    super(header, data);
  }

  /**
   * Get the data value at the specified index.
   *
   * @param index
   *     the location to get the data from
   * @return the data at the index
   */
  public int data(int index) {
    return this.data.readByte(index);
  }

  /**
   * Get the number of bytes that may be in the table.
   *
   * @return the byte count
   */
  public int byteCount() {
    return this.dataLength() / FontData.DataSize.BYTE.size();
  }

  /**
   * Builder for Control Program Table.
   */
  public static class Builder extends ByteArrayTableBuilder<ControlProgramTable> {

    /**
     * Create a new builder using the header information and data provided.
     *
     * @param header
     *     the header information
     * @param data
     *     the data holding the table
     * @return a new builder
     */
    public static Builder createBuilder(Header header, WritableFontData data) {
      return new Builder(header, data);
    }

    /**
     * Constructor.
     *
     * @param header
     *     the table header
     * @param data
     *     the writable data for the table
     */
    protected Builder(Header header, WritableFontData data) {
      super(header, data);
    }

    /**
     * Constructor.
     *
     * @param header
     *     the table header
     * @param data
     *     the readable data for the table
     */
    protected Builder(Header header, ReadableFontData data) {
      super(header, data);
    }

    @Override
    protected ControlProgramTable subBuildTable(ReadableFontData data) {
      return new ControlProgramTable(this.header(), data);
    }
  }
}
