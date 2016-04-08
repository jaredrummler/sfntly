/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.opentype;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.component.HeaderTable;

public abstract class SubstSubtable extends HeaderTable {

  private static final int FIELD_COUNT = 1;
  private static final int FORMAT_INDEX = 0;
  private static final int FORMAT_DEFAULT = 0;
  public final int format;

  protected SubstSubtable(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
    format = getField(FORMAT_INDEX);
  }

  @Override
  public int fieldCount() {
    return FIELD_COUNT;
  }

  public abstract static class Builder<T extends SubstSubtable> extends HeaderTable.Builder<T> {

    protected int format;

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data);
      format = getField(FORMAT_INDEX);
    }

    protected Builder() {
      super();
    }

    @Override
    protected void initFields() {
      setField(FORMAT_INDEX, FORMAT_DEFAULT);
    }

    @Override
    public int fieldCount() {
      return FIELD_COUNT;
    }
  }
}
