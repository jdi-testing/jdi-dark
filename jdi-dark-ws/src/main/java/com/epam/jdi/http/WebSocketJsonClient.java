package com.epam.jdi.http;

import com.epam.http.logger.ILogger;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.epam.http.logger.HTTPLogger.instance;

/**
 * WebSocket client implementation for exchanging json objects encoded in text strings
 */
@ClientEndpoint
public class WebSocketJsonClient extends Endpoint {

    private static final ILogger logger = instance("JDI_WS_Client");
    private final ClientManager client = ClientManager.createClient();
    private Session session;
    private CountDownLatch latch;
    private final List<JsonElement> messages = new ArrayList<>();
    private JsonElement lastMessage;

    public void connect(URI path) throws IOException, DeploymentException {
        logger.info("Connect to: " + path);
        client.connectToServer(this, path);
    }

    public void connect(String path) throws IOException, DeploymentException, URISyntaxException {
        connect(new URI(path));
    }

    public void setClientProperties(Map<String, Object> properties) {
        for(Map.Entry<String, Object> entry: properties.entrySet()) {
            client.getProperties().put(entry.getKey(), entry.getValue());
        }
    }

    public void setClientProperties(Properties properties) {
        for(Map.Entry<Object, Object> entry: properties.entrySet()) {
            client.getProperties().put((String) entry.getKey(), entry.getValue());
        }
    }

    public void close() throws IOException {
        logger.info("Close current session");
        this.session.close(new CloseReason(
                CloseReason.CloseCodes.GOING_AWAY, "Going away."
        ));
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.session.getBasicRemote();
        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String s) {
                logger.info("Received text message");
                lastMessage = JsonParser.parseString(s);
                messages.add(lastMessage);
                latch.countDown();
            }
        });
    }

    @Override
    public void onError(Session session, Throwable throwable) {
        logger.error(throwable.getMessage());
    }

    public void sendPlainText(String text) throws IOException {
        logger.info("Send text");
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

    public void waitNewMessage(int millis) throws InterruptedException {
        this.latch = new CountDownLatch(1);
        this.latch.await(millis, TimeUnit.MILLISECONDS);
    }

    public JsonElement waitAndGetNewMessage(int millis) throws InterruptedException {
        this.latch = new CountDownLatch(1);
        this.latch.await(millis, TimeUnit.MILLISECONDS);
        return lastMessage;
    }

    public void waitNewMessages(int count, int millis) throws InterruptedException {
        this.latch = new CountDownLatch(count);
        this.latch.await(millis, TimeUnit.MILLISECONDS);
    }

    public void waitNewMessageMatches(String regex, int maxMsgCount, int sec) throws InterruptedException {
        for (int i = 1; i <= maxMsgCount; i++) {
            if (waitAndGetNewMessage(sec).getAsString().matches(regex)){
                break;
            }
        }
        logger.info("Message matches '%s' not found", regex);
    }

    public void waitNewMessageContainsText(String text, int maxMsgCount, int sec) throws InterruptedException {
        for (int i = 1; i <= maxMsgCount; i++) {
            if (waitAndGetNewMessage(sec).getAsString().contains(text)){
                break;
            }
        }
        logger.info("Message contains text '%s' not found", text);
    }

    public void waitNewMessageContainsKey(String key, int maxMsgCount, int sec) throws InterruptedException {
        for (int i = 1; i <= maxMsgCount; i++) {
            if (waitAndGetNewMessage(sec).getAsJsonObject().has(key)){
                break;
            }
        }
        logger.info("Message with key '%s' not found", key);
    }

    public JsonObject getNewMessageAsJsonObject() {
        return lastMessage.getAsJsonObject();
    }

    public List<JsonElement> getMessages() {
        return messages;
    }

    public void clearMessages() {
        messages.clear();
    }

}
