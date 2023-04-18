package fileexport;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.fileexport.FileExporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileExporterTest {
  private File testFile;

  @BeforeEach
  public void setUp() throws IOException {
    testFile = new File("src/test/resources/fileexport/sloveniatext.docx.tmx");
    Ozrpp.projectProperites.setProjectRoot(new File("src/test/resources/fileexport"));
  }

  @AfterEach
  public void tearDown() {
    testFile = null;
  }

  @Test
  public void testGetTmxTargetStrings() throws Exception {
    List<String> targetStrings = FileExporter.getTmxTargetStrings(testFile.getAbsolutePath());
    assertNotNull(targetStrings, "Target strings should not be null");
    assertFalse(targetStrings.isEmpty(), "Target strings should not be empty");
  }

  @Test
  public void testSaveToTarget() throws Exception {
    List<String> targetStrings = FileExporter.getTmxTargetStrings(testFile.getAbsolutePath());
    FileExporter.saveToTarget(testFile, "docx");

    File outputFile = new File(Ozrpp.projectProperites.getProjectRoot().getAbsolutePath()
        + "/export/" + testFile.getName().substring(0, testFile.getName().lastIndexOf("."))
        + ".docx");

    assertTrue(outputFile.exists(), "Output file should exist");

    Files.deleteIfExists(outputFile.toPath());
  }
}
