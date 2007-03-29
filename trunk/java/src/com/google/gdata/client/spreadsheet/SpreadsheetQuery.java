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


package com.google.gdata.client.spreadsheet;


import com.google.gdata.client.DocumentQuery;

import java.net.URL;


/**
 * Simple class for cells-feed-specific queries.
 *
 * 
 */
public class SpreadsheetQuery extends DocumentQuery {

  /**
   * Constructs a query for querying spreadsheets that you have access to.
   * 
   * @param feedUrl the feed's URI
   */
  public SpreadsheetQuery(URL feedUrl) {
    super(feedUrl);
  }
}
