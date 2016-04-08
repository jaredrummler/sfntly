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
 * A table builder to do the minimal table building for an unknown table type.
 *
 * @author Stuart Gill
 */
final class GenericTableBuilder extends TableBasedTableBuilder<Table> {

  /**
   * Create a new builder using the header information and data provided.
   *
   * @param header
   *     the header information
   * @param data
   *     the data holding the table
   * @return a new builder
   */
  static GenericTableBuilder createBuilder(Header header, WritableFontData data) {
    return new GenericTableBuilder(header, data);
  }

  /**
   * @param header
   * @param data
   */
  private GenericTableBuilder(Header header, WritableFontData data) {
    super(header, data);
  }

  /**
   * @param header
   * @param data
   */
  private GenericTableBuilder(Header header, ReadableFontData data) {
    super(header, data);
  }

  @Override
  protected Table subBuildTable(ReadableFontData data) {
    return new Table(this.header(), this.internalReadData());
  }
}
