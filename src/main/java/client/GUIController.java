package client;

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
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.File;
// import java.util.Arrays;


/**
 * Controller for the client application's graphical user interface.
 *
 * @author ditNuts
 * @version 420.69
 * @since 2023-12-03
 */

public class GUIController {

    /**
     * The text area for the content.
     */
    @FXML
    private TextArea contentTextArea;

    /**
     * The image view for the content.
     */
    @FXML
    private ImageView imageView;

    /**
     * The send button.
     */
    @FXML
    private Button sendButton;

    /**
     * The upload image button.
     */
    @FXML
    private Button uploadImageButton;

    /**
     * The content to be sent.
     */
    private Content content= null;

    /**
     * The credentials for the client.
     */
    private Credentials credentials = Credentials.getCredentials();

    /**
     * The primary stage for the JavaFX application.
     */
    private Stage primaryStage;

    /**
     * Sets the primary stage for the JavaFX application.
     */

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Initializes the GUI components and sets up event handlers.
     */

    @FXML
    public void initialize() {

        /**
         * Sends the content to the server when the send button is clicked.
         */
        sendButton.setOnAction(event -> {
            String contentData = contentTextArea.getText();
            byte[] imageBytes= null;

            System.out.println("Credentials for Server Authentification are: " + credentials);

            if(imageView.getImage()== null)
            {
                content = new Content(credentials, ContentType.TEXT, contentData, imageBytes);
            }
            else if (imageView.getImage()!=null)
            {
                imageBytes= extractImageBytes();
                content = new Content(credentials, ContentType.IMAGE, contentData, imageBytes);
            }

            ContentSender.sendContent(content);
            clearInput();
        });

        /**
         * Uploads an image when the upload image button is clicked.
         */
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

    /**
     * Extracts the image bytes from the image view.
     *
     * @return The image bytes.
     */
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

    /**
     * Clears the input fields.
     */
    private void clearInput() {
        contentTextArea.clear();
        imageView.setImage(null);
    }

    /**
     * Sends the content to the server when the send button is clicked. (implemented in another way)
     */
    @FXML
    public void sendButtonClicked() {
        // Implement your logic for the sendButtonClicked event
    }

    /**
     * Uploads an image when the upload image button is clicked. (implemented in another way)
     */
    @FXML
    public void uploadImageButtonClicked() {
        // Implement your logic for the uploadImageButtonClicked event
    }

    /**
     * Sets the credentials for the client.
     *
     * @param credentials The credentials to be set.
     */

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}