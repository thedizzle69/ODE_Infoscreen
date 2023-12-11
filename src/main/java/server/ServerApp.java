package server;

import client.Content;
// import resources.ContentType;

import javafx.application.Application;
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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ServerApp.fxml")));
        primaryStage.setTitle("InfoScreen Server");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        startServer();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("Server is running. Waiting for client connection...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                new Thread(() -> processClientContent(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processClientContent(Socket clientSocket) {
        try (ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            Content receivedContent = (Content) inputStream.readObject();
            System.out.println("Received content from client: " + receivedContent.getData());

            String processedContent = ContentProcessor.processContent(receivedContent);
            ScreenOutput.displayContent(processedContent);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
