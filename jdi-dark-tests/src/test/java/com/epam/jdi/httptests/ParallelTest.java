package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ParallelTest extends WithJetty {
    JettyService jetty;

    @BeforeClass
    public void before() {
        jetty = init(JettyService.class);
    }

    @Test
    public void canSpecifyMultiValueCookiesPassingSeveralValuesToTheCookieMethod() {
        RestResponse response = jetty.getMultiCookieWithOneName("key1", "yo", "yo2");
        assertThat(response.getBody(), equalTo("[{\"key1\":\"yo\"},{\"key1\":\"yo2\"}]"));
    }

//    @Test
//    public void canPassCookiesAsObjectsArray() {
//        Object[][] cookiesArray = new Object[][]{{"key1", "value1"}, {"key2", "value2"}};
//        RestResponse response = jetty.getMultiCookiesArray(cookiesArray);
//        assertThat(response.getBody(), equalTo("[{\"key1\":\"value1\"},{\"key2\":\"value2\"}]"));
//    }

}
