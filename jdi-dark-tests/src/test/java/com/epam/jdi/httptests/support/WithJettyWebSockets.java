package com.epam.jdi.httptests.support;

import com.epam.jdi.services.websockets.WSEchoServer;
import com.epam.jdi.services.websockets.WSItemServer;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.glassfish.tyrus.client.SslContextConfigurator;
import org.glassfish.tyrus.client.SslEngineConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.websocket.server.ServerContainer;

public abstract class WithJettyWebSockets {
    private Server server;
    protected final String host = "ws://localhost:8081";
    protected final String sslHost = "wss://localhost:8443";

    @BeforeClass
    public void init() throws Exception {
        server = new Server();

        HttpConfiguration httpsConfig = new HttpConfiguration();
        httpsConfig.setSecureScheme("https");
        httpsConfig.setSecurePort(8443);

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
        wscontainer.addEndpoint(WSItemServer.class);

        server.setHandler(context);
        server.setConnectors(new Connector[]{https, http});
        server.start();
        server.join();
    }

    @AfterClass
    public void tearDown() throws Exception {
        server.stop();
        server.destroy();
    }

    protected SslEngineConfigurator getClientSslConfig() {
        SslContextConfigurator config = new SslContextConfigurator();
        config.setTrustStoreFile("src/test/resources/jetty_localhost_client.jks");
        config.setTrustStorePassword("test1234");
        SslEngineConfigurator sslEngineConfigurator = new SslEngineConfigurator(config, true, false, false);
        return sslEngineConfigurator.setHostVerificationEnabled(false);
    }
}
