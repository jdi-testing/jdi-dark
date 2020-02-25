package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.Config.redirect;

public class RedirectTest {

    @BeforeClass
    public void before() {
        init(Config.class);
    }

    @Test
    public void configCanBeSetPerRequest() {
        RequestSpecification rs = redirect.getInitSpec().config(RestAssuredConfig.config().redirect(RedirectConfig.redirectConfig().followRedirects(false)));
        RestResponse resp = redirect.call(rs);
        resp.assertThat().statusCode(HttpStatus.SC_MOVED_PERMANENTLY);
        resp.assertThat().header("Location", "https://www.google.com/gmail/");
    }

    @Test
    public void configCanBeSetPerRequest2() {
        RequestSpecification rs = redirect.getInitSpec().redirects().follow(true);
        RestResponse resp = redirect.call(rs);
        resp.assertThat().statusCode(HttpStatus.SC_OK);
    }

}
