package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.URL;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

public class Config {

    @ContentType(JSON)
    @URL("https://www.gmail.com")
    @GET("/")
    public static RestMethod redirect;

}
