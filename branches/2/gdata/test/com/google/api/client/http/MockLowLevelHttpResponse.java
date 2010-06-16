/*
 * Copyright (c) 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.client.http;

import com.google.api.client.util.ArrayMap;

import java.io.InputStream;

/**
 * Mock for {@link LowLevelHttpResponse}.
 * 
 * @author Yaniv Inbar
 */
public class MockLowLevelHttpResponse extends LowLevelHttpResponse {

  public ArrayMap<String, String> headers = ArrayMap.create();
  
  @Override
  public InputStream getContent() {
    return null;
  }

  @Override
  public String getContentEncoding() {
    return null;
  }

  @Override
  public long getContentLength() {
    return 0;
  }

  @Override
  public String getContentType() {
    return null;
  }

  @Override
  public int getHeaderCount() {
    return headers.size();
  }

  @Override
  public String getHeaderName(int index) {
    return headers.getKey(index);
  }

  @Override
  public String getHeaderValue(int index) {
    return headers.getValue(index);
  }

  @Override
  public String getReasonPhrase() {
    return null;
  }

  @Override
  public int getStatusCode() {
    return 0;
  }

  @Override
  public String getStatusLine() {
    return null;
  }

}
