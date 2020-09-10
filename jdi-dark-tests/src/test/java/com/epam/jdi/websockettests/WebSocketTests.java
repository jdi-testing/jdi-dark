package com.epam.jdi.websockettests;

import com.epam.http.logger.ILogger;
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

import static com.epam.http.logger.HTTPLogger.instance;
import static org.testng.Assert.assertEquals;

public class WebSocketTests {
    private Server server;
    private final WebSocketClient client = new WebSocketClient();
    private final TrelloClient client1 = new TrelloClient();
    private static final ILogger logger = instance("JDI_WS_tests");

    @BeforeClass
    public void init() throws DeploymentException {
        server = new Server(WSEchoServer.class);
        server.start();
        logger.info("Server just've been started");
    }

    @Test
    public void echoTest() throws DeploymentException, IOException, URISyntaxException, InterruptedException {
        String message = "{\"text\":\"Simple text test message\"}";

        client.connect("ws://localhost:8025/echo-ws");
        client.sendPlainText(message);
//        Thread.sleep(1000);
//        System.out.println("!!!" + client.getNewMessage());
        System.out.println("!!!" + client.waitNewMessage(1000));
//        assertEquals(client.waitNewMessage(1000), message, "Unexpected response from server");
        for(String mes: client.getMessages()) {
            System.out.println("!" + mes);
        }
        client.sendPlainText(message + "!!");
    }

    @Test
    public void trelloEchoTest() throws DeploymentException, IOException, URISyntaxException, InterruptedException {
        String message = "{\"text\":\"Simple text test message\"}";

        client1.connect("ws://localhost:8025/echo-ws");
        client1.sendMessage(message);
        Thread.sleep(1000);
        assertEquals(client1.waitAndGetNewMessage(1).toString(), message, "Unexpected response from server");
    }

    @AfterClass
    public void tearDown() throws IOException {
        logger.info("Tests ended, clearing prerequisites");
        server.stop();
    }
}
