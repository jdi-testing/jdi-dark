package com.epam.jdi.services;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

@ServiceDomain("https://api.graph.cool/")
public class GQLService {
    @POST("/simple/v1/cjrqzet3c0fc30162tgt8wzf4/")
    @ContentType(io.restassured.http.ContentType.JSON)
    public RestMethod simple;

}