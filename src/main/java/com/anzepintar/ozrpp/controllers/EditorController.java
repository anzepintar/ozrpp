package com.anzepintar.ozrpp.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.converters.tmxconvert.ObjectFactory;
import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.StringStatusLabel;
import com.anzepintar.ozrpp.editordata.TableRow;
import com.anzepintar.ozrpp.fileimport.FileImporter;
import com.anzepintar.ozrpp.fileimport.TmxLoader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

  ObjectFactory factory = new ObjectFactory();
  @FXML
  private TableView<TableRow> tableView;
  // za parameter prejme podatke in kater element prikaže
  @FXML
  private TableColumn<TableRow, AutoResizableTextArea> col1;
  @FXML
  private TableColumn<TableRow, AutoResizableTextArea> col2;
  @FXML
  private TableColumn<TableRow, StringStatusLabel> col3;
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

  @FXML
  private Label ProjectLangInfo;

  @FXML
  private Label ProjectNameInfo;


  public static double fuzzySearch(String query, String target) {
    // Convert both strings to lowercase
    query = query.toLowerCase();
    target = target.toLowerCase();

    // Split query into individual words
    String[] queryWords = query.split("\\W+");
    Locale locale = new Locale(Ozrpp.projectProperites.getSourceLang());
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

  /*public void displayMachedStrings() {
    for (TableRow data : editorTableRow) {
      if (fuzzySearch(data.getSourceField().getText(), data.getTargetField().getText())
          > 50.0) {
        catMatch.setText(
            data.getSourceField().getText() + "\n" + data.getTargetField().getText());
      }
    }
  }*/

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
    if (tableView.getSelectionModel().getSelectedItem() == null) {
      tableView.getSelectionModel().getSelectedItem().getTargetField().clear();
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
      fileImporter.importToTmx(sourceFile);
    }


  }

  // method which get strings from tmx file and shows them in tableview
  /*void showStrings() {
    for (TableRow data : editorTableRow) {
      data.getSourceField().setText(data.getSourceField().getText());
      if (data.getTargetField() == null) {
        data.getTargetField().setText(data.getTargetField().getText());
      }
    }
  }*/

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
  /*void fuzzyFindMatch() {
    for (TableRow data : editorTableRow) {
      //
    }
  }*/

  @FXML
  void saveToXliff(ActionEvent event) {

  }

  /*void copyAllStrings() {
    for (TableRow data : editorTableRow) {
      data.getSourceField().setText(data.getSourceField().getText());
      if (data.getTargetField() == null) {
        data.getTargetField().setText(data.getTargetField().getText());
      }
    }
  }*/

  //method copy source which copy source to target in selected textarea

  void copyToTarget() {
    if (tableView.getSelectionModel().getSelectedItem() != null) {
      tableView.getSelectionModel().getSelectedItem().getSourceField().setText(
          tableView.getSelectionModel().getSelectedItem().getSourceField().getText());
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // start table setup

    col1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.39));
    col2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.39));
    col3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.18));

    col1.setCellValueFactory(new PropertyValueFactory<>("sourceField"));
    col2.setCellValueFactory(new PropertyValueFactory<>("targetField"));
    col3.setCellValueFactory(new PropertyValueFactory<>("statusField"));
    // end table setup

    String filePath = Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/tmx/"
        + Ozrpp.projectProperites.getSourceFiles().get(0).getName() + ".tmx";

    List<TableRow> tableRowsList = new ArrayList<>();

    try {
      var list = TmxLoader.getTmxSourceStrings(filePath);

      for (int i = 0; i < list.size(); i++) {
        tableRowsList.add(new TableRow(TmxLoader.getTmxSourceStrings(filePath).get(i),
            TmxLoader.getTmxTargetStrings(filePath).get(i), TmxLoader.getTmxStatus(filePath).get(i)));
      }
    } catch (Exception e) {
    }


    ObservableList<TableRow> observableList = FXCollections.observableList(tableRowsList);
    tableView.setItems(observableList);
  }

}
