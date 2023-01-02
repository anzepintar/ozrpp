package com.anzepintar.ozrpp.ui.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


/**
 *
 */
public class LauncherController {

  public DirectoryChooser directoryChooser = new DirectoryChooser();

  public static File projectDirectory;
  @FXML
  private Text launcherTitle;
  @FXML
  private ListView<?> recentProjectList;


  @FXML
  void newProjectDialog(MouseEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    if (directoryChooser.getInitialDirectory() != null) {
      directoryChooser.setInitialDirectory(projectDirectory);
    } else {
      directoryChooser.setInitialDirectory(new File("/"));
    }
    projectDirectory = directoryChooser.showDialog(new Stage());

    stage.setTitle(projectDirectory.getAbsolutePath());
    Ozrpp.setRoot("ui/projectProperties");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  void openProjectFromFileDialog(MouseEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("Editor");
    stage.setMaximized(true);
    Ozrpp.setRoot("ui/editorScene");
  }
}
