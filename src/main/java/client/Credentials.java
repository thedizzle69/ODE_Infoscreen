// Credentials.java
package client;

import java.io.Serializable;

public record Credentials(String username, String password) implements Serializable {
}
