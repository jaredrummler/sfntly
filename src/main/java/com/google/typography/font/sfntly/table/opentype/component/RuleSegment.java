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

import com.google.typography.font.sfntly.table.core.PostScriptTable;

import java.util.Collection;
import java.util.LinkedList;

class RuleSegment extends LinkedList<GlyphGroup> {

  private static final long serialVersionUID = 4563803321401665616L;

  RuleSegment() {
    super();
  }

  RuleSegment(GlyphGroup glyphGroup) {
    addInternal(glyphGroup);
  }

  RuleSegment(int glyph) {
    GlyphGroup glyphGroup = new GlyphGroup(glyph);
    addInternal(glyphGroup);
  }

  RuleSegment(GlyphList glyphs) {
    for (int glyph : glyphs) {
      GlyphGroup glyphGroup = new GlyphGroup(glyph);
      addInternal(glyphGroup);
    }
  }

  boolean add(int glyph) {
    GlyphGroup glyphGroup = new GlyphGroup(glyph);
    return addInternal(glyphGroup);
  }

  @Override
  public boolean addAll(Collection<? extends GlyphGroup> glyphGroups) {
    for (GlyphGroup glyphGroup : glyphGroups) {
      if (glyphGroup == null) {
        throw new IllegalArgumentException("Null GlyphGroup not allowed");
      }
    }
    return super.addAll(glyphGroups);
  }

  private boolean addInternal(GlyphGroup glyphGroup) {
    if (glyphGroup == null) {
      throw new IllegalArgumentException("Null GlyphGroup not allowed");
    }
    return super.add(glyphGroup);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (GlyphGroup glyphGroup : this) {
      sb.append(glyphGroup.toString());
    }
    return sb.toString();
  }

  String toString(PostScriptTable post) {
    StringBuilder sb = new StringBuilder();
    for (GlyphGroup glyphGroup : this) {
      sb.append(glyphGroup.toString(post));
      sb.append(" ");
    }
    return sb.toString();
  }

}
