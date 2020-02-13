package com.epam.jdi.httptests.steps;

import com.epam.jdi.httptests.ServiceExample;
import io.cucumber.java.en.Given;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.http.Utils.service;

public class InitService {
    @Given("^I init service$")
    public void initService() {
        service.set(init(ServiceExample.class));
    }
}
