module iesfranciscodelosrios.chatroomsproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;


    opens iesfranciscodelosrios.chatroomsproject to javafx.fxml;
    exports iesfranciscodelosrios.chatroomsproject;
    exports iesfranciscodelosrios.chatroomsproject.controllers;
    opens iesfranciscodelosrios.chatroomsproject.model.domain to java.xml.bind;
    opens iesfranciscodelosrios.chatroomsproject.controllers to javafx.fxml;
}