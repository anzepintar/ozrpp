package com.anzepintar.ozrpp.projectproperties;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectPropertiesTest {

  private JAXBContext context;

  @BeforeEach
  public void init() throws JAXBException {
    this.context = JAXBContext.newInstance(ProjectProperties.class);
  }

  @Test
  public void serialization() throws JAXBException {
    Marshaller marshaller = this.context.createMarshaller();
    // formatiranje - https://stackoverflow.com/questions/46708498/jaxb-marshaller-indentation
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(new ProjectProperties("aa", "bb", "cc", "dd"), new File("target/project.xml"));

    Unmarshaller unmarshaller = this.context.createUnmarshaller();
    Object unmarshalled = unmarshaller.unmarshal(new File("target" + File.separator + "project.xml"));
    System.out.println("a = " + unmarshalled);
  }
}