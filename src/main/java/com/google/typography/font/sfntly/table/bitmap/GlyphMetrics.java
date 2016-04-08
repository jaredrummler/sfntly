/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.bitmap;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.SubTable;

/**
 * @author Stuart Gill
 */
abstract class GlyphMetrics extends SubTable {

  /**
   * Constructor.
   *
   * @param data
   */
  protected GlyphMetrics(ReadableFontData data) {
    super(data);
  }

  static abstract class Builder<T extends GlyphMetrics> extends SubTable.Builder<T> {

    /**
     * Constructor.
     *
     * @param data
     *     the data for the subtable being built
     */
    protected Builder(WritableFontData data) {
      super(data);
    }

    /**
     * Constructor.
     *
     * @param data
     *     the data for the subtable being built
     */
    protected Builder(ReadableFontData data) {
      super(data);
    }
  }
}
