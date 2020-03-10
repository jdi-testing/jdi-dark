package com.epam.jdi.httptests;

import com.epam.jdi.httptests.support.WithJetty;
import static com.epam.http.requests.RequestData.requestTrustStore;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyServiceHttps.getHello;
import static com.epam.jdi.httptests.JettyServiceHttps.getJsonStore;
import static com.epam.jdi.httptests.JettyServiceHttps.getProducts;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

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
        getJsonStore.call(requestTrustStore("src/test/resources/jetty_localhost_client.jks", "test1234")).isOk().assertThat()
                .statusCode(allOf(greaterThanOrEqualTo(200), lessThanOrEqualTo(300))).
                rootPath("store.book").
                body("findAll { book -> book.price < 10 }.title", hasItems("Sayings of the Century", "Moby Dick")).
                body("author.collect { it.length() }.sum()", equalTo(53));
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