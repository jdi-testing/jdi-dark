package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.Header;
import com.epam.http.annotations.Headers;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.PUT;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.annotations.QueryParameters;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;
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
    static RestMethod<Hello> postReflect;

    @GET("/setCommonIdCookies")
    static RestMethod getCommonIdCookies;

    @GET("/header")
    @Header(name = "HeaderTestName", value = "HeaderTestValue")
    static RestMethod getWithSingleHeaderInRequest;

    @GET("/header")
    @Headers({
            @Header(name = "Header1", value = "Value1"),
            @Header(name = "Header2", value = "Value2")
    })
    static RestMethod getWithMultipleHeadersInRequest;

    @DELETE("/cookie")
    static RestMethod deleteCookie;

    @DELETE("/greet")
    static RestMethod deleteGreet;

    @DELETE("/body")
    @ContentType(TEXT)
    static RestMethod deleteBody;

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

    @POST("/greet")
    static RestMethod greetPost;

    @POST("/notexist")
    static RestMethod notFoundedURIPost;

    @POST("/header")
    @Header(name = "MyHeader", value = "Something")
    static RestMethod headerPost;

    @GET("/hello")
    static RestMethod<Hello> getHello;

    @QueryParameters({
            @QueryParameter(name = "firstName", value = "John"),
            @QueryParameter(name = "lastName", value = "Doe")
    })
    @GET("/greetXML")
    static RestMethod<Greeting> getGreetXml;

    @GET("/mimeTypeWithPlusJson")
    static RestMethod<Message> getMimeType;

    @ContentType(URLENC)
    @POST("/greetXML")
    static RestMethod postGreetXml;
}
