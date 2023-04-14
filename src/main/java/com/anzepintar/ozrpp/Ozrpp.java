package com.anzepintar.ozrpp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Ozrpp extends Application {

  public static Scene scene;

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));
  }

  public static Parent loadFxml(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Ozrpp.class.getResource(fxml));
    return fxmlLoader.load();
  }

  public static Stage getStage(MouseEvent event) {
    return (Stage) ((Node) event.getSource()).getScene().getWindow();
  }

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    scene = new Scene(loadFxml("/ui/launcherScene.fxml"));
    stage.setScene(scene);
    stage.show();
  }
}
