package com.anzepintar.ozrpp.translationstrings;

import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.CustomEditorStringStatusLabel;
import java.util.Arrays;

public class TranslationStrings {

  // https://docs.lokalise.com/en/articles/3684557-translation-statuses-translated-verified-reviewed-and-completed
  private final String[] statusList = {
      "untranslated",
      "translated",
      "verified",
      "reviewed",
      "completed"
  };

  private AutoResizableTextArea sourceStrings;
  private AutoResizableTextArea targetStrings;

  private CustomEditorStringStatusLabel stringStatus;

  /**
   * @param sourceStrings
   * @param targetStrings
   */
  public TranslationStrings(String sourceStrings, String targetStrings) {
    this.sourceStrings = new AutoResizableTextArea(sourceStrings);
    this.targetStrings = new AutoResizableTextArea(targetStrings);
    this.stringStatus = new CustomEditorStringStatusLabel();
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

  public CustomEditorStringStatusLabel getStringStatus() {
    return stringStatus;
  }

  public void setStringStatus(CustomEditorStringStatusLabel stringStatus) {
    try {
      if (Arrays.asList(statusList).contains(stringStatus)) {
        this.stringStatus = stringStatus;
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }

  }
}
