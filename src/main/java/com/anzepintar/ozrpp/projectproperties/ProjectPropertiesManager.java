package com.anzepintar.ozrpp.projectproperties;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class ProjectPropertiesManager {

  public static void saveProperties(ProjectProperites properties) throws JAXBException {
    String projectName = properties.getProjectName();
    String projectDirectory = properties.getProjectRoot();
    String filePath =
        projectDirectory + File.separator + projectName + File.separator + projectName + ".project";

    File projectFolder = new File(projectDirectory + File.separator + projectName);
    if (!projectFolder.exists()) {
      projectFolder.mkdirs();
    }
    JAXBContext context = JAXBContext.newInstance(ProjectProperites.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(properties, new File(filePath));
  }

  public static ProjectProperites loadProperties(String filePath) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(ProjectProperites.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    return (ProjectProperites) unmarshaller.unmarshal(new File(filePath));

  }
}
