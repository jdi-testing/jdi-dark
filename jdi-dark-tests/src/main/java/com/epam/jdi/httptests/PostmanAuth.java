package com.epam.jdi.httptests;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://postman-echo.com/")
public class PostmanAuth {

    @ContentType(JSON)
    @GET("basic-auth")
    @Headers({
            @Header(name = "Authorization", value = "Basic cG9zdG1hbjpwYXNzd29yZA==")
    })

    public static RestMethod authBase;
    @ContentType(JSON)
    @GET("basic-auth")
    public static RestMethod auth2;

    @ContentType(JSON)
    @GET("basic-auth")
    public static RestMethod authBaseForm;

    @ContentType(JSON)
    @GET("digest-auth")
    public static RestMethod authDigest;

    @ContentType(JSON)
    @GET("auth/hawk")
    public static RestMethod authHawk;

    @ContentType(JSON)
    @GET("oauth1")
    public static RestMethod oauth;
}
