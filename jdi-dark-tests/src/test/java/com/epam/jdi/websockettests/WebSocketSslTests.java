package com.epam.jdi.websockettests;

import com.epam.jdi.http.WebSocketTextClient;
import com.epam.jdi.services.websockets.WSEchoServer;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WebSocketSslTests {
    private Server server;

    @BeforeClass
    public void init() throws Exception {
        server = new Server();

        String file = "src/test/resources/jetty_localhost_server.jks";
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(file);
        sslContextFactory.setKeyStorePassword("test1234");

        ServerConnector connector = new ServerConnector(server, sslContextFactory);
        connector.setPort(8443);
        server.setConnectors(new Connector[]{connector});

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);
        wscontainer.addEndpoint(WSEchoServer.class);

        server.start();
    }

    @Test
    public void textMessageTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";
        WebSocketTextClient client = new WebSocketTextClient();

        client.connect("wss://localhost:8443/echo-ws");
        client.sendPlainText(message);
        assertTrue(client.waitNewMessage(100));
        assertEquals(
                client.getLastMessage(), message,
                "Unexpected response from server"
        );
        client.close();
    }
}
