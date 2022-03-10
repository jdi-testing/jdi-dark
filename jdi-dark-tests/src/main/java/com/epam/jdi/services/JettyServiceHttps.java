package com.epam.jdi.services;


import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.annotations.TrustStore;
import com.epam.http.requests.RestMethod;
import com.epam.jdi.dto.Product;

import java.util.Arrays;
import java.util.List;


@ServiceDomain("https://localhost:8443")
public class JettyServiceHttps {

    @GET("/hello")
    public RestMethod getHello;

    @GET("/secured/hello")
    public RestMethod getSecuredHello;

    @GET("/greet")
    public RestMethod getGreet;

    @GET("/jsonStore")
    @TrustStore(pathToJks = "src/test/resources/truststore_mjvmobile.jks", password = "test4321")
    public RestMethod getJsonStore;

    @GET("/products")
    @TrustStore(pathToJks = "src/test/resources/jetty_localhost_client.jks", password = "test1234")
    public RestMethod getProducts;

    public List<Product> getProducts() {
        return Arrays.asList(getProducts.callAsData(Product[].class));
    }

}