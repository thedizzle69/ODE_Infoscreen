package server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ScreenOutput {

    @FXML
    private Label contentLabel;

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

    public void displayContent(String message) {
        System.out.println("Debug: Displaying content - " + message);
        Platform.runLater(() -> {
            if (contentLabel != null) {
                contentLabel.setText(message);
            } else {
                System.err.println("contentLabel is null");
            }
        });
    }
}
