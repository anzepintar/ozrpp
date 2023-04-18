package com.anzepintar.ozrpp.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.fileimport.FileImporter;
import com.anzepintar.ozrpp.projectproperties.ProjectPropertiesManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProjectPropertiesController implements Initializable {

  private final ObservableList<File> files = FXCollections.observableArrayList();
  @FXML
  public TextField projectNameTextField;
  @FXML
  private ComboBox<String> sourceLangSelector;
  @FXML
  private ComboBox<String> targetLangSelector;
  @FXML
  private ListView<File> fileList;

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
  private void updateProjectName() {
    Ozrpp.projectProperites.setProjectName(projectNameTextField.getText());
  }

  @FXML
  private void addFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Files");
    fileChooser.setInitialDirectory(Ozrpp.projectProperites.getProjectRoot());

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
  private void removeFile() {
    ObservableList<File> selectedFiles = fileList.getSelectionModel().getSelectedItems();
    if (!selectedFiles.isEmpty()) {
      files.removeAll(selectedFiles);
    }
  }

  @FXML
  private void cancelProjectPreferences(ActionEvent event) throws IOException, RuntimeException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("Orodje za računalniško podprto prevajanje");
    Ozrpp.setRoot("/ui/launcherScene.fxml");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  private void saveProjectPreferences(ActionEvent event) throws Exception {
    FileImporter fileImporter = new FileImporter();
    String newProjectName = projectNameTextField.getText();
    File newProjectRoot = new File(Ozrpp.projectProperites.getProjectRoot(), newProjectName);

    String newSourceLang = getLanguageCodesAndNames().get(sourceLangSelector.getValue());
    String newTargetLang = getLanguageCodesAndNames().get(targetLangSelector.getValue());


    Path sourceDir = Paths.get(newProjectRoot.getAbsolutePath()).resolve("source");
    if (!Files.exists(sourceDir)) {
      Files.createDirectories(sourceDir);
    }
    Path tmxDir = Paths.get(newProjectRoot.getAbsolutePath()).resolve("tmx");
    if (!Files.exists(tmxDir)) {
      Files.createDirectories(tmxDir);
    }
    Path targetDir = Paths.get(newProjectRoot.getAbsolutePath()).resolve("target");
    if (!Files.exists(targetDir)) {
      Files.createDirectories(targetDir);
    }
    Ozrpp.projectProperites.setProjectName(newProjectName);
    Ozrpp.projectProperites.setProjectRoot(newProjectRoot);
    Ozrpp.projectProperites.setSourceLang(newSourceLang);
    Ozrpp.projectProperites.setTargetLang(newTargetLang);

    List<File> newSourceFiles = new ArrayList<>();
    for (File file : files) {
      Path sourcePath = file.toPath();
      Path targetPath = sourceDir.resolve(sourcePath.getFileName());
      Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
      newSourceFiles.add(targetPath.toFile());
      fileImporter.importToTmx(file);

    }
    Ozrpp.projectProperites.setSourceFiles(Collections.unmodifiableList(newSourceFiles));


    if (Ozrpp.projectProperites.getProjectName() != null) {
      ProjectPropertiesManager.saveProperties(Ozrpp.projectProperites);
    }

    Stage stage = Ozrpp.getStageA(event);
    stage.setTitle("Editor");
    Ozrpp.setRoot("/ui/editorScene.fxml");
    stage.getScene().getWindow().sizeToScene();
    stage.setMaximized(true);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    projectNameTextField.setText("New Project");
    sourceLangSelector.getSelectionModel().select("Slovenian (sl)");
    targetLangSelector.getSelectionModel().select("English (en)");
    fileList.setItems(files);
    ObservableList<String> langOptions;
    try {
      langOptions = FXCollections.observableArrayList(getLanguageCodesAndNames().keySet());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    sourceLangSelector.setItems(new SortedList<>(langOptions, Collator.getInstance()));
    targetLangSelector.setItems(new SortedList<>(langOptions, Collator.getInstance()));


  }
}
