/**
 * The `ServerApp` class represents the main server application for handling client connections
 * and managing content received from clients. It extends the JavaFX `Application` class and
 * provides a graphical user interface for server interaction.
 *
 * The server operates on a separate thread to accept client connections and processes incoming
 * content from clients. The server utilizes JavaFX components for the user interface, including
 * a main frame and a text area for displaying log messages.
 *
 * The server application is designed to receive instances of the `Content` class from clients,
 * update a JavaFX `ListView` with the received content, and display log messages in the text area.
 *test
 * The server follows a multi-threaded approach, creating a new thread for each connected client to
 * handle content processing asynchronously.
 *
 * @author Samuel Bißmann
 * @version 1.0
 * @since 2024-01-06
 */
package server;

import client.Content;
import client.Credentials;
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

    ObservableList<Content> contentList =FXCollections.observableArrayList();

    private  String logMessages;

    public String getLogMessages() {
        return logMessages;
    }
    private ScreenOutput screenOutput;

    /**
     * The main method for launching the server application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Overrides the start method of the JavaFX `Application` class to initialize the main frame,
     * set up the user interface, and start the server.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {


        // Check JavaFX version
        System.out.println("JavaFX Runtime Version: " + System.getProperties().getProperty("javafx.runtime.version"));

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

            // myController.setScreenOutput(screenOutput); //


            myController.setObservable(contentList);



        } catch (IOException e) {
            System.out.println("Loader Failed");
            throw new RuntimeException(e);


        }

        appendToLog(" Main Frame loaded successfully\n");

        startServer();

    }

    /**
     * Starts the server on a separate thread, accepting client connections and spawning threads
     * for each connected client to process incoming content.
     */
    private void startServer() {

        appendToLog(" Starting Server...\n");

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5555);

                while(true)
                {

                    ClientSocket = serverSocket.accept();
                    appendToLog("Incoming request from " + ClientSocket.getInetAddress() + "\nChecking Credentials...\n");

                    //Checking Credentials
                    ObjectInputStream inputStream = new ObjectInputStream(ClientSocket.getInputStream());
                    Content receivedContent = (Content) inputStream.readObject();

                    if (isValidCredentials(receivedContent.getCredentials())) {
                        appendToLog("Credentials valid. Sending confirmation to client...\n");
                        // ObjectOutputStream outputStream = new ObjectOutputStream(ClientSocket.getOutputStream());
                        // outputStream.writeObject("Credentials valid. Sending confirmation to client...\n");


                        System.out.println ("Trying to process content from client...\n");
                        System.out.println("Content data: " + receivedContent.getData() + "\n");

                        new Thread(() -> processClientContent(inputStream)).start();

                    } else {
                        appendToLog("Credentials invalid. Sending error message to client...\n");
                        // ObjectOutputStream outputStream = new ObjectOutputStream(ClientSocket.getOutputStream());
                        // outputStream.writeObject("Credentials invalid. Sending error message to client...\n");
                        ClientSocket.close();
                    }

                }

            } catch (IOException e) {
                if (!serverSocket.isClosed())
                    throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }






    /**
     * Overrides the stop method of the JavaFX `Application` class to handle cleanup tasks when
     * the application is closed, such as closing the server socket.
     */
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

    /**
     * Appends log messages to the logMessages string and updates the JavaFX text area
     * with the latest log messages.
     *
     * @param Log Log message to be appended.
     */
    private void appendToLog(String Log)
    {
        logMessages=logMessages+Log;
        Platform.runLater(() -> {
            myController.setTextInTextArea(logMessages);
        });
    }




    /**
     * Processes content received from a connected client. It deserializes the content,
     * updates the JavaFX `ListView` with the received content, and displays log messages.
     */
    private void processClientContent(ObjectInputStream inputStream)
    {
        try {

            System.out.println ("Trying to process content from client...\n");
            appendToLog("Trying to process content from client...\n");

            Content processedContent = (Content) inputStream.readObject();

            appendToLog("Received content from client: " + processedContent.getData() +"\n");


            Platform.runLater(() -> {
                contentList.add(processedContent);
                myController.updateListView(contentList);
            });


        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    // Function to check credentials

    private boolean isValidCredentials(Credentials receivedCredentials) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/resources/server_credentials.csv"))) {
            // Skip the header row
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(receivedCredentials.getUsername()) && parts[1].equals(receivedCredentials.getPassword())) {
                    System.out.println("Connected user: " + parts[2]);
                    appendToLog("Connected user: " + parts[2] + "\n");
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



}