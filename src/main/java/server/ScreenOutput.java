package server;


import client.Content;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import resources.ContentType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

public class ScreenOutput {

    @FXML
    private ImageView IvImageView;

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





            stage.setTitle("Output");

            stage.setScene(new Scene(root, 400, 200));  // Adjust the size as needed


        } catch (IOException e) {
            System.err.println("Error loading FXML file: ");
            e.printStackTrace();
        }

    }


    public void displayContent(Content content) {

            if(content.getData() instanceof String)
            {
                System.out.println(content.getData().toString());

                Platform.runLater(() -> {
            contentLabel.setText(content.getData().toString());});

            } else if (content.getData() instanceof byte[]) {

                byte[] receivedArray=(byte[])content.getData();

                Image image= new Image(new ByteArrayInputStream(receivedArray));
                if(image==null)
                System.out.println("image is null");

                Platform.runLater(() -> {
                IvImageView.setImage(image);});




               // Image image = (Image) content.getData(); // Cast the data to Image
                //IvImageView.setImage(image);

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
