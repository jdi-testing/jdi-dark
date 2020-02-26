package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.Header;
import com.epam.http.annotations.Headers;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://postman-echo.com/")
public class AuthorizationPostman {

    @ContentType(JSON)
    @GET("basic-auth")
    static RestMethod postmanAuthBasic;

    @ContentType(JSON)
    @GET("oauth1")
    static RestMethod postmanAuthCustom;
}
