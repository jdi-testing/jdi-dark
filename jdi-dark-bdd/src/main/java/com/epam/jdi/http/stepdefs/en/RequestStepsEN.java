package com.epam.jdi.http.stepdefs.en;

import com.epam.http.requests.RestMethod;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;


import static com.epam.jdi.http.Utils.preparedHeader;
import static com.epam.jdi.http.Utils.requestContentType;
import static com.epam.jdi.http.Utils.restResponse;
import static com.epam.jdi.http.Utils.getRestMethod;
import static com.epam.jdi.tools.LinqUtils.first;
import static io.restassured.http.ContentType.values;

public class RequestStepsEN {

    @Then("^I verify that ([^\"]*) method is alive$")
    public void theGetMethodIsAlive(String methodName) throws IllegalAccessException, NoSuchFieldException {
        RestMethod restMethod = getRestMethod(methodName);
        restMethod.isAlive();
    }
    @When("^I do ([^\"]*) request$")
    public void iCallMethod(String methodName) throws IllegalAccessException, NoSuchFieldException {
        RestMethod restMethod = getRestMethod(methodName);
//        if (preparedHeader.get() != null) {
//            for (Map.Entry<String, String> entry : preparedHeader.get().entrySet()) {
//                restMethod.addHeaders(entry.getKey(), entry.getValue());
//            }
//        }
        if (requestContentType.get() != null)
            restMethod.setContentType(requestContentType.get());
        restResponse.set(restMethod.call());
    }

    @Given("^I have the following headers:$")
    public void iHaveTheFollowingHeaders(DataTable headers) {
        preparedHeader.set(null);
        HashMap<String, String> hashMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : headers.asMap(String.class, String.class).entrySet()) {
            hashMap.put((String) entry.getKey(), (String) entry.getValue());
        }
        preparedHeader.set(hashMap);
    }

    @And("^I set ([^\"]*) request content type$")
    public void iSetJSONRequestContentType(String type) {
        ContentType contentType = first(values(),
                ct -> type.equalsIgnoreCase(ct.name()));
        requestContentType.set(contentType);
    }
}
