package com.anzepintar.ozrpp.fileexport;

import com.anzepintar.ozrpp.Ozrpp;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TmxToXliffSaver {

  public static void saveTmxToXliff(File tmxFile, File xliffFile) throws Exception {
    String tmxContent = Files.readString(tmxFile.toPath(), StandardCharsets.UTF_8);

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document tmxDoc = dBuilder.parse(new ByteArrayInputStream(tmxContent.getBytes(StandardCharsets.UTF_8)));

    DocumentBuilderFactory xliffDocFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder xliffDocBuilder = xliffDocFactory.newDocumentBuilder();
    Document xliffDoc = xliffDocBuilder.newDocument();

    Element xliffRoot = xliffDoc.createElement("xliff");
    xliffRoot.setAttribute("version", "1.2");
    xliffRoot.setAttribute("xmlns", "urn:oasis:names:tc:xliff:document:1.2");
    xliffDoc.appendChild(xliffRoot);

    Element fileElement = xliffDoc.createElement("file");
    fileElement.setAttribute("source-language", Ozrpp.projectProperites.getSourceLang());
    fileElement.setAttribute("target-language", Ozrpp.projectProperites.getTargetLang());
    fileElement.setAttribute("datatype", "plaintext");
    fileElement.setAttribute("original", "tmxFile");
    xliffRoot.appendChild(fileElement);

    Element bodyElement = xliffDoc.createElement("body");
    fileElement.appendChild(bodyElement);

    NodeList tuNodes = tmxDoc.getElementsByTagName("tu");
    for (int i = 0; i < tuNodes.getLength(); i++) {
      Node tuNode = tuNodes.item(i);
      NodeList tuvNodes = tuNode.getChildNodes();

      String sourceText = "";
      String targetText = "";

      for (int j = 0; j < tuvNodes.getLength(); j++) {
        Node tuvNode = tuvNodes.item(j);

        if (tuvNode.getNodeType() == Node.ELEMENT_NODE) {
          Element tuvElement = (Element) tuvNode;
          String lang = tuvElement.getAttribute("lang");

          if ("source".equals(lang)) {
            sourceText = tuvElement.getElementsByTagName("seg").item(0).getTextContent();
          } else if ("target".equals(lang)) {
            targetText = tuvElement.getElementsByTagName("seg").item(0).getTextContent();
          }
        }
      }

      Element transUnitElement = xliffDoc.createElement("trans-unit");
      transUnitElement.setAttribute("id", String.valueOf(i + 1));
      bodyElement.appendChild(transUnitElement);

      Element sourceElement = xliffDoc.createElement("source");
      sourceElement.setTextContent(sourceText);
      transUnitElement.appendChild(sourceElement);

      Element targetElement = xliffDoc.createElement("target");
      targetElement.setTextContent(targetText);
      transUnitElement.appendChild(targetElement);
    }

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    transformer.transform(new DOMSource(xliffDoc), new StreamResult(xliffFile));
  }
}