package com.epam.jdi.soap;

import com.epam.http.annotations.POST;
import com.epam.http.annotations.SOAP12;
import com.epam.http.annotations.SOAPNamespace;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.SoapMethod;
import com.epam.jdi.soap.com.dataaccess.webservicesserver.NumberToDollars;
import com.epam.jdi.soap.com.dataaccess.webservicesserver.NumberToDollarsResponse;
import com.epam.jdi.soap.com.dataaccess.webservicesserver.NumberToWords;
import com.epam.jdi.soap.com.dataaccess.webservicesserver.NumberToWordsResponse;

@ServiceDomain("https://www.dataaccess.com/webservicesserver/NumberConversion.wso")
@SOAPNamespace("http://www.dataaccess.com/webservicesserver/")
public class NumberConversionService {

    @POST
    public static SoapMethod<NumberToWords, NumberToWordsResponse> numberToWords;

    @POST
    public static SoapMethod<NumberToDollars, NumberToDollarsResponse> numberToDollars;

    @POST
    @SOAP12
    public static SoapMethod<NumberToWords, NumberToWordsResponse> numberToWords12;

    @POST
    @SOAP12
    public static SoapMethod<NumberToDollars, NumberToDollarsResponse> numberToDollars12;


}
