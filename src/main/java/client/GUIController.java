package client;

import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import resources.ContentType;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
// import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class GUIController {
    public BorderPane root;
    public Button clearButton;
    public Button saveButton;
    public Button loadButton;
    public Button exitButton;
    @FXML
    private TextArea contentTextArea;

    @FXML
    private ImageView imageView;

    @FXML
    private Button sendButton;

    @FXML
    private Button uploadImageButton;

    public GUIController(TextArea contentTextArea) {
        this.contentTextArea = contentTextArea;
    }

    @FXML
    public void initialize() {
        sendButton.setOnAction(event -> {
            String contentData = contentTextArea.getText();
            byte[] imageBytes = extractImageBytes();
            Content content = new Content(ContentType.TEXT, contentData, imageBytes);
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

    public void sendButtonClicked() {
    }

    public void uploadImageButtonClicked() {
    }
}
