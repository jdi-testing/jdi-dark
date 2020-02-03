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
    static RestMethod<PostmanInfo> authBase;

    @ContentType(JSON)
    @GET("basic-auth")
    static RestMethod<PostmanInfo> authBaseForm;

    @ContentType(JSON)
    @GET("digest-auth")
    static RestMethod<PostmanAuth> authDigest;

    @ContentType(JSON)
    @GET("auth/hawk")
    static RestMethod<PostmanInfo> authHawk;

    @ContentType(JSON)
    @GET("oauth1")
    static RestMethod<PostmanInfo> oauth;
}
