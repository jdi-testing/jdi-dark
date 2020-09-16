package com.epam.jdi.http;

import com.epam.http.logger.ILogger;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.epam.http.logger.HTTPLogger.instance;

/**
 * Abstract class for implementing web socket custom clients,
 * that exchange abstract custom objects over text or binary messages.
 * In order to use it properly you should extend your WebSocket client class from {@code WebSocketGenericClient<T>}
 * and pass your class type, that represents an object being sent as message, as type parameter to it.
 * Also you should annotate your WebSocket client class with {@link ClientEndpoint}
 * and specify your {@link Encoder} and {@link Decoder} classes as annotation parameters.
 * @param <T> object message type
 */
public abstract class WebSocketGenericClient<T> {
    private static final ILogger logger = instance("JDI_WS_Client");
    private Session session;
    private CountDownLatch latch;
    private final ClientManager client = ClientManager.createClient();
    private T lastMessage;
    private final List<T> messages = new ArrayList<>();

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

    @OnMessage
    public  void onMessage(T message, Session session) {
        logger.info("Received message");
        lastMessage = message;
        messages.add(lastMessage);
        latch.countDown();
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

    public void sendMessage(T object) throws IOException, EncodeException {
        logger.info("Send Object");
        session.getBasicRemote().sendObject(object);
    }

    public boolean waitNewMessage(int millis) throws InterruptedException {
        latch = new CountDownLatch(1);
        return latch.await(millis, TimeUnit.MILLISECONDS);
    }

    public T waitAndGetNewMessage(int millis) throws InterruptedException {
        waitNewMessage(millis);
        return lastMessage;
    }

    public List<T> getMessages() {
        return messages;
    }
}
