package client;

import resources.ContentType;

import java.io.Serializable;
import java.util.Arrays;

public record Content(ContentType contentType, String textContent, byte[] imageContent) implements Serializable {

    public String getData() {

        if (contentType == ContentType.TEXT) {
            return textContent;
        } else if (contentType == ContentType.IMAGE) {
            return Arrays.toString(imageContent);
        } else {
            return "Unknown Content Type";
        }
    }
}
