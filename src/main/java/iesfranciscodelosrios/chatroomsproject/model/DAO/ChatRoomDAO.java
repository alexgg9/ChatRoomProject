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

    public ChatRoomDAO() {
        chatRoom = loadChat("");
    }
    public void createChatRoom(String chatRoomName) {

        if (isChatRoomExist(chatRoomName)) {
            System.out.println("La sala de chat ya existe.");
            return;
        }
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setName(chatRoomName);
        saveChat(chatRoomName);

        System.out.println("Sala de chat '" + chatRoomName + "' creada exitosamente.");
    }

    private boolean isChatRoomExist(String chatRoomName) {
        String filename = chatRoomName + ".xml";
        File file = new File(filename);
        return file.exists();
    }
    public void joinChat(String nickname) {
        User user = new User();
        user.setNickname(nickname);
        chatRoom.getUsers().add(user);
    }

    public void sendMessage(String sender, String message) {
        Message chatMessage = new Message();
        chatMessage.setSender(sender);
        chatMessage.setContent(message);
        chatMessage.setTimestamp(new Date().toString());
        chatRoom.getMessages().add(chatMessage);
    }

    public void leaveChat(String nickname) {
        chatRoom.getUsers().removeIf(user -> user.getNickname().equals(nickname));
    }

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
        return new ChatRoom();
    }
}