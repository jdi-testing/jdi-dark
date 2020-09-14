package com.epam.jdi.websockettests;

import com.epam.jdi.dto.Item;
import com.epam.jdi.http.WebSocketTextClient;
import com.epam.jdi.services.websockets.*;
import org.glassfish.tyrus.server.Server;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import static org.testng.Assert.*;

public class WebSocketTests {
    private Server server;

    @BeforeClass
    public void init() throws DeploymentException {
        server = new Server(WSEchoServer.class, WSItemServer.class);
        server.start();
    }

    @Test
    public void textMessageTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";
        WebSocketTextClient client = new WebSocketTextClient();

        client.connect("ws://localhost:8025/echo-ws");
        client.sendPlainText(message);
        assertEquals(
                client.waitAndGetNewMessage(1000), message,
                "Unexpected response from server"
        );

        client.sendPlainText(message + "\nP.S. Goodbye!");
        assertEquals(
                client.waitAndGetNewMessage(1000),
                message + "\nP.S. Goodbye!",
                "Unexpected response from server"
        );
        client.close();
    }

    @Test
    public void sendObjectTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException, EncodeException {
        Item message = new Item(2, "sofa");
        WSItemClient client = new WSItemClient();

        client.connect("ws://localhost:8025/item-ws");
        client.sendObject(message);
        assertEquals(
                client.waitAndGetNewMessage(1000), message,
                "Unexpected response from server"
        );
    }

    @Test
    public void binaryTextMessageTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";
        WebSocketTextClient client = new WebSocketTextClient();

        client.connect("ws://localhost:8025/echo-ws");
        client.sendBinary(ByteBuffer.wrap(message.getBytes()));

        assertEquals(
                client.waitAndGetNewMessage(1000), message,
                "Unexpected response from server"
        );
        client.close();
    }

    @Test
    public void jsonClientEchoTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "{\"text\":\"Simple text test message\"}";
        JsonClient client = new JsonClient();

        client.connect("ws://localhost:8025/echo-ws");
        client.sendMessage(message);
        assertEquals(
                client.waitAndGetNewMessage(1000).toString(), message,
                "Unexpected response from server"
        );
        assertEquals(
                client.getNewMessageAsJsonObject().get("text").getAsString(),
                "Simple text test message",
                "Unexpected response from server"
        );
    }

    @AfterClass
    public void tearDown() {
        server.stop();
    }
}
