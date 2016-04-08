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

/**
 * @author Stuart Gill
 */
public class SmallGlyphMetrics extends GlyphMetrics {

  enum Offset {
    metricsLength(5), height(0), width(1), BearingX(2), BearingY(3), Advance(4);

    final int offset;

    Offset(int offset) {
      this.offset = offset;
    }
  }

  private SmallGlyphMetrics(ReadableFontData data) {
    super(data);
  }

  public int height() {
    return this.data.readByte(Offset.height.offset);
  }

  public int width() {
    return this.data.readByte(Offset.width.offset);
  }

  public int bearingX() {
    return this.data.readChar(Offset.BearingX.offset);
  }

  public int bearingY() {
    return this.data.readChar(Offset.BearingY.offset);
  }

  public int advance() {
    return this.data.readByte(Offset.Advance.offset);
  }

  public static class Builder extends GlyphMetrics.Builder<SmallGlyphMetrics> {

    /**
     * Constructor.
     *
     * @param data
     *     the data for the builder
     */
    protected Builder(WritableFontData data) {
      super(data);
    }

    /**
     * Constructor.
     *
     * @param data
     *     the data for the builder
     */
    protected Builder(ReadableFontData data) {
      super(data);
    }

    public int height() {
      return this.internalReadData().readByte(Offset.height.offset);
    }

    public void setHeight(byte height) {
      this.internalWriteData().writeByte(Offset.height.offset, height);
    }

    public int width() {
      return this.internalReadData().readByte(Offset.width.offset);
    }

    public void setWidth(byte width) {
      this.internalWriteData().writeByte(Offset.width.offset, width);
    }

    public int bearingX() {
      return this.internalReadData().readChar(Offset.BearingX.offset);
    }

    public void setBearingX(byte bearing) {
      this.internalWriteData().writeChar(Offset.BearingX.offset, bearing);
    }

    public int bearingY() {
      return this.internalReadData().readChar(Offset.BearingY.offset);
    }

    public void setBearingY(byte bearing) {
      this.internalWriteData().writeChar(Offset.BearingY.offset, bearing);
    }

    public int advance() {
      return this.internalReadData().readByte(Offset.Advance.offset);
    }

    public void setAdvance(byte advance) {
      this.internalWriteData().writeByte(Offset.Advance.offset, advance);
    }

    @Override
    protected SmallGlyphMetrics subBuildTable(ReadableFontData data) {
      return new SmallGlyphMetrics(data);
    }

    @Override
    protected void subDataSet() {
      // NOP
    }

    @Override
    protected int subDataSizeToSerialize() {
      return 0;
    }

    @Override
    protected boolean subReadyToSerialize() {
      return false;
    }

    @Override
    protected int subSerialize(WritableFontData newData) {
      return this.data().copyTo(newData);
    }
  }
}
