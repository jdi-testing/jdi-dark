package com.epam.jdi.soap;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String args[]) throws InterruptedException {
        SoapServiceImpl implementor = new SoapServiceImpl();
        String address = "http://localhost:8080/soap_service";
        Endpoint.publish(address, implementor);
        Thread.sleep(60 * 1000);
        System.exit(0);
    }
}