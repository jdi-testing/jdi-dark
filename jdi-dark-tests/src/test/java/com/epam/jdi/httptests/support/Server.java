package com.epam.jdi.httptests.support;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String args[]) throws InterruptedException {
        BaeldungImpl implementor = new BaeldungImpl();
        String address = "http://localhost:8080/baeldung";
        Endpoint.publish(address, implementor);
        Thread.sleep(60 * 1000);
        System.exit(0);
    }
}