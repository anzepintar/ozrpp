package com.anzepintar.ozrpp.fileimport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TmxLoader {

  public static List<String> getTmxSourceStrings(String filePath)
      throws IOException, SAXException, ParserConfigurationException {
    File file = new File(filePath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(file);
    doc.getDocumentElement().normalize();

    NodeList tuNodes = doc.getElementsByTagName("tu");
    List<String> sourceStrings = new ArrayList<>();

    for (int i = 0; i < tuNodes.getLength(); i++) {
      Node tuNode = tuNodes.item(i);

      if (tuNode.getNodeType() == Node.ELEMENT_NODE) {
        Element tuElement = (Element) tuNode;
        NodeList tuvNodes = tuElement.getElementsByTagName("tuv");

        for (int j = 0; j < tuvNodes.getLength(); j++) {
          Element tuvElement = (Element) tuvNodes.item(j);

          String sourceText = tuvElement.getElementsByTagName("seg").item(0).getTextContent();
          sourceStrings.add(sourceText);

        }
      }
    }
    var d = sourceStrings;
    return sourceStrings;
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

        if (tuvNodes.getLength() == 2) {
          Element targetElement = (Element) tuvNodes.item(1);
          String targetText = targetElement.getElementsByTagName("seg").item(0).getTextContent();
          targetStrings.add(targetText);
        }
      }
    }
    return targetStrings;
  }

  public static List<String> getTmxStatus(String filePath)
      throws IOException, SAXException, ParserConfigurationException {
    File file = new File(filePath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(file);
    doc.getDocumentElement().normalize();

    NodeList tuNodes = doc.getElementsByTagName("tu");
    List<String> translationStatus = new ArrayList<>();

    for (int i = 0; i < tuNodes.getLength(); i++) {
      Node tuNode = tuNodes.item(i);

      if (tuNode.getNodeType() == Node.ELEMENT_NODE) {
        Element tuElement = (Element) tuNode;
        NodeList tuvNodes = tuElement.getElementsByTagName("tuv");

        if (tuvNodes.getLength() == 2) {
          Element targetElement = (Element) tuvNodes.item(1);
          String status = targetElement.hasAttribute("changedate") ? "translated" : "untranslated";
          translationStatus.add(status);
        }
      }
    }
    return translationStatus;
  }
}