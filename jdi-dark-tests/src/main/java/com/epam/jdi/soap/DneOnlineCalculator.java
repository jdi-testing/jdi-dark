package com.epam.jdi.soap;

import com.epam.http.annotations.*;
import com.epam.http.requests.SoapMethod;
import com.epam.jdi.soap.org.tempuri.*;

import java.util.concurrent.TimeUnit;

@RetryOnFailure
@ServiceDomain("http://www.dneonline.com/calculator.asmx")
@SOAPNamespace("http://tempuri.org/")
public class DneOnlineCalculator {

    @POST
    @SOAPAction("http://tempuri.org/Add")
    @RetryOnFailure(numberOfRetryAttempts = 5, delay = 1, unit = TimeUnit.SECONDS)
    public static SoapMethod<Add, AddResponse> add;

    @POST
    @SOAPAction("http://tempuri.org/Divide")
    @RetryOnFailure(numberOfRetryAttempts = 5, delay = 1, unit = TimeUnit.SECONDS)
    public static SoapMethod<Divide, DivideResponse> divide;

    @POST
    @SOAPAction("http://tempuri.org/Multiply")
    @SOAP12
    @RetryOnFailure(numberOfRetryAttempts = 5, delay = 1, unit = TimeUnit.SECONDS)
    public static SoapMethod<Multiply, MultiplyResponse> multiply;

    @POST
    @SOAPAction("http://tempuri.org/Subtract")
    @SOAP12
    @RetryOnFailure(numberOfRetryAttempts = 5, delay = 1, unit = TimeUnit.SECONDS)
    public static SoapMethod<Subtract, SubtractResponse> subtract;
}
