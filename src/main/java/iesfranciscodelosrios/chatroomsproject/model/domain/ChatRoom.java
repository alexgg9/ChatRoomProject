package iesfranciscodelosrios.chatroomsproject.model.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChatRoom")

public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private List<User> users = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public ChatRoom() {
        users = new ArrayList<>();
    }



    public ChatRoom(String name) {
        super();
        this.name = name;
    }



    public ChatRoom(String name, List<User> users, List<Message> messages) {
        this.name = name;
        this.users = users;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper (name = "Users")
    @XmlElement(name = "User")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    @XmlElementWrapper (name = "Messages")
    @XmlElement(name = "Message")
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "name='" + name + '\'' +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }
}
