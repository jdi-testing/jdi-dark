package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.SoapMethod;
import com.epam.jdi.soap.org.tempuri.*;

@ServiceDomain("http://www.dneonline.com/calculator.asmx")
public class DneOnlineCalculator {

    @POST("")
    @SOAPAction("http://tempuri.org/Add")
    @SOAPNamespace("http://tempuri.org/")
    public static SoapMethod<Add, AddResponse> add;

    @POST("")
    @SOAPAction("http://tempuri.org/Divide")
    @SOAPNamespace("http://tempuri.org/")
    public static SoapMethod<Divide, DivideResponse> divide;

    @POST("")
    @SOAPAction("http://tempuri.org/Multiply")
    @SOAPNamespace("http://tempuri.org/")
    public static SoapMethod<Multiply, MultiplyResponse> multiply;

    @POST("")
    @SOAPAction("http://tempuri.org/Subtract")
    @SOAPNamespace("http://tempuri.org/")
    public static SoapMethod<Subtract, SubtractResponse> subtract;
}
