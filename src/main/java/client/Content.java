package client;

// import javafx.scene.image.Image;
import resources.ContentType;

// import javax.imageio.ImageIO;
// import java.io.ByteArrayInputStream;
// import java.io.IOException;
import java.io.Serializable;
// import java.util.Arrays;


/**
 * Represents content sent between the client and the server.
 *
 * @author dizNuts
 * @version 420.69
 * @since 2023-12-03
 */
public record Content(Credentials credentials, ContentType contentType, String textContent, byte[] imageContent) implements Serializable {


    /**
     * Gets the data content based on the content type.
     *
     * @return The data content (text or image).
     */

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

    /**
     * Returns a string representation of the content.
     *
     * @return String representation of the content.
     */


    @Override
    public String toString() {
        // include credentials in the toString representation
        return "Credentials: " + credentials + ", Content: " + getData();
    }

    /**
     * Gets the user credentials associated with the content.
     *
     * @return The user credentials.
     */


    public Credentials getCredentials() {
        return credentials;
    }

}