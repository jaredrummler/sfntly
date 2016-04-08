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

package com.google.typography.font.sfntly.table.opentype.chaincontextsubst;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class ChainSubClassSet extends ChainSubGenericRuleSet<ChainSubClassRule> {

  ChainSubClassSet(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  @Override protected ChainSubClassRule readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new ChainSubClassRule(data, base, dataIsCanonical);
  }

  static class Builder
      extends ChainSubGenericRuleSet.Builder<ChainSubClassSet, ChainSubClassRule> {

    Builder() {
      super();
    }

    Builder(ChainSubClassSet table) {
      super(table);
    }

    Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    @Override protected ChainSubClassSet readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      return new ChainSubClassSet(data, base, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<ChainSubClassRule> createSubTableBuilder() {
      return new ChainSubClassRule.Builder();
    }

    @Override protected VisibleSubTable.Builder<ChainSubClassRule> createSubTableBuilder(
        ReadableFontData data, boolean dataIsCanonical) {
      return new ChainSubClassRule.Builder(data, 0, dataIsCanonical);
    }

    @Override protected VisibleSubTable.Builder<ChainSubClassRule> createSubTableBuilder(
        ChainSubClassRule subTable) {
      return new ChainSubClassRule.Builder(subTable);
    }

  }

}
