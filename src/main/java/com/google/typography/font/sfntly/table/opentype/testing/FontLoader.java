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

package com.google.typography.font.sfntly.table.opentype.testing;

import com.google.typography.font.sfntly.Font;
import com.google.typography.font.sfntly.FontFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FontLoader {

  public static List<File> getFontFiles(String fontDir) {
    List<File> fontFiles = new ArrayList<File>();
    getFontFiles(fontFiles, new File(fontDir), "", true);
    return fontFiles;
  }

  public static Font getFont(File fontFile) throws IOException {
    Font[] fonts = load(fontFile);
    if (fonts == null) {
      throw new IllegalArgumentException("No font found");
    }
    return fonts[0];
  }

  private static void getFontFiles(
      List<File> fonts, File dir, String startFrom, boolean foundStart) {
    File[] files = dir.listFiles();
    for (File file : files) {
      if (file.getName().endsWith(".ttf")) {
        if (foundStart || startFrom.endsWith(file.getName())) {
          foundStart = true;
          fonts.add(file);
        }
      }
      if (file.isDirectory()) {
        getFontFiles(fonts, file, startFrom, foundStart);
      }
    }
  }

  private static Font[] load(File file) throws IOException {
    FontFactory fontFactory = FontFactory.getInstance();
    fontFactory.fingerprintFont(true);
    FileInputStream is = new FileInputStream(file);
    try {
      return fontFactory.loadFonts(is);
    } catch (FileNotFoundException e) {
      System.err.println("Could not load the font : " + file.getName());
      return null;
    } finally {
      is.close();
    }
  }
}
