package server;

import client.Content;
// import resources.ContentType;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class ServerApp extends Application {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ServerApp.fxml")));
            primaryStage.setTitle("InfoScreen Server");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

            startServer();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle FXML loading exception here
        }
    }

    @Override
    public void stop() {
        // Perform cleanup and close resources (e.g., ServerSocket)
        // This method is called when the application is stopped.
        // Make sure to stop the server gracefully.
        // For simplicity, you can just close the ServerSocket in this example.
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("Server is running. Waiting for client connection...");

            while (!Thread.interrupted()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected.");

                    new Thread(() -> processClientContent(clientSocket)).start();
                } catch (IOException e) {
                    e.printStackTrace(); // Handle the exception or log it
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception or log it
        }
    }


    private void processClientContent(Socket clientSocket) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            Content receivedContent = (Content) inputStream.readObject();
            System.out.println("Received content from client: " + receivedContent.getData());

            // Perform UI update on the JavaFX Application Thread
            Platform.runLater(() -> ScreenOutput.displayContent(ContentProcessor.processContent(receivedContent)));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
