package com.epam.jdi.services.websockets;

import com.epam.http.logger.ILogger;
import com.epam.jdi.dto.Item;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.epam.http.logger.HTTPLogger.instance;

@ServerEndpoint(
        value = "/item-ws",
        decoders = ItemDecoder.class,
        encoders = ItemEncoder.class
)
public class WSItemServer {
    private static final ILogger logger = instance("JDI_WS_Server");

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        logger.info("Connection is opened");
    }

    @OnMessage
    public Item onItemMessage(Item message, Session session){
        logger.info("Received 'Item' message: " + message);
        return message;
    }

    @OnMessage
    public ByteBuffer onBinaryMessage(ByteBuffer message, Session session){
        logger.info("Received binary message: " + new String(message.array(), StandardCharsets.UTF_8));
        return message;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Session closed with reason: " + closeReason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error(throwable.getMessage());
    }
}
