package com.epam.jdi.httptests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class ParamTest {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void noValueParamWhenUsingParamWithGetRequest() {

    }
}
