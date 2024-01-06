package server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

public class ScreenOutput {

    @FXML
    private Label contentLabel;

    @FXML
    private ImageView imageView;

    private Stage stage;

    public ScreenOutput() {
        this.stage = new Stage();
        initializeStage();
    }


    private void initializeStage() {
        String fxmlPath = "src/main/java/resources/ScreenOutput.fxml";

        try {
            Path path = FileSystems.getDefault().getPath(fxmlPath);
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            loader.setController(this);
            Parent root = loader.load();


            loader.setController(this);


            stage.setTitle("Output");
            stage.setScene(new Scene(root, 400, 400));  // Adjust the size as needed
        } catch (IOException e) {
            System.err.println("Error loading FXML file: ");
            e.printStackTrace();
        }
    }

    public void displayContent(String message) {

        if (contentLabel != null) {
            contentLabel.setText(message);
        }

        // Check if the content is an image (assumes it is a byte array)
        if (message.startsWith("[B@")) {
            displayImage(message);
        }

        stage.show(); // Show the stage when content is updated

    }

    private void displayImage(String imageArray) {
        byte[] imageBytes = parseImageArray(imageArray);

        if (imageBytes != null) {
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            imageView.setImage(image);
        }
    }

    private byte[] parseImageArray(String imageArray) {
        String[] byteValues = imageArray.substring(1, imageArray.length() - 1).split(",");
        byte[] bytes = new byte[byteValues.length];

        for (int i = 0; i < byteValues.length; i++) {
            bytes[i] = Byte.parseByte(byteValues[i].trim());
        }

        return bytes;
    }
}
