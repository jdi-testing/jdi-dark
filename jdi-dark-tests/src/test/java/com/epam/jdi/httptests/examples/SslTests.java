package com.epam.jdi.httptests.examples;

import com.epam.jdi.httptests.DuckDuckGo;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class SslTests {

    @BeforeTest
    public void before() {
        init(DuckDuckGo.class);
    }

    @Test
    public void canMakeRequestToSitesWithValidSslCert() {
        DuckDuckGo.simpleGet.call().isOk();
    }
}