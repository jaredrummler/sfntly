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
 * A fixed size memory implementation of the ByteArray interface.
 *
 * @author Stuart Gill
 */
final class MemoryByteArray extends ByteArray<MemoryByteArray> {

  private byte[] b;

  /**
   * Construct a new MemoryByteArray with a new array of the size given. It is assumed
   * that none of the array is filled and readable.
   *
   * @param length
   *     the length to make the storage array
   */
  public MemoryByteArray(int length) {
    this(new byte[length], 0);
  }

  /**
   * Construct a new MemoryByteArray to wrap the actual underlying byte array.
   * This MemoryByteArray takes ownership of the array after construction and it
   * should not be used outside of this object. It is assumed that the entire
   * array is filled and readable.
   *
   * @param b
   *     the byte array that provides the actual storage
   */
  public MemoryByteArray(byte[] b) {
    this(b, b.length);
  }

  /**
   * Construct a new MemoryByteArray to wrap the actual underlying byte array.
   * This MemoryByteArray takes ownership of the array after construction and it
   * should not be used outside of this object.
   *
   * @param b
   *     the byte array that provides the actual storage
   * @param filledLength
   *     the index of the last byte in the array has data
   * @throws IndexOutOfBoundsException
   *     if the given bounds don't fit within the
   *     byte array given
   */
  public MemoryByteArray(byte[] b, int filledLength) {
    super(filledLength, b.length);
    this.b = b;
  }

  @Override
  protected void internalPut(int index, byte b) {
    this.b[index] = b;
  }

  @Override
  protected int internalPut(int index, byte[] b, int offset, int length) {
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
}
