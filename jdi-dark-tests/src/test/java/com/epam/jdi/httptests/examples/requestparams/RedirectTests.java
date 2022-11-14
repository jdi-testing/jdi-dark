package com.epam.jdi.httptests.examples.requestparams;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RedirectTests extends WithJetty {

    @Test
    public void followRedirect() {
        RequestSpecification rs = given().redirects().follow(true).param("url", "/hello");
        RestResponse resp = getJettyService().getRedirect.callBasedOnSpec(rs);
        resp.assertThat().statusCode(HttpStatus.SC_OK);
        resp.assertThat().body("hello", equalTo("Hello Scalatra"));
    }

    @Test
    public void doesNotFollowRedirect() {
        RequestSpecification rs = given().redirects().follow(false).param("url", "/hello");
        RestResponse resp = getJettyService().getRedirect.callBasedOnSpec(rs);
        resp.assertThat().statusCode(HttpStatus.SC_MOVED_TEMPORARILY);
        resp.assertThat().header("Location", "http://localhost:8081/hello");
    }

}
