package com.epam.jdi.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.*;

/**
 * WebSocket client implementation for exchanging json objects encoded in text strings
 */
@ClientEndpoint
public class WebSocketJsonClient extends WebSocketGenericEndpoint<JsonElement> {

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("Received text message");
        lastMessage = JsonParser.parseString(message);
        messages.add(lastMessage);
        latch.countDown();
    }

    public JsonObject getNewMessageAsJsonObject() {
        return lastMessage.getAsJsonObject();
    }
}
