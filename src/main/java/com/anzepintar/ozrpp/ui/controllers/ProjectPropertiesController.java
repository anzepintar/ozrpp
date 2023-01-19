package com.anzepintar.ozrpp.ui.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 */
public class ProjectPropertiesController implements Initializable {

  public static String projectName = "New project";
  @FXML
  public TextField projectNameTextField;
  @FXML
  private TextField sourceFilesPathLabel;
  @FXML
  private ComboBox<?> sourceLanguageSelector;
  @FXML
  private ComboBox<?> sourceLanguageTokenizerSelector;
  @FXML
  private TextField targetFilesPathLabel;
  @FXML
  private ComboBox<?> targetLanguageSelector;
  @FXML
  private ComboBox<?> targetLanguageTokenizerSelector;

  @FXML
  void updateProjectName(KeyEvent event) {

    projectName = projectNameTextField.getText();

    sourceFilesPathLabel.setText(
        LauncherController.projectDirectory.getAbsolutePath() + "\\" + projectName
            + "\\target\\");
    targetFilesPathLabel.setText(
        LauncherController.projectDirectory.getAbsolutePath() + "\\" + projectName
            + "\\source\\");
  }

  @FXML
  void browseSourceFilesLocation(MouseEvent event) {

  }

  @FXML
  void browseTargetFilesLocation(MouseEvent event) {

  }


  @FXML
  void cancelProjectPreferencesChanges(MouseEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("Launcher");
    Ozrpp.setRoot("ui/launcherScene");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  void saveProjectPreferencesChanges(MouseEvent event) throws IOException {

    new File(LauncherController.projectDirectory.getAbsolutePath() + "/"
        + projectName).mkdirs();
    new File(LauncherController.projectDirectory.getAbsolutePath()
        + "/" + projectName + "/source").mkdirs();
    new File(LauncherController.projectDirectory.getAbsolutePath()
        + "/" + projectName + "/target").mkdirs();
    new File(LauncherController.projectDirectory.getAbsolutePath()
        + "/" + projectName + "/ozrpp").mkdirs();
    new File(LauncherController.projectDirectory.getAbsolutePath()
        + "/" + projectName + "/ozrpp/project_save.tmx").createNewFile();
    new File(LauncherController.projectDirectory.getAbsolutePath()
        + "/" + projectName + "/" + projectName + ".ozrpp").createNewFile();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    projectNameTextField.setText(projectName);
  }
}
