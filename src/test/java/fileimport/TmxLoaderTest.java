package fileimport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.anzepintar.ozrpp.fileimport.TmxLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class TmxLoaderTest {

  private String testFilePath;

  @BeforeEach
  public void setUp() throws FileNotFoundException {
    URL testFileUrl = getClass().getClassLoader().getResource("fileimport/sloveniatext.tmx");
    if (testFileUrl == null) {
      throw new FileNotFoundException("Test file not found");
    }
    testFilePath = testFileUrl.getFile();
  }

  @Test
  public void testGetTmxSourceStrings() throws IOException, SAXException, ParserConfigurationException {
    List<String> sourceStrings = TmxLoader.getTmxSourceStrings(testFilePath);
    assertEquals(3, sourceStrings.size());
    assertTrue(sourceStrings.contains("Hello"));
    assertTrue(sourceStrings.contains("Goodbye"));
    assertTrue(sourceStrings.contains("Thank you"));
  }

  @Test
  public void testGetTmxTargetStrings() throws IOException, SAXException, ParserConfigurationException {
    List<String> targetStrings = TmxLoader.getTmxTargetStrings(testFilePath);
    assertEquals(3, targetStrings.size());
    assertTrue(targetStrings.contains("Hola"));
    assertTrue(targetStrings.contains("Adiós"));
    assertTrue(targetStrings.contains("Gracias"));
  }

  @Test
  public void testGetTmxStatus() throws IOException, SAXException, ParserConfigurationException {
    List<String> translationStatus = TmxLoader.getTmxStatus(testFilePath);
    assertEquals(3, translationStatus.size());
    assertEquals("translated", translationStatus.get(0));
    assertEquals("untranslated", translationStatus.get(1));
    assertEquals("translated", translationStatus.get(2));
  }
}
