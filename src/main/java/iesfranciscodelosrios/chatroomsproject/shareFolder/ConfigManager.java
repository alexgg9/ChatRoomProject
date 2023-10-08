package iesfranciscodelosrios.chatroomsproject.shareFolder;

import iesfranciscodelosrios.chatroomsproject.model.domain.Config;

import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ConfigManager {

    private String configFilePath;

    // Constructor para inicializar la ruta del archivo de configuración
    public ConfigManager(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    // Método para leer la ruta de la carpeta compartida desde el archivo de configuración
    public static String readSharedFolderPath() {
        try {
            // Crear el contexto JAXB y el unmarshaller
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Se utiliza la ruta del archivo de configuración proporcionada en el constructor
            File configFile = new File("config/config.xml");

            // Deserializar el archivo de configuración en un objeto Config
            Config config = (Config) unmarshaller.unmarshal(configFile);

            // Devolver la ruta de la carpeta compartida desde el objeto Config
            return config.getSharedFolderPath();
        } catch (JAXBException e) {
            e.printStackTrace(); // Imprimir información de la excepción en caso de error
            return null;
        }
    }

}
