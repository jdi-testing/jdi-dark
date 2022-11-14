package com.epam.jdi.httptests.examples.soap;

import com.epam.jdi.soap.GeoServices;
import com.epam.jdi.soap.https.geoservices_tamu.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.http.requests.ServiceInit.init;

public class GeoServicesTests {

    @BeforeTest
    public void before() {
        init(GeoServices.class);
    }

    @Test
    public void checkGeocodeAddressNonParsed() {
        GeocodeAddressNonParsedResponse response = GeoServices.geocodeAddressNonParsed.callSoap(new GeocodeAddressNonParsed()
                .withStreetAddress("9355 Burton Way")
                .withCity("Beverly Hills")
                .withState("ca")
                .withZip("90210")
                .withApiKey("demo")
                .withVersion(4.01)
                .withShouldCalculateCensus(true)
                .withCensusYear(CensusYear.ALL_AVAILABLE)
                .withShouldNotStoreTransactionDetails(false)
        );
        Assertions.assertThat(response.getGeocodeAddressNonParsedResult().getQueryStatusCodes()).isEqualTo(200);
        List<WebServiceGeocodeQueryResult> results = response.getGeocodeAddressNonParsedResult().getWebServiceGeocodeQueryResults().getWebServiceGeocodeQueryResult();
        List<CensusOutputRecord> censusOutputRecords = results.get(0).getCensusRecords().getCensusOutputRecord();
        Assertions.assertThat(censusOutputRecords.stream().map(CensusOutputRecord::getCensusYear).collect(Collectors.toList()))
                .isEqualTo(Arrays.asList(CensusYear.NINETEEN_NINETY, CensusYear.TWO_THOUSAND, CensusYear.TWO_THOUSAND_TEN));
    }

    @Test
    public void checkGeocodeAddressNonParsed12() {
        GeocodeAddressNonParsedResponse response = GeoServices.geocodeAddressNonParsed12.callSoap(new GeocodeAddressNonParsed()
                .withStreetAddress("9355 Burton Way")
                .withCity("Beverly Hills")
                .withState("ca")
                .withZip("90210")
                .withApiKey("demo")
                .withVersion(4.01)
                .withShouldCalculateCensus(true)
                .withCensusYear(CensusYear.ALL_AVAILABLE)
                .withShouldNotStoreTransactionDetails(false)
        );
        Assertions.assertThat(response.getGeocodeAddressNonParsedResult().getQueryStatusCodes()).isEqualTo(200);
        List<WebServiceGeocodeQueryResult> results = response.getGeocodeAddressNonParsedResult().getWebServiceGeocodeQueryResults().getWebServiceGeocodeQueryResult();
        List<CensusOutputRecord> censusOutputRecords = results.get(0).getCensusRecords().getCensusOutputRecord();
        Assertions.assertThat(censusOutputRecords.stream().map(CensusOutputRecord::getCensusYear).collect(Collectors.toList()))
                .isEqualTo(Arrays.asList(CensusYear.NINETEEN_NINETY, CensusYear.TWO_THOUSAND, CensusYear.TWO_THOUSAND_TEN));
    }

    @Test
    public void negativeGeocodeAddressNonParsed() {
        GeocodeAddressNonParsedResponse response = GeoServices.geocodeAddressNonParsed.callSoap(new GeocodeAddressNonParsed()
                .withStreetAddress("9355 Burton Way")
                .withCity("Beverly Hills")
                .withState("ca")
                .withZip("90210")
                .withApiKey("wrong")
                .withVersion(4.01)
                .withShouldCalculateCensus(true)
                .withCensusYear(CensusYear.ALL_AVAILABLE)
                .withShouldNotStoreTransactionDetails(false)
        );
        Assertions.assertThat(response.getGeocodeAddressNonParsedResult().getQueryStatusCodes()).isEqualTo(402);
    }

}
