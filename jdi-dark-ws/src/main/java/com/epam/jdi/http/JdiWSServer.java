package com.epam.jdi.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JdiWSServer extends Endpoint {

    public WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
    public CountDownLatch latch = new CountDownLatch(1);
    public JsonElement receivedMessage;
    private Session session;

    public void connect(URI path) throws IOException, DeploymentException {
        webSocketContainer.connectToServer(this, path);
    }

    public void connect(String path) throws IOException, DeploymentException, URISyntaxException {
        connect(new URI(path));
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.session.getBasicRemote();
        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String s) {
                System.out.println("Get message:\n" + s);
                receivedMessage = JsonParser.parseString(s);
                latch.countDown();
            }
        });
    }

    public void sendMessage(String s) throws IOException {
        receivedMessage = null;
        session.getBasicRemote().sendText(s);
    }

    public void waitNewMessage(int sec) throws InterruptedException {
        this.latch.await(sec, TimeUnit.SECONDS);
    }
}
