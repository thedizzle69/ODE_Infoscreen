package client;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class LoginController {

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
            openMainApp();
        } else {
            showAlert("Invalid credentials", "Please enter valid username and password.", Alert.AlertType.ERROR);
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // For demonstration purposes, hardcoding valid credentials
        return ("admin".equals(username) && "adminkey".equals(password)) ||
                ("user".equals(username) && "userkey".equals(password));
    }

    private void openMainApp() {
        // Close the login window
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        // Open the main application window
        ClientApp app = new ClientApp();
        app.start(new Stage());
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, content, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
