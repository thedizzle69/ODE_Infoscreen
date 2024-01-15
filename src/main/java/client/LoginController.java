package client;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    boolean loginSuccessful = false;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


     private Stage primaryStage;

    // Add this method to set the primaryStage
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidCredentials(username, password)) {
            loginSuccessful = true;

            System.out.println("Login successful.");

            // Create GUIController
            GUIController guiController = new GUIController();
            guiController.setLoginFields(usernameField, passwordField);

            ClientApp.openMainApp(primaryStage);

        }

        else {
            System.out.println("Login failed.");
            showAlert();
        }

/*
            // Use Platform.runLater to run on the JavaFX Application Thread
           openMainApp(primaryStage);
            openMainApp(loginButton.getScene().getWindow());
*/


         /* else {

             System.out.println("Login failed.");
            // showAlert();
        }
        */


    }

    private boolean isValidCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/resources/client_credentials.csv"))) {
            // Skip the header row
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(password)) {
                    System.out.println("Login successful. Hello " + parts[2]);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Login failed.");
        showAlert();
        return false;
    }



  /*  private void openMainApp(Window window) {
        // Close the login window
        Stage stage = (Stage) window;
        stage.close();
        // Open the main app window

    }


   */
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid username and password.", ButtonType.OK);
        alert.setTitle("Invalid credentials");
        alert.showAndWait();
    }
}
