/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.google.gdata.model.gd;

import com.google.gdata.model.Element;
import com.google.gdata.model.ElementCreator;
import com.google.gdata.model.ElementKey;
import com.google.gdata.model.MetadataRegistry;

/**
 * Describes an entry link used by a recurrence exception.
 *
 * 
 */
public class RecurrenceExceptionEntryLink extends EntryLink {

  /**
   * The key for this element.
   */
  @SuppressWarnings("hiding")
  public static final ElementKey<Void,
      RecurrenceExceptionEntryLink> KEY = ElementKey.of(EntryLink.KEY.getId(),
      Void.class, RecurrenceExceptionEntryLink.class);

  /**
   * Registers the metadata for this element.
   */
  public static void registerMetadata(MetadataRegistry registry) {
    if (registry.isRegistered(KEY)) {
      return;
    }

    // Register superclass metadata.
    EntryLink.registerMetadata(registry);

    // The builder for this element
    ElementCreator builder = registry.build(KEY);

    // Overridden elements
    builder.replaceElement(RecurrenceExceptionEntry.KEY);
  }

  /**
   * Default mutable constructor.
   */
  public RecurrenceExceptionEntryLink() {
    this(KEY);
  }

  /**
   * Create an instance using a different key.
   */
  public RecurrenceExceptionEntryLink(ElementKey<Void,
      ? extends RecurrenceExceptionEntryLink> key) {
    super(key);
  }

  /**
   * Constructs a new instance by doing a shallow copy of data from an existing
   * {@link Element} instance. Will use the given {@link ElementKey} as the key
   * for the element.
   *
   * @param key The key to use for this element.
   * @param source source element
   */
  public RecurrenceExceptionEntryLink(ElementKey<Void,
      ? extends RecurrenceExceptionEntryLink> key, Element source) {
    super(key, source);
  }

   @Override
   public RecurrenceExceptionEntryLink lock() {
     return (RecurrenceExceptionEntryLink) super.lock();
   }


}

