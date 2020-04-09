package com.epam.jdi.soap.com.herongyang.service;

import com.epam.http.annotations.POST;
import com.epam.http.annotations.SOAPNamespace;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.annotations.URL;
import com.epam.http.requests.SoapMethod;

@ServiceDomain("http://www.herongyang.com/Service/")
public class HerongYangService {

    @POST("")
    @URL("http://www.herongyang.com/Service/Hello_SOAP_11.php")
    public static SoapMethod<String, String> hello;

    @POST("")
    @URL("http://www.herongyang.com/Service/Registration12.php")
    @SOAPNamespace("http://www.herongyang.com/Service/")
    public static SoapMethod<RegistrationRequest, RegistrationResponse> registration;

}
