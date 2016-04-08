/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/5/16 8:36 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.bitmap;

import com.google.typography.font.sfntly.data.FontData;
import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.SubTable;

/**
 * @author Stuart Gill
 */
public abstract class BitmapGlyph extends SubTable {

  protected enum Offset {
    // header
    version(0),

    smallGlyphMetricsLength(5),
    bigGlyphMetricsLength(8),
    // format 1
    glyphFormat1_imageData(smallGlyphMetricsLength.offset),

    // format 2
    glyphFormat2_imageData(smallGlyphMetricsLength.offset),

    // format 3

    // format 4

    // format 5
    glyphFormat5_imageData(0),

    // format 6
    glyphFormat6_imageData(bigGlyphMetricsLength.offset),

    // format 7
    glyphFormat7_imageData(bigGlyphMetricsLength.offset),

    // format 8
    glyphFormat8_numComponents(Offset.smallGlyphMetricsLength.offset + 1),
    glyphFormat8_componentArray(glyphFormat8_numComponents.offset
        + FontData.DataSize.USHORT.size()),

    // format 9
    glyphFormat9_numComponents(Offset.bigGlyphMetricsLength.offset),
    glyphFormat9_componentArray(glyphFormat9_numComponents.offset
        + FontData.DataSize.USHORT.size()),

    // ebdtComponent
    ebdtComponentLength(FontData.DataSize.USHORT.size() + 2 * FontData.DataSize.CHAR.size()),
    ebdtComponent_glyphCode(0),
    ebdtComponent_xOffset(2),
    ebdtComponent_yOffset(3);

    protected final int offset;

    private Offset(int offset) {
      this.offset = offset;
    }
  }

  private int format;

  public static BitmapGlyph createGlyph(ReadableFontData data, int format) {
    BitmapGlyph glyph = null;
    Builder<? extends BitmapGlyph> builder = Builder.createGlyphBuilder(data, format);
    if (builder != null) {
      glyph = builder.build();
    }
    return glyph;
  }

  protected BitmapGlyph(ReadableFontData data, int format) {
    super(data);
    this.format = format;
  }

  protected BitmapGlyph(ReadableFontData data, int offset, int length, int format) {
    super(data, offset, length);
    this.format = format;
  }

  public int format() {
    return this.format;
  }

  public static abstract class Builder<T extends BitmapGlyph> extends SubTable.Builder<T> {

    private final int format;

    public static Builder<? extends BitmapGlyph> createGlyphBuilder(
        ReadableFontData data, int format) {
      switch (format) {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
          return new SimpleBitmapGlyph.Builder(data, format);
        case 8:
        case 9:
          return new CompositeBitmapGlyph.Builder(data, format);
      }
      return null;
    }

    protected Builder(WritableFontData data, int format) {
      super(data);
      this.format = format;
    }

    protected Builder(ReadableFontData data, int format) {
      super(data);
      this.format = format;
    }

    public int format() {
      return this.format;
    }

    @Override
    protected void subDataSet() {
      // NOP
    }

    @Override
    protected int subDataSizeToSerialize() {
      return this.internalReadData().length();
    }

    @Override
    protected boolean subReadyToSerialize() {
      return true;
    }

    @Override
    protected int subSerialize(WritableFontData newData) {
      return this.internalReadData().copyTo(newData);
    }
  }

  @Override
  public String toString() {
    return "BitmapGlyph [format=" + format + ", data = " + super.toString() + "]";
  }
}
