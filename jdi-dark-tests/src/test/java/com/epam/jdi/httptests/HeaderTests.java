package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getWithMultipleHeadersInRequest;
import static com.epam.jdi.httptests.JettyService.getWithSingleHeaderInRequest;
import static org.hamcrest.Matchers.containsString;

public class HeaderTests {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void singleHeaderIsAllowedInRequest() {
        RestResponse response = getWithSingleHeaderInRequest.call();
        response.isOk();
        response.assertBody(new Object[][] {{"headers.Headertestname", containsString("HeaderTestValue")}});
    }

    @Test
    public void multipleHeadersAreAllowedInRequest() {
        RestResponse response = getWithMultipleHeadersInRequest.call();
        response.isOk();
        response.assertBody(new Object[][]{{"headers.Header1", containsString("Value1")},
                {"headers.Header2", containsString("Value2")}});
    }
}