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
    TranslationCheckBox translationCheckBoxheckBox = new TranslationCheckBox();
    translationCheckBoxheckBox.setSelected(statusCheckBox);
    this.statusCheckBox = (translationCheckBoxheckBox);
  }

  public AutoResizableTextArea getSourceField() {
    return sourceField;
  }

  public AutoResizableTextArea getTargetField() {
    return targetField;
  }

  public TranslationCheckBox getStatusCheckBox() {
    return statusCheckBox;
  }

}
