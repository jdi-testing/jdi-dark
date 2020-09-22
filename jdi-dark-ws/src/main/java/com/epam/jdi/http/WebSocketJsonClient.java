package com.epam.jdi.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.*;

/**
 * WebSocket client implementation for exchanging json objects encoded as text strings
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

    public JsonObject getLastMessageAsJsonObject() {
        return lastMessage.getAsJsonObject();
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
}
