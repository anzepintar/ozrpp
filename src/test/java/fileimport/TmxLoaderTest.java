package fileimport;

import com.anzepintar.ozrpp.fileimport.TmxLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TmxLoaderTest {

  private String tmxFilePath;

  @BeforeEach
  public void setUp() {
    tmxFilePath = Paths.get("src", "test", "resources", "fileimport", "sloveniatext.tmx").toString();
  }

  @Test
  public void testGetTmxSourceStrings() throws IOException, SAXException, ParserConfigurationException {
    List<String> sourceStrings = TmxLoader.getTmxSourceStrings(tmxFilePath);
    assertEquals("SloveniaODT, officially the Republic of Slovenia, is a country in Central Europe.", sourceStrings.get(0));
  }

  @Test
  public void testGetTmxTargetStrings() throws IOException, SAXException, ParserConfigurationException {
    List<String> targetStrings = TmxLoader.getTmxTargetStrings(tmxFilePath);
    assertEquals("", targetStrings.get(0));
  }

  @Test
  public void testGetTmxStatus() throws IOException, SAXException, ParserConfigurationException {
    List<Boolean> translationStatus = TmxLoader.getTmxStatus(tmxFilePath);
    assertEquals(false, translationStatus.get(0));
  }
}
