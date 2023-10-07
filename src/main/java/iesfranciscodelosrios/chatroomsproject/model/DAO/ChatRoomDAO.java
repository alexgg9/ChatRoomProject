package iesfranciscodelosrios.chatroomsproject.model.DAO;
import iesfranciscodelosrios.chatroomsproject.model.domain.ChatRoom;
import iesfranciscodelosrios.chatroomsproject.model.domain.Message;
import iesfranciscodelosrios.chatroomsproject.model.domain.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Date;



public class ChatRoomDAO {
    private ChatRoom chatRoom;

    //Constructor: carga una sala de chat vacía o nueva
    public ChatRoomDAO() {
        chatRoom = loadChat("");
    }

    /**
     * Método para crear una nueva sala de chat
     * */
    public void createChatRoom(String chatRoomName) {

        //Verifica si la sala de chat ya existe
        if (isChatRoomExist(chatRoomName)) {
            System.out.println("La sala de chat ya existe.");
            return;
        }
        //Crea una nueva sala de chat y la guarda
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setName(chatRoomName);
        saveChat(chatRoomName);

        System.out.println("Sala de chat '" + chatRoomName + "' creada exitosamente.");
    }

    /**
     * Método para verificar si una sala de chat ya existe
     * */
    private boolean isChatRoomExist(String chatRoomName) {
        String filename = chatRoomName + ".xml";
        File file = new File(filename);
        return file.exists();
    }

    /**
     * Método para que un usuario se una a la sala de chat
     * */
    public void joinChat(String nickname) {
        User user = new User();
        user.setNickname(nickname);
        chatRoom.getUsers().add(user);
    }

    /**
     * Método para enviar un mensaje a la sala de chat
     * */
    public void sendMessage(String sender, String message) {
        Message chatMessage = new Message();
        chatMessage.setSender(sender);
        chatMessage.setContent(message);
        chatMessage.setTimestamp(new Date().toString());
        chatRoom.getMessages().add(chatMessage);
    }

    /**
     * Método para que un usuario abandone la sala de chat
     * */
    public void leaveChat(String nickname) {
        chatRoom.getUsers().removeIf(user -> user.getNickname().equals(nickname));
    }

    /**
     * Método para guardar la sala de chat en un archivo XML
     * */
    public void saveChat(String chatRoomName) {
        try {
            String filename = chatRoomName + ".xml";

            // Configura el contexto de JAXB y el marshaller
            JAXBContext context = JAXBContext.newInstance(ChatRoom.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ChatRoom existingChatRoom = loadChat(filename);

            // Agregar los nuevos datos al ChatRoom existente
            existingChatRoom.getUsers().addAll(chatRoom.getUsers());
            existingChatRoom.getMessages().addAll(chatRoom.getMessages());

            // Guardar los datos actualizados en el archivo XML
            marshaller.marshal(existingChatRoom, new File(filename));

            System.out.println("Chat guardado correctamente en " + filename);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }



    /**
     * Método para cargar una sala de chat desde un archivo XML
     * */
    public ChatRoom loadChat(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(ChatRoom.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (ChatRoom) unmarshaller.unmarshal(file);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ChatRoom();  //Devuelve una nueva sala de chat si el archivo no existe
    }
}