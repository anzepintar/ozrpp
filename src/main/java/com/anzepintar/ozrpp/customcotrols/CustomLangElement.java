package com.anzepintar.ozrpp.customcotrols;

public class CustomLangElement {

  private final String displayName;
  private final String shortCodedName;

  public CustomLangElement(String displayName, String shortCodedName) {
    this.displayName = displayName;
    this.shortCodedName = shortCodedName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getShortCodedName() {
    return shortCodedName;
  }
}

