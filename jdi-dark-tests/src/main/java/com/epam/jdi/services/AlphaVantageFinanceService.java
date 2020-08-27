package com.epam.jdi.services;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

@ServiceDomain("https://www.alphavantage.co/query")
public class AlphaVantageFinanceService {
    @GET public static RestMethod getQuotes;
}
