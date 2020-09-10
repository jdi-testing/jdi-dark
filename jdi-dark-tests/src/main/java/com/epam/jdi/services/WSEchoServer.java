package com.epam.jdi.services;

import com.epam.http.logger.ILogger;

import javax.websocket.*;
import javax.websocket.server.*;

import static com.epam.http.logger.HTTPLogger.instance;

@ServerEndpoint("/echo-ws")
public class WSEchoServer {
    private static ILogger logger = instance("JDI_WS_Server");

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        logger.info("Connection is opened");
    }

    @OnMessage
    public String onMessage(String message, Session session) throws InterruptedException {
        logger.info("Received message: " + message);
        Thread.sleep(500);
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
