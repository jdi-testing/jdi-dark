package com.epam.jdi.http;

import javax.websocket.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * WebSocket client implementation for simple text interactions via WebSockets
 */
@ClientEndpoint
public class WebSocketTextClient extends WebSocketGenericEndpoint<String> {

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("Received text message");
        lastMessage = message;
        messages.add(message);
        latch.countDown();
    }

    @OnMessage
    public void onMessage(ByteBuffer message, Session session) {
        logger.info("Received binary message");
        lastMessage = new String(message.array(), StandardCharsets.UTF_8);
        messages.add(lastMessage);
        latch.countDown();
    }
}
