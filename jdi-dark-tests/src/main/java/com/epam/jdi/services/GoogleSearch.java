package com.epam.jdi.services;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

@ServiceDomain("https://google.com/search")
public class GoogleSearch {

    @GET public static RestMethod search;
    @GET public  RestMethod searchInstanceMethod;

}
