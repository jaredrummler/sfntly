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
 * An abstract base to be used building tables in which the builder can use the
 * table itself to build from.
 *
 * @param <T>
 *     the type of table to be built
 * @author Stuart Gill
 */
public abstract class TableBasedTableBuilder<T extends Table> extends Table.Builder<T> {

  private T table;

  /**
   * Constructor.
   *
   * @param header
   * @param data
   */
  protected TableBasedTableBuilder(Header header, WritableFontData data) {
    super(header, data);
  }

  /**
   * Constructor.
   *
   * @param header
   * @param data
   */
  protected TableBasedTableBuilder(Header header, ReadableFontData data) {
    super(header, data);
  }

  protected TableBasedTableBuilder(Header header) {
    super(header);
  }

  protected T table() {
    if (this.table == null) {
      this.table = this.subBuildTable(this.internalReadData());
    }
    return this.table;
  }

  @Override
  protected void subDataSet() {
    this.table = null;
  }

  @Override
  protected int subDataSizeToSerialize() {
    return 0;
  }

  @Override
  protected boolean subReadyToSerialize() {
    return true;
  }

  @Override
  protected int subSerialize(WritableFontData newData) {
    return 0;
  }

  @Override
  public T build() {
    if (!this.subReadyToSerialize()) {
      return null;
    }
    T table = this.table();
    this.notifyPostTableBuild(table);
    return table;
  }
}