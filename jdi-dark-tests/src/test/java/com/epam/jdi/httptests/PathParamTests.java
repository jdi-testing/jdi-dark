package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.RequestData.requestPathParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getMatrix;
import static com.epam.jdi.httptests.JettyService.getParamAfterPath;
import static com.epam.jdi.httptests.JettyService.getParamBeforePath;
import static com.epam.jdi.httptests.JettyService.getUser;
import static org.hamcrest.Matchers.equalTo;


public class PathParamTests extends WithJetty {
    @BeforeClass
    public void initService() {
        init(JettyService.class);
    }

    @Test
    public void supportsPassingPathParamsToRequestSpec() {
        RestResponse response = getUser
                .call(requestPathParams(new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}}));
        response.isOk().body("fullName", equalTo("John Doe"));
    }

    @Test
    public void urlEncodesPathParams() {
        RestResponse response = getUser
                .call(requestPathParams(new Object[][]{{"firstName", "John:()"}, {"lastName", "Doe"}}));
        response.isOk().body("fullName", equalTo("John:() Doe"));
    }

    @Test
    public void doesntUrlEncodesPathParamsWhenUrlEncodingIsDisabled() throws Exception {
        RestAssured.urlEncodingEnabled = false;
        final String encoded = URLEncoder.encode("John:()", "UTF-8");
        try {
            RestResponse response = getUser
                    .call(requestPathParams(new Object[][]{{"firstName", encoded}, {"lastName", "Doe"}}));
            response.isOk().body("fullName", equalTo("John:() Doe"));
        } finally {
            RestAssured.reset();
        }
    }

    @Test
    public void supportsPassingPathParamsAsMapToRequestSpec() {
        final Map<String, String> params = new HashMap<>();
        params.put("firstName", "John2");
        params.put("lastName", "Doe");
        RestResponse response = getUser
                .call(requestData(d -> d.pathParams.addAll(params)));
        response.isOk().body("fullName", equalTo("John2 Doe"));
    }

    @Test
    public void namedPathParametersCanBeAppendedBeforeSubPath() {
        RestResponse response = getParamBeforePath.call(requestPathParams("path", "something"));
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void namedPathParametersCanBeAppendedAfterSubPath() {
        RestResponse response = getParamAfterPath.call(requestPathParams("format", "json"));
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void doesntUrlEncodePathParamsInMapWhenUrlEncodingIsDisabled() {
        RestAssured.urlEncodingEnabled = false;
        try {
            final Map<String, String> params = new HashMap<>();
            params.put("firstName", "John%20å");
            params.put("lastName", "Doe");
            RestResponse response = getUser
                    .call(requestData(d -> d.pathParams.addAll(params)));
            response.isOk().body("fullName", equalTo("John å Doe"));
        } finally {
            RestAssured.reset();
        }
    }

    @Test
    public void usePathParametersShorterTheTemplateName() {
        RestResponse response = getMatrix
                .call(requestPathParams(new Object[][]{{"abcde", "John"}, {"value", "Doe"}}));
        response.isOk().body("John", equalTo("Doe"));
    }

    @Test
    public void usePathParametersLongerTheTemplateName() {
        RestResponse response = getMatrix
                .call(requestPathParams(new Object[][]{{"abcde", "JohnJohn"}, {"value", "Doe"}}));
        response.isOk().body("JohnJohn", equalTo("Doe"));
    }
}
