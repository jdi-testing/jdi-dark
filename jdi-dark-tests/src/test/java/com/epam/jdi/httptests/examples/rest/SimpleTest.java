package com.epam.jdi.httptests.examples.rest;

import com.epam.jdi.httptests.support.WithRestService;
import com.epam.jdi.services.RestService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class SimpleTest extends WithRestService {

    @BeforeClass
    public void before() {
        init(RestService.class);
    }

    @Test
    public void simpleTest() {
        RestService.getStatus.call()
                .assertThat()
                .statusCode(200);
    }
}
