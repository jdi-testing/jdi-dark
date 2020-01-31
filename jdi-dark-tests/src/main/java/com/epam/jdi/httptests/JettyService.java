package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.Header;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;

@ServiceDomain("http://localhost:8080/")
public class JettyService {

    @GET("/multiCookie")
    static RestMethod getMultiCookie;

    @GET("/setCookies")
    static RestMethod setCookies;

    @GET("/cookie")
    static RestMethod getCookie;

    @POST("/reflect")
    static RestMethod postReflect;

    @GET("/setCommonIdCookies")
    static RestMethod getCommonIdCookies;

    @POST("/greet")
    static RestMethod greetPost;

    @POST("/notexist")
    static RestMethod notFoundedURIPost;

    @POST("/header")
    @Header(name = "MyHeader", value = "Something")
    static RestMethod headerPost;

    @ContentType(JSON)
    @POST("/jsonBody")
    static RestMethod jsonBodyPost;

    @POST("/secured/hello")
    static RestMethod unauthorizedPost;

    @POST("/cookie")
    static RestMethod cookiePost;

    @POST("/param-reflect")
    static RestMethod paramUrlPost;

    @ContentType(TEXT)
    @POST("/body")
    static RestMethod bodyPost;
}
