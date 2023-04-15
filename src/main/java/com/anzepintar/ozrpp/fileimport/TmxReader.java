package com.anzepintar.ozrpp.fileimport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TmxReader {

  public static String[] readTmxFile(String filePath) {
    String[] strings;
    try {
      File file = new File(filePath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(file);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getElementsByTagName("tu");
      List<String> stringList = new ArrayList<String>();
      for (int i = 0; i < nodeList.getLength(); i++) {
        Element element = (Element) nodeList.item(i);
        String text = element.getElementsByTagName("seg").item(0).getTextContent();
        stringList.add(text);
      }
      strings = stringList.toArray(new String[stringList.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      strings = new String[0];
    }
    return strings;
  }
}