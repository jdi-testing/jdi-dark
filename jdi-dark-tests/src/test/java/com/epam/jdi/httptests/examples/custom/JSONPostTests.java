package com.epam.jdi.httptests.examples.custom;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.RequestDataFactory.formParams;
import static com.epam.http.requests.RequestDataFactory.queryParams;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class JSONPostTests extends WithJetty {

    @Test
    public void simpleJSONAndHamcrestMatcherJDI() {
        Object[][] queryPramsArray = new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}};

        getJettyService().greetPost(queryPramsArray)
                .isOk()
                .body("greeting", equalTo("Greetings John Doe"));
    }

    @Test
    public void queryParamsAcceptsIntArgumentsJDI() {
        Object[][] queryParamsArray = new Object[][]{{"firstName", 1234}, {"lastName", 5678}};
        getJettyService().greetPost(queryParamsArray)
                .isOk()
                .body("greeting", equalTo("Greetings 1234 5678"));
    }

    @Test
    public void formParamsAcceptsIntArgumentsJDI() {
        RestResponse response = getJettyService().greetPost
                .call(formParams()
                        .addAll(new Object[][]{{"firstName", 1234}, {"lastName", 5678}}));
        response.isOk().body("greeting", equalTo("Greetings 1234 5678"));
    }

    @Test
    public void bodyWithSingleHamcrestMatching() {
        Object[][] queryPramsArray = new Object[][]{{"firstName", 1234}, {"lastName", 5678}};
        getJettyService().greetPost(queryPramsArray)
                .isOk()
                .body(containsString("greeting"));
    }

    @Test
    public void bodyHamcrestMatcherWithoutKey() {
        Object[][] queryPramsArray = {{"firstName", "John"}, {"lastName", "Doe"}};
        getJettyService().greetPost(queryPramsArray)
                .isOk()
                .body(equalTo("{\"greeting\":\"Greetings John Doe\"}"));
    }

    @Test
    public void requestContentType() {
        Map<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put("firstName", "John");
        queryParamsMap.put("lastName", "Doe");

        getJettyService().greetPostWithContentTypeAndMapOfFormParams(ContentType.URLENC.toString(), queryParamsMap)
                .isOk()
                .contentType(ContentType.JSON)
                .body("greeting", equalTo("Greetings John Doe"));
    }

    @Test
    public void responseAllowsSpecifyingJsonBodyForPost() {
        getJettyService().postJsonBodyAcceptHeader("accept", "application/json"
                , "{ \"message\" : \"hello world\"}")
                .isOk()
                .contentType(ContentType.JSON)
                .and()
                .body(equalTo("hello world"));
    }

    @Test
    public void uriNotFoundTWhenPost() {
        RestResponse response = getJettyService().notFoundedURIPost.call();
        response.assertThat().statusCode(greaterThanOrEqualTo(400));
    }

    @Test
    public void requestAllowsSpecifyingHeaders() {
        RestResponse response = getJettyService().headerPost.call();
        response.isOk().body(containsString("MyHeader"));
    }

    @Test
    public void requestAllowsSpecifyingJsonBodyForPost() {
        getJettyService().jsonBodyPost("{ \"message\" : \"hello world\"}")
                .isOk()
                .body(equalTo("hello world"));
    }

    @Test
    public void supportsReturningPostBody() {
        Object[][] queryPramsArray = {{"firstName", "John"}, {"lastName", "Doe"}};
        RestResponse response = getJettyService().greetPost(queryPramsArray);
        final JsonPath jsonPath = new JsonPath(response.getBody());
        assertThat(jsonPath.getString("greeting"), equalTo("Greetings John Doe"));
    }

    @Test
    public void bodyWithSingleHamcrestMatchingUsingQueryParams() {
        getJettyService().greetPostWithStringOfQueryParams("firstName=John&lastName=Doe")
                .isOk()
                .assertThat()
                .body(containsString("greeting"));
    }

    @Test
    public void requestAllowsSpecifyingCookie() {
        HashMap<String, Object> cookiesMap = new HashMap<>();
        cookiesMap.put("username", "John");
        cookiesMap.put("token", "1234");
        getJettyService().cookiePost(cookiesMap)
                .isOk()
                .body(equalTo("username, token"));
    }

    @Test
    public void queryParametersInPostAreUrlEncoded() {
        getJettyService().paramUrlPost.call(queryParams().add("first", "http://myurl.com"))
                .isOk()
                .body("first", equalTo("http://myurl.com"));
    }

    @Test
    public void requestAllowsSpecifyingStringBodyForPostJDI() {
        getJettyService().bodyPost("some body")
                .isOk()
                .body(equalTo("some body"));
    }

    @Test
    public void requestAllowsSpecifyingIntForPost() {
        getJettyService().postReflectWithBody(2)
                .isOk()
                .body(equalTo("2"));
    }

    @Test
    public void requestAllowsSpecifyingFloatForPost() {
        getJettyService().postReflectWithBody(2f)
                .isOk()
                .body(equalTo("2.0"));
    }

    @Test
    public void requestAllowsSpecifyingDoubleForPost() {
        getJettyService().postReflectWithBody(2d)
                .isOk()
                .body(equalTo("2.0"));
    }

    @Test
    public void requestAllowsSpecifyingShortForPost() {
        getJettyService().postReflectWithBody((short) 2)
                .isOk()
                .body(equalTo("2"));
    }

    @Test
    public void requestAllowsSpecifyingBooleanForPost() {
        getJettyService().postReflectWithBody(true)
                .isOk()
                .body(equalTo("true"));
    }
}
