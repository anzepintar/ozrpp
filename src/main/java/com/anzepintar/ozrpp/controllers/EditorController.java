package com.anzepintar.ozrpp.controllers;


import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.customcotrols.AutoResizableTextArea;
import com.anzepintar.ozrpp.customcotrols.TranslationCheckBox;
import com.anzepintar.ozrpp.editordata.TableRow;
import com.anzepintar.ozrpp.fileexport.FileExporter;
import com.anzepintar.ozrpp.fileexport.TmxSaver;
import com.anzepintar.ozrpp.fileexport.TmxToXliffSaver;
import com.anzepintar.ozrpp.fileimport.TmxLoader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


public class EditorController implements Initializable {

  @FXML
  public ListView<File> projectFilesList;
  @FXML
  public TableView<TableRow> tableView;
  @FXML
  private TableColumn<TableRow, AutoResizableTextArea> col1;
  @FXML
  private TableColumn<TableRow, AutoResizableTextArea> col2;
  @FXML
  private TableColumn<TableRow, TranslationCheckBox> col3;

  @FXML
  private Label ProjectLangInfo;

  @FXML
  private Label ProjectNameInfo;
  private File currentFile;


  @FXML
  private void closeProject() throws IOException {
    Ozrpp.setRoot("/ui/launcherScene.fxml");
  }

  @FXML
  private void openSourceCode() throws IOException, URISyntaxException {
    java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/anzepintar/Ozrpp/"));
  }

  private boolean checkCompletion() {
    boolean allSelected = true;
    for (TableRow data : tableView.getItems()) {
      CheckBox checkBox = data.getStatusCheckBox();
      if (!checkBox.isSelected()) {
        allSelected = false;
      }
    }
    if (allSelected) {
      return true;
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Missing segments");
      alert.setHeaderText(null);
      alert.setContentText(
          "Not all segments are selected. Please select all segments before saving.");
      alert.showAndWait();
      return false;
    }
  }


  @FXML
  private void saveFile() throws ParserConfigurationException, TransformerException {
    saveTmxFile();
  }


  @FXML
  private void openSelectedFile(MouseEvent event)
      throws IOException, ParserConfigurationException, TransformerException {
    saveFile();
    if (event.getClickCount() == 1) {
      File selectedItem = projectFilesList.getSelectionModel().getSelectedItem();
      if (selectedItem != null) {
        tableView.setItems(loadTmxFile(selectedItem));
        currentFile = selectedItem;
      }
    }
  }


  private void setTableView() {
    // start table setup
    col1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.45));
    col2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.45));
    col3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.06));
    tableView.setFixedCellSize(65);

    col1.setCellValueFactory(new PropertyValueFactory<>("sourceField"));
    col2.setCellValueFactory(new PropertyValueFactory<>("targetField"));
    col3.setCellValueFactory(new PropertyValueFactory<>("statusCheckBox"));
    // end table setup
  }

  private ObservableList<TableRow> loadTmxFile(File file) {
    String filePath = Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/tmx/"
        + file.getName() + ".tmx";

    List<TableRow> tableRowsList = new ArrayList<>();
    try {
      List<String> list = TmxLoader.getTmxSourceStrings(filePath);

      for (int i = 0; i < list.size(); i++) {
        tableRowsList.add(new TableRow(TmxLoader.getTmxSourceStrings(filePath).get(i),
            TmxLoader.getTmxTargetStrings(filePath).get(i),
            TmxLoader.getTmxStatus(filePath).get(i)));
      }
    } catch (Exception e) {
      System.err.println("An exception occurred while processing the TMX file:");
      e.printStackTrace();
    }

    return FXCollections.observableList(tableRowsList);
  }


  public void exportToXliff() throws Exception {
    saveTmxFile();
    if (checkCompletion()) {
      DirectoryChooser directoryChooser = new DirectoryChooser();
      directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
      File directory = directoryChooser.showDialog(new Stage());
      TmxToXliffSaver.saveTmxToXliff(new File(
              Ozrpp.projectProperites.getProjectRoot().getAbsoluteFile() + "/tmx/"
                  + currentFile.getName()
                  + ".tmx"),
          new File(directory.getAbsolutePath() + "/" + currentFile.getName() + ".xliff"));
    }
  }


  private void saveTmxFile()
      throws ParserConfigurationException, TransformerException {

    String filePath = Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/tmx/"
        + currentFile.getName() + ".tmx";
    List<String> sourceStrings = new ArrayList<>();
    List<String> targetStrings = new ArrayList<>();
    List<Boolean> status = new ArrayList<>();

    for (TableRow row : tableView.getItems()) {
      sourceStrings.add(row.getSourceField().getText());
      targetStrings.add(row.getTargetField().getText());
      status.add(row.getStatusCheckBox().isSelected());
    }
    TmxSaver.saveTmxData(filePath, sourceStrings, targetStrings, status);
  }


  public void exportToTarget() throws Exception {
    FileExporter.saveToTarget(new File(
        Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/tmx/" + currentFile.getName()
            + ".tmx"), getFileExtension(currentFile.getName()));

  }

  public String getFileExtension(String fileName) {
    int lastIndex = fileName.lastIndexOf(".");
    if (lastIndex == -1) {
      throw new IllegalArgumentException("File has no extension: " + fileName);
    }
    return fileName.substring(lastIndex + 1);

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setTableView();
    currentFile = Ozrpp.projectProperites.getSourceFiles().get(0);
    tableView.setItems(loadTmxFile(currentFile));
    ObservableList<File> files = FXCollections.observableArrayList();
    files.addAll(Ozrpp.projectProperites.getSourceFiles());
    projectFilesList.setItems(files);
    ProjectLangInfo.setText("Language: " + Ozrpp.projectProperites.getSourceLang() + " > "
        + Ozrpp.projectProperites.getTargetLang());
    ProjectNameInfo.setText("Project name: " + Ozrpp.projectProperites.getProjectName());
  }


}
