package com.epam.jdi.httptests;

import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

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

    @DELETE("/cookie")
    static RestMethod deleteCookie;

    @DELETE("/greet")
    static RestMethod deleteGreet;

    @DELETE("/body")
    static RestMethod deleteBody;
}
