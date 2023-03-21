package com.anzepintar.ozrpp.fileimport;

import com.anzepintar.ozrpp.ui.controllers.ProjectPropertiesController;
import java.io.File;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class FileImporterTest {
  // https://howtoprogram.xyz/2017/01/17/read-file-and-resource-in-junit-test/
  /* potrebuješ implementacijo testiranja, tako, da bodo delale spremenljivke v classu*/
  @Before
  public void setup() {
    ProjectPropertiesController.initializeProjectProperties();
  }
  @Test
  void importToTmx() throws Exception {
    // https://www.youtube.com/watch?v=vC_y0KvBVmU
    // https://java-design-patterns.com/patterns/arrange-act-assert/

    // Triple A pattern

    // Arrange - setup for test objects
    FileImporter fileImporter = new FileImporter();
    File file = new File("src/test/resources/com/anzepintar/ozrpp/fileimport/sloveniatext.odt");
    File file1 = new File("src/test/resources/com/anzepintar/ozrpp/fileimport/sloveniatext.docx");

    // Act
    fileImporter.importToTmx(file, "odt");
    fileImporter.importToTmx(file1, "docx");
    // Assert - check if the result is as expected


  }
}