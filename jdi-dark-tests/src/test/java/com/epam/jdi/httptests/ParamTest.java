package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

/**
 * This class is using for param cases for JettyService
 * Tests are similar to rest assured cases
 */
public class ParamTest {

    private static final String PARAM_NAME = "some";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME_VALUE = "John";
    private static final String LAST_NAME_VALUE = "Doe";

    @BeforeMethod
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void noValueParamWhenUsingQueryParamWithGetRequest() {
        RestResponse response = JettyService.getNoValueParam.call(requestData(d -> {
            d.queryParams.add(PARAM_NAME, "");
        }));
        assertEquals(response.body, "Params: some=");
    }

    @Test
    public void whenLastParamInGetRequestEndsWithEqualItsTreatedAsANoValueParam() {
        JettyService.getGreet.call(requestData(d -> {
            d.queryParams.add(FIRST_NAME, FIRST_NAME_VALUE);
            d.queryParams.add(LAST_NAME, "");
        })).isOk().assertThat().body("greeting", equalTo("Greetings John "));
    }

    @Test
    public void whenFirstParamInGetRequestEndsWithEqualItsTreatedAsANoValueParam() {
        JettyService.getGreet.call(requestData(d -> {
            d.queryParams.add(FIRST_NAME, "");
            d.queryParams.add(LAST_NAME, LAST_NAME_VALUE);
        })).isOk().assertThat().body("greeting", equalTo("Greetings  Doe"));
    }
}
