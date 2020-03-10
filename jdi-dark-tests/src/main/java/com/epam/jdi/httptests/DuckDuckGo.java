package com.epam.jdi.httptests;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

@ServiceDomain("https://duckduckgo.com/")
public class DuckDuckGo {
    @GET("")
    static RestMethod simpleGet;
}