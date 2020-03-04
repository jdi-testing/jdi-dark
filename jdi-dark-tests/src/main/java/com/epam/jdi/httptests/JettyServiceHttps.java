package com.epam.jdi.httptests;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

@ServiceDomain("https://localhost:8443")
public class JettyServiceHttps {

    @GET("/hello")
    public static RestMethod getHello;
}