package server;

import resources.ContentType;
import client.Content;
public class ContentProcessor {
    public static String processContent(Content content) {
        if (content.contentType() == ContentType.TEXT) {
            return "Text Content: " + content.textContent();
        } else if (content.contentType() == ContentType.IMAGE) {
            // Implement image processing logic here
            return "Image Content";
        } else {
            return "Unknown Content Type";
        }
    }
}
