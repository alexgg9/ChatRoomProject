package iesfranciscodelosrios.chatroomsproject.model.domain;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Users")
public class Users {
    private Set<User> users;

    public Users() {
        users = new HashSet<>();
    }

    @XmlElement(name = "User")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void add(User user) {
        this.users.add(user);
    }




}
