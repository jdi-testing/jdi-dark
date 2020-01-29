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

import static com.epam.jdi.tools.LinqUtils.first;
import static io.restassured.http.ContentType.values;

public class RequestStepsEN {

    @Then("^I verify that ([^\"]*) method is alive$")
    public void theGetMethodIsAlive(String methodName) {
        RestMethod getMethod = getRestMethod(methodName);
        getMethod.isAlive();
    }

    @When("^I do ([^\"]*) request$")
    public void iCallMethod(String methodName) {
        RestMethod restMethod;
        switch (methodName.toUpperCase()) {
            case "POST":
                restMethod = service.get().getPostMethod();
                break;
            case "GET":
                restMethod = service.get().getGetMethod();
                break;
            case "PUT":
                restMethod = service.get().getPutMethod();
                break;
            case "DELETE":
                restMethod = service.get().getDelete();
                break;
            case "PATCH":
                restMethod = service.get().getPatch();
                break;
            case "STATUS":
                restMethod = service.get().getStatus();
                break;
            default:
                return;
        }
        if (preparedHeader.get() != null) {
            for (Map.Entry<String, String> entry : preparedHeader.get().entrySet()) {
                restMethod.addOrReplaceHeader(entry.getKey(), entry.getValue());
            }
        }
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
