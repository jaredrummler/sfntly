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

package com.google.typography.font.sfntly.table.opentype.component;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.data.WritableFontData;

final class SubstLookupRecord implements Record {

  static final int RECORD_SIZE = 4;
  private static final int SEQUENCE_INDEX_OFFSET = 0;
  private static final int LOOKUP_LIST_INDEX_OFFSET = 2;
  final int sequenceIndex;
  final int lookupListIndex;

  SubstLookupRecord(ReadableFontData data, int base) {
    this.sequenceIndex = data.readUShort(base + SEQUENCE_INDEX_OFFSET);
    this.lookupListIndex = data.readUShort(base + LOOKUP_LIST_INDEX_OFFSET);
  }

  @Override
  public int writeTo(WritableFontData newData, int base) {
    newData.writeUShort(base + SEQUENCE_INDEX_OFFSET, sequenceIndex);
    newData.writeUShort(base + LOOKUP_LIST_INDEX_OFFSET, lookupListIndex);
    return RECORD_SIZE;
  }
}
