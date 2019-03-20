package com.epam.http.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;

import java.util.function.Function;

/**
 * Enumeration of HTTP methods supported by JDI HTTP.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public enum RestMethodTypes {
    GET(RequestSenderOptions::get),
    POST(RequestSenderOptions::post),
    PUT(RequestSenderOptions::put),
    DELETE(RequestSenderOptions::delete),
    PATCH(RequestSenderOptions::patch),
    OPTIONS(RequestSenderOptions::options),
    HEAD(RequestSenderOptions::head);

    public Function<RequestSpecification, Response> method;
    RestMethodTypes(Function<RequestSpecification, Response> method) {
        this.method = method;
    }
}
