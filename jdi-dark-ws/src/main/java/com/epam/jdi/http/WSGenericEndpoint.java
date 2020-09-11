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
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.epam.http.logger.HTTPLogger.instance;

public class WSGenericEndpoint<T> extends Endpoint {

    private static final ILogger logger = instance("JDI_WS");
    private final WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
    private Session session;
    private CountDownLatch latch;
    private final List<T> receivedMessages = new LinkedList<>();
    private T newMessage;
    private final MessageHandler.Whole<T> messageHandler;

    public WSGenericEndpoint() {
        messageHandler = s -> {
            logger.info("Receive message:\n" + s);
            newMessage = s;
            receivedMessages.add(newMessage);
            latch.countDown();
        };
    }

    public void connect(URI path) throws IOException, DeploymentException {
        logger.info("Connect to: " + path);
        webSocketContainer.connectToServer(this, path);
    }

    public void connect(String path) throws IOException, DeploymentException, URISyntaxException {
        connect(new URI(path));
    }

    public void close() throws IOException {
        logger.info("Close connection");
        session.close(new CloseReason(
                CloseReason.CloseCodes.GOING_AWAY, "Going away."
        ));
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.session.addMessageHandler(messageHandler);
    }

    public void sendMessage(T message) throws IOException, EncodeException {
        logger.info("Send message:\n" + message);
        session.getBasicRemote().sendObject(message);
    }

    public void waitNewMessage(int millis) throws InterruptedException {
        this.latch = new CountDownLatch(1);
        this.latch.await(millis, TimeUnit.MILLISECONDS);
    }

    public T waitAndGetNewMessage(int millis) throws InterruptedException {
        this.latch = new CountDownLatch(1);
        this.latch.await(millis, TimeUnit.MILLISECONDS);
        return newMessage;
    }

    public void waitNewMessages(int count, int millis) throws InterruptedException {
        this.latch = new CountDownLatch(count);
        this.latch.await(millis, TimeUnit.MILLISECONDS);
    }

    public void clearMessages() {
        receivedMessages.clear();
    }

}
