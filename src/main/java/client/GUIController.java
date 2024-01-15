package client;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import resources.ContentType;

// import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;

public class GUIController {
    @FXML
    private TextArea contentTextArea;

    @FXML
    private ImageView imageView;

    @FXML
    private Button sendButton;

    @FXML
    private Button uploadImageButton;

    // Use the usernameField and passwordField from LoginController
    private TextField usernameField;

    private PasswordField passwordField;

    private Content content = null;

    public void setLoginFields(TextField usernameField, PasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @FXML
    public void initialize() {
        sendButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String contentData = contentTextArea.getText();

            byte[] imageBytes = null;

            if (imageView.getImage() == null) {
                content = new Content(ContentType.TEXT, contentData, imageBytes, username, password);
            } else if (imageView.getImage() != null) {

                imageBytes = extractImageBytes();
                content = new Content(ContentType.IMAGE, contentData, imageBytes, username, password);
            }

            ContentSender.sendContent(content);
            clearInput();
        });

        uploadImageButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);

            }
        });
    }

    private byte[] extractImageBytes() {
        if (imageView.getImage() != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Image image = imageView.getImage();
                PixelReader pixelReader = image.getPixelReader();

                int width = (int) image.getWidth();
                int height = (int) image.getHeight();

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        Color color = pixelReader.getColor(x, y);
                        int red = (int) (color.getRed() * 255);
                        int green = (int) (color.getGreen() * 255);
                        int blue = (int) (color.getBlue() * 255);

                        byteArrayOutputStream.write(red);
                        byteArrayOutputStream.write(green);
                        byteArrayOutputStream.write(blue);
                    }
                }

                return byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void clearInput() {
        contentTextArea.clear();
        imageView.setImage(null);
    }

    @FXML
    public void sendButtonClicked() {
        // Implement your logic for the sendButtonClicked event
    }

    @FXML
    public void uploadImageButtonClicked() {
        // Implement your logic for the uploadImageButtonClicked event
    }
}