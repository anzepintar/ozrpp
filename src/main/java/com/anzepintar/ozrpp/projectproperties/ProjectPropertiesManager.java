package com.anzepintar.ozrpp.projectproperties;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class ProjectPropertiesManager {

  public static void saveProperties(ProjectProperites properties) throws JAXBException {
    String projectName = properties.getProjectName();

    File projectFolder = new File(
        properties.getProjectRoot().getAbsolutePath());
    projectFolder.mkdirs();

    JAXBContext context = JAXBContext.newInstance(ProjectProperites.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(properties, new File(
        properties.getProjectRoot().getAbsolutePath() + File.separator + projectName + ".project"));
  }

  public static ProjectProperites loadProperties(String filePath) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(ProjectProperites.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    return (ProjectProperites) unmarshaller.unmarshal(new File(filePath));
  }
}
