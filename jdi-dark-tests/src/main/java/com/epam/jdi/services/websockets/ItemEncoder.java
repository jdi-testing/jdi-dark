package com.epam.jdi.services.websockets;

import com.epam.http.logger.ILogger;
import com.epam.jdi.dto.Item;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import static com.epam.http.logger.HTTPLogger.instance;

public class ItemEncoder implements Encoder.Text<Item> {
    private static final ILogger logger = instance("JDI_WS_Encoder");
    private static final Gson gson = new Gson();

    @Override
    public String encode(Item item) {
        logger.info("Encoding message");
        return gson.toJson(item);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        logger.info("Initiate encoder");
    }

    @Override
    public void destroy() {
        logger.info("Destroy encoder");
    }
}
