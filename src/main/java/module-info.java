module com.anzepintar {
  requires javafx.base;
  requires javafx.controls;
  requires javafx.fxml;
  requires org.controlsfx.controls;

  exports com.anzepintar.ozrpp;
  opens com.anzepintar.ozrpp to javafx.fxml;

  opens com.anzepintar.ozrpp.ui to javafx.fxml;

  exports com.anzepintar.ozrpp.ui.controllers;
  opens com.anzepintar.ozrpp.ui.controllers to javafx.fxml;

  exports com.anzepintar.ozrpp.editordata to javafx.base;
  opens com.anzepintar.ozrpp.editordata to javafx.base;

  exports com.anzepintar.ozrpp.fileexport;
  opens com.anzepintar.ozrpp.fileexport to javafx.fxml;

  exports com.anzepintar.ozrpp.fileimport;
  opens com.anzepintar.ozrpp.fileimport to javafx.fxml;
  exports com.anzepintar.ozrpp.ui.customcotrols;
  opens com.anzepintar.ozrpp.ui.customcotrols to javafx.fxml;
}