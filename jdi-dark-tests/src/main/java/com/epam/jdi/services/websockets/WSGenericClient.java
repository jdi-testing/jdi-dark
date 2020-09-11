package com.epam.jdi.services.websockets;

import com.epam.jdi.http.WSGenericEndpoint;

import javax.websocket.ClientEndpoint;

@ClientEndpoint
public class WSGenericClient<T> extends WSGenericEndpoint<T> {
}
