package com.epam.jdi.soap;

import com.epam.http.annotations.*;
import com.epam.http.requests.SoapMethod;
import com.epam.jdi.soap.org.tempuri.*;

@ServiceDomain("http://www.dneonline.com/calculator.asmx")
@SOAPNamespace("http://tempuri.org/")
public class DneOnlineCalculator {

    @POST
    @SOAPAction("http://tempuri.org/Add")
    public SoapMethod<Add, AddResponse> add;

    @POST
    @SOAPAction("http://tempuri.org/Divide")
    public SoapMethod<Divide, DivideResponse> divide;

    @POST
    @SOAPAction("http://tempuri.org/Multiply")
    @SOAP12
    public SoapMethod<Multiply, MultiplyResponse> multiply;

    @POST
    @SOAPAction("http://tempuri.org/Subtract")
    @SOAP12
    public SoapMethod<Subtract, SubtractResponse> subtract;
}
