package iesfranciscodelosrios.chatroomsproject.controllers;

import iesfranciscodelosrios.chatroomsproject.model.DAO.ChatRoomDAO;
import iesfranciscodelosrios.chatroomsproject.model.DAO.UserDAO;
import iesfranciscodelosrios.chatroomsproject.model.domain.User;
import iesfranciscodelosrios.chatroomsproject.shareFolder.ConfigManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField salaField;

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura acciones de botones u otros elementos aquí
        loginButton.setOnAction(actionEvent -> handleLoginButtonAction());
    }

    private void handleLoginButtonAction() {
        // Aquí implementa la lógica de autenticación
        UserDAO userDAO = new UserDAO();
        ChatRoomDAO chatRoomDAO = new ChatRoomDAO();
        String path = ConfigManager.readSharedFolderPath();
        String username = usernameField.getText();
        String sala = salaField.getText();
        User user = new User(username);
        userDAO.addUser(user);
        userDAO.saveUsers(path+"users.xml");

        chatRoomDAO.createChatRoom(sala);
        chatRoomDAO.saveChat(path + sala);
        chatRoomDAO.joinChat(username);



    }
}


