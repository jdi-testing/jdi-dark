package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.specification.RequestSpecification;
import org.apache.http.message.BasicHeader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static com.epam.http.requests.RequestData.requestQueryParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getRedirect;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.apache.http.client.params.ClientPNames.DEFAULT_HEADERS;
import static org.hamcrest.Matchers.equalTo;

public class HttpClientConfigTests extends WithJetty {

    @BeforeTest
    public void before() {
        init(JettyService.class, requestSpecification);
    }


    @Test
    public void doesntFollowRedirectsIfSpecifiedInTheHttpClientConfig() {
        final List<BasicHeader> httpClientHeaders = new ArrayList<BasicHeader>();
        httpClientHeaders.add(new BasicHeader("header1", "value1"));
        httpClientHeaders.add(new BasicHeader("header2", "value2"));
        RequestSpecification requestSpecification = getRedirect.getInitSpec().config(config()
                .httpClient(httpClientConfig().setParam(DEFAULT_HEADERS, httpClientHeaders)));
        RestResponse response = getRedirect.call(requestSpecification
                .queryParam("url", "multiHeaderReflect"));
        response.assertThat().header("header1", equalTo("value1"))
                .header("header2", equalTo("value2"));
    }

    @Test
    public void doesntFollowRedirectsIfSpecifiedInTheHttpClientConfigServiceObjectQueryParam() {
        RestResponse response = getRedirect.call(requestQueryParams("url", "multiHeaderReflect"));
        response.assertThat().header("Redirect_test_header", equalTo("Redirect_test_header_value"));;
    }
}