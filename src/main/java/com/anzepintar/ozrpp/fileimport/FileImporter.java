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
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileImporter {

  public void importToTmx(File file) throws Exception {
    JAXBContext jc = JAXBContext.newInstance("com.anzepintar.ozrpp.converters.tmxconvert");
    ObjectFactory objFactory = new ObjectFactory();

    Tmx tmxFile = objFactory.createTmx();

    Header header = new Header();
    header.setCreationtool("ozrpp");
    tmxFile.setHeader(header);
    Body body = new Body();
    tmxFile.setBody(body);

    String fileName = file.getName();
    int lastIndex = fileName.lastIndexOf(".");
    if (lastIndex == -1) {
      throw new IllegalArgumentException("File has no extension: " + fileName);
    }
    String fileExtension = fileName.substring(lastIndex + 1);

    switch (fileExtension) {
      case "txt":
        body.getTu().addAll(parseTxtFile(file));
        break;
      case "docx":
        body.getTu().addAll(parseDocxFile(file));
        break;
      case "odt":
        body.getTu().addAll(parseOdtFile(file));
        break;
      default:
        throw new IllegalArgumentException("Unsupported file format: " + fileExtension);
    }

    Marshaller m = jc.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    File savedfile = new File(
        Ozrpp.projectProperites.getProjectRoot().getAbsolutePath() + "/tmx/" + fileName + ".tmx");
    m.marshal(tmxFile, savedfile);
  }

  public List<Tu> parseTxtFile(File file) throws IOException {
    List<Tu> tuList = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(file));

    String line;
    while ((line = br.readLine()) != null) {
      Tu tu = new Tu();

      Tuv tuv1 = new Tuv();
      tuv1.setSeg(line);
      tuv1.setLang(Ozrpp.projectProperites.getSourceLang());
      tu.getTuv().add(tuv1);


      Tuv tuv2 = new Tuv();
      tuv2.setSeg("");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
      tuv2.setCreationdate(LocalDateTime.now().format((formatter)));
      tuv2.setLang(Ozrpp.projectProperites.getTargetLang());
      tu.getTuv().add(tuv2);


      tuList.add(tu);
    }
    return tuList;
  }

  public List<Tu> parseDocxFile(File file) throws IOException {
    List<Tu> tuList = new ArrayList<>();

    FileInputStream fileInputStream = new FileInputStream(file);
    XWPFDocument document = new XWPFDocument(fileInputStream);

    for (XWPFParagraph paragraph : document.getParagraphs()) {
      Tu tu = new Tu();

      Tuv tuv1 = new Tuv();
      tuv1.setSeg(paragraph.getText());
      tuv1.setLang(Ozrpp.projectProperites.getSourceLang());

      Tuv tuv2 = new Tuv();
      tuv2.setSeg("");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
      tuv2.setCreationdate(LocalDateTime.now().format((formatter)));
      tuv2.setLang(Ozrpp.projectProperites.getTargetLang());
      tu.getTuv().add(tuv1);
      tu.getTuv().add(tuv2);

      tuList.add(tu);
    }
    document.close();
    fileInputStream.close();
    return tuList;
  }

  public List<Tu> parseOdtFile(File file) throws Exception {
    List<Tu> tuList = new ArrayList<>();

    OdfTextDocument document = OdfTextDocument.loadDocument(file);
    OfficeTextElement fileRoot = document.getContentRoot();
    NodeList children = fileRoot.getChildNodes();

    for (int i = 1; i < children.getLength(); i++) {
      Node child = children.item(i);
      String text = child.getTextContent();

      Tu tu = new Tu();

      Tuv tuv1 = new Tuv();
      tuv1.setSeg(text);
      tuv1.setLang(Ozrpp.projectProperites.getSourceLang());


      Tuv tuv2 = new Tuv();
      tuv2.setSeg("");
      tuv2.setLang(Ozrpp.projectProperites.getTargetLang());
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
      tuv2.setCreationdate(LocalDateTime.now().format((formatter)));
      tu.getTuv().add(tuv1);
      tu.getTuv().add(tuv2);

      tuList.add(tu);
    }
    return tuList;
  }

}
