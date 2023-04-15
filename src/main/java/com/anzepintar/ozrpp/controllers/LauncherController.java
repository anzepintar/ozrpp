package com.anzepintar.ozrpp.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.projectproperties.ProjectPropertiesManager;
import jakarta.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class LauncherController {

  @FXML
  private Text launcherTitle;
  @FXML
  private ListView<?> recentProjectList;


  @FXML
  void newProjectDialog(MouseEvent event) throws IOException {
    Stage stage = Ozrpp.getStage(event);
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    Ozrpp.projectProperites.setProjectRoot(directoryChooser.showDialog(new Stage()));
    Ozrpp.setRoot("/ui/projectPropertiesScene.fxml");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  void openProjectFromFileDialog(MouseEvent event) throws IOException, JAXBException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    File file = fileChooser.showOpenDialog(new Stage());
    Ozrpp.projectProperites = ProjectPropertiesManager.loadProperties(file.getAbsolutePath());
    Stage stage = Ozrpp.getStage(event);
    stage.setTitle("Editor");
    stage.setMaximized(true);
    Ozrpp.setRoot("/ui/editorScene.fxml");
  }
}
