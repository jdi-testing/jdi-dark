package com.epam.jdi.httptests;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;
import org.omg.CORBA.PUBLIC_MEMBER;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://postman-echo.com/")
public class PostmanAuth {

    @ContentType(JSON)
    @GET("basic-auth")
    @Headers({
            @Header(name = "Authorization", value = "Basic cG9zdG1hbjpwYXNzd29yZA==")
    })

    static RestMethod authBase;
    PUBLIC @ContentType(JSON)
    @GET("basic-auth")
    static RestMethod auth2;

    public @ContentType(JSON)
    public @GET("basic-auth")
    static RestMethod authBaseForm;

    public @ContentType(JSON)
    public @GET("digest-auth")
    static RestMethod authDigest;

    public @ContentType(JSON)
    public @GET("auth/hawk")
    static RestMethod authHawk;

    public @ContentType(JSON)
    public @GET("oauth1")
    static RestMethod oauth;
}
