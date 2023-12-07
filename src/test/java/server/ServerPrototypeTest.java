package server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerPrototypeTest {

    @Test
    public void testGetMessage() {
        ServerPrototype serverPrototype = new ServerPrototype();
        assertEquals("Hello from ServerPrototype!", serverPrototype.getMessage());
    }
}
