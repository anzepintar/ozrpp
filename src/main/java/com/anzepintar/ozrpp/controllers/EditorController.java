package com.anzepintar.ozrpp.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.converters.tmxconvert.ObjectFactory;
import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.StringStatusLabel;
import com.anzepintar.ozrpp.editordata.TableRowData;
import com.anzepintar.ozrpp.fileimport.FileImporter;
import com.anzepintar.ozrpp.fileimport.TmxReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.text.similarity.FuzzyScore;


public class EditorController implements Initializable {


  @FXML
  public TextArea catMatch;
  @FXML
  public ListView projectFilesList;
  FileChooser fileChooser = new FileChooser();
  ObservableList<TableRowData> editorData = FXCollections.observableArrayList();
  ObjectFactory factory = new ObjectFactory();
  @FXML
  private TableView<TableRowData> editorDataTableView;
  // za parameter prejme podatke in kater element prikaže
  @FXML
  private TableColumn<TableRowData, AutoResizableTextArea> sourceStringsCol;
  @FXML
  private TableColumn<TableRowData, AutoResizableTextArea> targetStringsCol;
  @FXML
  private TableColumn<TableRowData, StringStatusLabel> stringStatusCol;
  @FXML
  private MenuItem menuFileCloseFile;
  @FXML
  private MenuItem menuFileOpenFile;
  @FXML
  private MenuItem menuFileSaveFile;
  @FXML
  private MenuItem menuFileSaveToXliff;
  @FXML
  private MenuItem menuEditCopySource;
  @FXML
  private MenuItem menuEditDelete;

  public static double fuzzySearch(String query, String target) {
    // Convert both strings to lowercase
    query = query.toLowerCase();
    target = target.toLowerCase();

    // Split query into individual words
    String[] queryWords = query.split("\\W+");
    Locale locale = new Locale("en");
    FuzzyScore fuzzyScore = new FuzzyScore(locale);

    // Calculate fuzzy match score for each word in query
    int[] matchScores = new int[queryWords.length];
    for (int i = 0; i < queryWords.length; i++) {
      matchScores[i] = fuzzyScore.fuzzyScore(queryWords[i], target);
    }

    // Calculate percentage of words from query that have a match in target
    int numMatchingWords = 0;
    for (int score : matchScores) {
      if (score > 0) {
        numMatchingWords++;
      }
    }
    double percentMatch = (double) numMatchingWords / queryWords.length * 100.0;

    return percentMatch;
  }

  // method which displays strings from source target in textarea if fuzzy search percentage is over 50
  public void displayMachedStrings() {
    for (TableRowData data : editorData) {
      if (fuzzySearch(data.getSourceStrings().getText(), data.getTargetStrings().getText())
          > 50.0) {
        catMatch.setText(
            data.getSourceStrings().getText() + "\n" + data.getTargetStrings().getText());
      }
    }
  }

  @FXML
  void closeFile(ActionEvent event) throws IOException {
    Stage stage = (Stage) menuFileCloseFile.getParentPopup().getOwnerWindow();
    Ozrpp.setRoot("/ui/launcherScene.fxml");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  void copySelectionToTarget(ActionEvent event) {

  }

  // method which delete text in target textarea
  void clearTarget() {
    if (editorDataTableView.getSelectionModel().getSelectedItem() == null) {
      editorDataTableView.getSelectionModel().getSelectedItem().getTargetStrings().clear();
    }
  }

  @FXML
  void deleteSelection(ActionEvent event) {
  }

  @FXML
  void openFile(ActionEvent event) throws Exception {
    File sourceFile = fileChooser.showOpenDialog(new Stage());
    if (sourceFile != null) {
      String fileExtension = getFileExtension(sourceFile);

      FileImporter fileImporter = new FileImporter();
      fileImporter.importToTmx(sourceFile, fileExtension);
    }


  }
  // method which get strings from tmx file and shows them in tableview
  void showStrings() {
    for (TableRowData data : editorData) {
      data.getSourceStrings().setText(data.getSourceStrings().getText());
      if (data.getTargetStrings() == null) {
        data.getTargetStrings().setText(data.getTargetStrings().getText());
      }
    }
  }

  // method which get strings from tmx file and writes them into editorData
  void importStrings() {

  }


  private String getFileExtension(File file) {
    String fileName = file.getName();
    int lastDotIndex = fileName.lastIndexOf(".");
    if (lastDotIndex != -1 && lastDotIndex != 0) {
      return fileName.substring(lastDotIndex + 1).toLowerCase();
    } else {
      return "";
    }
  }

  //metod which saves target stringst to tmx file with use of projectProperties into target folder
  @FXML
  void saveFile() {

  }

  //method which uses fuzy search to find simmilar string in target textarea and returns the match as percentage
  void fuzzyFindMatch() {
    for (TableRowData data : editorData) {
      //
    }
  }

  @FXML
  void saveToXliff(ActionEvent event) {

  }

  void copyAllStrings() {
    for (TableRowData data : editorData) {
      data.getSourceStrings().setText(data.getSourceStrings().getText());
      if (data.getTargetStrings() == null) {
        data.getTargetStrings().setText(data.getTargetStrings().getText());
      }
    }
  }

  //method copy source which copy source to target in selected textarea

  void copyToTarget() {
    if (editorDataTableView.getSelectionModel().getSelectedItem() != null) {
      editorDataTableView.getSelectionModel().getSelectedItem().getSourceStrings().setText(
          editorDataTableView.getSelectionModel().getSelectedItem().getSourceStrings().getText());
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    String[] myStrings = TmxReader.readTmxFile("myFile.tmx");
    //samo za izgled
    sourceStringsCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.39));
    targetStringsCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.39));
    stringStatusCol.prefWidthProperty().bind(editorDataTableView.widthProperty().multiply(0.18));

    sourceStringsCol.setCellValueFactory(
        new PropertyValueFactory<TableRowData, AutoResizableTextArea>("sourceStrings")
    );
    targetStringsCol.setCellValueFactory(
        new PropertyValueFactory<TableRowData, AutoResizableTextArea>("targetStrings")
    );
    stringStatusCol.setCellValueFactory(
        new PropertyValueFactory<TableRowData, StringStatusLabel>("stringStatus")
    );
    editorDataTableView.setItems(editorData);

  }

}
