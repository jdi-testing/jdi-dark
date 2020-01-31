package com.epam.jdi.httptests.steps;

import com.epam.jdi.httptests.ServiceExample;
import io.cucumber.java.en.Given;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.http.Utils.domainUrl;
import static com.epam.jdi.http.Utils.service;
import static com.epam.jdi.http.Utils.getDomain;

public class InitService {
    @Given("^I init service$")
    public void initService() {
        domainUrl.set(getDomain(ServiceExample.class));
        service.set(init(ServiceExample.class));
    }
}
