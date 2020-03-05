package com.epam.jdi.httptests;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.PATCH;
import com.epam.http.annotations.PUT;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.Header;
import com.epam.http.annotations.Headers;
import com.epam.http.annotations.DELETE;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("http://httpbin.org/")
public class ServiceExample {
    public @ContentType(JSON)
    public @GET("/get")
    public @Headers({
            @Header(name = "Name", value = "Roman"),
            @Header(name = "Id", value = "Test")
    })
    R estMethod getMethod;

    public  @Header(name = "Type", value = "Test")
    @POST("/post")
    RestMethod postMethod;

    public @PUT("/put")
    RestMethod putMethod;
    public @PATCH("/patch")
    RestMethod patch;
    public @DELETE("/delete")
    RestMethod delete;
    public @GET("/status/%s")
    RestMethod status;

}
