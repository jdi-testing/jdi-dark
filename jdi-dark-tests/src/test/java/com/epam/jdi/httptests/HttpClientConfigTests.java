package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static com.epam.http.requests.RequestData.requestQueryParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getRedirect;
import static io.restassured.RestAssured.requestSpecification;
import static org.apache.http.client.params.ClientPNames.DEFAULT_HEADERS;
import static org.hamcrest.Matchers.equalTo;

public class HttpClientConfigTests extends WithJetty {

    @BeforeTest
    public void before() { init(JettyService.class, requestSpecification);
    }

    @Test
    public void followsRedirectsWhileKeepingHeadersSpecifiedIfRestAssuredConfig() throws Exception {
        final List<Header> httpClientHeaders = new ArrayList<Header>();
        httpClientHeaders.add(new BasicHeader("header1", "value1"));
        httpClientHeaders.add(new BasicHeader("header2", "value2"));
        RestAssured.config = RestAssuredConfig.newConfig().httpClient(HttpClientConfig
                .httpClientConfig().setParam(DEFAULT_HEADERS, httpClientHeaders));
        RestResponse response = getRedirect.call(requestQueryParams("url", "multiHeaderReflect"));
        response.isOk();
        response.assertThat().header("header1", equalTo("value1"))
                .header("header2", equalTo("value2"));
        RestAssured.reset();
    }
}