package com.anzepintar.ozrpp.ui.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.fileimport.FileImporter;
import com.anzepintar.ozrpp.tmxconvert.ObjectFactory;
import com.anzepintar.ozrpp.translationstrings.TranslationStrings;
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
  ObservableList<TranslationStrings> editorData = FXCollections.observableArrayList();

  ObjectFactory factory = new ObjectFactory();
  @FXML
  private TableView<TranslationStrings> editorDataTableView;
  @FXML
  private TableColumn<TranslationStrings, CustomEditorSourceTextArea> sourceStringsCol;
  @FXML
  private TableColumn<TranslationStrings, CustomEditorTextArea> targetStringsCol;
  @FXML
  private TableColumn<TranslationStrings, CustomEditorStringStatusLabel> stringStatusCol;
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
  void closeFile(ActionEvent event) throws IOException {
    Stage stage = (Stage) menuFileCloseFile.getParentPopup().getOwnerWindow();
    Ozrpp.setRoot("ui/launcherScene");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  void copySelectionToTarget(ActionEvent event) {

  }

  @FXML
  void deleteSelection(ActionEvent event) {
  }

  @FXML
  void openFile(ActionEvent event) throws Exception {
    File sourceFile = fileChooser.showOpenDialog(new Stage());
    // https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java
    FileImporter fileImporter = new FileImporter();
    fileImporter.importToTmx(sourceFile, "docx");
    try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
      for (String line; (line = br.readLine()) != null; ) {
        editorData.add(new TranslationStrings("" + line, ""));
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  void saveFile(ActionEvent event) {
    File targetFile = fileChooser.showSaveDialog(new Stage());
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
      for (TranslationStrings data : editorData) {
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
    fileChooser.setInitialDirectory(new File("/"));

    sourceStringsCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.39));
    targetStringsCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.39));
    stringStatusCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.18));

    sourceStringsCol.setCellValueFactory(
        new PropertyValueFactory<TranslationStrings, CustomEditorSourceTextArea>("sourceStrings")
    );
    targetStringsCol.setCellValueFactory(
        new PropertyValueFactory<TranslationStrings, CustomEditorTextArea>("targetStrings")
    );
    stringStatusCol.setCellValueFactory(
        new PropertyValueFactory<TranslationStrings, CustomEditorStringStatusLabel>("stringStatus")
    );
    editorDataTableView.setItems(editorData);

  }

}
