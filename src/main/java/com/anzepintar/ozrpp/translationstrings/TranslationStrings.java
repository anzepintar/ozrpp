package com.anzepintar.ozrpp.translationstrings;

import com.anzepintar.ozrpp.ui.customcotrols.CustomEditorSourceTextArea;
import com.anzepintar.ozrpp.ui.customcotrols.CustomEditorStringStatusLabel;
import com.anzepintar.ozrpp.ui.customcotrols.CustomEditorTextArea;
import java.util.Arrays;

/**
 *
 */
public class TranslationStrings {

  // https://docs.lokalise.com/en/articles/3684557-translation-statuses-translated-verified-reviewed-and-completed
  private final String[] statusList = {
      "untranslated",
      "translated",
      "verified",
      "reviewed",
      "completed"
  };

  private CustomEditorSourceTextArea sourceStrings;
  private CustomEditorTextArea targetStrings;

  private CustomEditorStringStatusLabel stringStatus;

  /**
   * @param sourceStrings
   * @param targetStrings
   */
  public TranslationStrings(String sourceStrings, String targetStrings) {
    this.sourceStrings = new CustomEditorSourceTextArea(sourceStrings);
    this.targetStrings = new CustomEditorTextArea(targetStrings);
    this.stringStatus = new CustomEditorStringStatusLabel();
  }

  public CustomEditorSourceTextArea getSourceStrings() {
    return sourceStrings;
  }

  public void setSourceStrings(CustomEditorSourceTextArea sourceStrings) {
    this.sourceStrings = sourceStrings;
  }

  public CustomEditorTextArea getTargetStrings() {
    return targetStrings;
  }

  public void setTargetStrings(CustomEditorTextArea targetStrings) {
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
