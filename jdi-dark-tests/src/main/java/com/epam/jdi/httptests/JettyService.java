package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.PUT;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.annotations.QueryParameters;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.TEXT;
import static io.restassured.http.ContentType.URLENC;

@ServiceDomain("http://localhost:8080/")
public class JettyService {

    @GET("/multiCookie")
    static RestMethod getMultiCookie;

    @GET("/setCookies")
    static RestMethod setCookies;

    @GET("/cookie")
    static RestMethod getCookie;

    @PUT("/cookie")
    static RestMethod putCookie;

    @POST("/reflect")
    static RestMethod postReflect;

    @GET("/setCommonIdCookies")
    static RestMethod getCommonIdCookies;

    @DELETE("/cookie")
    static RestMethod deleteCookie;

    @DELETE("/greet")
    static RestMethod deleteGreet;

    @DELETE("/body")
    @ContentType(TEXT)
    static RestMethod deleteBody;

    @GET("/hello")
    static RestMethod getHello;

    @ContentType(URLENC)
    @POST("/greetXML")
    static RestMethod postGreetXml;
}