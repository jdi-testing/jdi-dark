package com.epam.jdi.http.stepdefs.en;

import com.epam.http.requests.RestMethod;
import io.cucumber.java.en.When;

import static com.epam.http.performance.RestLoad.loadService;
import static com.epam.jdi.http.Utils.*;

public class ServiceStepsEN {

    @When("^I load service for (\\d+) sec with ([^\"]*) requests$")
    public void loadServiceForSecWithGetRequests(int seconds, String methodName) throws IllegalAccessException, NoSuchFieldException {
        RestMethod restMethod = getRestMethod(methodName);
        performanceResult.set(loadService(seconds, restMethod));
    }

    @When("^I do status request with (\\d+) code$")
    public void iCallStatusRequest(int status) throws IllegalAccessException, NoSuchFieldException {
        restResponse.set(getRestMethod("status").callWithNamedParams(String.valueOf(status)));
    }
}
