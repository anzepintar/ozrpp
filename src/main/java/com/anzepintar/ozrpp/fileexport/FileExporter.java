package com.anzepintar.ozrpp.fileexport;

import com.anzepintar.ozrpp.Ozrpp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileExporter {

  public static void saveToTarget(File tmxFile, String desiredExtension) throws Exception {

    List<String> targetList = getTmxTargetStrings(tmxFile.getAbsolutePath());

    String fileName = tmxFile.getName();
    int lastIndex = fileName.lastIndexOf(".");
    String outputFileName = lastIndex == -1 ? fileName : fileName.substring(0, lastIndex);

    switch (desiredExtension.toLowerCase()) {
      case "txt":
        saveAsTxt(outputFileName, targetList);
        break;
      case "docx":
        saveAsDocx(outputFileName, targetList);
        break;
      case "odt":
        saveAsOdt(outputFileName, targetList);
        break;
      default:
        throw new IllegalArgumentException("Unsupported file format: " + desiredExtension);
    }
  }

  private static void saveAsTxt(String fileName, List<String> strings) throws IOException {
    StringBuilder content = new StringBuilder();
    for (String str : strings) {
      content.append(str);
    }
    File outputFile = new File(
        Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/target/" + fileName
            + ".txt");
    outputFile.getParentFile().mkdirs();
    Files.write(outputFile.toPath(), content.toString().getBytes(StandardCharsets.UTF_8));
  }

  private static void saveAsDocx(String fileName, List<String> strings) throws IOException {
    XWPFDocument document = new XWPFDocument();

    for (String str : strings) {
      XWPFParagraph paragraph = document.createParagraph();
      paragraph.createRun().setText(str);
    }

    File outputFile = new File(
        Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/target/" + fileName
            + ".docx");
    outputFile.getParentFile().mkdirs();
    try (FileOutputStream out = new FileOutputStream(outputFile)) {
      document.write(out);
    }
    document.close();
  }

  private static void saveAsOdt(String fileName, List<String> strings) throws Exception {
    OdfTextDocument doc = OdfTextDocument.newTextDocument();
    for (String str : strings) {
      TextPElement paragraph = doc.newParagraph();
      paragraph.setTextContent(str);
      doc.getContentRoot().appendChild(paragraph);
    }

    File outputFile = new File(
        Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/target/" + fileName
            + ".odt");
    outputFile.getParentFile().mkdirs();
    doc.save(outputFile);
    doc.close();
  }

  public static List<String> getTmxTargetStrings(String filePath)
      throws IOException, SAXException, ParserConfigurationException {
    File file = new File(filePath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(file);
    doc.getDocumentElement().normalize();

    NodeList tuNodes = doc.getElementsByTagName("tu");
    List<String> targetStrings = new ArrayList<>();

    for (int i = 0; i < tuNodes.getLength(); i++) {
      Node tuNode = tuNodes.item(i);
      if (tuNode.getNodeType() == Node.ELEMENT_NODE) {
        Element tuElement = (Element) tuNode;
        NodeList tuvNodes = tuElement.getElementsByTagName("tuv");
        Element tuvElement = (Element) tuvNodes.item(1);
        String targetText = tuvElement.getElementsByTagName("seg").item(0).getTextContent();
        targetStrings.add(targetText);


      }
    }
    return targetStrings;
  }

}