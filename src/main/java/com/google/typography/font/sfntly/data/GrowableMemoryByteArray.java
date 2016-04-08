/*
 * Copyright (C) 2016. JRummy Apps, Inc. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * File created on 4/7/16 5:17 PM by Jared Rummler.
 */

package com.google.typography.font.sfntly.data;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A growable memory implementation of the ByteArray interface.
 *
 * @author Stuart Gill
 */
final class GrowableMemoryByteArray extends ByteArray<GrowableMemoryByteArray> {

  private static final int INITIAL_LENGTH = 256;
  private byte[] b;

  public GrowableMemoryByteArray() {
    super(0, Integer.MAX_VALUE, true /*growable*/);
    b = new byte[INITIAL_LENGTH];
  }

  @Override
  protected void internalPut(int index, byte b) {
    growTo(index + 1);
    this.b[index] = b;
  }

  @Override
  protected int internalPut(int index, byte[] b, int offset, int length) {
    growTo(index + length);
    System.arraycopy(b, offset, this.b, index, length);
    return length;
  }

  @Override
  protected int internalGet(int index) {
    return this.b[index];
  }

  @Override
  protected int internalGet(int index, byte[] b, int offset, int length) {
    System.arraycopy(this.b, index, b, offset, length);
    return length;
  }

  @Override
  public void close() {

    this.b = null;
  }

  @Override
  public int copyTo(OutputStream os, int offset, int length) throws IOException {
    os.write(b, offset, length);
    return length;
  }

  private void growTo(int newSize) {
    if (newSize <= b.length) {
      return;
    }
    newSize = Math.max(newSize, b.length * 2);
    byte[] newArray = new byte[newSize];
    System.arraycopy(b, 0, newArray, 0, b.length);
    b = newArray;
  }
}
