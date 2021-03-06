package com.epam.jdi.websockettests;

import com.epam.jdi.dto.Item;
import com.epam.jdi.http.WebSocketJsonClient;
import com.epam.jdi.http.WebSocketTextClient;
import com.epam.jdi.httptests.support.WithJettyWebSockets;
import com.epam.jdi.services.websockets.*;
import com.google.gson.*;
import org.testng.annotations.Test;

import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import static org.testng.Assert.*;

public class WebSocketClientTests extends WithJettyWebSockets {

    @Test
    public void textMessageTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";
        WebSocketTextClient client = new WebSocketTextClient();

        client.connect(host + "echo-ws");
        client.sendPlainText(message);
        assertTrue(client.waitNewMessage(100));
        assertEquals(
                client.getLastMessage(), message,
                "Unexpected response from server"
        );

        client.sendPlainText(message + "\nP.S. Goodbye!");
        assertEquals(
                client.waitAndGetNewMessage(100),
                message + "\nP.S. Goodbye!",
                "Unexpected response from server"
        );
        client.close();
    }

    @Test
    public void sendObjectGenericTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException, EncodeException
    {
        Item message = new Item(2, "sofa");
        WSItemClient client = new WSItemClient();

        client.connect(host + "item-ws");
        client.sendMessage(message);
        assertTrue(client.waitNewMessage(100));
        assertEquals(
                client.waitAndGetNewMessage(100), message,
                "Unexpected response from server"
        );
        client.close();
    }

    @Test
    public void binaryTextMessageTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";
        WebSocketTextClient client = new WebSocketTextClient();

        client.connect(host + "echo-ws");
        client.sendBinary(ByteBuffer.wrap(message.getBytes()));

        assertTrue(client.waitNewMessage(100));
        assertEquals(
                client.waitAndGetNewMessage(100), message,
                "Unexpected response from server"
        );
        client.close();
    }

    @Test
    public void jsonOverTextTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "{\"text\":\"Simple text test message\"}";
        WebSocketJsonClient client = new WebSocketJsonClient();

        client.connect(host + "echo-ws");
        client.sendPlainText(message);
        assertEquals(
                client.waitAndGetNewMessage(100).toString(), message,
                "Unexpected response from server"
        );
        assertEquals(
                client.getLastMessageAsJsonObject().get("text").getAsString(),
                "Simple text test message",
                "Unexpected response from server"
        );
        client.close();
    }

    @Test
    public void jsonClientEchoTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        WebSocketJsonClient client = new WebSocketJsonClient();
        Item item = new Item(2, "sofa");
        Gson gson = new Gson();
        String message = gson.toJson(item);

        client.connect(host + "echo-ws");
        client.sendPlainText(message);
        assertEquals(
                client.waitAndGetNewMessage(100).toString(), message,
                "Unexpected response from server"
        );
        assertEquals(
                client.getLastMessageAsJsonObject().get("id").getAsInt(),
                2,
                "Received object's fields isn't as expected"
        );
        assertEquals(
                client.getLastMessageAsJsonObject().get("name").getAsString(),
                "sofa",
                "Received object's fields isn't as expected"
        );
        client.close();
    }
}
