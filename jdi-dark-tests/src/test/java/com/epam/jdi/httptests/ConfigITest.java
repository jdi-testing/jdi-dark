package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.config.EncoderConfig;
import io.restassured.config.JsonConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getJsonStore;
import static com.epam.jdi.httptests.JettyService.getRedirect;
import static com.epam.jdi.httptests.JettyService.postReflect;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ConfigITest extends WithJetty {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void configCanBeSetPerRequest() {
        RequestSpecification rs = getRedirect.getInitSpec()
                .config(RestAssuredConfig.newConfig()
                        .redirect(RedirectConfig.redirectConfig().followRedirects(false))).param("url", "/hello");
        RestResponse response = getRedirect.call(rs);
        response.assertThat()
                .statusCode(302)
                .and()
                .header("Location", is("http://localhost:8080/hello"));
    }

    @Test
    public void supportsSpecifyingDefaultContentCharset() {
        String body = "Something {\\\\+$%???";
        RequestSpecification rs = postReflect.getInitSpec()
                .config(RestAssuredConfig.newConfig()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .defaultContentCharset("US-ASCII")));
        postReflect.setContentType(ContentType.TEXT);
        RestResponse resp = postReflect.call(rs.body(body));
        resp.isOk().assertThat()
                .header("Content-Type", is("text/plain; charset=US-ASCII"))
                .body(equalTo(body));
    }

    @Test
    public void supportsConfiguringJsonConfigProperties() {
        RequestSpecification rs = getJsonStore.getInitSpec().
                config(RestAssuredConfig.newConfig().
                        jsonConfig(JsonConfig.jsonConfig().
                                numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)));
        RestResponse resp = getJsonStore.call(rs);
        resp.isOk()
                .rootPath("store.book")
                .body("price.min()", is(new BigDecimal("8.95")))
                .body("price.max()", is(new BigDecimal("22.99")));
    }
}
