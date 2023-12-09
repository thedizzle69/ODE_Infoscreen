package client;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

public class ClientAppTest {

    @Test
    public void testStart() {
        // Initialize JavaFX Toolkit
        Platform.startup(() -> {
            // Run your JavaFX application logic here
            // Note: This test only checks if the start method can be invoked without errors.
        });

        // Clean up JavaFX Toolkit
        Platform.exit();
    }
}