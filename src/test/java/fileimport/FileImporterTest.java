package fileimport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.anzepintar.ozrpp.converters.tmxconvert.Tu;
import com.anzepintar.ozrpp.fileimport.FileImporter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileImporterTest {

  private FileImporter fileImporter;

  @BeforeEach
  public void setUp() {
    fileImporter = new FileImporter();
  }

  @Test
  public void testParseTxtFile() throws IOException {
    URL testFileUrl = getClass().getClassLoader().getResource("fileimport/sloveniatext.txt");
    if (testFileUrl == null) {
      throw new FileNotFoundException("Test file not found");
    }
    List<Tu> tuList = fileImporter.parseTxtFile(new File(testFileUrl.getFile()));

    for (Tu tu : tuList) {
      System.out.println(tu.getTuv().get(0).getSeg());
    }

    assertEquals(
        "SloveniaTXT, officially the Republic of Slovenia, is a country in Central Europe.",
        tuList.get(0).getTuv().get(0).getSeg());
    assertEquals(
        "It is bordered by Italy to the west, Austria to the north, Hungary to the northeast, Croatia to the southeast, and the Adriatic Sea to the southwest.",
        tuList.get(1).getTuv().get(0).getSeg());
    assertEquals(
        "Slovenia is mostly mountainous and forested, covers 20,271 square kilometres (7,827 sq mi), and has a population of 2.1 million (2,108,708 people).",
        tuList.get(2).getTuv().get(0).getSeg());
  }

  @Test
  public void testParseDocxFile() throws IOException {
    URL testFileUrl = getClass().getClassLoader().getResource("fileimport/sloveniatext.docx");
    if (testFileUrl == null) {
      throw new FileNotFoundException("Test file not found");
    }
    File file = new File("C:\\Users\\anze\\dev\\projects\\ozrpp\\src\\test\\resources\\fileimport\\sloveniatext.docx");
    List<Tu> tuList = fileImporter.parseTxtFile(file);

    assertEquals(
        "SloveniaTXT, officially the Republic of Slovenia, is a country in Central Europe.",
        tuList.get(0).getTuv().get(0).getSeg());
    assertEquals(
        "It is bordered by Italy to the west, Austria to the north, Hungary to the northeast, Croatia to the southeast, and the Adriatic Sea to the southwest.",
        tuList.get(1).getTuv().get(0).getSeg());
    assertEquals(
        "Slovenia is mostly mountainous and forested, covers 20,271 square kilometres (7,827 sq mi), and has a population of 2.1 million (2,108,708 people).",
        tuList.get(2).getTuv().get(0).getSeg());
  }

  @Test
  public void testParseOdtFile() throws Exception {
    URL testFileUrl = getClass().getClassLoader().getResource("fileimport/sloveniatext.odt");
    if (testFileUrl == null) {
      throw new FileNotFoundException("Test file not found");
    }
    List<Tu> tuList = fileImporter.parseTxtFile(new File(testFileUrl.getFile()));
    for (Tu tu : tuList) {
      System.out.println(tu.getTuv().get(0).getSeg());
    }
    assertEquals(
        "SloveniaTXT, officially the Republic of Slovenia, is a country in Central Europe.",
        tuList.get(0).getTuv().get(0).getSeg());
    assertEquals(
        "It is bordered by Italy to the west, Austria to the north, Hungary to the northeast, Croatia to the southeast, and the Adriatic Sea to the southwest.",
        tuList.get(1).getTuv().get(0).getSeg());
    assertEquals(
        "Slovenia is mostly mountainous and forested, covers 20,271 square kilometres (7,827 sq mi), and has a population of 2.1 million (2,108,708 people).",
        tuList.get(2).getTuv().get(0).getSeg());
  }

  @Test
  public void testImportToTmx_txtFile() throws Exception {
    // Prepare a sample .txt file

    // Test the fileImporter.importToTmx method with the sample .txt file

    // Check that the TMX file was created and its content is as expected
  }

  @Test
  public void testImportToTmx_docxFile() throws Exception {
    // Prepare a sample .docx file using Apache POI XWPF

    // Test the fileImporter.importToTmx method with the sample .docx file

    // Check that the TMX file was created and its content is as expected
  }

  @Test
  public void testImportToTmx_odtFile() throws Exception {
    // Prepare a sample .odt file using ODF Toolkit

    // Test the fileImporter.importToTmx method with the sample .odt file

    // Check that the TMX file was created and its content is as expected
  }

  @Test
  public void testImportToTmx_unsupportedFile() {
    File unsupportedFile = new File("unsupported.xyz");

    assertThrows(IllegalArgumentException.class, () -> fileImporter.importToTmx(unsupportedFile));
  }
}
