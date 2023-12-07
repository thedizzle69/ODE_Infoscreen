package client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientPrototypeTest {

    @Test
    public void testGetMessage() {
        ClientPrototype clientPrototype = new ClientPrototype();
        assertEquals("Hello from ClientPrototype!", clientPrototype.getMessage());
    }
}
