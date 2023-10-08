package iesfranciscodelosrios.chatroomsproject.controllers;

import iesfranciscodelosrios.chatroomsproject.App;
import iesfranciscodelosrios.chatroomsproject.model.DAO.ChatRoomDAO;
import iesfranciscodelosrios.chatroomsproject.model.domain.ChatRoom;
import iesfranciscodelosrios.chatroomsproject.model.domain.Message;
import iesfranciscodelosrios.chatroomsproject.shareFolder.ConfigManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


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


    private ChatRoomDAO chatRoomDAO;



    public ChatController() {

        chatRoomDAO = new ChatRoomDAO();
    }


    public void initialize() {
        sendButton.setOnAction(event -> sendMessage());
        userLabel.setText("Usuario: " + LoginController.usuario);
        roomLabel.setText("Sala: " + LoginController.Room);
         cargarMensajesCadaDosSegundos();
        }



    // Este método se llamará cuando el usuario haga clic en el botón de enviar.
    @FXML
    private void sendMessage() {
        String path = ConfigManager.readSharedFolderPath();
        String message = messageTextField.getText().trim();
        if (!message.isEmpty()) {
            String userNickname = userLabel.getText().replace("Usuario: ", "");
            chatTextArea.appendText(userNickname + ": " + message + "\n");
            messageTextField.clear();
            chatRoomDAO.sendMessage(userNickname, message);
            chatRoomDAO.saveChat(path + LoginController.Room);
        }
    }


    // Este método carga los mensajes anteriores de la sala y los muestra en la vista de chat.
    private void loadChatMessages() {
        try {
            ChatRoom chatRoom = chatRoomDAO.loadChat(LoginController.Room + ".xml");
            chatTextArea.clear();
            for (Message message : chatRoom.getMessages()) {
                chatTextArea.appendText(message.getSender() + ": " + message.getContent() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void refreshChat() {
        loadChatMessages();
    }


    public void cargarMensajesCadaDosSegundos() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loadChatMessages();
                System.out.println("Chat cargados");
            }
        }, 0, 10000); //
    }
    @FXML
    private void switchToLogin() throws IOException {

        App.setRoot("loginView");

    }
}

