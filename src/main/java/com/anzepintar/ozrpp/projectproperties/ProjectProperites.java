package com.anzepintar.ozrpp.projectproperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

/*
Izgledati mora podobno kot to:
omegat.project
*/
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectProperites {

  public ProjectProperites(String projectRoot, String projectName, String sourceLang, String targetLang) {
    this.projectRoot = projectRoot;
    this.projectName = projectName;
    this.sourceLang = sourceLang;
    this.targetLang = targetLang;
  }

  public ProjectProperites() {
  }

  private String projectRoot;
  private String projectName;
  private String sourceLang;
  private String targetLang;

  public String getProjectRoot() {
    return projectRoot;
  }

  public void setProjectRoot(String projectRoot) {
    this.projectRoot = projectRoot;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getSourceLang() {
    return sourceLang;
  }

  public void setSourceLang(String sourceLang) {
    this.sourceLang = sourceLang;
  }

  public String getTargetLang() {
    return targetLang;
  }

  public void setTargetLang(String targetLang) {
    this.targetLang = targetLang;
  }
}
