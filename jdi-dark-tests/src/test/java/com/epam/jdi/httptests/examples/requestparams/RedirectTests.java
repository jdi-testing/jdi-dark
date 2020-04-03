package com.epam.jdi.httptests.examples.requestparams;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.services.JettyService;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.JettyService.getRedirect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RedirectTests extends WithJetty {

    @BeforeClass
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void followRedirect() {
        RequestSpecification rs = given().redirects().follow(true).param("url", "/hello");
        RestResponse resp = getRedirect.callAsSpec(rs);
        resp.assertThat().statusCode(HttpStatus.SC_OK);
        resp.assertThat().body("hello", equalTo("Hello Scalatra"));
    }

    @Test
    public void doesNotFollowRedirect() {
        RequestSpecification rs = given().redirects().follow(false).param("url", "/hello");
        RestResponse resp = getRedirect.callAsSpec(rs);
        resp.assertThat().statusCode(HttpStatus.SC_MOVED_TEMPORARILY);
        resp.assertThat().header("Location", "http://localhost:8080/hello");
    }

}
