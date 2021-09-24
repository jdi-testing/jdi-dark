package com.epam.jdi.http.stepdefs.en;

import com.epam.http.requests.RestMethod;
import com.epam.jdi.http.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static com.jdiai.tools.LinqUtils.first;
import static io.restassured.http.ContentType.values;

public class RequestStepsEN extends Utils {

    @Given("{string} method is alive")
    public void theGetMethodIsAlive(String methodName) throws IllegalAccessException, NoSuchFieldException {
        RestMethod restMethod = getRestMethod(methodName);
        restMethod.isAlive();
    }

    @When("perform {string} request")
    public void performRequest(String methodName) throws IllegalAccessException, NoSuchFieldException {
        RestMethod restMethod = prepareRestMethod(methodName);
        restResponse.set(restMethod.call());
    }

    @And("set request headers:")
    public void setRequestHeaders(DataTable headers) {
        preparedHeader.set(null);
        HashMap<String, String> hashMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : headers.asMap(String.class, String.class).entrySet()) {
            hashMap.put((String) entry.getKey(), (String) entry.getValue());
        }
        preparedHeader.set(hashMap);
    }

    @And("set request content type to {string}")
    public void setJSONRequestContentType(String type) {
        ContentType contentType = first(values(),
                ct -> type.equalsIgnoreCase(ct.name()));
        requestContentType.set(contentType);
    }

    @And("perform {string} request with named path parameters {string}")
    public void performRequestWithNamedParams(String methodName, String params) throws IllegalAccessException, NoSuchFieldException {
        RestMethod restMethod = prepareRestMethod(methodName);
        restResponse.set(restMethod.pathParams(params.split(",")).call());
    }

    @And("perform {string} request with query parameters {string}")
    public void performRequestWithQueryParams(String methodName, String params) throws IllegalAccessException, NoSuchFieldException {
        RestMethod restMethod = prepareRestMethod(methodName);
        restResponse.set(restMethod.queryParams(params).call());
    }
}
