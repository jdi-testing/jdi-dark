package com.epam.jdi.services;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

@ServiceDomain("https://api.ratesapi.io/")
public class CurrencyRatesService {


@GET("/latest")
 public RestMethod latest;

    @GET("/{date}")
    public RestMethod historical;
}
