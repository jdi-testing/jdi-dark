package com.epam.jdi.websockettests;

import com.epam.jdi.http.WebSocketTextClient;
import com.epam.jdi.services.websockets.WSEchoServer;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.glassfish.tyrus.client.ClientProperties;
import org.glassfish.tyrus.client.SslContextConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WebSocketSslTests {
    private Server server;

    @BeforeClass
    public void init() throws Exception {
        server = new Server();

        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecureScheme("https");
        httpConfig.setSecurePort(8443);

        HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
        httpsConfig.addCustomizer(new SecureRequestCustomizer());

        String file = "src/test/resources/jetty_localhost_server.jks";
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(file);
        sslContextFactory.setKeyStorePassword("test1234");

        ServerConnector https = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(httpsConfig));
        https.setPort(8443);

        ServerConnector http = new ServerConnector(server);
        http.setPort(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        ServerContainer wscontainer = WebSocketServerContainerInitializer.initialize(context);
        wscontainer.addEndpoint(WSEchoServer.class);

        server.setHandler(context);
        server.setConnectors(new Connector[]{https, http});
        server.start();
//        server.dump(System.err);
    }

    @Test
    public void textMessageTest()
            throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        String message = "Simple text test message";
        WebSocketTextClient client = new WebSocketTextClient();

        System.getProperties().put("javax.net.debug", "all");
        System.getProperties().put(SslContextConfigurator.KEY_STORE_FILE, "src/test/resources/jetty_localhost_client.jks");
//        System.getProperties().put(SslContextConfigurator.TRUST_STORE_FILE, "...");
        System.getProperties().put(SslContextConfigurator.KEY_STORE_PASSWORD, "test1234");
//        System.getProperties().put(SslContextConfigurator.TRUST_STORE_PASSWORD, "...");
        SslContextConfigurator config = SslContextConfigurator.DEFAULT_CONFIG;
        client.setClientProperties(Collections.singletonMap(ClientProperties.SSL_ENGINE_CONFIGURATOR, config));

        client.connect("ws://localhost:8081/echo-ws");
        client.sendPlainText(message);
        assertTrue(client.waitNewMessage(100));
        assertEquals(
                client.getLastMessage(), message,
                "Unexpected response from server"
        );
        client.close();
    }

    @AfterClass
    public void tearDown() throws Exception {
        server.stop();
    }
}
