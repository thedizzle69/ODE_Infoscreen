package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Sends content to the server.
 *
 * @author dizNuts
 * @version 420.69
 * @since 2023-12-03
 */
public class ContentSender {


    /**
     * Sends content to the server.
     *
     * @param content The content to be sent.
     */

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