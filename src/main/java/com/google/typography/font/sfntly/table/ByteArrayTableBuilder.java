/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;

import java.io.IOException;

/**
 * An abstract builder base for byte array based tables.
 *
 * @author Stuart Gill
 */
public abstract class ByteArrayTableBuilder<T extends Table> extends TableBasedTableBuilder<
    T> {

  /**
   * Constructor.
   *
   * @param header
   * @param data
   */
  protected ByteArrayTableBuilder(Header header, WritableFontData data) {
    super(header, data);
  }

  /**
   * Constructor.
   *
   * @param header
   * @param data
   */
  protected ByteArrayTableBuilder(Header header, ReadableFontData data) {
    super(header, data);
  }

  /**
   * Get the byte value at the specified index. The index is relative to the
   * start of the table.
   *
   * @param index
   *     index relative to the start of the table
   * @return byte value at the given index
   * @throws IOException
   */
  public int byteValue(int index) throws IOException {
    ReadableFontData data = this.internalReadData();
    if (data == null) {
      throw new IOException("No font data for the table.");
    }
    return data.readByte(index);
  }

  /**
   * Get the byte value at the specified index. The index is relative to the
   * start of the table.
   *
   * @param index
   *     index relative to the start of the table
   * @param b
   *     byte value to tset
   * @throws IOException
   */
  public void setByteValue(int index, byte b) throws IOException {
    WritableFontData data = this.internalWriteData();
    if (data == null) {
      throw new IOException("No font data for the table.");
    }
    data.writeByte(index, b);
  }

  /**
   * Get the number of bytes set for this table. It may include padding bytes at
   * the end.
   *
   * @return number of bytes for the table
   * @throws IOException
   */
  public int byteCount() throws IOException {
    ReadableFontData data = this.internalReadData();
    if (data == null) {
      throw new IOException("No font data for the table.");
    }
    return data.length();
  }
}
