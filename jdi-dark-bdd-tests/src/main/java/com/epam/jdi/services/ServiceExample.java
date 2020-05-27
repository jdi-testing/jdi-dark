package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("http://httpbin.org")
public class ServiceExample {
    @ContentType(JSON)
    @GET("/get")
    @Header(name = "Name", value = "Roman")
    @Header(name = "Id", value = "Test")
    RestMethod getMethod;

    @ContentType(JSON)
    @GET("/get")
    RestMethod get;

    @Header(name = "Type", value = "Test")
    @POST("/post")
    RestMethod postMethod;

    @PUT("/put")
    RestMethod putMethod;
    @PATCH("/patch")
    RestMethod patch;
    @DELETE("/delete")
    RestMethod delete;
    @GET("/status/{status}")
    RestMethod status;

}
