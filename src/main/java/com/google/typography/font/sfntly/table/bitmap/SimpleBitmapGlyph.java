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
public final class SimpleBitmapGlyph extends BitmapGlyph {

  protected SimpleBitmapGlyph(ReadableFontData data, int format) {
    super(data, format);
  }

  public static class Builder extends BitmapGlyph.Builder<BitmapGlyph> {

    protected Builder(WritableFontData data, int format) {
      super(data, format);
    }

    protected Builder(ReadableFontData data, int format) {
      super(data, format);
    }

    @Override
    protected SimpleBitmapGlyph subBuildTable(ReadableFontData data) {
      return new SimpleBitmapGlyph(data, this.format());
    }
  }
}
