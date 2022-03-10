package com.epam.jdi.httptests.examples.rest;

import com.epam.jdi.httptests.support.WithRestService;
import com.epam.jdi.services.RestService;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class SimpleTest extends WithRestService {

    @Test
    public void simpleTest() {
        init(RestService.class).getStatus.call()
                .assertThat()
                .statusCode(200);
    }
}
