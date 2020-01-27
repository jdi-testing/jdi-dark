package com.epam.jdi.http.stepdefs.en;

import com.epam.http.requests.RestMethod;
import io.cucumber.java.en.When;

import static com.epam.http.performance.RestLoad.loadService;
import static com.epam.jdi.http.Utils.*;

public class ServiceStepsEN {

    @When("^I load service for (\\d+) sec with get requests$")
    public void loadServiceForSecWithGetRequests(int seconds) {
        RestMethod getMethod = service.get().getGetMethod();
        performanceResult.set(loadService(seconds, getMethod));
    }

    @When("^I do status request with (\\d+) code$")
    public void iCallStatusRequest(int status) {
        restResponse.set(service.get().getStatus().call(String.valueOf(status)));
    }
}
