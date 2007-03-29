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

package com.google.gdata.data.geo.impl;

import com.google.gdata.data.Extension;
import com.google.gdata.data.ExtensionPoint;
import com.google.gdata.data.ExtensionProfile;
import com.google.gdata.data.geo.Point;
import com.google.gdata.data.geo.PointData;

/**
 * Implementation of the PointData interface.  Currently only supports
 * a non-repeating Point extension.  This class uses an {@link ExtensionPoint}
 * that is passed in to store the Point extension.
 * 
 * 
 */
public class PointDataImpl implements PointData {

  private ExtensionPoint extPoint;
  
  /**
   * Construct a new implementation of PointData with the given
   * extension point as the backing storage for data.
   */
  public PointDataImpl(ExtensionPoint extensionPoint) {
    this.extPoint = extensionPoint;
  }
  
  /**
   * Sets the geo-location of the entity based on the lat and long coordinates
   * passed in.
   * 
   * @param lat The latitude coordinate, between -90 and 90 degrees.
   * @param lon The longitude coordinate, between -180 and 180 degrees.
   * @throws IllegalArgumentException if the latitude and longitude coordinates
   *        are invalid.
   */
  public void setGeoLocation(Double lat, Double lon) 
      throws IllegalArgumentException {
    removePointExtension();
    if (lat != null && lon != null) {
      setGeoLocation(new W3CPoint(lat, lon));
    } else if (lat != null || lon != null) {
      throw new IllegalArgumentException(
          "'lat' and 'lon' must either both be null or non-null.");
    }
  }
  
  /**
   * Sets the geo-location of the entity based on the Point extension.
   * 
   * @param point A point containing the latitude and longitude coordinates.
   */
  public void setGeoLocation(Point point) {
    removePointExtension();
    if (point != null) {
      extPoint.setExtension(point);
    }
  }
  
  /**
   * Gets the geo-location of the entity.
   * @return a Point that contains the geo-coordinates (latitude and longitude).
   */
  public Point getGeoLocation() {
    return getPointExtension();
  }
  
  /*
   * Declare the extensions that are used for storing Point information.
   */
  public void declareExtensions(ExtensionProfile extProfile) {
    Class<? extends ExtensionPoint> extClass = extPoint.getClass();
    
    // Declare all all Point implementations here so they are parsable
    // in the context of extClass.
    
    // Declare the W3C's geo:Point extension as non-repeatable.
    extProfile.declare(extClass, W3CPoint.getDefaultDescription(false));
  }
  
  /**
   * Helper method that iterates through all extensions in the ExtensionPoint
   * and returns an instance of the Point extension if it exists.
   */
  private Point getPointExtension() {
    for (Extension ext : extPoint.getExtensions()) {
      if (ext instanceof Point) {
        return (Point)ext;
      }
    }
    return null;
  }
  
  /**
   * Removes the Point extension from the ExtensionPoint.
   */
  private void removePointExtension() {
    Point p = getPointExtension();
    if (p != null) {
      extPoint.removeExtension(p);
    }
  }
}