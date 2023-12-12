package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ClientApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        String fxmlPath = "LoginScreen.fxml";

        // Resolve the path to the FXML file
        Path path = FileSystems.getDefault().getPath(fxmlPath);

        try {
            // Load FXML file directly from the file system
            Parent root = FXMLLoader.load(path.toUri().toURL());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Client App");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
