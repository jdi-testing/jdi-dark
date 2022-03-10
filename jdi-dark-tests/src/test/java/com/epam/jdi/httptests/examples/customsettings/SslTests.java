package com.epam.jdi.httptests.examples.customsettings;

import com.epam.jdi.services.DuckDuckGo;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class SslTests {

    @Test
    public void canMakeRequestToSitesWithValidSslCert() {
        init(DuckDuckGo.class).simpleGet.call().isOk();
    }
}