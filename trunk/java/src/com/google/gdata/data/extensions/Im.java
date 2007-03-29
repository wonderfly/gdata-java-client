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


package com.google.gdata.data.extensions;

import com.google.gdata.util.common.xml.XmlWriter;
import com.google.gdata.data.Extension;
import com.google.gdata.data.ExtensionDescription;
import com.google.gdata.data.ExtensionPoint;
import com.google.gdata.data.ExtensionProfile;
import com.google.gdata.util.Namespaces;
import com.google.gdata.util.ParseException;
import com.google.gdata.util.XmlParser;

import org.xml.sax.Attributes;

import java.io.IOException;
import java.util.ArrayList;


/**
 * GData schema extension describing an IM address.
 *
 * 
 */
public class Im extends ExtensionPoint implements Extension {


  /** Label. */
  protected String label;
  public String getLabel() { return label; }
  public void setLabel(String v) { label = v; }


  /** Protocol. */
  public static final class Protocol {

    /** SIP protocol. */
    public static final String SIP = "sip";

    /** XMPP protocol. */
    public static final String XMPP = "xmpp";

    /** AIM protocol. */
    public static final String AIM = "http://www.aim.com/";

    /** Yahoo Messenger protocol. */
    public static final String YAHOO = "http://messenger.yahoo.com/";

    /** MSN Messenger protocol. */
    public static final String MSN = "Http://messenger.msn.com/";
  }

  protected String protocol;
  public String getProtocol() { return protocol; }
  public void setProtocol(String v) { protocol = v; }


  /** Im address. */
  protected String address;
  public String getAddress() { return address; }
  public void setAddress(String v) { address = v; }


  /** Returns the suggested extension manifest. */
  public static ExtensionDescription getDefaultDescription() {
    ExtensionDescription desc = new ExtensionDescription();
    desc.setExtensionClass(Im.class);
    desc.setNamespace(Namespaces.gNs);
    desc.setLocalName("im");
    desc.setRepeatable(true);
    return desc;
  }


  public void generate(XmlWriter w, ExtensionProfile extProfile)
      throws IOException {

    ArrayList<XmlWriter.Attribute> attrs = new ArrayList<XmlWriter.Attribute>();

    if (label != null) {
      attrs.add(new XmlWriter.Attribute("label", label));
    }

    if (protocol != null) {
      attrs.add(new XmlWriter.Attribute("protocol", label));
    }

    if (address != null) {
      attrs.add(new XmlWriter.Attribute("address", address));
    }

    generateStartElement(w, Namespaces.gNs, "im", attrs, null);

    // Invoke ExtensionPoint.
    generateExtensions(w, extProfile);

    w.endElement(Namespaces.gNs, "im");
  }


  public XmlParser.ElementHandler getHandler(ExtensionProfile extProfile,
                                             String namespace,
                                             String localName,
                                             Attributes attrs)
      throws ParseException, IOException {

    return new Handler(extProfile);
  }


  /** <g:im> parser. */
  private class Handler extends ExtensionPoint.ExtensionHandler {


    public Handler(ExtensionProfile extProfile)
        throws ParseException, IOException {

      super(extProfile, Im.class);
    }


    public void processAttribute(String namespace,
                                 String localName,
                                 String value)
        throws ParseException {

      if (namespace.equals("")) {
        if (localName.equals("label")) {
          label = value;
        } else if (localName.equals("protocol")) {
          protocol = value;
        } else if (localName.equals("address")) {
          address = value;
        }
      }
    }


    public void processEndElement() throws ParseException {

      if (address == null) {
        throw new ParseException("g:im/@address is required.");
      }

      super.processEndElement();
    }
  }
}
