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

package com.google.typography.font.sfntly.table.opentype;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.component.TagOffsetsTable;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

import java.util.HashMap;
import java.util.Map;

public class ScriptListTable extends TagOffsetsTable<ScriptTable> {

  ScriptListTable(ReadableFontData data, boolean dataIsCanonical) {
    super(data, dataIsCanonical);
  }

  @Override
  protected ScriptTable readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new ScriptTable(data, 0, dataIsCanonical);
  }

  public ScriptTag scriptAt(int index) {
    return ScriptTag.fromTag(this.tagAt(index));
  }

  public Map<ScriptTag, ScriptTable> map() {
    Map<ScriptTag, ScriptTable> map = new HashMap<ScriptTag, ScriptTable>();
    for (int i = 0; i < count(); i++) {
      ScriptTag script;
      try {
        script = scriptAt(i);
      } catch (IllegalArgumentException e) {
        System.err.println("Invalid Script tag found: " + e.getMessage());
        continue;
      }
      map.put(script, subTableAt(i));
    }
    return map;
  }

  static class Builder extends TagOffsetsTable.Builder<ScriptListTable, ScriptTable> {

    @Override
    protected VisibleSubTable.Builder<ScriptTable> createSubTableBuilder(
        ReadableFontData data, int tag, boolean dataIsCanonical) {
      return new ScriptTable.Builder(data, 0, dataIsCanonical);
    }

    @Override
    protected VisibleSubTable.Builder<ScriptTable> createSubTableBuilder() {
      return new ScriptTable.Builder();
    }

    @Override
    protected ScriptListTable readTable(
        ReadableFontData data, int baseUnused, boolean dataIsCanonical) {
      return new ScriptListTable(data, dataIsCanonical);
    }

    @Override
    protected void initFields() {
    }

    @Override
    public int fieldCount() {
      return 0;
    }
  }

  @Override
  public int fieldCount() {
    return 0;
  }
}
