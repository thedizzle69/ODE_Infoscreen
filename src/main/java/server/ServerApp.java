package server;

import client.Content;
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
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ServerApp extends Application {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String fxmlPath = "src/main/java/resources/ServerApp.fxml";

        // Resolve the path to the FXML file
        Path path = FileSystems.getDefault().getPath(fxmlPath);

        try {
            // Load FXML file directly from the file system
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            Parent root = loader.load();

            // Get the controller instance
            ServerController controller = loader.getController();

            primaryStage.setTitle("InfoScreen Server");
            primaryStage.setScene(new Scene(root, 600.0, 400.0));
            primaryStage.show();

            if (controller == null) {
                System.err.println("Controller is null");
            } else {
                System.out.println("Controller loaded successfully");
            }

            // Start the server
            new Thread(() -> startServer(controller)).start();


        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlPath);
            e.printStackTrace();
        }
    }


    @Override
    public void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startServer(ServerController controller) {
        try {
            serverSocket = new ServerSocket(5555);
            System.out.println("server1");

            while (!Thread.interrupted()) {
                try {
                    System.out.println("server2");
                    Socket clientSocket = serverSocket.accept(); //failing here
                    System.out.println("server3");
                    System.out.println("Client connected.");
                    new Thread(() -> processClientContent(clientSocket, controller)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processClientContent(Socket clientSocket, ServerController controller) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

            Content receivedContent = (Content) inputStream.readObject();

            System.out.println("Received content from client: " + receivedContent.getData());

            Platform.runLater(() -> {
                // Update dynamic information in the controller

                controller.updateDynamicInfo("Received content from client: " + receivedContent.getData());

                // Assuming ScreenOutput and ContentProcessor are available
                ScreenOutput.displayContent(ContentProcessor.processContent(receivedContent));

            });
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
