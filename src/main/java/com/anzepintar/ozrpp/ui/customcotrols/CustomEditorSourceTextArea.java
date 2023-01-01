package com.anzepintar.ozrpp.ui.customcotrols;

/**
 *
 */
public class CustomEditorSourceTextArea extends CustomEditorTextArea {

  /**
   * @param text
   */
  public CustomEditorSourceTextArea(String text) {
    super(text);
    setEditable(false);
  }
}
