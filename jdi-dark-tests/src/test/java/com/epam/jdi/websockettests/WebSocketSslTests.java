package com.epam.jdi.websockettests;

import com.epam.jdi.dto.Item;
import com.epam.jdi.http.WebSocketTextClient;
import com.epam.jdi.httptests.support.WithJettyWebSockets;
import com.epam.jdi.services.websockets.WSItemClient;
import org.glassfish.tyrus.client.ClientProperties;
import org.testng.annotations.Test;

import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WebSocketSslTests extends WithJettyWebSockets {

    @Test
    public void textMessageTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";
        WebSocketTextClient client = new WebSocketTextClient();

        client.setClientProperties(Collections.singletonMap(ClientProperties.SSL_ENGINE_CONFIGURATOR, getClientSslConfig()));
        client.connect(sslHost + "/echo-ws");
        client.sendPlainText(message);
        assertTrue(client.waitNewMessage(100));
        assertEquals(
                client.getLastMessage(), message,
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

        client.connect(host + "/item-ws");
        client.sendMessage(message);
        assertTrue(client.waitNewMessage(100));
        assertEquals(
                client.waitAndGetNewMessage(100), message,
                "Unexpected response from server"
        );
        client.close();
    }
}
