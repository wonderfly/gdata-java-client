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


package com.google.gdata.data.media.mediarss;

import com.google.gdata.util.common.xml.XmlWriter;
import com.google.gdata.data.ExtensionDescription;
import com.google.gdata.data.AttributeHelper;
import com.google.gdata.util.ParseException;

import java.util.List;

/**
 * {@code <media:content>}.
 *
 * See description on
 * <a href="http://search.yahoo.com/mrss">http://search.yahoo.com/mrss</a>.
 *
 * 
 */
public class MediaContent extends AbstractMediaResource {
  private long fileSize;
  private String type;
  private String medium;
  private boolean isDefault;
  private Expression expression;
  private int bitrate;
  private int framerate;
  private int samplingrate;
  private int channels;
  private int duration;
  private String language;

  public MediaContent() {
    super("content");
  }

  /**
   * Describes the tag to an {@link com.google.gdata.data.ExtensionProfile}.
   *
   * @param repeat if true, the description will be repeatable (MediaContent
   *   can be repeated when inside MediaGroup, but not when inside BaseEntry.)
   */
  public static ExtensionDescription getDefaultDescription(boolean repeat) {
    ExtensionDescription retval =
        ExtensionUtils.getDefaultDescription("content", MediaContent.class);
    retval.setRepeatable(repeat);
    return retval;
  }


  public int getBitrate() {
    return bitrate;
  }

  public void setBitrate(int bitrate) {
    this.bitrate = bitrate;
  }

  public int getChannels() {
    return channels;
  }

  public void setChannels(int channels) {
    this.channels = channels;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Expression getExpression() {
    return expression;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public int getFramerate() {
    return framerate;
  }

  public void setFramerate(int framerate) {
    this.framerate = framerate;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean aDefault) {
    isDefault = aDefault;
  }

  public String getMedium() {
    return medium;
  }

  public void setMedium(String medium) {
    this.medium = medium;
  }

  public int getSamplingrate() {
    return samplingrate;
  }

  public void setSamplingrate(int samplingrate) {
    this.samplingrate = samplingrate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  protected void addAttributes(List<XmlWriter.Attribute> attrs) {
    super.addAttributes(attrs);

    ExtensionUtils.addAttribute(attrs, "fileSize", fileSize);
    ExtensionUtils.addAttribute(attrs, "type", type);
    ExtensionUtils.addAttribute(attrs, "medium", medium);
    if (isDefault) {
      ExtensionUtils.addAttribute(attrs, "isDefault", isDefault);
    }
    ExtensionUtils.addAttribute(attrs, "expression", expression);
    ExtensionUtils.addAttribute(attrs, "bitrate", bitrate);
    ExtensionUtils.addAttribute(attrs, "framerate", framerate);
    ExtensionUtils.addAttribute(attrs, "samplingrate", samplingrate);
    ExtensionUtils.addAttribute(attrs, "channels", channels);
    ExtensionUtils.addAttribute(attrs, "duration", duration);
    ExtensionUtils.addAttribute(attrs, "language", language);
  }

  @Override
  protected void consumeAttributes(AttributeHelper attrsHelper)
      throws ParseException {
    fileSize = attrsHelper.consumeLong("fileSize", false);
    type = attrsHelper.consume("type", false);
    medium = attrsHelper.consume("medium", false);
    isDefault = attrsHelper.consumeBoolean("isDefault", false);
    expression =
        attrsHelper.consumeEnum("expression", false, Expression.class);
    bitrate = attrsHelper.consumeInteger("bitrate", false);
    framerate = attrsHelper.consumeInteger("framerate", false);
    samplingrate = attrsHelper.consumeInteger("samplingrate", false);
    channels = attrsHelper.consumeInteger("channels", false);
    duration = attrsHelper.consumeInteger("duration", false);
    language = attrsHelper.consume("language", false);
  }

  /** Values for the expression attribute: sample, full and nonstop. */
  public enum Expression {
    SAMPLE, FULL, NONSTOP
  }
}
