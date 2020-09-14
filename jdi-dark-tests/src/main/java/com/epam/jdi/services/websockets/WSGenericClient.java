package com.epam.jdi.services.websockets;

import com.epam.jdi.http.WebSocketGenericClient;

import javax.websocket.ClientEndpoint;

@ClientEndpoint
public class WSGenericClient<T> extends WebSocketGenericClient<T> {
}
