package client;

import javafx.scene.image.Image;
import resources.ContentType;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public record Content(ContentType contentType, String textContent, byte[] imageContent) implements Serializable {


    public Object getData() {

        if (contentType == ContentType.TEXT) {
            return textContent;
        } else if (contentType == ContentType.IMAGE) {
            try {
                System.out.println("returning Picture");
                return ImageIO.read(new ByteArrayInputStream(imageContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return "Unknown Content Type";
        }
    }


    @Override
    public String toString() {

        if(contentType==ContentType.TEXT) {
            return "Text erhalten:" + textContent;

        }
        else
        {
            return "Bild erhalten";


        }
    }
}
