package com.epam.jdi.soap;

import com.epam.http.annotations.*;
import com.epam.http.requests.SoapMethod;
import com.epam.jdi.soap.https.geoservices_tamu.GeocodeAddressNonParsed;
import com.epam.jdi.soap.https.geoservices_tamu.GeocodeAddressNonParsedResponse;

@ServiceDomain("https://geoservices.tamu.edu/Services/Geocode/WebService/GeocoderService_V04_01.asmx")
@SOAPNamespace("https://geoservices.tamu.edu/")
public class GeoServices {

    @POST("")
    @SOAPAction("https://geoservices.tamu.edu/GeocodeAddressNonParsed")
    public static SoapMethod<GeocodeAddressNonParsed, GeocodeAddressNonParsedResponse> geocodeAddressNonParsed;

    @POST("")
    @SOAP12
    public static SoapMethod<GeocodeAddressNonParsed, GeocodeAddressNonParsedResponse> geocodeAddressNonParsed12;

}
