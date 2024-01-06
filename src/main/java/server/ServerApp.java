package server;

import client.Content;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ServerApp extends Application {
    private ServerSocket serverSocket= null;
    private Socket ClientSocket= null;

    private ServerController myController= null;

    ObservableList<String> contentList =FXCollections.observableArrayList();

    private  String logMessages;


    public String getLogMessages() {
        return logMessages;
    }
    private ScreenOutput screenOutput;

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {

        screenOutput = new ScreenOutput();


        logMessages= " Loading main frame...\n";

        try {
            String fxmlPath = "src/main/java/resources/ServerApp.fxml";
            Path path = FileSystems.getDefault().getPath(fxmlPath);
            FXMLLoader loader= new FXMLLoader(path.toUri().toURL());
            Parent root = loader.load();


            System.out.println("loading successful");
            primaryStage.setScene(new Scene(root, 600,400));
            primaryStage.show();
            myController= loader.getController();
            myController.setScreenOutput(screenOutput); //



        } catch (IOException e) {
            System.out.println("Loader Failed");
            throw new RuntimeException(e);


        }

        appendToLog(" Main Frame loaded successfully\n");

        startServer();

    }

    private void startServer() {

        appendToLog(" Starting Server...\n");

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5555);

                while(true)
                {
                    ClientSocket = serverSocket.accept();
                    appendToLog("Server accepted, connected to" + ClientSocket.getInetAddress()+ "\n");
                    new Thread(() -> processClientContent()).start();
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
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

    private void appendToLog(String Log)
    {
        logMessages=logMessages+Log;
        Platform.runLater(() -> {
            myController.setTextInTextArea(logMessages);
        });
    }





    private void processClientContent()
        {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(ClientSocket.getInputStream());
            Content receivedContent = (Content) inputStream.readObject();
            appendToLog("Received content from client: " + receivedContent.getData() +"\n");

            //todo Objekt als List view Speichern
            contentList.add(receivedContent.toString());
            System.out.println(contentList.toString());
            myController.updateListView(contentList);


                //String processedContent = ContentProcessor.processContent(receivedContent);
                //screenOutput.displayContent(processedContent); // Call the instance method

            String contentToShow = receivedContent.getData();
            Platform.runLater(() -> screenOutput.displayContent(contentToShow));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
