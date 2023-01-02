package com.anzepintar.ozrpp.ui.controllers;

import com.anzepintar.ozrpp.editordata.EditorData;
import com.anzepintar.ozrpp.ui.customcotrols.CustomEditorSourceTextArea;
import com.anzepintar.ozrpp.ui.customcotrols.CustomEditorStringStatusLabel;
import com.anzepintar.ozrpp.ui.customcotrols.CustomEditorTextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 */
public class EditorController implements Initializable {

  FileChooser fileChooser = new FileChooser();
  ObservableList<EditorData> editorData = FXCollections.observableArrayList();
  @FXML
  private TableView<EditorData> editorDataTableView;
  @FXML
  private TableColumn<EditorData, CustomEditorSourceTextArea> sourceStringsCol;
  @FXML
  private TableColumn<EditorData, CustomEditorTextArea> targetStringsCol;
  @FXML
  private TableColumn<EditorData, CustomEditorStringStatusLabel> stringStatusCol;
  @FXML
  private MenuItem menuFileCloseFile;
  @FXML
  private MenuItem menuFileOpenFile;
  @FXML
  private MenuItem menuFileSaveFile;
  @FXML
  private MenuItem menuEditCopySource;
  @FXML
  private MenuItem menuEditDelete;

  @FXML
  void closeFile(ActionEvent event) {
  }

  @FXML
  void copySelectionToTarget(ActionEvent event) {

  }

  @FXML
  void deleteSelection(ActionEvent event) {
  }

  @FXML
  void openFile(ActionEvent event) {
    File sourceFile = fileChooser.showOpenDialog(new Stage());
    // https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java

    try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
      for (String line; (line = br.readLine()) != null; ) {
        editorData.add(new EditorData("" + line, ""));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  void saveFile(ActionEvent event) {
    File targetFile = fileChooser.showSaveDialog(new Stage());
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
      for (EditorData data : editorData) {
        String targetString = data.getTargetStrings().getText();
        bw.write(targetString);
        bw.newLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param url
   * @param resourceBundle
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    fileChooser.setInitialDirectory(new File("C:\\Users\\anze\\Downloads"));

    sourceStringsCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.39));
    targetStringsCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.39));
    stringStatusCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.18));

    sourceStringsCol.setCellValueFactory(
        new PropertyValueFactory<EditorData, CustomEditorSourceTextArea>("sourceStrings")
    );
    targetStringsCol.setCellValueFactory(
        new PropertyValueFactory<EditorData, CustomEditorTextArea>("targetStrings")
    );
    stringStatusCol.setCellValueFactory(
        new PropertyValueFactory<EditorData, CustomEditorStringStatusLabel>("stringStatus")
    );

    editorDataTableView.setItems(editorData);

  }

}
