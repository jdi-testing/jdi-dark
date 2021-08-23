package com.epam.jdi.services.websockets;

import com.epam.jdi.dto.Item;
import com.epam.jdi.http.WebSocketGenericEndpoint;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;

/**
 * Example of client implementation for custom protocol over WebSockets,
 * that uses custom class as message type.
 */
@ClientEndpoint(
        decoders = ItemDecoder.class,
        encoders = ItemEncoder.class
)
public class WSItemClient extends WebSocketGenericEndpoint<Item> {

    @OnMessage
    public void onMessage(Item message, Session session) {
        //logger.info("Received message");
        lastMessage = message;
        messages.add(lastMessage);
        latch.countDown();
    }
}
