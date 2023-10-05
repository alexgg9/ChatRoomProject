package iesfranciscodelosrios.chatroomsproject.tests;

import iesfranciscodelosrios.chatroomsproject.model.DAO.ChatRoomDAO;
import iesfranciscodelosrios.chatroomsproject.model.DAO.UserDAO;
import iesfranciscodelosrios.chatroomsproject.model.domain.User;
import iesfranciscodelosrios.chatroomsproject.shareFolder.ConfigManager;
import java.util.Scanner;



public class Test {
    public static void main(String[] args) {

        //unmarsahll config y lees la ruta
        String path = ConfigManager.readSharedFolderPath();
        ChatRoomDAO chatRoomDAO = new ChatRoomDAO();

        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa tu apodo: ");
        String nickname = scanner.nextLine();
        User user = new User(nickname);
        userDAO.addUser(user);
        userDAO.saveUsers(path+"users.xml");
        System.out.print("Pon el nombre de la sala: ");
        String chatRoomName = scanner.nextLine();
        chatRoomDAO.createChatRoom(chatRoomName);


        chatRoomDAO.joinChat(nickname);

        while (true) {
            System.out.print("Mensaje: ");
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("exit")) {
                chatRoomDAO.leaveChat(nickname);
                chatRoomDAO.saveChat(path + chatRoomName);
                System.out.println("Hasta luego, " + nickname + "!");
                break;
            }
            chatRoomDAO.sendMessage(nickname, message);
        }
    }
}
