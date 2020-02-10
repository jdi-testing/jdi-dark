package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.GET;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

public class Config {

    @ContentType(JSON)
    @GET("https://www.gmail.com")
    public static RestMethod redirect;

    @ContentType(JSON)
    @GET("http://jsonplaceholder.typicode.com/todos/1")
    public static RestMethod listener;

}
