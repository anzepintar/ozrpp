package com.anzepintar.ozrpp.editordata;

import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.StringStatusLabel;
import java.util.Arrays;

public class TableRowData {
  private final String[] statusList = {
      "untranslated",
      "translated",
  };

  private AutoResizableTextArea sourceStrings;
  private AutoResizableTextArea targetStrings;

  private StringStatusLabel stringStatus;

  public TableRowData(String sourceStrings, String targetStrings) {
    this.sourceStrings = new AutoResizableTextArea(sourceStrings);
    this.targetStrings = new AutoResizableTextArea(targetStrings);
    this.stringStatus = new StringStatusLabel();
  }

  public AutoResizableTextArea getSourceStrings() {
    return sourceStrings;
  }

  public void setSourceStrings(AutoResizableTextArea sourceStrings) {
    this.sourceStrings = sourceStrings;
  }

  public AutoResizableTextArea getTargetStrings() {
    return targetStrings;
  }

  public void setTargetStrings(AutoResizableTextArea targetStrings) {
    this.targetStrings = targetStrings;
  }

  public StringStatusLabel getStringStatus() {
    return stringStatus;
  }

  public void setStringStatus(StringStatusLabel stringStatus) {
    try {
      if (Arrays.asList(statusList).contains(stringStatus)) {
        this.stringStatus = stringStatus;
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }

  }
}
