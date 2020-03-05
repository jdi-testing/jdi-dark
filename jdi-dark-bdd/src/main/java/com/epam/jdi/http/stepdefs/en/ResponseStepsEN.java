package com.epam.jdi.http.stepdefs.en;

import com.epam.http.response.ResponseStatusType;
import com.epam.jdi.tools.map.MapArray;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.hamcrest.Matcher;

import static com.epam.jdi.http.Utils.performanceResult;
import static com.epam.jdi.http.Utils.restResponse;
import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResponseStepsEN {

    @Then("^Performance results don't have any fails$")
    public void performanceResultsDonTHaveAnyFails() {
        assertTrue(performanceResult.get().noFails());
    }

    @And("^I check number of requests$")
    public void iCheckNumberOfRequests() {
        System.out.println("There were " + performanceResult.get().NumberOfRequests + " requests.");
    }

    @Then("^Response status code equals (\\d+)$")
    public void responseStatusCodeEquals(int statusCode){
        assertEquals(restResponse.get().status.code, statusCode);
    }

    @And("^Response body is empty")
    public void responseBodyIs() {
        assertEquals(restResponse.get().body, "");
    }

    @And("^Response status type is ([^\"]*)$")
    public void responseStatusTypeIs(String type) {
        Boolean typeMatches = false;
        for (ResponseStatusType responseStatusType : ResponseStatusType.values()) {
            if(type.equalsIgnoreCase(responseStatusType.name()))
                typeMatches = true;
        }
        assertTrue(typeMatches);
    }

    @And("^Response \"([^\"]*)\" is \"([^\"]*)\"$")
    public void responseParameterIsValue(String parameter, String value) {
        restResponse.get().assertThat().body(parameter, equalTo(value));
    }
    @And("^Response \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void responseParameterContainsValue(String parameter, String value) {
        restResponse.get().assertThat().body(parameter, containsString(value));
    }
    @And("^Response body has values$")
    public void responseBodyHasValues(DataTable params) {
        MapArray<String, Matcher<?>> map =
            new MapArray<>(params.asLists(), p -> p.get(0), p -> equalTo(p.get(1)));
        restResponse.get().assertBody(map);
    }

    @Then("^I check if performance results contain any fails$")
    public void iCheckIfPerformanceResultsContainAnyFails() {
        long numberOfFails = performanceResult.get().NumberOfFails;
        assertEquals(numberOfFails, 0,
                format("There were %s failures.", numberOfFails));
    }

    @And("^Average response time is lesser than (\\d+) sec$")
    public void averageResponseTime(long seconds) {
        long respTime = performanceResult.get().AverageResponseTime;
        assertTrue(respTime < seconds*1000,
                format("Average response time %s msec but expected not more than 2 sec", respTime));
    }

    @And("^Response header \"([^\"]*)\" is \"([^\"]*)\"$")
    public void responseHeaderIs(String parameter, String value) {
        restResponse.get().assertThat().header(parameter, value);
    }

    @And("^I print response$")
    public void iPrintResponse() {
        restResponse.get();
    }
}
