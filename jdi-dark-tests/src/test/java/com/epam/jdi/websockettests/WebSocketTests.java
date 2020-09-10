package com.epam.jdi.websockettests;

import com.epam.jdi.http.WebSocketClient;
import com.epam.jdi.services.TrelloClient;
import com.epam.jdi.services.WSEchoServer;
import org.glassfish.tyrus.server.Server;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.testng.Assert.assertEquals;

public class WebSocketTests {
    private Server server;
    private final WebSocketClient client = new WebSocketClient();
    private final TrelloClient client1 = new TrelloClient();

    @BeforeClass
    public void init() throws DeploymentException {
        server = new Server(WSEchoServer.class);
        server.start();
    }

    @Test
    public void echoTest() throws DeploymentException, IOException, URISyntaxException, InterruptedException {
        String message = "Simple text test message";

        client.connect("ws://localhost:8025/echo-ws");
        client.sendPlainText(message);
        assertEquals(
                client.returnNewMessage(1000), message,
                "Unexpected response from server"
        );

        client.sendPlainText(message + "\nP.S. Goodbye!");
        assertEquals(
                client.returnNewMessage(1000),
                message + "\nP.S. Goodbye!",
                "Unexpected response from server"
        );
        client.close();
    }

    @Test
    public void trelloClientEchoTest() throws DeploymentException, IOException, URISyntaxException, InterruptedException {
        String message = "{\"text\":\"Simple text test message\"}";

        client1.connect("ws://localhost:8025/echo-ws");
        client1.sendMessage(message);
        assertEquals(client1.waitAndGetNewMessage(1).toString(), message, "Unexpected response from server");
    }

    @AfterClass
    public void tearDown() {
        server.stop();
    }
}
