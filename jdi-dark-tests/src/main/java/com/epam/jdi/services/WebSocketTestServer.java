package com.epam.jdi.services;

import com.epam.http.logger.ILogger;

import javax.websocket.*;
import javax.websocket.server.*;

import static com.epam.http.logger.HTTPLogger.instance;

@ServerEndpoint("/test-ws")
public class WebSocketTestServer {
    public static ILogger logger = instance("JDI_WS_Server");

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connection is open");
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        logger.info("Reseived message:" + message);
        return message;
    }

    @OnClose
    public void onClose(Session session) {
        logger.info("Connection is closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error(throwable.getMessage());
    }
}
