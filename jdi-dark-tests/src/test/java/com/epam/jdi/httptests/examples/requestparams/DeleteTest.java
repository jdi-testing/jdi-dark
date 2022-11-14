package com.epam.jdi.httptests.examples.requestparams;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataFactory.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

/**
 * This class is using for delete cases for JettyService
 * Tests are similar to rest assured cases
 */
public class DeleteTest extends WithJetty {

    private static final String TEST_BODY_VALUE = "a body";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME_VALUE = "John";
    private static final String LAST_NAME_VALUE = "Doe";
    private static final String USERNAME = "username";
    private static final String TOKEN = "token";
    private static final String TOKEN_VALUE = "1234";

    @Test
    public void requestSpecificationAllowsSpecifyingCookie() {
        RestResponse response = getJettyService().deleteCookie.call(cookies().addAll(new Object[][]{
            {USERNAME, FIRST_NAME_VALUE},
            {TOKEN, TOKEN_VALUE}
        }));
        assertEquals(response.getBody(), "username, token");
    }

    @Test
    public void bodyHamcrestMatcherWithKey() {
        getJettyService().deleteGreet.call(queryParams().addAll(new Object[][] {
            { FIRST_NAME, FIRST_NAME_VALUE },
            { LAST_NAME, LAST_NAME_VALUE }
        })).isOk().assertThat().body("greeting", equalTo("Greetings John Doe"));
    }

    @Test
    public void bodyHamcrestMatcherWithOutKey() {
        getJettyService().deleteGreet.call(queryParams().addAll(
                new Object[][]{{FIRST_NAME, FIRST_NAME_VALUE},
                        {LAST_NAME, LAST_NAME_VALUE}
                })).isOk().assertThat().body(equalTo("{\"greeting\":\"Greetings John Doe\"}"));
    }

    @Test
    public void deleteSupportsStringBody() {
        RestResponse response = getJettyService().deleteBody.call(body(TEST_BODY_VALUE));
        response.assertThat().body(is(TEST_BODY_VALUE));
    }
}
