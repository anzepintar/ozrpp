package fileimport;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.anzepintar.ozrpp.converters.tmxconvert.Tuv;
import com.anzepintar.ozrpp.fileimport.FileImporter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileImporterTest {

  private FileImporter fileImporter;

  @BeforeEach
  public void setUp() {
    fileImporter = new FileImporter();
  }

  @Test
  public void testParseTxtFile() throws IOException, URISyntaxException {
    URL testTxtResource = getClass().getClassLoader().getResource("fileimport/sloveniatext.txt");
    if (testTxtResource == null) {
      throw new FileNotFoundException("Test file not found: fileimport/sloveniatext.txt");
    }
    File testTxtFile = Paths.get(testTxtResource.toURI()).toFile();

    ArrayList<Tuv> tuvList = fileImporter.parseTxtFile(testTxtFile);

    assertEquals(18, tuvList.size());
    assertEquals(
        "SloveniaTXT, officially the Republic of Slovenia, is a country in Central Europe.",
        tuvList.get(0).getSeg());
    assertEquals("sl", tuvList.get(0).getLang());
    assertEquals("", tuvList.get(1).getSeg());
    assertEquals("en", tuvList.get(1).getLang());
  }

  @Test
  public void testParseDocxFile() throws IOException, URISyntaxException {
    URL testDocxResource = getClass().getClassLoader().getResource("fileimport/sloveniatext.docx");
    if (testDocxResource == null) {
      throw new FileNotFoundException("Test file not found: fileimport/sloveniatext.docx");
    }
    File testDocxFile = Paths.get(testDocxResource.toURI()).toFile();

    ArrayList<Tuv> tuvList = fileImporter.parseDocxFile(testDocxFile);

    assertEquals(18, tuvList.size());
    assertEquals(
        "SloveniaDOCX, officially the Republic of Slovenia, is a country in Central Europe.",
        tuvList.get(0).getSeg());
    assertEquals("sl", tuvList.get(0).getLang());
    assertEquals("", tuvList.get(1).getSeg());
    assertEquals("en", tuvList.get(1).getLang());
  }

  @Test
  public void testParseOdtFile() throws Exception {
    URL testOdtResource = getClass().getClassLoader().getResource("fileimport/sloveniatext.odt");
    if (testOdtResource == null) {
      throw new FileNotFoundException("Test file not found: fileimport/sloveniatext.odt");
    }
    File testOdtFile = Paths.get(testOdtResource.toURI()).toFile();

    ArrayList<Tuv> tuvList = fileImporter.parseOdtFile(testOdtFile);

    assertEquals(18, tuvList.size());
    assertEquals(
        "SloveniaODT, officially the Republic of Slovenia, is a country in Central Europe.",
        tuvList.get(0).getSeg());
    assertEquals("sl", tuvList.get(0).getLang());
    assertEquals("", tuvList.get(1).getSeg());
    assertEquals("en", tuvList.get(1).getLang());
  }
}
