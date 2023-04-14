package com.anzepintar.ozrpp.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.projectproperties.ProjectProperites;
import com.anzepintar.ozrpp.projectproperties.ProjectPropertiesManager;
import jakarta.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.Collator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProjectPropertiesController implements Initializable {

  public static String project_name = "New project";
  public static File project_root = new File(System.getProperty("user.home"));
  public static ProjectProperites project_properties;
  private final ObservableList<File> selectedFiles = FXCollections.observableArrayList();
  @FXML
  public TextField projectNameTextField;
  @FXML
  private ComboBox<String> sourceLangSelector;
  @FXML
  private ComboBox<String> targetLangSelector;
  @FXML
  private ListView<File> fileList;

  private final ObservableList<File> files = FXCollections.observableArrayList();

  public static Map<String, String> getLanguageCodesAndNames() throws IOException {
    Map<String, String> languages = new HashMap<>();
    String[] parts;
    BufferedReader br = new BufferedReader(new FileReader("src/main/resources/langAndCodes.csv"));
    String line;
    while ((line = br.readLine()) != null) {
      parts = line.split(";");
      String name = String.format("%s (%s)", parts[0], parts[1]);
      String code = parts[1];
      languages.put(name, code);
    }
    return languages;
  }


  @FXML
  void updateProjectName(KeyEvent event) {
    project_name = projectNameTextField.getText();
  }

  @FXML
  void addFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Files");
    fileChooser.setInitialDirectory(project_root);

    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
        "Text Files (*.txt), Word Files (*.docx), OpenDocument Text Files (*.odt)", "*.txt",
        "*.docx", "*.odt");

    fileChooser.getExtensionFilters().addAll(filter);

    List<File> selectedFiles = fileChooser.showOpenMultipleDialog(new Stage());
    if (selectedFiles != null) {
      files.addAll(selectedFiles);
      fileList.setItems(files);
    }
  }

  @FXML
  void deleteFile(ActionEvent event) {
    ObservableList<File> selectedFiles = fileList.getSelectionModel().getSelectedItems();
    if (!selectedFiles.isEmpty()) {
      files.removeAll(selectedFiles);
    }
  }

  @FXML
  void cancelProjectPreferences(ActionEvent event) throws IOException, RuntimeException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("Launcher");
    Ozrpp.setRoot("/ui/launcherScene.fxml");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  void saveProjectPreferences(ActionEvent event) throws IOException {

    ProjectProperites projectProperties = new ProjectProperites(project_root.getAbsolutePath(),
        project_name, getLanguageCodesAndNames().get(targetLangSelector.getValue()),
        getLanguageCodesAndNames().get(sourceLangSelector.getValue()));
    if (project_root != null) {
      try {
        ProjectPropertiesManager.saveProperties(projectProperties);
      } catch (JAXBException ex) {
        ex.printStackTrace();
      }
    }

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("Project");
    Ozrpp.setRoot("/ui/editorScene.fxml");
    stage.getScene().getWindow().sizeToScene();

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    projectNameTextField.setText(project_name);
    fileList.setItems(files);
    ObservableList<String> langOptions;
    try {
      langOptions = FXCollections.observableArrayList(getLanguageCodesAndNames().keySet());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    sourceLangSelector.setItems(new SortedList<String>(langOptions, Collator.getInstance()));
    targetLangSelector.setItems(new SortedList<String>(langOptions, Collator.getInstance()));


  }
}
