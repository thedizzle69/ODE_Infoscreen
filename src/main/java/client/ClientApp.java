package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ClientApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlPath = "GUI.fxml";
        URL resourceUrl = getClass().getResource(fxmlPath);

        if (resourceUrl == null) {
            System.err.println("FXML file not found: " + fxmlPath);
        } else {
            FXMLLoader loader = new FXMLLoader(resourceUrl);
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Client App");
                primaryStage.show();
            } catch (IOException e) {
                System.err.println("Error loading FXML file: " + fxmlPath);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
