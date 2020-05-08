package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;
import com.epam.jdi.dto.Info;

import static io.restassured.http.ContentType.HTML;
import static io.restassured.http.ContentType.JSON;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
@ServiceDomain("https://httpbin.org/")
public class ServiceExample {
    @ContentType(JSON) @GET("/get")
    @Header(name = "Name", value = "Roman")
    @Header(name = "Id", value = "Test")
    public static RestMethod getInfo;

    public static Info getInfo() {
        return getInfo.callAsData(Info.class);
    }

    @Header(name = "Type", value = "Test")
    @POST("/post")
    public RestMethod postMethod;

    public @PUT("/put") RestMethod putMethod;
    public @PATCH("/patch") RestMethod patch;
    public @DELETE("/delete") RestMethod delete;
    public @GET("/status/{status}") RestMethod status;

    public @GET("/status/{status}?q={value}") RestMethod statusWithQuery;
    public @PUT("/get?q=1") RestMethod getMethodWithQuery;

    @ContentType(HTML) @GET("/html")
    public RestMethod getHTMLMethod;

    @Cookie(name = "session_id", value = "1234")
    @Cookie(name = "hello", value = "world")
    @GET("/cookies")
    public RestMethod getCookies;

    @GET("/basic-auth/user/password")
    public RestMethod getWithAuth;
}
