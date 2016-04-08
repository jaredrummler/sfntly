/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.opentype.chaincontextsubst;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.component.VisibleSubTable;

public class ChainSubClassSet extends ChainSubGenericRuleSet<ChainSubClassRule> {

  ChainSubClassSet(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  @Override
  protected ChainSubClassRule readSubTable(ReadableFontData data, boolean dataIsCanonical) {
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

    @Override
    protected ChainSubClassSet readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      return new ChainSubClassSet(data, base, dataIsCanonical);
    }

    @Override
    protected VisibleSubTable.Builder<ChainSubClassRule> createSubTableBuilder() {
      return new ChainSubClassRule.Builder();
    }

    @Override
    protected VisibleSubTable.Builder<ChainSubClassRule> createSubTableBuilder(
        ReadableFontData data, boolean dataIsCanonical) {
      return new ChainSubClassRule.Builder(data, 0, dataIsCanonical);
    }

    @Override
    protected VisibleSubTable.Builder<ChainSubClassRule> createSubTableBuilder(
        ChainSubClassRule subTable) {
      return new ChainSubClassRule.Builder(subTable);
    }

  }
}
