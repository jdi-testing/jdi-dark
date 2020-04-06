package com.epam.jdi.httptests.steps;

import com.epam.jdi.services.ServiceExample;
import io.cucumber.java.en.Given;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.http.Utils.service;

public class InitService {
    @Given("init service example")
    public void initService() {
        service.set(init(ServiceExample.class));
    }
}
