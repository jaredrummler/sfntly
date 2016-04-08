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

package com.google.typography.font.sfntly.table.opentype.contextsubst;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class SubClassSet extends SubGenericRuleSet<SubClassRule> {

  SubClassSet(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  @Override
  protected SubClassRule readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new SubClassRule(data, base, dataIsCanonical);
  }

  static class Builder extends SubGenericRuleSet.Builder<SubClassSet, SubClassRule> {

    Builder() {
      super();
    }

    Builder(SubClassSet table) {
      super(table);
    }

    Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    @Override
    protected SubClassSet readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      return new SubClassSet(data, base, dataIsCanonical);
    }

    @Override
    protected VisibleSubTable.Builder<SubClassRule> createSubTableBuilder() {
      return new SubClassRule.Builder();
    }

    @Override
    protected VisibleSubTable.Builder<SubClassRule> createSubTableBuilder(
        ReadableFontData data, boolean dataIsCanonical) {
      return new SubClassRule.Builder(data, 0, dataIsCanonical);
    }

    @Override
    protected VisibleSubTable.Builder<SubClassRule> createSubTableBuilder(SubClassRule subTable) {
      return new SubClassRule.Builder(subTable);
    }
  }
}
