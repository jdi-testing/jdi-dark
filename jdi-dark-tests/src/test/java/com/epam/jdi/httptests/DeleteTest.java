package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.map.MapArray;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestBody;
import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.deleteBody;
import static com.epam.jdi.httptests.JettyService.deleteCookie;
import static com.epam.jdi.httptests.JettyService.deleteGreet;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

/**
 * This class is using for delete cases for JettyService
 * Tests are similar to rest assured cases
 */
public class DeleteTest {

    private static final String TEST_BODY_VALUE = "a body";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME_VALUE = "John";
    private static final String LAST_NAME_VALUE = "Doe";
    private static final String USERNAME = "username";
    private static final String TOKEN = "token";
    private static final String TOKEN_VALUE = "1234";

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookie() {
        RestResponse response = deleteCookie.call(requestData(requestData ->
                requestData.cookies = new MapArray<>(new Object[][]{
                        {USERNAME, FIRST_NAME_VALUE},
                        {TOKEN, TOKEN_VALUE}
                })));
        assertEquals(response.body, "username, token");
    }

    @Test
    public void bodyHamcrestMatcherWithoutKey() {
        deleteGreet.call(requestData(d -> {
            d.queryParams.add(FIRST_NAME, FIRST_NAME_VALUE);
            d.queryParams.add(LAST_NAME, LAST_NAME_VALUE);
        })).isOk().assertThat().body("greeting", equalTo("Greetings John Doe"));
    }

    @Test
    public void deleteSupportsStringBody() {
        RestResponse response = deleteBody.call(requestBody(TEST_BODY_VALUE));
        assertEquals(response.body, TEST_BODY_VALUE);
    }
}
