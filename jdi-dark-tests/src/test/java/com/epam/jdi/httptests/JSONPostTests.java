package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.map.MapArray;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestBody;
import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class JSONPostTests {
    @BeforeClass
    public void initService() {
        init(JettyApi.class);
    }

    @Test(priority = 1)
    public void simpleJSONAndHamcrestMatcherJDI() {
        RestResponse response = JettyApi.greetPost
                .call(requestData(d -> {
                    d.queryParams.add(new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}});
                }));
        response.isOk().body("greeting", equalTo("Greetings John Doe"));
    }

    @Test
    public void formParamsAcceptsIntArgumentsJDI() {
        RestResponse response = JettyApi.greetPost
                .call(requestData(d -> {
                    d.queryParams.add(new Object[][]{{"firstName", 1234}, {"lastName", 5678}});
                }));
        response.isOk().body("greeting", equalTo("Greetings 1234 5678"));
    }

    @Test(priority = 2)
    public void bodyWithSingleHamcrestMatching() {
        RestResponse response = JettyApi.greetPost
                .call(requestData(d -> {
                    d.queryParams.add(new Object[][]{{"firstName", 1234}, {"lastName", 5678}});
                }));
        response.isOk().body(containsString("greeting"));
    }

    @Test
    public void bodyHamcrestMatcherWithoutKey() {
        RestResponse response = JettyApi.greetPost
                .call(requestData(d -> {
                    d.queryParams.add(new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}});
                }));
        response.isOk().body(equalTo("{\"greeting\":\"Greetings John Doe\"}"));

    }

    @Test
    public void uriNotFoundTWhenPost() {
        RestResponse response = JettyApi.notFoundedURI
                .call();
        response.assertThat().statusCode(greaterThanOrEqualTo(400));
    }

    @Test
    public void requestSpecificationAllowsSpecifyingHeaders() {
        RestResponse response = JettyApi.header
                .call();
        response.isOk().body(containsString("MyHeader"));
    }


    @Test
    public void requestSpecificationAllowsSpecifyingJsonBodyForPost() {
        RestResponse response = JettyApi.jsonBody
                .call(requestBody("{ \"message\" : \"hello world\"}"));

        response.isOk().body(equalTo("hello world"));
        given().body("{ \"message\" : \"hello world\"}").with().contentType(ContentType.JSON).then().expect().body(equalTo("hello world")).when().post("/jsonBody");
    }

    @Test
    public void supportsReturningPostBody() {
        RestResponse response = JettyApi.greetPost
                .call(requestData(d -> {
                    d.queryParams.add(new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}});
                }));
        final JsonPath jsonPath = new JsonPath(response.body);
        assertThat(jsonPath.getString("greeting"), equalTo("Greetings John Doe"));
    }

    @Test
    public void supportsGettingResponseBodyWhenStatusCodeIs401() {
        RestResponse response = JettyApi.unathorizedRq.call();
        assertThat(response.body, allOf(containsString("401"), containsString("Unauthorized")));
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookie() {
        RestResponse response = JettyApi.cookie.call(requestData(requestData ->
                requestData.cookies = new MapArray<>(new Object[][]{
                        {"username", "John"},
                        {"token", "1234"}
                })));
        response.isOk()
                .body(equalTo("username, token"));

    }


}
