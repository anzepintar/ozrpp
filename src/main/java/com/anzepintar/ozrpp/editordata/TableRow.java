package com.anzepintar.ozrpp.editordata;

import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.TranslationCheckBox;

public class TableRow {

  private AutoResizableTextArea sourceField;
  private AutoResizableTextArea targetField;
  private TranslationCheckBox statusCheckBox;

  public TableRow(String sourceField, String targetField, Boolean statusCheckBox) {
    this.sourceField = new AutoResizableTextArea(sourceField);
    this.targetField = new AutoResizableTextArea(targetField);
    this.statusCheckBox = new TranslationCheckBox(statusCheckBox);
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

  public TranslationCheckBox getStatusCheckBox() {
    return statusCheckBox;
  }

  public void setStatusCheckBox(String statusCheckBox) {

  }
}
