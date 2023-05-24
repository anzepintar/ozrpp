package com.anzepintar.ozrpp.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.projectproperties.ProjectPropertiesManager;
import jakarta.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class LauncherController {



  @FXML
  private void newProjectDialog(MouseEvent event) throws IOException {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    File directory = directoryChooser.showDialog(new Stage());
    if (directory != null) {
      Ozrpp.projectProperites.setProjectRoot(directory);
      Stage stage = Ozrpp.getStageM(event);
      stage.setTitle("Project Properties");
      Ozrpp.setRoot("/ui/projectPropertiesScene.fxml");
      stage.getScene().getWindow().sizeToScene();
    }
  }

  @FXML
  private void openProjectDialog(MouseEvent event) throws IOException, JAXBException {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
        "Ozrpp Project Files", "*.project");
    fileChooser.getExtensionFilters().addAll(filter);
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    File file = fileChooser.showOpenDialog(new Stage());
    if (file != null) {
      Ozrpp.projectProperites = ProjectPropertiesManager.loadProperties(file.getAbsolutePath());
      Stage stage = Ozrpp.getStageM(event);
      stage.setTitle("Editor");
      Ozrpp.setRoot("/ui/editorScene.fxml");
      stage.getScene().getWindow().sizeToScene();
      stage.setMaximized(true);
    }
  }

  @FXML
  private void openRepo() throws URISyntaxException, IOException {
    java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/anzepintar/Ozrpp/"));
  }

}
