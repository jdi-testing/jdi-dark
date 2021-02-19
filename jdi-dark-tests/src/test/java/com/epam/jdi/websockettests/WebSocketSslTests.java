package com.epam.jdi.websockettests;

import com.epam.jdi.http.WebSocketTextClient;
import com.epam.jdi.httptests.support.WithJettyWebSockets;
import org.glassfish.tyrus.client.ClientProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import static org.testng.Assert.assertEquals;

import static com.epam.http.JdiHttpSettings.logger;

public class WebSocketSslTests extends WithJettyWebSockets {

    private WebSocketTextClient client;

    @BeforeMethod
    public void initClient() {
        client = new WebSocketTextClient();
    }

    @AfterMethod
    public void initService() {
        try {
            client.close();
        }
        catch (Exception ignore) {
            logger.error("Error during close websocket client", ignore);
        }
    }

    @Test
    public void sslOutsideConfigTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";
        client = new WebSocketTextClient();

        client.setClientProperties(Collections.singletonMap(ClientProperties.SSL_ENGINE_CONFIGURATOR,
                getClientSslConfig()));
        client.connect(sslHost + "echo-ws");
        client.sendPlainText(message);
        //assertTrue(client.waitNewMessage(100), "There is no message from the server");
        assertEquals(
                client.waitAndGetNewMessage(100), message,
                "Unexpected response from server"
        );
    }

    @Test
    public void sslInnerConfigTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";

        client.setClientSslConfig(
                "src/test/resources/jetty_localhost_client.jks",
                "test1234", true, false, false
        );
        client.connect(sslHost + "echo-ws");
        client.sendPlainText(message);
        //assertTrue(client.waitNewMessage(100), "There is no message from the server");
        assertEquals(
                client.waitAndGetNewMessage(100), message,
                "Unexpected response from server"
        );
    }
}
