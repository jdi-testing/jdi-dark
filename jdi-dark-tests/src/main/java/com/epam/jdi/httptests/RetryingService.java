//package com.epam.jdi.httptests;
//
//import com.epam.http.annotations.GET;
//import com.epam.http.annotations.IgnoreRetry;
//import com.epam.http.annotations.RetryOnFailure;
//import com.epam.http.annotations.ServiceDomain;
//import com.epam.http.requests.RestMethod;
//
//@RetryOnFailure
//@ServiceDomain(value = "http://localhost:8080/")
//public class RetryingService {
//
//    @GET(value = "502")
//    public static RestMethod get502;
//
//    @GET(value = "503")
//    @RetryOnFailure(numberOfAttempts = 2)
//    public static RestMethod get503;
//
//    @GET(value = "451")
//    @RetryOnFailure(numberOfAttempts = 6, errorCodes = 451)
//    public static RestMethod get451;
//
//    @GET(value = "502")
//    @IgnoreRetry
//    public static RestMethod ignoreRetrying;
//
//    @GET(value = "502")
//    @RetryOnFailure(errorCodes = 503)
//    public static RestMethod ignoreUnspecifiedStatus;
//}
