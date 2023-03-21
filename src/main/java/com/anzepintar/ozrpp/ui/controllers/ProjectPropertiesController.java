package com.anzepintar.ozrpp.ui.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.projectproperties.ProjectProperties;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
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

  public static String projectPath = LauncherController.projectDir.getPath();

  public static String projectDir;

  public static ProjectProperties projectProperties = new ProjectProperties();
  private final JAXBContext context;
  @FXML
  public TextField projectNameTextField;

  @FXML
  private TextField sourceFilesPathLabel;
  @FXML
  private ComboBox<?> sourceLangSelector;
  @FXML
  private ComboBox<?> sourceLangTokenizerSelector;
  @FXML
  private TextField targetFilesPathLabel;
  @FXML
  private ComboBox<?> targetLangSelector;
  @FXML
  private ComboBox<?> targetLangTokenizerSelector;

  public ProjectPropertiesController() throws JAXBException {
    this.context = JAXBContext.newInstance(ProjectProperties.class);
  }

  public static void initializeProjectProperties() {
    projectProperties = new ProjectProperties();
  }

  @FXML
  void updateProjectName(KeyEvent event) {

    projectName = projectNameTextField.getText();
    projectDir = projectPath + File.separator + projectName + File.separator;

    sourceFilesPathLabel.setText(projectDir + "source" + File.separator);
    targetFilesPathLabel.setText(projectDir + "target" + File.separator);
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
  public void saveProjectPreferencesChanges(MouseEvent event) throws IOException, JAXBException {

    projectProperties.setProjectProperties(projectDir + "source",
        projectDir + "target",
        sourceLangSelector.getItems().toString(),
        targetLangSelector.getItems().toString());

    new File(projectDir + projectName).mkdirs();
    Marshaller marshaller = this.context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(projectProperties, new File(projectDir, projectName + ".xml"));

    new File(projectProperties.getSource_dir()).mkdirs();
    new File(projectProperties.getTarget_dir()).mkdirs();

    // not implemented yet
    new File(projectDir, "ozrpp").mkdirs();
    new File(projectDir + "ozrpp", "project_save.tmx").createNewFile();
    new File(projectDir, projectName + ".ozrpp").createNewFile();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle(LauncherController.projectDir.getAbsolutePath());
    Ozrpp.setRoot("ui/editorScene");
    stage.getScene().getWindow().sizeToScene();

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    projectNameTextField.setText(projectName);
  }
}
