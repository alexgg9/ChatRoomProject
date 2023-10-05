package iesfranciscodelosrios.chatroomsproject.model.domain;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Config")
public class Config {
    private String sharedFolderPath;

    @XmlElement(name = "SharedFolderPath")
    public String getSharedFolderPath() {
        return sharedFolderPath;
    }

    public void setSharedFolderPath(String sharedFolderPath) {
        this.sharedFolderPath = sharedFolderPath;
    }
}
