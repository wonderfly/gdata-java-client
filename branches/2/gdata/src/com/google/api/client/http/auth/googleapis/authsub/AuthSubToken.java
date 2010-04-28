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

package com.google.api.client.http.auth.googleapis.authsub;

import com.google.api.client.Name;
import com.google.api.client.auth.googleapis.authsub.AuthSub;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.auth.googleapis.AuthKeyValueParser;
import com.google.api.client.http.googleapis.GoogleTransport;

import java.io.IOException;
import java.security.PrivateKey;

/**
 * Manages the AuthSub tokens for a single user.
 * <p>
 * To properly initialize, set:
 * <ul>
 * <li>{@link #setToken}: single-use or session token (required)</li>
 * <li>{@link #transport}: Google transport (recommended)</li>
 * <li>{@link #privateKey}: private key for secure AuthSub (recommended)</li>
 * </ul>
 * .
 */
public class AuthSubToken {

  /** Private key for secure AuthSub. */
  public volatile PrivateKey privateKey;

  /**
   * Google transport whose authorization header to set or {@code null} to
   * ignore (for example if using an alternative HTTP library.
   */
  public volatile GoogleTransport transport;

  /** HTTP transport for AuthSub requests. */
  private final HttpTransport authSubTransport = new HttpTransport();

  /** Token (may be single use or session). */
  private String token;

  public AuthSubToken() {
    AuthKeyValueParser.setAsParserOf(this.authSubTransport);
  }

  /** Entity to parse a success response for an AuthSubSessionToken request. */
  public static final class SessionTokenResponse {

    @Name("Token")
    public String sessionToken;
  }

  /** Entity to parse a success response for an AuthSubTokenInfo request. */
  public static final class TokenInfoResponse {

    @Name("Secure")
    public boolean secure;

    @Name("Target")
    public String target;

    @Name("Scope")
    public String scope;
  }

  /**
   * Sets to the given single-use or session token (or resets any existing token
   * if {@code null}). Sets the authorization header of the Google transport
   * using the token. Any previous stored single-use or session token will be
   * forgotten.
   */
  public void setToken(String token) {
    if (token != this.token) {
      this.token = token;
      setAuthorizationHeaderOf(this.transport);
      setAuthorizationHeaderOf(this.authSubTransport);
    }
  }

  /**
   * Exchanges the single-use token for a session token as described in <a href=
   * "http://code.google.com/apis/accounts/docs/AuthSub.html#AuthSubSessionToken"
   * >AuthSubSessionToken</a>. Sets the authorization header of the Google
   * transport using the session token, and automatically sets the token used by
   * this instance using {@link #setToken(String)}.
   * <p>
   * Note that Google allows at most 10 session tokens per use per web
   * application, so the session token for each user must be persisted.
   * 
   * @return session token
   * @throws HttpResponseException if the authentication response has an error
   *         code
   * @throws IOException some other kind of I/O exception
   */
  public String exchangeForSessionToken() throws IOException {
    HttpTransport authSubTransport = this.authSubTransport;
    HttpRequest request =
        authSubTransport
            .buildGetRequest("https://www.google.com/accounts/AuthSubSessionToken");
    SessionTokenResponse sessionTokenResponse =
        request.execute().parseAs(SessionTokenResponse.class);
    String sessionToken = sessionTokenResponse.sessionToken;
    setToken(sessionToken);
    return sessionToken;
  }

  /**
   * Revokes the session token. Clears any existing authorization header of the
   * Google transport and automatically resets the token by calling {@code
   * setToken(null)}.
   * <p>
   * See <a href=
   * "http://code.google.com/apis/accounts/docs/AuthSub.html#AuthSubRevokeToken"
   * >AuthSubRevokeToken</a> for protocol details.
   * 
   * @throws HttpResponseException if the authentication response has an error
   *         code
   * @throws IOException some other kind of I/O exception
   */
  public void revokeSessionToken() throws IOException {
    HttpTransport authSubTransport = this.authSubTransport;
    HttpRequest request =
        authSubTransport
            .buildGetRequest("https://www.google.com/accounts/AuthSubRevokeToken");
    request.execute().ignore();
    setToken(null);
  }

  /**
   * Retries the token information as described in <a href=
   * "http://code.google.com/apis/accounts/docs/AuthSub.html#AuthSubTokenInfo"
   * >AuthSubTokenInfo</a>.
   * 
   * @throws HttpResponseException if the authentication response has an error
   *         code
   * @throws IOException some other kind of I/O exception
   */
  public TokenInfoResponse requestTokenInfo() throws IOException {
    HttpTransport authSubTransport = this.authSubTransport;
    HttpRequest request =
        authSubTransport
            .buildGetRequest("https://www.google.com/accounts/AuthSubTokenInfo");
    HttpResponse response = request.execute();
    if (response.getParser() == null) {
      throw new IllegalStateException(response.parseAsString());
    }
    return response.parseAs(TokenInfoResponse.class);
  }

  /**
   * Sets the authorization header for the given HTTP transport based on the
   * current token.
   */
  private void setAuthorizationHeaderOf(HttpTransport httpTransport) {
    if (httpTransport != null) {
      String token = this.token;
      if (token == null) {
        httpTransport.authorizationHeaderProvider = null;
        httpTransport.setAuthorizationHeader(null);
      } else {
        PrivateKey privateKey = this.privateKey;
        if (privateKey == null) {
          httpTransport.authorizationHeaderProvider = null;
          httpTransport.setAuthorizationHeader(AuthSub
              .getAuthorizationHeaderValue(token));
        } else {
          httpTransport.setAuthorizationHeader(null);
          httpTransport.authorizationHeaderProvider =
              new AuthSubAuthoritzationHeaderProvider(token, privateKey);
        }
      }
    }
  }
}