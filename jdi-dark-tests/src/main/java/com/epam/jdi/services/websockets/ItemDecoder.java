package com.epam.jdi.services.websockets;

import com.epam.http.logger.ILogger;
import com.epam.jdi.dto.Item;
import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import static com.epam.http.logger.HTTPLogger.instance;

public class ItemDecoder implements Decoder.Text<Item> {
    private static final ILogger logger = instance("JDI_WS_Decoder");
    private static final Gson gson = new Gson();

    @Override
    public Item decode(String s) {
        logger.info("Decoding message");
        return gson.fromJson(s, Item.class);
    }

    @Override
    public boolean willDecode(String s) {
        logger.info("Check if message will decode");
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        logger.info("Initiate decoder");
    }

    @Override
    public void destroy() {
        logger.info("Destroy decoder");
    }
}
