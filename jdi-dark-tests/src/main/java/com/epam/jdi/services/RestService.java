package com.epam.jdi.services;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.RetryOnFailure;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@RetryOnFailure
@ServiceDomain(value = "http://localhost:8080/")
public class RestService {

    @GET(value = "status")
    public static RestMethod getStatus;

    @POST(value = "actuator/shutdown")
    @ContentType(JSON)
    public static RestMethod shutDown;

}
