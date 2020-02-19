package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestBody;
import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.RequestData.requestQueryParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.bodyPost;
import static com.epam.jdi.httptests.JettyService.cookiePost;
import static com.epam.jdi.httptests.JettyService.greetPost;
import static com.epam.jdi.httptests.JettyService.headerPost;
import static com.epam.jdi.httptests.JettyService.jsonBodyPost;
import static com.epam.jdi.httptests.JettyService.notFoundedURIPost;
import static com.epam.jdi.httptests.JettyService.paramUrlPost;
import static com.epam.jdi.httptests.JettyService.postJsonBodyAcceptHeader;
import static com.epam.jdi.httptests.JettyService.postReflect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class JSONPostTests extends WithJetty {
    @BeforeClass
    public void initService() {
        init(JettyService.class);
    }

    @Test
    public void simpleJSONAndHamcrestMatcherJDI() {
        RestResponse response = greetPost
                .call(requestQueryParams(new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}}));
        response.isOk().body("greeting", equalTo("Greetings John Doe"));
    }

    @Test
    public void formParamsAcceptsIntArgumentsJDI() {
        RestResponse response = greetPost
                .call(requestQueryParams(new Object[][]{{"firstName", 1234}, {"lastName", 5678}}));
        response.isOk().body("greeting", equalTo("Greetings 1234 5678"));
    }

    @Test
    public void bodyWithSingleHamcrestMatching() {
        RestResponse response = greetPost
                .call(requestQueryParams(new Object[][]{{"firstName", 1234}, {"lastName", 5678}}));
        response.isOk().body(containsString("greeting"));
    }

    @Test
    public void bodyHamcrestMatcherWithoutKey() {
        RestResponse response = greetPost
                .call(requestQueryParams(new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}}));
        response.isOk().body(equalTo("{\"greeting\":\"Greetings John Doe\"}"));
    }

    @Test
    public void requestContentType() {
        greetPost.call(requestData(d -> {
            d.contentType = ContentType.URLENC.toString();
            d.queryParams.add("firstName", "John");
            d.queryParams.add("lastName", "Doe");
        }))
                .isOk().contentType(ContentType.JSON)
                .body("greeting", equalTo("Greetings John Doe"));
    }

    @Test
    public void responseAllowsSpecifyingJsonBodyForPost() {
        postJsonBodyAcceptHeader.call(requestData(d -> {
            d.addHeader("accept", "application/json");
            d.body = "{ \"message\" : \"hello world\"}";
        }))
                .isOk().contentType(ContentType.JSON).and().body(equalTo("hello world"));
    }

    @Test
    public void uriNotFoundTWhenPost() {
        RestResponse response = notFoundedURIPost.call();
        response.assertThat().statusCode(greaterThanOrEqualTo(400));
    }

    @Test
    public void requestAllowsSpecifyingHeaders() {
        RestResponse response = headerPost.call();
        response.isOk().body(containsString("MyHeader"));
    }

    @Test
    public void requestAllowsSpecifyingJsonBodyForPost() {
        RestResponse response = jsonBodyPost
                .call(requestBody("{ \"message\" : \"hello world\"}"));
        response.isOk().body(equalTo("hello world"));
    }

    @Test
    public void supportsReturningPostBody() {
        RestResponse response = greetPost
                .call(requestQueryParams(new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}}));
        final JsonPath jsonPath = new JsonPath(response.body);
        assertThat(jsonPath.getString("greeting"), equalTo("Greetings John Doe"));
    }

    @Test
    public void requestAllowsSpecifyingCookie() {
        RestResponse response = cookiePost.call(requestData(requestData ->
                requestData.addCookies("username", "John", "token", "1234")));
        response.isOk()
                .body(equalTo("username, token"));
    }

    @Test
    public void queryParametersInPostAreUrlEncoded() {
        RestResponse response = paramUrlPost
                .call(requestQueryParams("first", "http://myurl.com"));
        response.isOk().body("first", equalTo("http://myurl.com"));
    }

    @Test
    public void requestAllowsSpecifyingStringBodyForPostJDI() {
        RestResponse response = bodyPost
                .call(requestBody("some body"));
        response.isOk().body(equalTo("some body"));
    }

    @Test
    public void requestAllowsSpecifyingIntForPost() {
        postReflect.call(requestBody(2)).isOk().body(equalTo("2"));
    }

    @Test
    public void requestAllowsSpecifyingFloatForPost() {
        postReflect.call(requestBody(2f)).isOk().body(equalTo("2.0"));
    }

    @Test
    public void requestAllowsSpecifyingDoubleForPost() {
        postReflect.call(requestBody(2d)).isOk().body(equalTo("2.0"));
    }

    @Test
    public void requestAllowsSpecifyingShortForPost() {
        postReflect.call(requestBody((short) 2)).isOk().body(equalTo("2"));
    }

    @Test
    public void requestAllowsSpecifyingBooleanForPost() {
        postReflect.call(requestBody(true)).isOk().body(equalTo("true"));
    }


}
