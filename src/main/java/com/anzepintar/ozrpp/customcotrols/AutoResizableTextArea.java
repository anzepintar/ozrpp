package com.anzepintar.ozrpp.customcotrols;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AutoResizableTextArea extends TextArea {

  private static final int DEFAULT_PADDING = 5;

  public AutoResizableTextArea(String text) {
    super(text);

    textProperty().addListener((observable, oldValue, newValue) -> {
      setPrefHeight(calculateHeight(newValue));
    });
    setWrapText(true);
    Font font = new Font(14);
    setFont(font);
  }


  private double calculateHeight(String text) {
    Text tempText = new Text(text);
    tempText.setFont(getFont());
    tempText.setWrappingWidth(getPrefWidth());
    double textHeight = tempText.getLayoutBounds().getHeight();

    return textHeight + DEFAULT_PADDING;
  }
}


