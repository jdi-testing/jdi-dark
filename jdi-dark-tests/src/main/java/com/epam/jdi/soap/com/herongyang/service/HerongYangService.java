package com.epam.jdi.soap.com.herongyang.service;

import com.epam.http.annotations.*;
import com.epam.http.requests.SoapMethod;

@ServiceDomain("http://www.herongyang.com/Service/")
public class HerongYangService {

    @POST("")
    @URL("http://www.herongyang.com/Service/Hello_SOAP_11.php")
    public static SoapMethod<String, String> hello;

    @POST("")
    @URL("http://www.herongyang.com/Service/Registration.php")
    @SOAPNamespace("http://www.herongyang.com/Service/")
    public static SoapMethod<RegistrationRequest, RegistrationResponse> registration;

    @POST("")
    @URL("http://www.herongyang.com/Service/RefillOrder.php")
    @SOAPNamespace("http://www.herongyang.com/Service/")
    public static SoapMethod<RefillOrderRequest, RefillOrderResponse> refillOrder;

    @POST("")
    @SOAP12
    @URL("http://www.herongyang.com/Service/Registration12.php")
    @SOAPNamespace("http://www.herongyang.com/Service/")
    public static SoapMethod<RegistrationRequest, RegistrationResponse> registration12;

    @POST("")
    @SOAP12
    @URL("http://www.herongyang.com/Service/RefillOrder.php")
    @SOAPNamespace("http://www.herongyang.com/Service/")
    public static SoapMethod<RefillOrderRequest, RefillOrderResponse> refillOrder12;

}
