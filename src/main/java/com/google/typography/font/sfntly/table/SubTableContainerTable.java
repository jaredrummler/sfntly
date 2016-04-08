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

/**
 * Abstract base class for tables that have contained subtables.
 *
 * @author Stuart Gill
 */
public abstract class SubTableContainerTable extends Table {

  /**
   * Constructor.
   *
   * @param header
   *     the header for the table
   * @param data
   *     the data that contains the table
   */
  protected SubTableContainerTable(Header header, ReadableFontData data) {
    super(header, data);
  }

  public abstract static class Builder<T extends SubTableContainerTable>
      extends Table.Builder<T> {

    protected Builder(Header header, WritableFontData data) {
      super(header, data);
    }

    protected Builder(Header header, ReadableFontData data) {
      super(header, data);
    }
  }
}