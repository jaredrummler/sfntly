/*
 * Copyright 2010 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.typography.font.sfntly.table.core;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.core.CMapTable.CMapId;
import com.google.typography.font.sfntly.table.core.CMapTable.Offset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A cmap format 10 sub table.
 */
public final class CMapFormat10 extends CMap {

  private final int startCharCode;
  private final int numChars;

  protected CMapFormat10(ReadableFontData data, CMapId cmapId) {
    super(data, CMapFormat.Format10.value, cmapId);
    this.startCharCode = this.data.readULongAsInt(Offset.format10StartCharCode.offset);
    this.numChars = this.data.readUShort(Offset.format10NumChars.offset);
  }

  @Override public int glyphId(int character) {
    if (character < startCharCode || character >= (startCharCode + numChars)) {
      return CMapTable.NOTDEF;
    }
    return this.readFontData().readUShort(character - startCharCode);
  }

  @Override public int language() {
    return this.data.readULongAsInt(Offset.format10Language.offset);
  }

  @Override public Iterator<Integer> iterator() {
    return new CharacterIterator();
  }

  private class CharacterIterator implements Iterator<Integer> {

    private int character = startCharCode;

    private CharacterIterator() {
      // Prevent construction.
    }

    @Override public boolean hasNext() {
      if (character < startCharCode + numChars) {
        return true;
      }
      return false;
    }

    @Override public Integer next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more characters to iterate.");
      }
      return this.character++;
    }

    @Override public void remove() {
      throw new UnsupportedOperationException("Unable to remove a character from cmap.");
    }
  }

  public static class Builder extends CMap.Builder<CMapFormat10> {

    protected Builder(WritableFontData data, int offset, CMapId cmapId) {
      super(data == null ? null : data.slice(
          offset, data.readULongAsInt(offset + Offset.format10Length.offset)),
          CMapFormat.Format10, cmapId);
    }

    protected Builder(ReadableFontData data, int offset, CMapId cmapId) {
      super(data == null ? null : data.slice(
          offset, data.readULongAsInt(offset + Offset.format10Length.offset)),
          CMapFormat.Format10, cmapId);
    }

    @Override protected CMapFormat10 subBuildTable(ReadableFontData data) {
      return new CMapFormat10(data, this.cmapId());
    }
  }

}