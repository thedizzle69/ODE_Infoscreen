package client;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {

    private boolean loginSuccessful = false;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    public void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidCredentials(username, password)) {
            loginSuccessful = true;

            System.out.println("Login successful. Trying to load main app.");

/*
            // Use Platform.runLater to run on the JavaFX Application Thread
            Platform.runLater(() -> openMainApp(primaryStage));


 */


            openMainApp(loginButton.getScene().getWindow());


        } else {
            showAlert("Invalid credentials", "Please enter valid username and password.", Alert.AlertType.ERROR);
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // For demonstration purposes, hardcoding valid credentials
        return ("admin".equals(username) && "adminkey".equals(password)) ||
                ("user".equals(username) && "userkey".equals(password));
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    private void openMainApp(Window window) {
        // Close the login window
        Stage stage = (Stage) window;
        stage.close();
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, content, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
