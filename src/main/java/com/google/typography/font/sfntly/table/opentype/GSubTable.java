/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.table.opentype;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;
import com.google.typography.font.sfntly.table.Header;
import com.google.typography.font.sfntly.table.Table;

import java.util.concurrent.atomic.AtomicReference;

/**
 * A GSub table.
 */
public class GSubTable extends Table {

  private final GsubCommonTable gsub;
  private final AtomicReference<ScriptListTable>
      scriptListTable = new AtomicReference<ScriptListTable>();
  private final AtomicReference<FeatureListTable>
      featureListTable = new AtomicReference<FeatureListTable>();
  private final AtomicReference<LookupListTable>
      lookupListTable = new AtomicReference<LookupListTable>();

  /**
   * Constructor.
   *
   * @param header
   *     header for the table
   * @param data
   *     data for the table
   */
  private GSubTable(Header header, ReadableFontData data, boolean dataIsCanonical) {
    super(header, data);
    gsub = new GsubCommonTable(data, dataIsCanonical);
  }

  /**
   * Return information about the script tables in this GSUB table.
   *
   * @return the ScriptList
   */
  public ScriptListTable scriptList() {
    if (scriptListTable.get() == null) {
      scriptListTable.compareAndSet(null, gsub.createScriptList());
    }
    return scriptListTable.get();
  }

  /**
   * Return information about the feature tables in this GSUB table.
   *
   * @return the FeatureList
   */
  public FeatureListTable featureList() {
    if (featureListTable.get() == null) {
      featureListTable.compareAndSet(null, gsub.createFeatureList());
    }
    return featureListTable.get();
  }

  /**
   * Return information about the lookup tables in this GSUB table.
   *
   * @return the LookupList
   */
  public LookupListTable lookupList() {
    if (lookupListTable.get() == null) {
      lookupListTable.compareAndSet(null, gsub.createLookupList());
    }
    return lookupListTable.get();
  }

  /**
   * GSUB Table Builder.
   */
  public static class Builder extends Table.Builder<GSubTable> {

    private final GsubCommonTable.Builder gsub;

    /**
     * Creates a new builder using the header information and data provided.
     *
     * @param header
     *     the header information
     * @param data
     *     the data holding the table
     * @return a new builder
     */
    public static Builder createBuilder(Header header, WritableFontData data) {
      return new Builder(header, data);
    }

    /**
     * Constructor. This constructor will try to maintain the data as readable
     * but if editing operations are attempted then a writable copy will be made
     * the readable data will be discarded.
     *
     * @param header
     *     the table header
     * @param data
     *     the readable data for the table
     */
    private Builder(Header header, ReadableFontData data) {
      super(header, data);
      gsub = new GsubCommonTable.Builder(data, false);
    }

    @Override
    protected int subSerialize(WritableFontData newData) {
      return gsub.subSerialize(newData);
    }

    @Override
    protected boolean subReadyToSerialize() {
      return gsub.subReadyToSerialize();
    }

    @Override
    protected int subDataSizeToSerialize() {
      return 0; // TODO(cibu): need to implement using gsub
    }

    @Override
    protected void subDataSet() {
      // TODO(cibu): need to implement using gsub
    }

    @Override
    protected GSubTable subBuildTable(ReadableFontData data) {
      return new GSubTable(this.header(), data, false);
    }
  }
}
