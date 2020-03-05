package com.epam.jdi.httptests;

import com.epam.http.annotations.*;
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
    static RestMethod getInfo;

    public static Info getInfo() {
        return getInfo.callAsData(Info.class);
    }

    @Header(name = "Type", value = "Test")
    @POST("/post")
    RestMethod postMethod;

    @PUT("/put") RestMethod putMethod;
    @PATCH("/patch") RestMethod patch;
    @DELETE("/delete") RestMethod delete;
    @GET("/status/{status}") RestMethod status;

    @GET("/status/{status}?q={value}") RestMethod statusWithQuery;
    @PUT("/get?q=1") RestMethod getMethodWithQuery;

    @ContentType(HTML) @GET("/html")
    RestMethod getHTMLMethod;

    @Cookies({
            @Cookie(name = "session_id", value = "1234"),
            @Cookie(name = "hello", value = "world")
    })
    @GET("/cookies") RestMethod getCookies;

    @GET("/basic-auth/user/password") RestMethod getWithAuth;
}
