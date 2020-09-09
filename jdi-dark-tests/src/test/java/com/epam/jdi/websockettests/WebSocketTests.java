package com.epam.jdi.websockettests;

import com.epam.jdi.http.WebSocketClient;
import com.epam.jdi.http.WebSocketServer;
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
    private WebSocketClient client = new WebSocketClient();

    @BeforeClass
    public void init() throws DeploymentException {
        server = new Server(WebSocketServer.class);
        server.start();
    }

    @Test
    public void messageTest() throws DeploymentException, IOException, URISyntaxException, InterruptedException {
        String message = "{\"text\":\"Simple text test message\"}";

        client.connect("ws://localhost:8025/test-ws");
        client.sendPlainText(message);
//        JsonElement jsonMessage = client.waitAndGetNewMessage(1);
//        assertEquals(jsonMessage.toString(), message, "Unexpected response from server");
    }

    @AfterClass
    public void close() {
        server.stop();
    }
}
