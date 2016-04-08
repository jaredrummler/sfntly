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

/**
 * A cmap format 14 sub table.
 */
// TODO(stuartg): completely unsupported yet
public final class CMapFormat14 extends CMap {

  protected CMapFormat14(ReadableFontData data, CMapId cmapId) {
    super(data, CMapFormat.Format14.value, cmapId);
  }

  @Override public int glyphId(int character) {
    return CMapTable.NOTDEF;
  }

  @Override public int language() {
    return 0;
  }

  @Override public Iterator<Integer> iterator() {
    return null;
  }

  public static class Builder extends CMap.Builder<CMapFormat14> {

    protected Builder(WritableFontData data, int offset, CMapId cmapId) {
      super(data == null ? null : data.slice(
          offset, data.readULongAsInt(offset + Offset.format14Length.offset)),
          CMapFormat.Format14, cmapId);
    }

    protected Builder(ReadableFontData data, int offset, CMapId cmapId) {
      super(data == null ? null : data.slice(
          offset, data.readULongAsInt(offset + Offset.format14Length.offset)),
          CMapFormat.Format14, cmapId);
    }

    @Override protected CMapFormat14 subBuildTable(ReadableFontData data) {
      return new CMapFormat14(data, this.cmapId());
    }
  }

}