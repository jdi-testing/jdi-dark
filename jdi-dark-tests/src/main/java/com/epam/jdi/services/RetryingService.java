package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

import java.util.concurrent.TimeUnit;

@RetryOnFailure
@ServiceDomain(value = "http://localhost:8080/")
public class RetryingService {

    @GET(value = "502")
    public static RestMethod get502;

    @GET(value = "503")
    @RetryOnFailure(numberOfRetryAttempts = 2, delay = 1, unit = TimeUnit.SECONDS)
    public static RestMethod get503;

    @GET(value = "451")
    @RetryOnFailure(numberOfRetryAttempts = 6, errorCodes = 451, unit = TimeUnit.NANOSECONDS)
    public static RestMethod get451;

    @GET(value = "502")
    @IgnoreRetry
    public static RestMethod ignoreRetrying;

    @GET(value = "502")
    @RetryOnFailure(errorCodes = 503)
    public static RestMethod ignoreUnspecifiedStatus;
}
