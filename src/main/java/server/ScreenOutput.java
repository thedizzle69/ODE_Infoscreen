package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class ScreenOutput extends Application {
    private static String contentToDisplay = "";

    public static void displayContent(String content) {
        contentToDisplay = content;
        //launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("src/main/java/resources/ScreenOutput.fxml")));
        primaryStage.setTitle("InfoScreen Output");
        Scene scene = new Scene(root, 1920, 1080); // Full HD resolution
        primaryStage.setScene(scene);

        Label contentLabel = (Label) scene.lookup("#contentLabel");
        contentLabel.setText(contentToDisplay);

        primaryStage.show();
    }

    public static String getContentToDisplay() {
        return contentToDisplay;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
