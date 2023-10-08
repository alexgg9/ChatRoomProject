package iesfranciscodelosrios.chatroomsproject.controllers;

import iesfranciscodelosrios.chatroomsproject.App;
import iesfranciscodelosrios.chatroomsproject.model.DAO.ChatRoomDAO;
import iesfranciscodelosrios.chatroomsproject.model.DAO.UserDAO;
import iesfranciscodelosrios.chatroomsproject.model.domain.ChatRoom;
import iesfranciscodelosrios.chatroomsproject.model.domain.User;
import iesfranciscodelosrios.chatroomsproject.shareFolder.ConfigManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public LoginController() {
        username=getUsername();
    }

    // Variable para almacenar el nombre de usuario
    public  String username;
    @FXML
    private TextField usernameField;

    @FXML
    private TextField salaField;

    @FXML
    private Button loginButton;

    @FXML
    private ChatController chatController;

    // Variables para almacenar el usuario y la sala (estáticas)
    static String usuario;

    static String Room;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura acciones de botones u otros elementos aquí
        loginButton.setOnAction(actionEvent -> {
            try {
                handleLoginButtonAction();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Método para establecer el controlador de chat
    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }

    // Manejar la acción del botón de inicio de sesión
    private void handleLoginButtonAction() throws IOException {
        // Aquí implementa la lógica de autenticación
        UserDAO userDAO = new UserDAO();
        ChatRoomDAO chatRoomDAO = new ChatRoomDAO();
        String path = ConfigManager.readSharedFolderPath();
        usuario = usernameField.getText();
        Room = salaField.getText();
        User user = new User(username);
        userDAO.addUser(user);
        userDAO.saveUsers(path+"users.xml");
        chatRoomDAO.createChatRoom(Room);
        chatRoomDAO.saveChat(path + Room);
        chatRoomDAO.joinChat(username);
        switchToChat();  // Cambiar a la vista del chat

    }

    // Método para obtener el nombre de usuario
    public String getUsername(){
        return this.username;
    }

    // Método para cambiar a la vista del chat
    @FXML
    private void switchToChat() throws IOException {
        ChatController chatController = new ChatController();
        App.setRoot("chat");

    }

}


