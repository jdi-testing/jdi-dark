package com.epam.jdi.http.stepdefs.en;

import com.epam.http.requests.RestMethod;
import com.epam.jdi.http.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import static com.epam.http.performance.RestLoad.loadService;
import static java.lang.String.format;

public class PerformanceStepsEN extends Utils {

    @When("load service for {int} seconds with {string} request")
    public void loadServiceForSecWithGetRequests(int seconds, String methodName) throws IllegalAccessException,
            NoSuchFieldException, InterruptedException {
        RestMethod restMethod = getRestMethod(methodName);
        performanceResult.set(loadService(seconds, restMethod));
    }

    @When("load service by {int} concurrent threads for {int} seconds with {string} request")
    public void loadServiceForSecWithGetRequests(int concurrentThreads, int seconds, String methodName) throws
            IllegalAccessException, InterruptedException, NoSuchFieldException {
        RestMethod restMethod = getRestMethod(methodName);
        performanceResult.set(loadService(concurrentThreads, seconds, restMethod));
    }

    @Then("performance result doesn't have any fails")
    public void performanceResultsDonTHaveAnyFails() {
        Assertions.assertThat(performanceResult.get().noFails())
                .describedAs(format("There were %s failures.", performanceResult.get().getNumberOfFails())).isTrue();
    }

    @And("print number of performance results requests")
    public void printNumberOfRequests() {
        System.out.println("There were " + performanceResult.get().getNumberOfRequests() + " requests.");
    }
}
