package com.anzepintar.ozrpp.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.StringStatusLabel;
import com.anzepintar.ozrpp.editordata.TableRow;
import com.anzepintar.ozrpp.fileexport.TmxSaver;
import com.anzepintar.ozrpp.fileimport.FileImporter;
import com.anzepintar.ozrpp.fileimport.TmxLoader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.text.similarity.FuzzyScore;


public class EditorController implements Initializable {

  @FXML
  public ListView projectFilesList;
  FileChooser fileChooser = new FileChooser();
  @FXML
  private TextArea catMatch;
  @FXML
  private MenuItem menuGetProjectSourceCode;
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


  private double fuzzySearch(String query, String target) {
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


  @FXML
  private void closeFile(ActionEvent event) throws IOException {
    Stage stage = (Stage) menuFileCloseFile.getParentPopup().getOwnerWindow();
    Ozrpp.setRoot("/ui/launcherScene.fxml");
    stage.getScene().getWindow().sizeToScene();
  }

  @FXML
  private void copySelectionToTarget(ActionEvent event) {

  }

  @FXML
  private void openSourceCode(ActionEvent event) throws IOException, URISyntaxException {
    java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/anzepintar/Ozrpp/"));
  }

  private void clearTarget() {
    if (tableView.getSelectionModel().getSelectedItem() == null) {
      tableView.getSelectionModel().getSelectedItem().getTargetField().clear();
    }
  }

  @FXML
  private void deleteSelection(ActionEvent event) {
  }

  @FXML
  private void openFile(ActionEvent event) throws Exception {
    File sourceFile = fileChooser.showOpenDialog(new Stage());
    if (sourceFile != null) {
      String fileExtension = getFileExtension(sourceFile);

      FileImporter fileImporter = new FileImporter();
      fileImporter.importToTmx(sourceFile);
    }


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


  @FXML
  private void saveToXliff(ActionEvent event) {

  }

  private void copyToTarget() {
    if (tableView.getSelectionModel().getSelectedItem() != null) {
      tableView.getSelectionModel().getSelectedItem().getSourceField().setText(
          tableView.getSelectionModel().getSelectedItem().getSourceField().getText());
    }
  }

  private void setTableView() {
    // start table setup
    col1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.39));
    col2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.39));
    col3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.18));

    col1.setCellValueFactory(new PropertyValueFactory<>("sourceField"));
    col2.setCellValueFactory(new PropertyValueFactory<>("targetField"));
    col3.setCellValueFactory(new PropertyValueFactory<>("statusField"));
    // end table setup
  }

  private ObservableList<TableRow> loadTmxFile() {
    String filePath = Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/tmx/"
        + Ozrpp.projectProperites.getSourceFiles().get(0).getName() + ".tmx";

    List<TableRow> tableRowsList = new ArrayList<>();
    try {
      var list = TmxLoader.getTmxSourceStrings(filePath);

      for (int i = 0; i < list.size(); i++) {
        tableRowsList.add(new TableRow(TmxLoader.getTmxSourceStrings(filePath).get(i),
            TmxLoader.getTmxTargetStrings(filePath).get(i),
            TmxLoader.getTmxStatus(filePath).get(i)));
      }
    } catch (Exception e) {
    }

    ObservableList<TableRow> observableList = FXCollections.observableList(tableRowsList);
    return observableList;
  }


  @FXML
  private void saveFile() throws IOException, ParserConfigurationException, TransformerException {
    saveTmxFile();
  }

  private void saveTmxFile()
      throws IOException, ParserConfigurationException, TransformerException {
    String filePath = Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/tmx/"
        + Ozrpp.projectProperites.getSourceFiles().get(0).getName() + ".tmx";
    List<String> sourceStrings = new ArrayList<>();
    List<String> targetStrings = new ArrayList<>();
    List<String> status = new ArrayList<>();

    for (TableRow row : tableView.getItems()) {
      sourceStrings.add(row.getSourceField().getText());
      targetStrings.add(row.getTargetField().getText());
      status.add(row.getStatusField().getText());
    }
    TmxSaver.saveTmxData(filePath, sourceStrings, targetStrings, status);


  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setTableView();

    tableView.setItems(loadTmxFile());
  }

}
