package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ContentSender {
    public static void sendContent(Content content) {
        try (Socket socket = new Socket("localhost", 5555);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            outputStream.writeObject(content);
            System.out.println("Content sent to the server.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}