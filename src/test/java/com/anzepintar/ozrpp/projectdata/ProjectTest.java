package com.anzepintar.ozrpp.projectdata;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {

  private JAXBContext context;

  @BeforeEach
  public void init() throws JAXBException {
    this.context = JAXBContext.newInstance(Project.class);
  }

  @Test
  public void serialization() throws JAXBException {
    Marshaller marshaller = this.context.createMarshaller();
    // formatiranje - https://stackoverflow.com/questions/46708498/jaxb-marshaller-indentation
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(new Project("aa", "bb", "cc", "dd"), new File("project.xml"));

    Unmarshaller unmarshaller = this.context.createUnmarshaller();
    Object unmarshalled = unmarshaller.unmarshal(new File("project.xml"));
    System.out.println("a = " + unmarshalled);
  }
}