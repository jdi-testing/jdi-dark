package com.epam.jdi.services.websockets;

import com.epam.jdi.dto.Item;
import com.epam.jdi.http.WebSocketClient;

import javax.websocket.ClientEndpoint;

@ClientEndpoint(
        decoders = ItemDecoder.class,
        encoders = ItemEncoder.class
)
public class WSItemClient extends WebSocketClient {

    @Override
    public Item waitAndGetNewMessage(int millis) throws InterruptedException {
        return (Item) super.waitAndGetNewMessage(millis);
    }
}
