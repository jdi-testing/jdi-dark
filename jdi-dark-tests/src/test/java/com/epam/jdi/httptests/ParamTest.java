package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static org.testng.Assert.assertEquals;

public class ParamTest {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void noValueParamWhenUsingQueryParamWithGetRequest() {
        RestResponse response = JettyService.getNoValueParam.call(requestData(d -> {
            d.queryParams.add("some", "");
        }));
        assertEquals(response.body, "Params: some=");
    }

    @Test
    public void multipleNoValueQueryParamWhenUsingQueryParamInUrlForGetRequest() {

    }
}
