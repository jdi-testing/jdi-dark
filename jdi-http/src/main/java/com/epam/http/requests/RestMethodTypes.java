package com.epam.http.requests;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;

import java.util.function.Function;

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
