package com.epam.jdi.httptests;

import com.epam.http.requests.ServiceSettings;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.authentication.CertAuthScheme;
import io.restassured.authentication.CertificateAuthSettings;
import static com.epam.http.requests.RequestData.requestTrustStore;
import javafx.util.Pair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyServiceHttps.getGreet;
import static com.epam.jdi.httptests.JettyServiceHttps.getHello;
import static com.epam.jdi.httptests.JettyServiceHttps.getProducts;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SslAnnotationAndRequestDataTests extends WithJetty {

    @BeforeClass
    public void before() {
        init(JettyServiceHttps.class);
    }

    @Test
    public void givenTrustStoreUsingRequestDataAllowsToUseSSL() throws Exception {
        getHello.call(requestTrustStore("src/test/resources/jetty_localhost_client.jks", "test1234"))
                .isOk().body("hello", equalTo("Hello Scalatra"));
    }

    @Test
    public void givenTrustStoreUsingRequestData() {
        getGreet.call(requestData(d -> {
            d.queryParams.add("firstName", "John");
            d.queryParams.add("lastName", "");
            d.trustStore = new Pair<>("src/test/resources/jetty_localhost_client.jks", "test1234");
        })).isOk().assertThat().body("greeting", equalTo("Greetings John "));
    }

    @Test
    public void givenTrustStoreUsingAnnotation() {
        getProducts.call().isOk().assertThat()
                .body("price.sum()", is(38.0d))
                .body("dimensions.width.min()", is(1.0f))
                .body("name.collect { it.length() }.max()", is(16))
                .body("dimensions.multiply(2).height.sum()", is(21.0));
    }

}