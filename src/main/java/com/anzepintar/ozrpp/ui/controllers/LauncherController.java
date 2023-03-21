package com.anzepintar.ozrpp.ui.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


/**
 *
 */
public class LauncherController {

  public DirectoryChooser dirChooser = new DirectoryChooser();

  public static File projectDir;
  @FXML
  private Text launcherTitle;
  @FXML
  private ListView<?> recentProjectList;


  @FXML
  void newProjectDialog(MouseEvent event) throws IOException {
    Stage stage = Ozrpp.getStage(event);
    if (dirChooser.getInitialDirectory() != null) {
      dirChooser.setInitialDirectory(projectDir);
    } else {
      dirChooser.setInitialDirectory(new File("/"));
    }
    projectDir = dirChooser.showDialog(new Stage());

    stage.setTitle(projectDir.getAbsolutePath());
    Ozrpp.setRoot("ui/projectPropertiesScene");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  void openProjectFromFileDialog(MouseEvent event) throws IOException {
    Stage stage = Ozrpp.getStage(event);
    stage.setTitle("Editor");
    stage.setMaximized(true);
    Ozrpp.setRoot("ui/editorScene");
  }
}
