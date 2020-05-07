package com.epam.jdi.http;

import com.epam.http.logger.ILogger;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.epam.http.logger.HTTPLogger.instance;

public class JdiWSServer extends Endpoint {

    public static ILogger logger = instance("JDI_WS");
    public WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
    public CountDownLatch latch;
    public Queue<JsonElement> receivedMessages = new LinkedList<JsonElement>();
    public JsonElement newMessage;
    private Session session;

    public void connect(URI path) throws IOException, DeploymentException {
        webSocketContainer.connectToServer(this, path);
    }

    public void connect(String path) throws IOException, DeploymentException, URISyntaxException {
        logger.info("Connect to: " + path);
        connect(new URI(path));
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.session.getBasicRemote();
        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String s) {
                logger.info("Get message:\n" + s);
                newMessage = JsonParser.parseString(s);
                receivedMessages.add(newMessage);
                latch.countDown();
            }
        });
    }

    public void sendMessage(String s) throws IOException {
        logger.info("Send message:\n" + s);
        session.getBasicRemote().sendText(s);
    }

    public void waitNewMessage(int sec) throws InterruptedException {
        this.latch = new CountDownLatch(1);
        this.latch.await(sec, TimeUnit.SECONDS);
    }

    public void waitNewMessages(int count, int sec) throws InterruptedException {
        this.latch = new CountDownLatch(count);
        this.latch.await(sec, TimeUnit.SECONDS);
    }

    public JsonObject getNewMessageAsJsonObject() {
        return newMessage.getAsJsonObject();
    }

    public void clearMessages() {
        receivedMessages.clear();
    }

}
