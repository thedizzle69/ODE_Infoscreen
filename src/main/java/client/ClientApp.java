package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ClientPrototype clientPrototype = new ClientPrototype();
        String message = clientPrototype.getMessage();

        Label label = new Label(message);
        Scene scene = new Scene(label, 300, 200);

        primaryStage.setTitle("Client App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
