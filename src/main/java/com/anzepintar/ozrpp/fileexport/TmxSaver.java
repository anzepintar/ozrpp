package com.anzepintar.ozrpp.fileexport;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TmxSaver {

  public static void saveTmxData(String filePath, List<String> sourceStrings,
      List<String> targetStrings, List<Boolean> translationStatus)
      throws IOException, ParserConfigurationException, TransformerException {

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document document = dBuilder.newDocument();

    // Create root element
    Element rootElement = document.createElement("tmx");
    document.appendChild(rootElement);

    for (int i = 0; i < sourceStrings.size(); i++) {
      Element tuElement = document.createElement("tu");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        tuElement.setAttribute("changedate", LocalDateTime.now().format((formatter)));


      // Create source element
      Element sourceElement = createTuvElement(document, "source", sourceStrings.get(i));
      tuElement.appendChild(sourceElement);

      // Create target element
      Element targetElement = createTuvElement(document, "target", targetStrings.get(i));
      tuElement.appendChild(targetElement);

      rootElement.appendChild(tuElement);
    }

    // Save the content to XML file
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty("indent", "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    DOMSource domSource = new DOMSource(document);
    StreamResult streamResult = new StreamResult(new File(filePath));
    transformer.transform(domSource, streamResult);
  }

  private static Element createTuvElement(Document doc, String type, String textContent) {
    Element tuvElement = doc.createElement("tuv");
    tuvElement.setAttribute("lang", type);

    Element segElement = doc.createElement("seg");
    segElement.setTextContent(textContent);
    tuvElement.appendChild(segElement);

    return tuvElement;
  }
}