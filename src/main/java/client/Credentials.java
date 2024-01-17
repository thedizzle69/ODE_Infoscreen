// Credentials.java
package client;

import java.io.Serializable;

/**
 * Represents user credentials for authentication.
 *
 * @param username The username.
 * @param password The password.
 */
public record Credentials(String username, String password) implements Serializable {

    /**
     * Sets the credentials.
     *
     * @param credentials The credentials.
     */
    public static void setCredentials(Credentials credentials) {
    }

    /**
     * Gets the credentials.
     *
     * @return The credentials.
     */

    public static Credentials getCredentials() {
        return null;
    }

    /**
     * Gets the username.
     *
     * @return The username.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return The password.
     */

    public String getPassword() {
        return password;
    }
}
