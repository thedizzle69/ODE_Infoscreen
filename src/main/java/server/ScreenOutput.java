package server;

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

        // Resolve the path to the FXML file
        Path path = FileSystems.getDefault().getPath(fxmlPath);

        try {
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            Parent root = loader.load();


            loader.setController(this);

            stage.setTitle("Output");
            stage.setScene(new Scene(root, 400, 200));  // Adjust the size as needed
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public void displayContent(String message) {
        if (contentLabel != null) {
            contentLabel.setText(message);
        }
        stage.show(); // Show the stage when content is updated
    }
}
