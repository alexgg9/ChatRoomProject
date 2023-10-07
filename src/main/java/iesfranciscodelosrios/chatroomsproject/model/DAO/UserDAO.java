package iesfranciscodelosrios.chatroomsproject.model.DAO;

import iesfranciscodelosrios.chatroomsproject.model.domain.User;
import iesfranciscodelosrios.chatroomsproject.model.domain.Users;
import iesfranciscodelosrios.chatroomsproject.shareFolder.ConfigManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UserDAO {

    // Ruta del archivo XML que almacenará los datos de los usuarios
    private static final String XML_FILE = "users.xml";

    // Instancia para almacenar los usuarios
    private Users users;

    // Ruta para la carpeta compartida obtenida de la configuración
    String path = ConfigManager.readSharedFolderPath();

    // Constructor: carga los usuarios desde el archivo XML
    public UserDAO(){
        users = loadUsers(path+"users.xml");

    }

    /**
     * Método para agregar un nuevo usuario
     * */
    public void addUser(User newUser) {
        if(this.users==null) {
            this.users = new Users();
        }
        this.users.add(newUser);
    }

    /**
     * Método para guardar los usuarios en un archivo XML
     * */
    public void saveUsers(String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Si el archivo XML ya existe, carga los datos existentes
            Users existingUsers = loadUsers(filename);

            // Agrega los nuevos usuarios a la lista existente
            existingUsers.getUsers().addAll(users.getUsers());

            // Guarda los datos actualizados en el archivo XML
            marshaller.marshal(existingUsers, new File(filename));

            System.out.println("Usuarios guardados correctamente en " + filename);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para cargar los usuarios desde un archivo XML
     * */
    public Users loadUsers(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(Users.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (Users) unmarshaller.unmarshal(file);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new Users(); // Si el archivo no existe, crea una instancia vacía
    }
}
