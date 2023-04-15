package fileimport;

import com.anzepintar.ozrpp.fileimport.TmxReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TmxReaderTest {

  @Test
  public void testReadTmxFile() {
    String filePath = "src/test/resources/fileimport/sloveniatext.tmx";
    String[] expectedStrings = {"SloveniaTXT, officially the Republic of Slovenia, is a country in Central Europe."};


    String[] strings = TmxReader.readTmxFile(filePath);

    Assertions.assertArrayEquals(expectedStrings, strings);
  }
}