package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.map.MapArray;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.apache.http.client.utils.DateUtils;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.detailedCookie;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CookiesTests {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    // 1 - rename methods
    // 2- check all
    // 3 - check all together
    // 4 - clarify logic
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
        response.isOk();
        assertThat(response.cookie("cookie1"), equalTo("cookieValue2"));
    }

    @Test
    public void supportsCookieStringMatchingViaDsl() {
        RestResponse response = JettyService.setCookies.call();
        response.assertThat().cookie("key1", "value1");
    }


    @Test
    public void multipleCookieStatementsAreConcatenated() {
        RestResponse response = JettyService.setCookies.call();
        response.assertThat().cookie("key1", "value1").and().cookie("key2", "value2");
    }

    @Test
    public void multipleCookiesShortVersionUsingPlainStrings() {
        RestResponse response = JettyService.setCookies.call();
        response.assertThat().cookies("key1", "value1", "key3", "value3");
    }

    @Test
    public void multipleCookiesShortVersionUsingHamcrestMatching() {
        RestResponse response = JettyService.setCookies.call();
        response.assertThat().cookies("key2", containsString("2"), "key3", equalTo("value3"));
    }

    @Test
    public void multipleCookiesShortVersionUsingMixOfHamcrestMatchingAndStringMatching() {
        JettyService.setCookies.call().assertThat().cookies("key1", containsString("1"), "key2", "value2");
    }

    @Test
    public void multipleCookiesUsingMap() {
        Map<String, String> expectedCookies = new HashMap<>();
        expectedCookies.put("key1", "value1");
        expectedCookies.put("key2", "value2");

        JettyService.setCookies.call().assertThat().cookies(expectedCookies);
    }

    @Test
    public void multipleCookiesUsingMapWithHamcrestMatcher() {
        Map<String, Matcher<String>> expectedCookies = new HashMap<>();
        expectedCookies.put("key1", containsString("1"));
        expectedCookies.put("key3", equalTo("value3"));

        JettyService.setCookies.call().assertThat().cookies(expectedCookies);
    }

    @Test
    public void multipleCookiesUsingMapWithMixOfStringAndHamcrestMatcher() {
        Map expectedCookies = new HashMap();
        expectedCookies.put("key1", containsString("1"));
        expectedCookies.put("key2", "value2");

        JettyService.setCookies.call().assertThat().cookies(expectedCookies);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*1 expectation failed.*")
    public void whenExpectedCookieDoesntMatchAnAssertionThenAssertionErrorIsThrown() {
        JettyService.setCookies.call().assertThat().cookie("key1", "value2");
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*1 expectation failed.*")
    public void whenExpectedCookieIsNotFoundThenAnAssertionErrorIsThrown() {
        JettyService.setCookies.call().assertThat().cookie("Not-Defined", "something");
    }

    // TODO - I AM HERE
    @Test
    public void cookiesSupportEqualCharacterInCookieValue() throws Exception {
        given().
                cookie("jsessionid", "9HTaCatOIaiO7ccHojDzuxwVoIU=").
                expect().
                cookie("JSESSIONID", "9HTaCatOIaiO7ccHojDzuxwVoIU=").
                when().
                post("/reflect");
    }

    @Test
    public void cookiesParsingSupportsNoValueCookies() throws Exception {
        given().
                cookie("no-value-cookie").
                expect().
                cookie("no-value-cookie").
                when().
                post("/reflect");
    }

    @Test
    public void detailedCookieWorks() throws Exception {
        final Response response = get("/html_with_cookie");
        final Cookie detailedCookieJsessionId = response.detailedCookie("JSESSIONID");

        assertThat(detailedCookieJsessionId, notNullValue());
        assertThat(detailedCookieJsessionId.getPath(), equalTo("/"));
        assertThat(detailedCookieJsessionId.getValue(), equalTo("B3134D534F40968A3805968207273EF5"));
    }

    @Test
    public void getDetailedCookieWorks() throws Exception {
        final Response response = get("/html_with_cookie");
        final Cookie detailedCookieJsessionId = response.getDetailedCookie("JSESSIONID");

        assertThat(detailedCookieJsessionId, notNullValue());
        assertThat(detailedCookieJsessionId.getPath(), equalTo("/"));
        assertThat(detailedCookieJsessionId.getValue(), equalTo("B3134D534F40968A3805968207273EF5"));
    }
    @Test
    public void multipleCookiesWithSameKey() throws Exception {
        final Response response = get("/setCommonIdCookies");
        Map<String, String> map = new HashMap<String, String>();
        map = response.cookies();
        assertThat(map.get("key1"), equalTo("value3"));
    }

    @Test
    public void usesCookiesDefinedInAStaticRequestSpecification() throws Exception {
        RestAssured.requestSpecification = new RequestSpecBuilder().addCookie("my-cookie", "1234").build();

        try {
            when().
                    post("/reflect").
                    then().
                    log().ifValidationFails().
                    cookie("my-cookie", "1234");
        } finally {
            RestAssured.reset();
        }
    }

    @Test
    public void parsesValidExpiresDateCorrectly() throws Exception {
        Cookies cookies =
                when().
                        get("/cookieWithValidExpiresDate").
                        then().
                        extract().detailedCookies();

        assertThat(cookies.asList(), hasSize(1));
        Cookie cookie = cookies.get("name");
        assertThat(cookie.getExpiryDate(), equalTo(DateUtils.parseDate("Sat, 02 May 2009 23:38:25 GMT")));
    }

    @Test
    public void removesDoubleQuotesFromCookieWithExpiresDate() throws Exception {
        Cookies cookies =
                when().
                        get("/cookieWithDoubleQuoteExpiresDate").
                        then().
                        extract().detailedCookies();

        assertThat(cookies.asList(), hasSize(1));
        Cookie cookie = cookies.get("name");
        assertThat(cookie.getExpiryDate(), equalTo(DateUtils.parseDate("Sat, 02 May 2009 23:38:25 GMT")));
    }

    @Test
    public void setsExpiresPropertyToNullWhenCookieHasInvalidExpiresDate() throws Exception {
        Cookies cookies =
                when().
                        get("/cookieWithInvalidExpiresDate").
                        then().
                        extract().detailedCookies();

        assertThat(cookies.asList(), hasSize(1));
        Cookie cookie = cookies.get("name");
        assertThat(cookie.getExpiryDate(), nullValue());
    }

    @Test
    public void canGetCookieSameSiteAttribute() {
        Cookies cookies = when().get("/sameSiteCookie").then().extract().detailedCookies();

        assertThat(cookies.asList(), hasSize(1));
        final Cookie cookie = cookies.get("name");
        assertThat(cookie.getValue(), equalTo("value"));
        assertThat(cookie.isSecured(), is(true));
        assertThat(cookie.getSameSite(), equalTo("None"));
    }

    @Test
    public void detailedCookieMatcherSupportsSameSiteAttribute() {
        given()
                .get("/sameSiteCookie")
                .then()
                .cookie("name", detailedCookie().value(Matchers.notNullValue()).secured(true).sameSite("None"));
    }



}
