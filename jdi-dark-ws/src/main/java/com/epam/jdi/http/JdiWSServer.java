package com.epam.jdi.http;

import com.epam.http.logger.ILogger;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.epam.http.logger.HTTPLogger.instance;

public class JdiWSServer extends Endpoint {

    public static ILogger logger = instance("JDI_WS");
    public WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
    public Session session;
    public CountDownLatch latch;
    public Queue<JsonElement> receivedMessages = new LinkedList<>();
    public JsonElement newMessage;

    public void connect(URI path) throws IOException, DeploymentException {
        webSocketContainer.connectToServer(this, path);
    }

    public void connect(String path) throws IOException, DeploymentException, URISyntaxException {
        logger.info("Connect to: " + path);
        connect(new URI(path));
    }

    public void closeSession() throws IOException {
        logger.info("Close current session");
        this.session.close();
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.session.getBasicRemote();
        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String s) {
                logger.info("Receive message:\n" + s);
                newMessage = JsonParser.parseString(s);
                receivedMessages.add(newMessage);
                latch.countDown();
            }
        });
    }

    public void sendMessage(String text) throws IOException {
        logger.info("Send message:\n" + text);
        session.getBasicRemote().sendText(text);
    }

    public void sendObject(Object object) throws IOException, EncodeException {
        logger.info("Send Object");
        session.getBasicRemote().sendObject(object);
    }

    public void sendBinary(ByteBuffer data) throws IOException {
        logger.info("Send Binary");
        session.getBasicRemote().sendBinary(data);
    }

    public void waitNewMessage(int sec) throws InterruptedException {
        this.latch = new CountDownLatch(1);
        this.latch.await(sec, TimeUnit.SECONDS);
    }

    public JsonElement waitAndGetNewMessage(int sec) throws InterruptedException {
        this.latch = new CountDownLatch(1);
        this.latch.await(sec, TimeUnit.SECONDS);
        return newMessage;
    }

    public void waitNewMessages(int count, int sec) throws InterruptedException {
        this.latch = new CountDownLatch(count);
        this.latch.await(sec, TimeUnit.SECONDS);
    }

    public void waitNewMessageMatches(String regex, int maxMsgCount, int sec) throws InterruptedException {
        for (int i = 1; i <= maxMsgCount; i++) {
            if (waitAndGetNewMessage(sec).getAsString().matches(regex)){
                break;
            }
        }
        logger.info(String.format("Message matches '%s' not found", regex));
    }

    public void waitNewMessageContainsText(String text, int maxMsgCount, int sec) throws InterruptedException {
        for (int i = 1; i <= maxMsgCount; i++) {
            if (waitAndGetNewMessage(sec).getAsString().contains(text)){
                break;
            }
        }
        logger.info(String.format("Message contains text '%s' not found", text));
    }

    public void waitNewMessageContainsKey(String key, int maxMsgCount, int sec) throws InterruptedException {
        for (int i = 1; i <= maxMsgCount; i++) {
            if (waitAndGetNewMessage(sec).getAsJsonObject().has(key)){
                break;
            }
        }
        logger.info(String.format("Message with key '%s' not found", key));
    }

    public JsonObject getNewMessageAsJsonObject() {
        return newMessage.getAsJsonObject();
    }

    public void clearMessages() {
        receivedMessages.clear();
    }

}
