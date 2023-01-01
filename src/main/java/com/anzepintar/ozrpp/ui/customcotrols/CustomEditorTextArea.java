package com.anzepintar.ozrpp.ui.customcotrols;

import javafx.scene.control.TextArea;

/**
 *
 */
public class CustomEditorTextArea extends TextArea {

  /**
   * @param text
   */
  public CustomEditorTextArea(String text) {
    super(text);
    setWrapText(true);
    setHeight(100);
  }
}