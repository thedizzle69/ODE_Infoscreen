// Credentials.java
package client;

import java.io.Serializable;

public record Credentials(String username, String password) implements Serializable {
    public static void setCredentials(Credentials credentials) {
    }

    public static Credentials getCredentials() {
        return null;
    }
}
