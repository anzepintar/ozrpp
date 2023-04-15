package com.anzepintar.ozrpp.fileimport;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.converters.tmxconvert.Body;
import com.anzepintar.ozrpp.converters.tmxconvert.Header;
import com.anzepintar.ozrpp.converters.tmxconvert.ObjectFactory;
import com.anzepintar.ozrpp.converters.tmxconvert.Tmx;
import com.anzepintar.ozrpp.converters.tmxconvert.Tu;
import com.anzepintar.ozrpp.converters.tmxconvert.Tuv;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileImporter {

  public void importToTmx(File file, String importFormat)
      throws Exception {

    JAXBContext jc = JAXBContext.newInstance("com.anzepintar.ozrpp.converters.tmxconvert");
    ObjectFactory objFactory = new ObjectFactory();

    Tmx tmxFile = objFactory.createTmx();

    Header header = new Header();
    header.setCreationtool("ozrpp");
    tmxFile.setHeader(header);
    Body body = new Body();
    tmxFile.setBody(body);

    Tu tu = new Tu();

    switch (importFormat) {
      case "txt":
        tu.getTuv().addAll(parseTxtFile(file));
        break;
      case "docx":
        tu.getTuv().addAll(parseDocxFile(file));
        break;
      case "odt":
        tu.getTuv().addAll(parseOdtFile(file));
        break;
    }


    body.getTu().add(tu);
    tmxFile.setBody(body);

    // https://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/1.5/tutorial/doc/JAXBUsing3.html
    Marshaller m = jc.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    m.marshal(tmxFile, System.out);

    File savedfile = new File( Ozrpp.projectProperites.getProjectRoot() + file.getName() + ".tmx");
    m.marshal(tmxFile, savedfile);
  }

  public ArrayList<Tuv> parseTxtFile(File file) throws IOException {
    ArrayList<Tuv> tuv = new ArrayList<Tuv>();
    BufferedReader br = new BufferedReader(new FileReader(file));

    String line;
    while ((line = br.readLine()) != null) {
      Tuv sourceSegment = new Tuv();
      sourceSegment.setSeg(line);
      sourceSegment.setLang("sl");
      //sourceSegment.setLang(ProjectPropertiesController.projectProperties.getTarget_lang());
      Tuv targetSegment = new Tuv();
      targetSegment.setSeg("");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
      targetSegment.setCreationdate(LocalDateTime.now().format((formatter)));
      //targetSegment.setLang(ProjectPropertiesController.projectProperties.getTarget_lang());
      targetSegment.setLang("en");
      tuv.add(sourceSegment);
      tuv.add(targetSegment);

    }
    return tuv;
  }

  public ArrayList<Tuv> parseDocxFile(File file) throws IOException {
    ArrayList<Tuv> tuv = new ArrayList<>();

    FileInputStream fileInputStream = new FileInputStream(file);
    XWPFDocument document = new XWPFDocument(fileInputStream);

    for (XWPFParagraph paragraph : document.getParagraphs()) {
      Tuv sourceSegment = new Tuv();
      sourceSegment.setSeg(paragraph.getText());
      sourceSegment.setLang("sl");
      Tuv targetSegment = new Tuv();
      targetSegment.setSeg("");
      targetSegment.setLang("en");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
      targetSegment.setCreationdate(LocalDateTime.now().format((formatter)));
      tuv.add(sourceSegment);
      tuv.add(targetSegment);
    }
    document.close();
    fileInputStream.close();
    return tuv;
  }

  public ArrayList<Tuv> parseOdtFile(File file) throws Exception {
    //https://odftoolkit.org/api/odfdom/index.html#The_ODF_Document_API
    ArrayList<Tuv> tuv = new ArrayList<>();

    OdfTextDocument document = OdfTextDocument.loadDocument(file);
    OfficeTextElement fileRoot = document.getContentRoot();
    NodeList children = fileRoot.getChildNodes();

    for (int i = 1; i < children.getLength(); i++) {
      Node child = children.item(i);
      String text = child.getTextContent();

      Tuv sourceSegment = new Tuv();
      sourceSegment.setSeg(text);
      sourceSegment.setLang("sl");
      Tuv targetSegment = new Tuv();
      targetSegment.setSeg("");
      targetSegment.setLang("en");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
      targetSegment.setCreationdate(LocalDateTime.now().format((formatter)));
      tuv.add(sourceSegment);
      tuv.add(targetSegment);
    }
    return tuv;
  }

}
