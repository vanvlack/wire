/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire.internal.protoparser;

import com.google.auto.value.AutoValue;
import com.squareup.wire.internal.Util;
import com.squareup.wire.schema.Location;

import static com.squareup.wire.internal.Util.appendDocumentation;

@AutoValue
public abstract class ExtensionsElement {
  public static ExtensionsElement create(
      Location location, int start, int end, String documentation) {
    return new AutoValue_ExtensionsElement(location, documentation, start, end);
  }

  public abstract Location location();
  public abstract String documentation();
  public abstract int start();
  public abstract int end();

  public final String toSchema() {
    StringBuilder builder = new StringBuilder();
    appendDocumentation(builder, documentation());
    builder.append("extensions ")
        .append(start());
    if (start() != end()) {
      builder.append(" to ");
      if (end() < Util.MAX_TAG_VALUE) {
        builder.append(end());
      } else {
        builder.append("max");
      }
    }
    return builder.append(";\n").toString();
  }
}
