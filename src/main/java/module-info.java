module iesfranciscodelosrios.chatroomsproject {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens iesfranciscodelosrios.chatroomsproject to javafx.fxml;
    exports iesfranciscodelosrios.chatroomsproject;
}