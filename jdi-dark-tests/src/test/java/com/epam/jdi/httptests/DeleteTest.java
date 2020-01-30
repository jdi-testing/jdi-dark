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

public class DeleteTest {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookie() {
        RestResponse response = deleteCookie.call(requestData(requestData ->
                requestData.cookies = new MapArray<>(new Object[][]{
                        {"username", "John"},
                        {"token", "1234"}
                })));
        assertEquals(response.body, "username, token");
    }

    @Test
    public void bodyHamcrestMatcherWithoutKey() {
        deleteGreet.call(requestData(d -> {
            d.queryParams.add("firstName", "John");
            d.queryParams.add("lastName", "Doe");
        })).isOk().assertThat().body("greeting", equalTo("Greetings John Doe"));
    }

    @Test
    public void deleteSupportsStringBody() {
        RestResponse response = deleteBody.call(requestBody("a body"));
        assertEquals(response.body, "a body");
    }
}
