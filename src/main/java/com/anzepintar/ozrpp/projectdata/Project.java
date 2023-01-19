package com.anzepintar.ozrpp.projectdata;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {

  private String source_dir;
  private String target_dir;
  private String source_lang;
  private String target_lang;

  public Project() {
  }

  public Project(String source_dir, String target_dir, String source_lang, String target_lang) {
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
