module com.anzepintar {
  requires javafx.base;
  requires javafx.controls;
  requires javafx.fxml;
  requires jakarta.xml.bind;
  requires org.apache.poi.ooxml;
  requires odfdom.java;
  requires org.apache.commons.text;
  requires java.desktop;

  exports com.anzepintar.ozrpp;
  opens com.anzepintar.ozrpp to javafx.fxml;

  exports com.anzepintar.ozrpp.controllers;
  opens com.anzepintar.ozrpp.controllers to javafx.fxml;

  exports com.anzepintar.ozrpp.editordata to javafx.base;
  opens com.anzepintar.ozrpp.editordata to javafx.base;

  exports com.anzepintar.ozrpp.fileexport;
  opens com.anzepintar.ozrpp.fileexport to javafx.fxml;

  exports com.anzepintar.ozrpp.fileimport;
  opens com.anzepintar.ozrpp.fileimport to javafx.fxml, jakarta.xml.bind;

  exports com.anzepintar.ozrpp.customcotrols;
  opens com.anzepintar.ozrpp.customcotrols to javafx.fxml;

  exports com.anzepintar.ozrpp.projectproperties;
  opens com.anzepintar.ozrpp.projectproperties to jakarta.xml.bind;

  exports com.anzepintar.ozrpp.generatedclasses.tmxgenerated;
  opens com.anzepintar.ozrpp.generatedclasses.tmxgenerated to jakarta.xml.bind;

}