package client;

import resources.ContentType;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class GUIController {
    @FXML
    private TextArea contentTextArea;

    @FXML
    private ImageView imageView;

    @FXML
    private Button sendButton;

    @FXML
    private Button uploadImageButton;

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
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

                // Use JavaFX classes for image writing
                javax.imageio.ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private void clearInput() {
        contentTextArea.clear();
        imageView.setImage(null);
    }
}
