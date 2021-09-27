package com.epam.jdi.http.stepdefs.en;

import com.epam.http.response.ResponseStatusType;
import com.epam.jdi.http.Utils;
import com.jdiai.tools.map.MapArray;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

public class ResponseStepsEN extends Utils {

    @Then("response status code is {int}")
    public void responseStatusCodeIs(int statusCode) {
        restResponse.get().assertThat().statusCode(statusCode);
    }

    @And("response body is empty")
    public void responseBodyIsEmpty() {
        Assertions.assertThat(restResponse.get().getBody().isEmpty()).isTrue();
    }

    @And("response status type is {}")
    public void responseStatusTypeIs(ResponseStatusType type) {
        Assertions.assertThat(restResponse.get().getStatus().type).isEqualTo(type);
    }

    @And("response parameter {string} is {string}")
    public void responseParameterIsValue(String parameter, String value) {
        restResponse.get().assertThat().body(parameter, equalTo(value));
    }

    @And("response parameter {string} contains {string}")
    public void responseParameterContainsValue(String parameter, String value) {
        restResponse.get().assertThat().body(parameter, containsString(value));
    }

    @And("response body has values:")
    public void responseBodyHasValues(DataTable params) {
        MapArray<String, Matcher<?>> map =
                new MapArray<>(params.asLists(), p -> p.get(0), p -> equalTo(p.get(1)));
        restResponse.get().assertBody(map);
    }

    @And("the average response time is less than {} seconds")
    public void averageResponseTime(long seconds) {
        long respTime = performanceResult.get().getAverageResponseTime();
        Assertions.assertThat(respTime)
                .describedAs("The average response time is greater than expected.")
                .isLessThan(seconds * 1000);
    }

    @And("the maximum response time is less than {} seconds")
    public void maxResponseTime(long seconds) {
        long respTime = performanceResult.get().getMaxResponseTime();
        Assertions.assertThat(respTime)
                .describedAs("The maximum response time is greater than expected.")
                .isLessThan(seconds * 1000);
    }

    @And("response header {string} is {string}")
    public void responseHeaderIs(String header, String value) {
        restResponse.get().assertThat().header(header, value);
    }

    @And("print response")
    public void printResponse() {
        restResponse.get();
    }

}
