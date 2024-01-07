package server;

import client.Content;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import resources.ContentType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ScreenOutput {

    @FXML
    private ImageView IvImageView;

    @FXML
    private Label contentLabel;

    private Stage stage;

    public ScreenOutput() {
        this.stage = new Stage();
        initializeStage();
    }

    private void initializeStage() {
        String fxmlPath = "src/main/java/resources/ScreenOutput.fxml";

        // Resolve the path to the FXML file
        Path path = FileSystems.getDefault().getPath(fxmlPath);

        try {
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            loader.setController(this);

            Parent root = loader.load();




            stage.setTitle("Output");
            stage.setScene(new Scene(root, 400, 200));  // Adjust the size as needed

        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlPath);
            e.printStackTrace();
        }

    }

    public void displayContent(Content content) {
        Platform.runLater(() -> {
            if(content.getData() instanceof String)
            {
                System.out.println(content.getData().toString());
            contentLabel.setText(content.getData().toString());

            } else if (content.getData() instanceof Image) {
                System.out.println("Picture print baaby");


                Image image = (Image) content.getData(); // Cast the data to Image
                IvImageView.setImage(image);

            }


        });
        stage.show(); // Show the stage when content is updated
    }
}
