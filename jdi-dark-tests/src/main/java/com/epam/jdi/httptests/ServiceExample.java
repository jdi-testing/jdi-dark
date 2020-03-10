package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.Cookie;
import com.epam.http.annotations.Cookies;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.Header;
import com.epam.http.annotations.Headers;
import com.epam.http.annotations.PATCH;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.PUT;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.HTML;
import static io.restassured.http.ContentType.JSON;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
@ServiceDomain("https://httpbin.org/")
public class ServiceExample {
    @ContentType(JSON) @GET("/get")
    @Headers({
        @Header(name = "Name", value = "Roman"),
        @Header(name = "Id", value = "Test")
    })
    public static RestMethod getInfo;

    public static Info getInfo() {
        return getInfo.callAsData(Info.class);
    }

    @Header(name = "Type", value = "Test")
    @POST("/post")
    public RestMethod postMethod;

    @PUT("/put")
    public RestMethod putMethod;
    @PATCH("/patch")
    public RestMethod patch;
    @DELETE("/delete")
    public RestMethod delete;
    @GET("/status/{status}")
    public RestMethod status;

    @GET("/status/{status}?q={value}")
    public RestMethod statusWithQuery;
    @PUT("/get?q=1") RestMethod getMethodWithQuery;

    @ContentType(HTML) @GET("/html")
    public RestMethod getHTMLMethod;

    @Cookies({
            @Cookie(name = "session_id", value = "1234"),
            @Cookie(name = "hello", value = "world")
    })
    @GET("/cookies")
    public RestMethod getCookies;

    @GET("/basic-auth/user/password")
    public RestMethod getWithAuth;
}
