package com.anzepintar.ozrpp.controllers;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.TranslationCheckBox;
import com.anzepintar.ozrpp.editordata.TableRow;
import com.anzepintar.ozrpp.fileexport.TmxSaver;
import com.anzepintar.ozrpp.fileimport.TmxLoader;
import com.anzepintar.ozrpp.projectproperties.ProjectPropertiesManager;
import jakarta.xml.bind.JAXBException;
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
import javafx.scene.input.MouseEvent;
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
  public TableView<TableRow> tableView;
  // za parameter prejme podatke in kater element prikaže
  @FXML
  private TableColumn<TableRow, AutoResizableTextArea> col1;
  @FXML
  private TableColumn<TableRow, AutoResizableTextArea> col2;
  @FXML
  private TableColumn<TableRow, TranslationCheckBox> col3;
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
  private void closeProject(ActionEvent event) throws IOException {
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
  private void newProject(MouseEvent event) throws IOException, JAXBException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    File file = fileChooser.showOpenDialog(new Stage());
    Ozrpp.projectProperites = ProjectPropertiesManager.loadProperties(file.getAbsolutePath());
    Stage stage = Ozrpp.getStage(event);
    stage.setTitle("Editor");
    stage.setMaximized(true);
    Ozrpp.setRoot("/ui/editorScene.fxml");
  }

  @FXML
  private void saveFile() throws IOException, ParserConfigurationException, TransformerException {
    saveTmxFile();
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
    tableView.setFixedCellSize(65);

    col1.setCellValueFactory(new PropertyValueFactory<>("sourceField"));
    col2.setCellValueFactory(new PropertyValueFactory<>("targetField"));
    col3.setCellValueFactory(new PropertyValueFactory<>("statusCheckBox"));
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


  public void exportToXliff(ActionEvent event) {
  }


  private void saveTmxFile()
      throws IOException, ParserConfigurationException, TransformerException {
    String filePath = Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/tmx/"
        + Ozrpp.projectProperites.getSourceFiles().get(0).getName() + ".tmx";
    List<String> sourceStrings = new ArrayList<>();
    List<String> targetStrings = new ArrayList<>();
    List<Boolean> status = new ArrayList<>();

    for (TableRow row : tableView.getItems()) {
      sourceStrings.add(row.getSourceField().getText());
      targetStrings.add(row.getTargetField().getText());
      status.add(row.getStatusCheckBox().getState());
    }
    TmxSaver.saveTmxData(filePath, sourceStrings, targetStrings, status);


  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setTableView();

    tableView.setItems(loadTmxFile());
  }


}
