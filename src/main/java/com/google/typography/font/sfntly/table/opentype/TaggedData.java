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

/**
 * @author dougfelt@google.com (Doug Felt)
 */
public interface TaggedData {

  /**
   * @param string
   *     label
   * @param start
   *     start of range to tag
   * @param length
   *     length of range to tag
   * @param depth
   *     nesting depth of range
   */
  void tagRange(String string, int start, int length, int depth);

  /**
   * @param position
   *     the position of the field
   * @param width
   *     number of bytes for the field at position
   * @param value
   *     the value in those bytes
   * @param alt
   *     an alternate presentation of the value (in decimal, a tag)
   * @param label
   *     the label of this field
   */
  void tagField(int position, int width, int value, String alt, String label);

  /**
   * @param position
   *     the position of the reference to target
   * @param value
   *     the raw value of the field
   * @param targetPosition
   *     the target position;
   * @param label
   *     name for this reference, or null
   */
  void tagTarget(int position, int value, int targetPosition, String label);

  void pushRange(String string, ReadableFontData data);

  void pushRangeAtOffset(String label, int base);

  int tagRangeField(FieldType ft, String label);

  void setRangePosition(int rangePosition);

  void popRange();

  enum FieldType {
    TAG, SHORT, SHORT_IGNORED, SHORT_IGNORED_FFFF, OFFSET, OFFSET_NONZERO, OFFSET32, GLYPH;
  }

}
