package com.epam.jdi.httptests;


import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.annotations.TrustStore;
import com.epam.http.requests.RestMethod;

import java.util.Arrays;
import java.util.List;


@ServiceDomain("https://localhost:8443")
public class JettyServiceHttps {

    @GET("/hello")
    public static RestMethod getHello;

    @GET("/greet")
    public static RestMethod getGreet;

    @GET("/jsonStore")
    @TrustStore(pathToJks = "src/test/resources/truststore_mjvmobile.jks", password = "test4321")
    public static RestMethod getJsonStore;

    @GET("/products")
    @TrustStore(pathToJks = "src/test/resources/jetty_localhost_client.jks", password = "test1234")
    public static RestMethod getProducts;

    public static List<Product> getProducts() {
        return Arrays.asList(getProducts.callAsData(Product[].class));
    }
}