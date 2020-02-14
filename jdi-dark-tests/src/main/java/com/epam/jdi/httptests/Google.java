package com.epam.jdi.httptests;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

@ServiceDomain("http://www.google.se")
public class Google {

    @GET("/search?q=query&hl=en")
    public static RestMethod searchGoogle;


}
