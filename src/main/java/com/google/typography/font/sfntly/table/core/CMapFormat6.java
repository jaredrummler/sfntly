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

import com.google.typography.font.sfntly.data.FontData;
import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.core.CMapTable.CMapId;
import com.google.typography.font.sfntly.table.core.CMapTable.Offset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A cmap format 6 sub table.
 */
public final class CMapFormat6 extends CMap {

  private final int firstCode;
  private final int entryCount;

  protected CMapFormat6(ReadableFontData data, CMapId cmapId) {
    super(data, CMapFormat.Format6.value, cmapId);
    this.firstCode = this.data.readUShort(Offset.format6FirstCode.offset);
    this.entryCount = this.data.readUShort(Offset.format6EntryCount.offset);
  }

  @Override public int glyphId(int character) {
    if (character < this.firstCode || character >= this.firstCode + this.entryCount) {
      return CMapTable.NOTDEF;
    }
    return this.data.readUShort(Offset.format6GlyphIdArray.offset + (character - this.firstCode)
        * FontData.DataSize.USHORT.size());
  }

  @Override public int language() {
    return this.data.readUShort(Offset.format6Language.offset);
  }

  @Override public Iterator<Integer> iterator() {
    return new CharacterIterator();
  }

  private class CharacterIterator implements Iterator<Integer> {

    private int character = firstCode;

    private CharacterIterator() {
      // Prevent construction.
    }

    @Override public boolean hasNext() {
      if (character < (firstCode + entryCount)) {
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

  public static class Builder extends CMap.Builder<CMapFormat6> {

    protected Builder(WritableFontData data, int offset, CMapId cmapId) {
      super(data == null ? null : data.slice(
          offset, data.readUShort(offset + Offset.format6Length.offset)), CMapFormat.Format6,
          cmapId);
    }

    protected Builder(ReadableFontData data, int offset, CMapId cmapId) {
      super(data == null ? null : data.slice(
          offset, data.readUShort(offset + Offset.format6Length.offset)), CMapFormat.Format6,
          cmapId);
    }

    @Override protected CMapFormat6 subBuildTable(ReadableFontData data) {
      return new CMapFormat6(data, this.cmapId());
    }
  }

}