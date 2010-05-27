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

package com.google.api.data.latitude.v1;

/**
 * Constants for the Google Latitude API.
 *
 * @since 2.2
 */
public final class GoogleLatitude {

  /** Root URL. */
  public static final String ROOT_URL =
      "https://www.googleapis.com/latitude/v1/";

  /**
   * OAuth authorization service endpoint.
   *
   * @since 2.3
   */
  public static final String OAUTH_AUTHORIZATION_URL =
      "https://www.google.com/latitude/apps/OAuthAuthorizeToken";

  /**
   * @since 2.3
   */
  public static final String OAUTH_SCOPE =
      "https://www.googleapis.com/auth/latitude";

  private GoogleLatitude() {
  }
}