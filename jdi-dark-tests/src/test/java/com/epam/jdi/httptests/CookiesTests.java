package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.map.MapArray;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.core.IsEqual.equalTo;

public class CookiesTests {

    @BeforeTest
    public void before() {
        init(ServiceExample.class);
    }


/*    @Test
    public void cookiesTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse response = service.getCookies.call(
                requestData(requestData ->
                        requestData.cookies = new MapArray<>(new Object[][] {
                                {"additionalCookie", "test"}
                        })));
        response.isOk()
                .body("cookies.additionalCookie", equalTo("test"))
                .body("cookies.session_id", equalTo("1234"))
                .body("cookies.hello", equalTo("world"));
    }*/

}
