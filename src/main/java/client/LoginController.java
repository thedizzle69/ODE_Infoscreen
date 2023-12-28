package client;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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


            ClientApp.openMainApp(primaryStage);

        }

        else {
            System.out.println("Login failed.");
            showAlert();
        }


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
        // For demonstration purposes, hardcoding valid credentials
        return ("admin".equals(username) && "adminkey".equals(password)) ||
                ("user".equals(username) && "userkey".equals(password));
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
