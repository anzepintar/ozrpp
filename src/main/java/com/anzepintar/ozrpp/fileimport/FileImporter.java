package com.anzepintar.ozrpp.fileimport;

import com.anzepintar.ozrpp.Ozrpp;
import com.anzepintar.ozrpp.generatedclasses.tmxgenerated.Body;
import com.anzepintar.ozrpp.generatedclasses.tmxgenerated.Header;
import com.anzepintar.ozrpp.generatedclasses.tmxgenerated.ObjectFactory;
import com.anzepintar.ozrpp.generatedclasses.tmxgenerated.Tmx;
import com.anzepintar.ozrpp.generatedclasses.tmxgenerated.Tu;
import com.anzepintar.ozrpp.generatedclasses.tmxgenerated.Tuv;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FileImporter {

  public void importToTmx(File file) throws Exception {
    JAXBContext jc = JAXBContext.newInstance("com.anzepintar.ozrpp.generatedclasses.tmxgenerated");
    ObjectFactory objFactory = new ObjectFactory();

    Tmx tmxFile = objFactory.createTmx();

    Header header = new Header();
    header.setCreationtool("ozrpp");
    tmxFile.setHeader(header);
    Body body = new Body();
    tmxFile.setBody(body);
    header.setCreationtool("Ozrpp");
    header.setDatatype("PlainText");
    header.setCreationtoolversion("1.0");
    header.setSegtype("sentence");
    header.setSrclang(Ozrpp.projectProperites.getSourceLang());
    header.setAdminlang("EN-US");
    tmxFile.setHeader(header);

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
    byte[] bytes = Files.readAllBytes(file.toPath());
    String content = new String(bytes, StandardCharsets.UTF_8);

    String[] sentences = content.split("(?<=[.!?])\\s+");
    for (String sentence : sentences) {
      if (sentence.isEmpty()) {
        continue;
      }
      Tuv tuv1 = createSourceSegment(sentence);
      Tuv tuv2 = createTargetSegment();
      Tu tu = new Tu();
      tu.getTuv().add(tuv1);
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
      if (paragraph.isEmpty()) {
        continue;
      }
      Tu tu = new Tu();

      Tuv tuv1 = createSourceSegment(paragraph.getText());
      Tuv tuv2 = createTargetSegment();

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
    OdfTextDocument doc = OdfTextDocument.loadDocument(file);
    Element contentRoot = doc.getContentDom().getDocumentElement();
    NodeList paragraphs = contentRoot.getElementsByTagName("text:p");
    String[] sentences = new String[paragraphs.getLength()];
    for (int i = 0; i < paragraphs.getLength(); i++) {
      Element paragraph = (Element) paragraphs.item(i);
      if (paragraph.getTextContent().isEmpty()) {
        continue;
      }
      sentences[i] = paragraph.getTextContent();
    }
    doc.close();
    for (String sentence : sentences) {
      Tuv tuv1 = createSourceSegment(sentence);
      Tuv tuv2 = createTargetSegment();
      Tu tu = new Tu();
      tu.getTuv().add(tuv1);
      tu.getTuv().add(tuv2);
      tuList.add(tu);
    }
    return tuList;
  }


  private Tuv createSourceSegment(String text) {
    Tuv sourceSegment = new Tuv();
    sourceSegment.setSeg(text);
    sourceSegment.setLang(Ozrpp.projectProperites.getSourceLang());
    return sourceSegment;
  }

  private Tuv createTargetSegment() {
    Tuv targetSegment = new Tuv();
    targetSegment.setSeg("");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
    targetSegment.setCreationdate(LocalDateTime.now().format((formatter)));
    targetSegment.setLang(Ozrpp.projectProperites.getTargetLang());
    return targetSegment;
  }
}
