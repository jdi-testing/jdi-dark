package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.map.MapArray;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;

public class CookiesTests {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void multiValueCookieReturnsTheLastValueInMap() {
        RestResponse response = JettyService.getMultiCookie.call();
        response.isOk();
        final Map<String, String> cookies = response.cookies();
        assertThat(cookies, hasEntry("cookie1", "cookieValue2"));
    }

    @Test
    public void multiValueCookieReturnsTheLastValueInDsl() {
        RestResponse response = JettyService.getMultiCookie.call();
        assertThat(response.cookie("cookie1"), equalTo("cookieValue2"));
    }

    @Test
    public void supportsCookieStringMatchingViaDsl() {
        JettyService.setCookies.call().isOk().assertThat().cookie("key1", "value1");
    }

    @Test
    public void supportsMultipleCookieStatements() {
        JettyService.setCookies.call().isOk().assertThat().cookie("key1", "value1").and().cookie("key2", "value2");
    }

    @Test
    public void supportsMultipleCookiesShortVersionUsingPlainStrings() {
        JettyService.setCookies.call().isOk().assertThat().cookies("key1", "value1", "key3", "value3");
    }

    @Test
    public void supportsMultipleCookiesShortVersionUsingHamcrestMatching() {
        JettyService.setCookies.call().isOk().assertThat().cookies("key2", containsString("2"), "key3", equalTo("value3"));
    }

    @Test
    public void supportsMultipleCookiesShortVersionUsingMixOfHamcrestMatchingAndStringMatching() {
        JettyService.setCookies.call().isOk().assertThat().cookies("key1", containsString("1"), "key2", "value2");
    }

    @Test
    public void supportsMultipleCookiesUsingMap() {
        Map<String, String> expectedCookies = new HashMap<>();
        expectedCookies.put("key1", "value1");
        expectedCookies.put("key2", "value2");
        JettyService.setCookies.call().isOk().assertThat().cookies(expectedCookies);
    }

    @Test
    public void supportsMultipleCookiesUsingMapWithHamcrestMatcher() {
        Map<String, Matcher<String>> expectedCookies = new HashMap<>();
        expectedCookies.put("key1", containsString("1"));
        expectedCookies.put("key3", equalTo("value3"));
        JettyService.setCookies.call().isOk().assertThat().cookies(expectedCookies);
    }

    @Test
    public void supportsMultipleCookiesUsingMapWithMixOfStringAndHamcrestMatcher() {
        Map<String, Object> expectedCookies = new HashMap<>();
        expectedCookies.put("key1", containsString("1"));
        expectedCookies.put("key2", "value2");
        JettyService.setCookies.call().isOk().assertThat().cookies(expectedCookies);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*1 expectation failed.*")
    public void assertionErrorIsThrownWhenCookieDoesNotMatch() {
        JettyService.setCookies.call().isOk().assertThat().cookie("key1", "value2");
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*1 expectation failed.*")
    public void assertionErrorIsThrownWhenCookieIsNotFound() {
        JettyService.setCookies.call().isOk().assertThat().cookie("Not-Defined", "something");
    }

    @Test
    public void supportEqualCharacterInCookieValue() {
        JettyService.postReflect.call(requestData(requestData ->
                requestData.cookies = new MapArray<>(new Object[][]{
                        {"jsessionid", "9HTaCatOIaiO7ccHojDzuxwVoIU="}
                }))).isOk().assertThat().cookie("JSESSIONID", "9HTaCatOIaiO7ccHojDzuxwVoIU=");
    }

    @Test
    public void supportsMultipleCookiesWithSameKey() {
        Map<String, String> map = JettyService.getCommonIdCookies.call().cookies();
        assertThat(map.get("key1"), equalTo("value3"));
    }

}
