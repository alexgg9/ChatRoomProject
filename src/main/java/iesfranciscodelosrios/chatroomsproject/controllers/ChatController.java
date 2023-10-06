package iesfranciscodelosrios.chatroomsproject.controllers;

import iesfranciscodelosrios.chatroomsproject.model.DAO.ChatRoomDAO;
import iesfranciscodelosrios.chatroomsproject.model.domain.ChatRoom;
import iesfranciscodelosrios.chatroomsproject.model.domain.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {

    @FXML
    private Label userLabel;
    @FXML
    private Label roomLabel;
    @FXML
    private TextArea chatTextArea;
    @FXML
    private TextField messageTextField;
    @FXML
    private Button sendButton;

    private LoginController loginController;
    private ChatRoomDAO chatRoomDAO;

    public ChatController() {

        chatRoomDAO = new ChatRoomDAO();
    }

    public void initialize() {
        sendButton.setOnAction(event -> sendMessage());
       loadChatMessages();
        userLabel.setText("Usuario: " + LoginController.usuario);
        roomLabel.setText("Sala: " + LoginController.Room);
    }



    // Este método se llamará cuando el usuario haga clic en el botón de enviar.
    @FXML
    private void sendMessage() {
        String message = messageTextField.getText().trim();
        if (!message.isEmpty()) {
            String userNickname = userLabel.getText().replace("Usuario: ", "");
            chatRoomDAO.sendMessage(userNickname, message);
            messageTextField.clear();
            chatTextArea.appendText(userNickname + ": " + message + "\n");
        }
    }

    // Este método carga los mensajes anteriores de la sala y los muestra en la vista de chat.
    private void loadChatMessages() {
        ChatRoom chatRoom = chatRoomDAO.loadChat("");
        for (Message message : chatRoom.getMessages()) {
            chatTextArea.appendText(message.getSender() + ": " + message.getContent() + "\n");
        }
    }
}

