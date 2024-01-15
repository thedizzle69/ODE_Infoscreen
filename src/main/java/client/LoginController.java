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

            // Storing the credentials in the Credentials class
            Credentials credentials = new Credentials(username, password);

            System.out.println("Login successful. Trying to load MainApplication...");

            ClientApp.openMainApp(primaryStage);

        } else {
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
                    System.out.println("Credentials valid. Hello " + parts[2]);

                    //Show hello Message on screen
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello " + parts[2] + "!\n\n" + "Please press the OK button to enter the Application.", ButtonType.OK);
                    alert.setTitle("Credentials Valid!");
                    alert.showAndWait();

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
        alert.setTitle("Invalid credentials!");
        alert.showAndWait();
    }

}