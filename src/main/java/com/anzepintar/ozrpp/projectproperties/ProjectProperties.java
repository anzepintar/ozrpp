package com.anzepintar.ozrpp.projectproperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectProperties {

  public ProjectProperties(String source_dir, String target_dir, String source_lang, String target_lang) {
    this.source_dir = source_dir;
    this.target_dir = target_dir;
    this.source_lang = source_lang;
    this.target_lang = target_lang;
  }

  public ProjectProperties() {

  }

  public String getSource_dir() {
    return source_dir;
  }

  public String getTarget_dir() {
    return target_dir;
  }

  public String getSource_lang() {
    return source_lang;
  }

  public String getTarget_lang() {
    return target_lang;
  }

  private String source_dir;
  private String target_dir;
  private String source_lang;
  private String target_lang;

  public void setProjectProperties(String source_dir, String target_dir, String source_lang, String target_lang) {
    this.source_dir = source_dir;
    this.target_dir = target_dir;
    this.source_lang = source_lang;
    this.target_lang = target_lang;
  }



  @Override
  public String toString() {
    return "project{" +
        "source_dir='" + source_dir + '\'' +
        ", target_dir='" + target_dir + '\'' +
        ", source_lang='" + source_lang + '\'' +
        ", target_lang='" + target_lang + '\'' +
        '}';
  }
}
