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
public class CompositeBitmapGlyph extends BitmapGlyph {

  public static final class Component {

    private final int glyphCode;
    private int xOffset;
    private int yOffset;

    protected Component(int glyphCode, int xOffset, int yOffset) {
      this.glyphCode = glyphCode;
      this.xOffset = xOffset;
      this.yOffset = yOffset;
    }

    public int glyphCode() {
      return this.glyphCode;
    }

    public int xOffset() {
      return this.xOffset;
    }

    public int yOffset() {
      return this.yOffset;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + glyphCode;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Component)) {
        return false;
      }
      Component other = (Component) obj;
      if (glyphCode != other.glyphCode) {
        return false;
      }
      return true;
    }
  }

  private int numComponentsOffset;
  private int componentArrayOffset;

  protected CompositeBitmapGlyph(ReadableFontData data, int format) {
    super(data, format);
    initialize(format);
  }

  /**
   * Initializes the internal state from the data.
   *
   * @param format
   *     the glyph format
   */
  private void initialize(int format) {
    if (format == 8) {
      this.numComponentsOffset = Offset.glyphFormat8_numComponents.offset;
      this.componentArrayOffset = Offset.glyphFormat8_componentArray.offset;
    } else if (format == 9) {
      this.numComponentsOffset = Offset.glyphFormat9_numComponents.offset;
      this.componentArrayOffset = Offset.glyphFormat9_componentArray.offset;
    } else {
      throw new IllegalStateException(
          "Attempt to create a Composite Bitmap Glyph with a non-composite format.");
    }
  }

  public int numComponents() {
    return this.data.readUShort(this.numComponentsOffset);
  }

  public Component component(int componentNum) {
    int componentOffset =
        this.componentArrayOffset + componentNum * Offset.ebdtComponentLength.offset;
    return new Component(
        this.data.readUShort(componentOffset + Offset.ebdtComponent_glyphCode.offset),
        this.data.readChar(componentOffset + Offset.ebdtComponent_xOffset.offset),
        this.data.readChar(componentOffset + Offset.ebdtComponent_yOffset.offset));
  }

  public static class Builder extends BitmapGlyph.Builder<CompositeBitmapGlyph> {

    protected Builder(WritableFontData data, int format) {
      super(data, format);
    }

    protected Builder(ReadableFontData data, int format) {
      super(data, format);
    }

    @Override
    protected CompositeBitmapGlyph subBuildTable(ReadableFontData data) {
      return new CompositeBitmapGlyph(data, this.format());
    }
  }
}
