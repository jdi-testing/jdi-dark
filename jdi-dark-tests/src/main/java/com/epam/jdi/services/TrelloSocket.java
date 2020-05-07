package com.epam.jdi.services;

import com.epam.jdi.http.JdiWSServer;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/")
public class TrelloSocket extends JdiWSServer {

}
