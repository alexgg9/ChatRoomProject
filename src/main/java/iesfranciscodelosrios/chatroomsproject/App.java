package iesfranciscodelosrios.chatroomsproject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;



    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loginView.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, 620, 460);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static Scene createScene(String fxml, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, width, height);
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {

        Parent p = loadFXML(fxml);
        Scene newScene;

        if (fxml.equals("chat")) {
            newScene = createScene(fxml, 640, 460);
            primaryStage.setResizable(false);
        }else{
            newScene = createScene(fxml, 640, 480);
            primaryStage.setResizable(true);
        }
        primaryStage.setScene(newScene);
        App.scene.setRoot(p);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}