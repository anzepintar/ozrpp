package com.anzepintar.ozrpp.editordata;

import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.StringStatusLabel;

public class TableRow {

  private AutoResizableTextArea sourceField;
  private AutoResizableTextArea targetField;
  private StringStatusLabel statusField;

  public TableRow(String sourceField, String targetField, String statusField) {
    this.sourceField = new AutoResizableTextArea(sourceField);
    this.targetField = new AutoResizableTextArea(targetField);
    this.statusField = new StringStatusLabel(statusField);
  }

  public AutoResizableTextArea getSourceField() {
    return sourceField;
  }

  public void setSourceField(AutoResizableTextArea sourceField) {
    this.sourceField = sourceField;
  }

  public AutoResizableTextArea getTargetField() {
    return targetField;
  }

  public void setTargetField(AutoResizableTextArea targetField) {
    this.targetField = targetField;
  }

  public StringStatusLabel getStatusField() {
    return statusField;
  }

  public void setStatusField(String statusField) {

  }
}
