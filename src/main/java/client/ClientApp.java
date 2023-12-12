package client;

import javafx.application.Application;
import javafx.application.Platform;
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

        System.out.println("Hello There...");

        LoginController loginController = openLoginScreen(primaryStage);

        System.out.println("General Kenobi!");

/*        // Check if login was successful before loading the main application window
        if (loginController != null && loginController.loginSuccessful()) {
            System.out.println("opening main app!");
            // Use Platform.runLater to run on the JavaFX Application Thread
            Platform.runLater(() -> openMainApp(primaryStage));
        }

 */

        assert loginController != null;
        if (loginController.loginSuccessful) {

            System.out.println("Trying to load MainApplication...");

            openMainApp(primaryStage);

        }

    }

    private LoginController openLoginScreen(Stage primaryStage) {

        System.out.println("Fetching LoginController.fxml");

        String fxmlPath = "LoginController.fxml";

        // Resolve the path to the FXML file
        Path path = FileSystems.getDefault().getPath(fxmlPath);

        try {
            // Load FXML file directly from the file system
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");

            // Pass primaryStage to the controller
            LoginController loginController = loader.getController();
            loginController.setPrimaryStage(primaryStage);

            primaryStage.show();

            System.out.println("Fetched and will return in 3 seconds");

            Thread.sleep(3000);

            return loginController;

            //works

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void openMainApp(Stage primaryStage) {

        System.out.println("Fetching GUI.fxml");

        String fxmlPath = "GUI.fxml";

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

    private void printf(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
