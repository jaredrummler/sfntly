/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/5/16 8:36 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.bitmap;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.bitmap.EblcTable.Offset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Format 2 Index Subtable Entry.
 *
 * @author Stuart Gill
 */
public final class IndexSubTableFormat2 extends IndexSubTable {

  private final int imageSize;

  private IndexSubTableFormat2(ReadableFontData data, int first, int last) {
    super(data, first, last);
    this.imageSize = this.data.readULongAsInt(Offset.indexSubTable2_imageSize.offset);
  }

  public int imageSize() {
    return this.data.readULongAsInt(Offset.indexSubTable2_imageSize.offset);
  }

  public BigGlyphMetrics bigMetrics() {
    return new BigGlyphMetrics(this.data.slice(Offset.indexSubTable2_bigGlyphMetrics.offset,
        BigGlyphMetrics.Offset.metricsLength.offset));
  }

  @Override
  public int numGlyphs() {
    return this.lastGlyphIndex() - this.firstGlyphIndex() + 1;
  }

  @Override
  public int glyphStartOffset(int glyphId) {
    int loca = this.checkGlyphRange(glyphId);
    return loca * this.imageSize;
  }

  @Override
  public int glyphLength(int glyphId) {
    this.checkGlyphRange(glyphId);
    return this.imageSize;
  }

  public static final class Builder extends IndexSubTable.Builder<IndexSubTableFormat2> {

    private BigGlyphMetrics.Builder metrics;

    public static Builder createBuilder() {
      return new Builder();
    }

    static Builder createBuilder(
        ReadableFontData data, int indexSubTableOffset, int firstGlyphIndex, int lastGlyphIndex) {
      int length = Builder.dataLength(data, indexSubTableOffset, firstGlyphIndex, lastGlyphIndex);
      return new Builder(data.slice(indexSubTableOffset, length), firstGlyphIndex, lastGlyphIndex);
    }

    static Builder createBuilder(
        WritableFontData data, int indexSubTableOffset, int firstGlyphIndex, int lastGlyphIndex) {
      int length = Builder.dataLength(data, indexSubTableOffset, firstGlyphIndex, lastGlyphIndex);
      return new Builder(data.slice(indexSubTableOffset, length), firstGlyphIndex, lastGlyphIndex);
    }

    private static int dataLength(
        ReadableFontData data, int indexSubTableOffset, int firstGlyphIndex, int lastGlyphIndex) {
      return Offset.indexSubTable2Length.offset;
    }

    private Builder() {
      super(Offset.indexSubTable2_builderDataSize.offset, Format.FORMAT_2);
      this.metrics = BigGlyphMetrics.Builder.createBuilder();
    }

    private Builder(WritableFontData data, int firstGlyphIndex, int lastGlyphIndex) {
      super(data, firstGlyphIndex, lastGlyphIndex);
    }

    private Builder(ReadableFontData data, int firstGlyphIndex, int lastGlyphIndex) {
      super(data, firstGlyphIndex, lastGlyphIndex);
    }

    @Override
    public int numGlyphs() {
      return this.lastGlyphIndex() - this.firstGlyphIndex() + 1;
    }

    @Override
    public int glyphStartOffset(int glyphId) {
      int loca = super.checkGlyphRange(glyphId);
      return loca * this.imageSize();
    }

    @Override
    public int glyphLength(int glyphId) {
      super.checkGlyphRange(glyphId);
      return this.imageSize();
    }

    public int imageSize() {
      return this.internalReadData().readULongAsInt(Offset.indexSubTable2_imageSize.offset);
    }

    public void setImageSize(int imageSize) {
      this.internalWriteData().writeULong(Offset.indexSubTable2_imageSize.offset, imageSize);
    }

    public BigGlyphMetrics.Builder bigMetrics() {
      if (this.metrics == null) {
        WritableFontData data =
            this.internalWriteData().slice(Offset.indexSubTable2_bigGlyphMetrics.offset,
                BigGlyphMetrics.Offset.metricsLength.offset);
        this.metrics = new BigGlyphMetrics.Builder(data);
      }
      return this.metrics;
    }

    private class BitmapGlyphInfoIterator implements Iterator<BitmapGlyphInfo> {

      private int glyphId;

      public BitmapGlyphInfoIterator() {
        this.glyphId = Builder.this.firstGlyphIndex();
      }

      @Override
      public boolean hasNext() {
        if (this.glyphId <= Builder.this.lastGlyphIndex()) {
          return true;
        }
        return false;
      }

      @Override
      public BitmapGlyphInfo next() {
        if (!hasNext()) {
          throw new NoSuchElementException("No more characters to iterate.");
        }
        BitmapGlyphInfo info =
            new BitmapGlyphInfo(this.glyphId, Builder.this.imageDataOffset(),
                Builder.this.glyphStartOffset(this.glyphId),
                Builder.this.glyphLength(this.glyphId),
                Builder.this.imageFormat());
        this.glyphId++;
        return info;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("Unable to remove a glyph info.");
      }
    }

    @Override
    Iterator<BitmapGlyphInfo> iterator() {
      return new BitmapGlyphInfoIterator();
    }

    @Override
    protected IndexSubTableFormat2 subBuildTable(ReadableFontData data) {
      return new IndexSubTableFormat2(data, this.firstGlyphIndex(), this.lastGlyphIndex());
    }

    @Override
    protected void subDataSet() {
      this.revert();
    }

    @Override
    protected int subDataSizeToSerialize() {
      return Offset.indexSubTable2Length.offset;
    }

    @Override
    protected boolean subReadyToSerialize() {
      return true;
    }

    @Override
    protected int subSerialize(WritableFontData newData) {
      int size = super.serializeIndexSubHeader(newData);
      if (this.metrics == null) {
        size += this.internalReadData().slice(size).copyTo(newData.slice(size));
      } else {
        size += newData.writeLong(Offset.indexSubTable2_imageSize.offset, this.imageSize());
        size += this.metrics.subSerialize(newData.slice(size));
      }
      return size;
    }
  }
}
