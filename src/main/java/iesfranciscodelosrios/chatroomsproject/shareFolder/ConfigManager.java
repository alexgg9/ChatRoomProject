package iesfranciscodelosrios.chatroomsproject.shareFolder;

import iesfranciscodelosrios.chatroomsproject.model.domain.Config;

import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ConfigManager {

    private String configFilePath;

    public ConfigManager(String configFilePath) {
        this.configFilePath = configFilePath;
    }
    public static String readSharedFolderPath() {
        try {
            // Crear el contexto JAXB y el unmarshaller
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File configFile = new File("config/config.xml");
            Config config = (Config) unmarshaller.unmarshal(configFile);
            return config.getSharedFolderPath();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}
