package iesfranciscodelosrios.chatroomsproject.shareFolder;

import iesfranciscodelosrios.chatroomsproject.model.domain.Config;

import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ConfigManager {

    public static String readSharedFolderPath() {
        try {
            // Crear el contexto JAXB y el unmarshaller
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Leer el archivo de configuración
            File configFile = new File("config/config.xml");
            Config config = (Config) unmarshaller.unmarshal(configFile);

            // Obtener la ruta compartida del objeto Config
            return config.getSharedFolderPath();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Leer la ruta de la carpeta compartida desde el archivo de configuración
        String sharedFolderPath = readSharedFolderPath();

        if (sharedFolderPath != null) {
            System.out.println("Ruta de carpeta compartida: " + sharedFolderPath);
        } else {
            System.out.println("No se pudo leer la ruta de la carpeta compartida.");
        }
    }

}
