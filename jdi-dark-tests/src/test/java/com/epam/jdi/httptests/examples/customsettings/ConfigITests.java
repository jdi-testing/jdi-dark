package com.epam.jdi.httptests.examples.customsettings;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.config.EncoderConfig;
import io.restassured.config.JsonConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ConfigITests extends WithJetty {

    @Test
    public void configCanBeSetPerRequest() {
        JettyService jetty = getJettyService();

        RequestSpecification rs = jetty.getRedirect.getInitSpec()
            .config(RestAssuredConfig.newConfig()
                    .redirect(RedirectConfig.redirectConfig().followRedirects(false))).param("url", "/hello");
        RestResponse response = jetty.getRedirect.call(rs);
        response.assertThat()
                .statusCode(302)
                .and()
                .header("Location", is("http://localhost:8081/hello"));
    }

    @Test
    public void supportsSpecifyingDefaultContentCharset() {
        JettyService jetty = getJettyService();

        String body = "Something {\\\\+$%???";
        RequestSpecification rs = jetty.postReflect.getInitSpec()
            .config(RestAssuredConfig.newConfig()
                    .encoderConfig(EncoderConfig.encoderConfig()
                            .defaultContentCharset("US-ASCII")));
        jetty.postReflect.getData().setContentType(ContentType.TEXT);
        RestResponse resp = jetty.postReflect.call(rs.body(body));
        resp.isOk().assertThat()
            .header("Content-Type", is("text/plain; charset=US-ASCII"))
            .body(equalTo(body));
    }

    @Test
    public void supportsConfiguringJsonConfigProperties() {
        RestResponse resp = getJettyService().getJsonStore.call(RestAssuredConfig.newConfig().
            jsonConfig(JsonConfig.jsonConfig().
                    numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)));
        resp.isOk()
            .rootPath("store.book")
            .body("price.min()", is(new BigDecimal("8.95")))
            .body("price.max()", is(new BigDecimal("22.99")));
    }
}
