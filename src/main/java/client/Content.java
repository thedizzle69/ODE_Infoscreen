package client;

import javafx.scene.image.Image;
import resources.ContentType;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public record Content(Credentials credentials, ContentType contentType, String textContent, byte[] imageContent) implements Serializable {


    public Object getData() {

        if (contentType == ContentType.TEXT) {
            return textContent;
        } else if (contentType == ContentType.IMAGE) {
            System.out.println("returning Picture");
            return imageContent;
        } else {
            return "Unknown Content Type";
        }
    }


    @Override
    public String toString() {
        // include credentials in the toString representation
        return "Credentials: " + credentials + ", Content: " + getData();
    }

    public Credentials getCredentials() {
        return credentials;
    }

}