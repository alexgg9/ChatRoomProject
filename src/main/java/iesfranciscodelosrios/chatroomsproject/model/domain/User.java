package iesfranciscodelosrios.chatroomsproject.model.domain;
import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement ( name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nickname;


    public User() {
    }

    public User(String nickname) {
        this.nickname = nickname;

    }
    @XmlElement( name = "Nickname")
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(nickname, other.nickname);
    }


    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                '}';
    }

}
