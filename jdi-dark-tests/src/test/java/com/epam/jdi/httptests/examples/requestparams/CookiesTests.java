package com.epam.jdi.httptests.examples.requestparams;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.epam.http.requests.RequestDataFactory.cookies;
import static com.epam.http.requests.ServiceInit.init;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CookiesTests extends WithJetty {

    @Test
    public void multiValueCookieReturnsTheLastValueInMap() {
        RestResponse response = getJettyService().getMultiCookie.call();
        response.isOk();
        final Map<String, String> cookies = response.cookies();
        assertThat(cookies, hasEntry("cookie1", "cookieValue2"));
    }

    @Test
    public void detailedCookiesAllowsToGetMultiValues() {
        final Cookies cookies = getJettyService().getMultiCookie.call().getRaResponse().detailedCookies();
        assertThat(cookies.getValues("cookie1"), hasItems("cookieValue1", "cookieValue2"));
    }

    @Test
    public void multiValueCookieReturnsTheLastValueInDsl() {
        RestResponse response = getJettyService().getMultiCookie.call();
        assertThat(response.cookie("cookie1"), equalTo("cookieValue2"));
    }

    @Test
    public void canSpecifyMultiValueCookiesPassingSeveralValuesToTheCookieMethod() {
        RestResponse response = getJettyService().getMultiCookieRequest.call(cookies().add("key1", "value1", "value2"));
        assertThat(response.getBody(), equalTo("[{\"key1\":\"value1\"},{\"key1\":\"value2\"}]"));
    }

    @Test
    public void canSpecifyMultiValueCookiesPassingSeveralValuesToTheServiceObject() {
        RestResponse response = getJettyService().getMultiCookieWithCookies.call();
        assertThat(response.getBody(), equalTo("[{\"key1\":\"value1\"},{\"key1\":\"value2\"}]"));
    }

    @Test
    public void supportsCookieStringMatchingViaDsl() {
        getJettyService().setCookies.call().isOk().assertThat().cookie("key1", "value1");
    }

    @Test
    public void supportsMultipleCookieStatements() {
        getJettyService().setCookies.call().isOk().assertThat().cookie("key1", "value1").and().cookie("key2", "value2");
    }

    @Test
    public void supportsMultipleCookiesShortVersionUsingPlainStrings() {
        getJettyService().setCookies.call().isOk().assertThat().cookies("key1", "value1", "key3", "value3");
    }

    @Test
    public void supportsMultipleCookiesShortVersionUsingHamcrestMatching() {
        getJettyService().setCookies.call().isOk().assertThat().cookies("key2", containsString("2"), "key3", equalTo("value3"));
    }

    @Test
    public void supportsMultipleCookiesShortVersionUsingMixOfHamcrestMatchingAndStringMatching() {
        getJettyService().setCookies.call().isOk().assertThat().cookies("key1", containsString("1"), "key2", "value2");
    }

    @Test
    public void supportsMultipleCookiesVerificationUsingMap() {
        Map<String, String> expectedCookies = new HashMap<>();
        expectedCookies.put("key1", "value1");
        expectedCookies.put("key2", "value2");
        getJettyService().setCookies.call().isOk().assertThat().cookies(expectedCookies);
    }

    @Test
    public void canPassCookiesAsObjectsArray() {
        Object[][] cookiesArray = new Object[][]{{"key1", "value1"}, {"key2", "value2"}};
        RestResponse response = getJettyService().getMultiCookieRequest.call(cookies().addAll(cookiesArray));
        assertThat(response.getBody(), equalTo("[{\"key1\":\"value1\"},{\"key2\":\"value2\"}]"));
    }

    @Test
    public void supportsMultipleCookiesUsingMapWithHamcrestMatcher() {
        Map<String, Matcher<String>> expectedCookies = new HashMap<>();
        expectedCookies.put("key1", containsString("1"));
        expectedCookies.put("key3", equalTo("value3"));
        getJettyService().setCookies.call().isOk().assertThat().cookies(expectedCookies);
    }

    @Test
    public void supportsMultipleCookiesUsingMapWithMixOfStringAndHamcrestMatcher() {
        Map<String, Object> expectedCookies = new HashMap<>();
        expectedCookies.put("key1", containsString("1"));
        expectedCookies.put("key2", "value2");
        getJettyService().setCookies.call().isOk().assertThat().cookies(expectedCookies);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*1 expectation failed.*")
    public void assertionErrorIsThrownWhenCookieDoesNotMatch() {
        JettyService jetty = getJettyService();

        jetty.setCookies.call().isOk().assertThat().cookie("key1", "value2");
        Cookie cookie = jetty.setCookies.call().getRaResponse().getDetailedCookie("key1");
        assertThat(cookie, notNullValue());
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookieWithNoValue() {
        RestResponse response = getJettyService().getCookieWithNoValue.call(cookies().add("some_cookie"));
        assertThat(response.getBody(), equalTo("some_cookie"));
    }

    @Test
    public void serviceObjectAllowsSpecifyingCookieWithNoValue() {
        RestResponse response = getJettyService().getCookieWithNoValueWithCookies.call();
        assertThat(response.getBody(), equalTo("some_cookie"));
    }

    @Test
    public void responseSpecificationAllowsParsingCookieWithNoValue() {
        Response response = getJettyService().getResponseCookieWithNoValue.call().getRaResponse();
        assertThat("Cookie is empty", response.cookie("PLAY_FLASH").isEmpty());
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookies() {
        RestResponse response = getJettyService().getSpecifiedCookiePairs("username", "name1Value", "token", "name2Value");
        assertThat(response.getBody(), equalTo("username, token"));
    }

    @Test
    public void serviceObjectAllowsSpecifyingCookies() {
        RestResponse response = getJettyService().getCookieWithCookies.call();
        assertThat(response.getBody(), equalTo("username, token"));
    }

    @Test
    public void cookiesCombinedFromServiceObjectAndRequestData() {
        RestResponse response = getJettyService().getCookieWithCookies.call(cookies().add("userCookie1", "userValue1"));
        assertThat(response.getBody(), equalTo("username, token, userCookie1"));
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookieUsingMap() {
        Map<String, String> cookies = new LinkedHashMap<>();
        cookies.put("username", "John");
        cookies.put("token", "1234");

        RestResponse response = getJettyService().getCookie.call(cookies().addAll(cookies));
        assertThat(response.getBody(), equalTo("username, token"));
    }

    @Test
    public void requestSpecificationAllowsSpecifyingMultipleCookies() {
        Map<String, String> cookies = new LinkedHashMap<>();
        cookies.put("username", "John");
        cookies.put("token", "1234");

        RestResponse response = getJettyService().getMultipleCookieSpecifiedUsingMap(cookies, "key1", "value1");

        assertThat(response.getBody(), equalTo("username, token, key1"));
    }

    @Test
    public void canGetCookieDetails() {
        final List<Cookie> cookies = getJettyService().getMultiCookie.call().getRaResponse().getDetailedCookies().getList("cookie1");

        assertThat(cookies.size(), is(2));
        final Cookie firstCookie = cookies.get(0);
        assertThat(firstCookie.getValue(), equalTo("cookieValue1"));
        assertThat(firstCookie.getDomain(), equalTo("localhost"));

        final Cookie secondCookie = cookies.get(1);
        assertThat(secondCookie.getValue(), equalTo("cookieValue2"));
        assertThat(secondCookie.getDomain(), equalTo("localhost"));
        assertThat(secondCookie.getPath(), equalTo("/"));
        assertThat(secondCookie.getMaxAge(), is(1234567));
        assertThat(secondCookie.isSecured(), is(true));
        assertThat(secondCookie.getVersion(), is(-1));
    }

    @Test
    public void cookiesParsingSupportsNoValueCookies() {
        RestResponse response = getJettyService().postEmptyCookie("no-value-cookie");
        assertThat("Cookie is empty", response.cookie("no-value-cookie").isEmpty());
    }

    @Test
    public void detailedCookieWorks() {
        final Cookie detailedCookieJsessionId = getJettyService().getHtmlWithCookie.call().getRaResponse().detailedCookie("JSESSIONID");
        assertThat(detailedCookieJsessionId, notNullValue());
        assertThat(detailedCookieJsessionId.getPath(), equalTo("/"));
        assertThat(detailedCookieJsessionId.getValue(), equalTo("B3134D534F40968A3805968207273EF5"));
    }

    @Test
    public void getDetailedCookieWorks() {
        final Cookie detailedCookieJsessionId = getJettyService().getHtmlWithCookie.call().getRaResponse().getDetailedCookie("JSESSIONID");
        assertThat(detailedCookieJsessionId, notNullValue());
        assertThat(detailedCookieJsessionId.getPath(), equalTo("/"));
        assertThat(detailedCookieJsessionId.getValue(), equalTo("B3134D534F40968A3805968207273EF5"));
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*1 expectation failed.*")
    public void assertionErrorIsThrownWhenCookieIsNotFound() {
        getJettyService().setCookies.call().isOk().assertThat().cookie("Not-Defined", "something");
    }

    @Test
    public void supportEqualCharacterInCookieValue() {
        getJettyService().postSpecifiedCookie("jsessionid", "9HTaCatOIaiO7ccHojDzuxwVoIU=").isOk()
                .assertThat().cookie("JSESSIONID", "9HTaCatOIaiO7ccHojDzuxwVoIU=");
    }

    @Test
    public void supportsMultipleCookiesWithSameKey() {
        Map<String, String> map = getJettyService().getCommonIdCookies.call().cookies();
        assertThat(map.get("key1"), equalTo("value3"));
    }

    @Test
    public void supportsMultipleCookiesWithDifferentAnnotation() {
        RestResponse response = getJettyService().getMultiCookieWithManyCookies.call();
        assertThat(response.getBody(), equalTo("[{\"key1\":\"value1\"},{\"key1\":\"value2\"},{\"key2\":\"\"},{\"key3\":\"value3\"},{\"key4\":\"value4\"}]"));
    }

    @Test
    public void supportsCookiesFromRequestSpecification() {
        RequestSpecification rs = given().filter(new AllureRestAssured()).cookie("SpecCookie1", "SpecCookieValue1");
        init(JettyService.class, ServiceSettings.builder().requestSpecification(rs).build());
        RestResponse response = init(JettyService.class, ServiceSettings.builder().requestSpecification(rs).build())
                .getMultiCookieRequest.call(cookies().add("userCookie1", "userValue1"));
        assertThat(response.getBody(), equalTo("[{\"SpecCookie1\":\"SpecCookieValue1\"},{\"userCookie1\":\"userValue1\"}]"));
    }
}
