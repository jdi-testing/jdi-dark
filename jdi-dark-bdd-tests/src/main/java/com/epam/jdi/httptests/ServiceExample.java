package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.Header;
import com.epam.http.annotations.Headers;
import com.epam.http.annotations.PATCH;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.PUT;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("http://httpbin.org/")
public class ServiceExample {
    @ContentType(JSON)
    @GET("/get")
    @Headers({
            @Header(name = "Name", value = "Roman"),
            @Header(name = "Id", value = "Test")
    })
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
