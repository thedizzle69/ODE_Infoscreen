package server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ServerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ServerPrototype serverPrototype = new ServerPrototype();
        String message = serverPrototype.getMessage();

        Label label = new Label(message);
        Scene scene = new Scene(label, 300, 200);

        primaryStage.setTitle("Server App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
