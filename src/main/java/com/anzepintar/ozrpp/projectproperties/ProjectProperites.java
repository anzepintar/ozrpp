package com.anzepintar.ozrpp.projectproperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "projectProperites")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectProperites {

  public ProjectProperites(File projectRoot, String projectName, String sourceLang, String targetLang, List<File> sourceFiles) {
    this.projectRoot = projectRoot;
    this.projectName = projectName;
    this.sourceLang = sourceLang;
    this.targetLang = targetLang;
    this.sourceFiles = sourceFiles;
  }

  public ProjectProperites() {
  }

  @XmlElement
  private File projectRoot;
  @XmlElement
  private String projectName;
  @XmlElement
  private String sourceLang;
  @XmlElement
  private String targetLang;
  @XmlElementWrapper(name = "sourceFiles")
  @XmlElement(name = "file")
  private List<File> sourceFiles;

  public File getProjectRoot() {
    return projectRoot;
  }

  public void setProjectRoot(File projectRoot) {
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

  public List<File> getSourceFiles() {
    return sourceFiles;
  }

  public void setSourceFiles(List<File> sourceFiles) {
    this.sourceFiles = sourceFiles;
  }
}
