package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.annotations.QueryParameters;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

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
    static RestMethod<Hello> postReflect;

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
    static RestMethod<Hello> getHello;

    @QueryParameters({
            @QueryParameter(name = "firstName", value = "John"),
            @QueryParameter(name = "lastName", value = "Doe")
    })
    @GET("/greetXML")
    static RestMethod<Greeting> getGreetXml;

    @GET("/mimeTypeWithPlusJson")
    static RestMethod<Message> getMimeType;
}
