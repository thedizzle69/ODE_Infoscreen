package client;

import javafx.application.Application;
// import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


/**
 * The main entry point for the client application. Extends JavaFX Application class.
 *
 * @author You know who it is.
 * @version 420.69
 * @since 2023-12-03
 */
public class ClientApp extends Application {

    /**
     * Entry point for the client application. Initializes the login screen.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */

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

        // assert loginController != null; commented out as not working correctly

        if (loginController == null) {
            System.out.println("loginController is null");

            /* if (loginController.loginSuccessful) {
                System.out.println("Login successful. Trying to load MainApplication...");
                openMainApp(primaryStage);
            } else {
                System.out.println("Login unsuccessful.");
            }*/ // commented out as not working correctly
        }
    }


    /**
     * Opens the login screen and returns the LoginController for further interaction.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     * @return The LoginController instance for login screen interaction.
     */

    private LoginController openLoginScreen(Stage primaryStage) {

        // System.out.println("Fetching LoginController.fxml");

        String fxmlPath = "src/main/java/resources/LoginController.fxml";

        // Resolve the path to the FXML file
        Path path = FileSystems.getDefault().getPath(fxmlPath);

        try {
            // Load FXML file directly from the file system
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login.");

            // Pass primaryStage to the controller
            LoginController loginController = loader.getController();
            loginController.setPrimaryStage(primaryStage);

            primaryStage.show();

            // System.out.println("Fetched and will return in 3 seconds");

            // Thread.sleep(3000);

            return loginController;

            //works

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } /* catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/ //commented out as running together with Thread.sleep(3000) above
    }



    /**
     * Opens the main application window if login is successful.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     * @param credentials The user credentials used for login.
     */


    public static void openMainApp(Stage primaryStage, Credentials credentials) {

        String fxmlPath = "src/main/java/resources/GUI.fxml";

        // Resolve the path to the FXML file
        Path path = FileSystems.getDefault().getPath(fxmlPath);

        //Check if credentials are set
        if (credentials == null) {
            System.out.println("Credentials are null");
            return;
        } else {
            System.out.println("Credentials are: " + credentials );
        }

        try {
            // Load FXML file directly from the file system
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Client App");

            // Pass primaryStage to the controller and check credentials
            // System.out.println("The credentials are still: " + credentials);
            GUIController guiController = loader.getController();
            guiController.setPrimaryStage(primaryStage);
            guiController.setCredentials(credentials);

            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlPath);
            e.printStackTrace();
        }

    }

    /* private void printf(String s) {
        System.out.println(s);
    }

     */

    /**
     * Main method to launch the client application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}