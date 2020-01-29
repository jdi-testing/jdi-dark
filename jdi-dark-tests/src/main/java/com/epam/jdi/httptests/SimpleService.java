package com.epam.jdi.httptests;

import com.epam.http.requests.M;

import static io.restassured.http.ContentType.HTML;
import static io.restassured.http.ContentType.JSON;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
@ServiceDomain("https://httpbin.org/")
public class SimpleService {
    @ContentType(JSON) @GET("/get")
    @Headers({
        @Header(name = "Name", value = "Roman"),
        @Header(name = "Id", value = "Test")
    }) M<Info> getInfo;

    @Header(name = "Type", value = "Test")
    @POST("/post") M postMethod;

    @PUT("/put") M putMethod;
    @PATCH("/patch") M patch;
    @DELETE("/delete") M delete;
    @GET("/status/%s") M status;

    @ContentType(HTML) @GET("/html") M getHTMLMethod;
}
