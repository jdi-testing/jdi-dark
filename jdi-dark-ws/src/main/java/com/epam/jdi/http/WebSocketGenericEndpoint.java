package com.epam.jdi.http;

import com.epam.http.logger.ILogger;
import org.glassfish.tyrus.client.ClientManager;

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
 * Abstract class for implementing web socket custom clients,
 * that exchange abstract custom objects over text or binary messages.
 * In order to use it properly you should extend your WebSocket client class from {@code WebSocketGenericEndpoint<T>}
 * and pass your class type, that represents an object being sent as message, as type parameter to it.
 * Then implement onMessage method(s) and annotate them with {@link OnMessage}.
 * Annotate your WebSocket client class with {@link ClientEndpoint}.
 * Specify your {@link Encoder} and {@link Decoder} classes as annotation parameters,
 * if you going to send and receive some custom classes.
 * @param <T> object message type
 */
public abstract class WebSocketGenericEndpoint<T> {
    protected static final ILogger logger = instance("JDI_WS");
    protected Session session;
    protected CountDownLatch latch;
    protected final ClientManager client = ClientManager.createClient();
    protected T lastMessage;
    protected final Queue<T> messages = new LinkedList<>();

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
        logger.info("Close connection");
        session.close(new CloseReason(
                CloseReason.CloseCodes.GOING_AWAY, "Going away."
        ));
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        logger.info("Connection is opened");
        this.session = session;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Session closed with reason: " + closeReason.getReasonPhrase());
        client.shutdown();
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error(throwable.getMessage());
    }

    public void sendMessage(T message) throws IOException, EncodeException {
        logger.info("Send Object");
        session.getBasicRemote().sendObject(message);
    }

    public void sendPlainText(String message) throws IOException {
        logger.info("Send text");
        session.getBasicRemote().sendText(message);
    }

    public void sendObject(Object object) throws IOException, EncodeException {
        logger.info("Send Object");
        session.getBasicRemote().sendObject(object);
    }

    public void sendBinary(ByteBuffer data) throws IOException {
        logger.info("Send Binary");
        session.getBasicRemote().sendBinary(data);
    }

    public boolean waitNewMessages(int count, int millis) throws InterruptedException {
        latch = new CountDownLatch(count);
        return latch.await(millis, TimeUnit.MILLISECONDS);
    }

    public boolean waitNewMessage(int millis) throws InterruptedException {
        return waitNewMessages(1, millis);
    }

    public T waitAndGetNewMessage(int millis) throws InterruptedException {
        waitNewMessage(millis);
        return lastMessage;
    }

    public T getLastMessage() {
        return lastMessage;
    }

    public Queue<T> getMessages() {
        return messages;
    }
}
