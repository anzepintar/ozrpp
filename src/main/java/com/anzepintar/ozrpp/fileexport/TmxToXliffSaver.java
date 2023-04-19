package com.anzepintar.ozrpp.fileexport;

import com.anzepintar.ozrpp.Ozrpp;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TmxToXliffSaver {

  public static void saveTmxToXliff(File tmxFile, File xliffFile) throws Exception {
    String tmxContent = Files.readString(tmxFile.toPath(), StandardCharsets.UTF_8);

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document tmxDoc = dBuilder.parse(
        new ByteArrayInputStream(tmxContent.getBytes(StandardCharsets.UTF_8)));

    DocumentBuilderFactory xliffDocFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder xliffDocBuilder = xliffDocFactory.newDocumentBuilder();
    Document xliffDoc = xliffDocBuilder.newDocument();

    Element xliffRoot = xliffDoc.createElement("xliff");
    xliffRoot.setAttribute("version", "2");
    xliffRoot.setAttribute("xmlns", "urn:oasis:names:tc:xliff:document:1.2");
    xliffDoc.appendChild(xliffRoot);

    Element fileElement = xliffDoc.createElement("file");
    fileElement.setAttribute("source-language", Ozrpp.projectProperites.getSourceLang());
    fileElement.setAttribute("target-language", Ozrpp.projectProperites.getSourceLang());
    fileElement.setAttribute("datatype", "plaintext");
    fileElement.setAttribute("original", "tmxFile");
    xliffRoot.appendChild(fileElement);

    Element bodyElement = xliffDoc.createElement("body");
    fileElement.appendChild(bodyElement);
    List<String> sourceStrings = getTmxSourceStrings(tmxFile.getAbsolutePath());
    List<String> targetStrings = getTmxTargetStrings(tmxFile.getAbsolutePath());

    for (int i = 0; i < sourceStrings.size(); i++) {

      Element transUnitElement = xliffDoc.createElement("trans-unit");
      transUnitElement.setAttribute("id", String.valueOf(i + 1));
      bodyElement.appendChild(transUnitElement);

      Element sourceElement = xliffDoc.createElement("source");
      sourceElement.setTextContent(sourceStrings.get(i));
      transUnitElement.appendChild(sourceElement);

      Element targetElement = xliffDoc.createElement("target");
      targetElement.setTextContent(targetStrings.get(i));
      transUnitElement.appendChild(targetElement);
    }

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    transformer.transform(new DOMSource(xliffDoc), new StreamResult(xliffFile));
  }


  private static List<String> getTmxTargetStrings(String filePath)
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

  private static List<String> getTmxSourceStrings(String filePath)
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