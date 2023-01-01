package com.anzepintar.ozrpp.ui.controllers;

import static com.anzepintar.ozrpp.Ozrpp.scene;

import com.anzepintar.ozrpp.Ozrpp;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 */
public class LauncherController {

  @FXML
  private Text launcherTitle;

  @FXML
  private ListView<?> recentProjectList;

  @FXML
  void newProjectDialog(MouseEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("Create new project");
    Ozrpp.setRoot("ui/projectProperties");
    scene.getWindow().sizeToScene();
  }

  @FXML
  void openProjectFromFileDialog(MouseEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("Editor");
    stage.setMaximized(true);
    Ozrpp.setRoot("ui/editorScene");
  }

}
