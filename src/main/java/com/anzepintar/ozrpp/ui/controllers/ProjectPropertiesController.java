package com.anzepintar.ozrpp.ui.controllers;

import static com.anzepintar.ozrpp.ui.controllers.LauncherController.projectDirectory;

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

  public static String projectPath =
      projectDirectory.getPath();

  public static String projectDir;
  private final JAXBContext context;
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

  public ProjectPropertiesController() throws JAXBException {
    this.context = JAXBContext.newInstance(ProjectProperties.class);
  }

  @FXML
  void updateProjectName(KeyEvent event) {

    projectName = projectNameTextField.getText();
    projectDir = projectPath + File.separator + projectName + File.separator;

    sourceFilesPathLabel.setText(
        projectDir
            + "source" + File.separator);
    targetFilesPathLabel.setText(
        projectDir + "target" + File.separator);
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
  void saveProjectPreferencesChanges(MouseEvent event) throws IOException, JAXBException {

    ProjectProperties project = new ProjectProperties(
        projectDir + "source",
        projectDir + "target",
        sourceLanguageSelector.getItems().toString(),
        targetLanguageSelector.getItems().toString());

    new File(projectDir + projectName).mkdirs();
    Marshaller marshaller = this.context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(project, new File(projectDir, projectName + ".xml"));

    new File(project.getSource_dir()).mkdirs();
    new File(project.getTarget_dir()).mkdirs();

    // not implemented yet
    new File(projectDir, "ozrpp").mkdirs();
    new File(projectDir + "ozrpp", "project_save.tmx").createNewFile();
    new File(projectDir, projectName + ".ozrpp").createNewFile();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle(projectDirectory.getAbsolutePath());
    Ozrpp.setRoot("ui/editorScene");
    stage.getScene().getWindow().sizeToScene();

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    projectNameTextField.setText(projectName);
  }
}
