package com.epam.jdi.services;

import com.epam.jdi.http.JdiWSClient;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/")
public class WebSocketClient extends JdiWSClient {

}
