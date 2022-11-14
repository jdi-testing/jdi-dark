package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://postman-echo.com/")
public class PostmanAuth {

    @ContentType(JSON)
    @GET("basic-auth")
    @Header(name = "Authorization", value = "Basic cG9zdG1hbjpwYXNzd29yZA==")
    public RestMethod authBase;
    @ContentType(JSON)
    @GET("basic-auth")
    public RestMethod auth2;

    @ContentType(JSON)
    @GET("basic-auth")
    public RestMethod authBaseForm;

    @ContentType(JSON)
    @GET("digest-auth")
    public RestMethod authDigest;

    @ContentType(JSON)
    @GET("auth/hawk")
    public RestMethod authHawk;

    @ContentType(JSON)
    @GET("oauth1")
    public RestMethod oauth;
}
