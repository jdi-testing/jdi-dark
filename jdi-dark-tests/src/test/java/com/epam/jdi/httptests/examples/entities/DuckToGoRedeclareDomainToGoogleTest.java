package com.epam.jdi.httptests.examples.entities;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.DuckDuckGo;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class DuckToGoRedeclareDomainToGoogleTest {

    @Test
    public void callGetWithRedeclaredDomainWithSimpleString() {
        init(DuckDuckGo.class, "https://google.com/");
    
        final RestResponse call = DuckDuckGo.simpleGet.call();
        call.isOk();
        call.validate((it-> {
            final String body = it.getBody();
            return body.contains("Google");
        }));
    }

    @Test
    public void callGetWithRedeclaredDomainWithSimpleStringWithTemplate() {
        init(DuckDuckGo.class, "${google}");
    
        final RestResponse call = DuckDuckGo.simpleGet.call();
        call.isOk();
        call.validate((it-> {
            final String body = it.getBody();
            return body.contains("Google");
        }));
    }
}
