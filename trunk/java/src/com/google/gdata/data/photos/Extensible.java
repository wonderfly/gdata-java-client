/* Copyright (c) 2006 Google Inc.
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


package com.google.gdata.data.photos;

import com.google.gdata.data.Extension;
import com.google.gdata.data.ExtensionProfile;

/**
 * Basic interface for all extendable objects.  This is just an interface for
 * a subset of the methods on ExtensionPoint.
 *
 * 
 */
public interface Extensible {

  /**
   * Declares the set of expected Extension types for an Extensible
   * within the target extension profile.
   *
   * @param extProfile
   *          the ExtensionProfile to initialize.
   */
  public void declareExtensions(ExtensionProfile extProfile);

  /**
   * Sets an extension object. If one exists of this type, it's replaced.
   */
  public void setExtension(Extension extension);

  /**
   * Adds an extension object.
   */
  public void addExtension(Extension extension);

  /**
   * Removes an extension object.
   */
  public void removeExtension(Extension extension);

  /**
   * Removes an extension object based on its class.
   */
  public void removeExtension(Class<? extends Extension> extensionClass);
}
