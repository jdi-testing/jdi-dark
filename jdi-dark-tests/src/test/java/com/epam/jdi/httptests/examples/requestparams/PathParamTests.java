package com.epam.jdi.httptests.examples.requestparams;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import com.jdiai.tools.pairs.Pair;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.RequestDataFactory.pathParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.JettyService.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class PathParamTests extends WithJetty {
    @BeforeClass
    public void initService() {
        init(JettyService.class);
    }

    @Test
    public void supportsPassingPathParamsToRequestSpec() {
        Object[][] pathParams = new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}};
        RestResponse response = getUser.call(pathParams().addAll(pathParams));
        response.isOk().body("fullName", equalTo("John Doe"));
    }

    @Test
    public void urlEncodesPathParams() {
        Object[][] pathParams = new Object[][]{{"firstName", "John:()"}, {"lastName", "Doe"}};
        RestResponse response = getUserPathParamsSetByArray(pathParams);
        response.isOk().body("fullName", equalTo("John:() Doe"));
    }

    @Test
    public void doesntUrlEncodesPathParamsWhenUrlEncodingIsDisabled() throws Exception {
        RestAssured.urlEncodingEnabled = false;
        final String encoded = URLEncoder.encode("John:()", "UTF-8");
        try {
            Object[][] pathParams = new Object[][]{{"firstName", encoded}, {"lastName", "Doe"}};
            RestResponse response = getUserPathParamsSetByArray(pathParams);
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
        RestResponse response = getUserPathParamsSetByMap(params);
        response.isOk().body("fullName", equalTo("John2 Doe"));
    }

    @Test
    public void namedPathParametersCanBeAppendedBeforeSubPath() {
        RestResponse response = getParamBeforePath.call(pathParams().add("path", "something"));
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void unnamedPathParametersCanBeAppendedBeforeSubPath() {
        RestResponse response = getParamBeforePath.pathParams("something").call();
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void namedPathParametersCanBeAppendedAfterSubPath() {
        RestResponse response = getParamAfterPath.call(pathParams().add("format", "json"));
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void unnamedPathParametersCanBeAppendedAfterSubPath() {
        RestResponse response = getParamAfterPath.pathParams("json").call();
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void doesntUrlEncodePathParamsInMapWhenUrlEncodingIsDisabled() {
        RestAssured.urlEncodingEnabled = false;
        try {
            final Map<String, String> params = new HashMap<>();
            params.put("firstName", "John%20å");
            params.put("lastName", "Doe");
            RestResponse response = getUserPathParamsSetByMap(params);
            response.isOk().body("fullName", equalTo("John å Doe"));
        } finally {
            RestAssured.reset();
        }
    }

    @Test
    public void usePathParametersShorterTheTemplateName() {
        Object[][] pathParams = new Object[][]{{"abcde", "John"}, {"value", "Doe"}};
        RestResponse response = getMatrix.call(pathParams().addAll(pathParams));
        response.isOk().body("John", equalTo("Doe"));
    }

    @Test
    public void usePath() {
        Object[][] pathParams = new Object[][]{
                {"firstName", "Last"},
                {"lastName", "Name"}
        };
        RestResponse response = getUser
                .call(pathParams().addAll(pathParams));
        response.isOk().body("fullName", equalTo("Last Name"));
    }

    @Test
    public void mixingUnnamedPathParametersAndQueryParametersWorks() {
        RestResponse response = getMixedParam.pathParams("games", "http://myurl.com").call();
        assertEquals(response.getBody(), "Not found");
    }

    @Test
    public void mixingPathParametersAndQueryParametersWorks() {
        Pair<String, String> pathParams = new Pair<>("channelName", "games");
        Pair<String, String> queryParams = new Pair<>("url", "http://myurl.com");
        RestResponse response = getMixedParam.call(pathParams().add(pathParams).queryParamsUpdater().add(queryParams));
        assertEquals(response.getBody(), "Not found");
    }

    @Test
    public void usePathParametersLongerTheTemplateName() {
        Object[][] pathParams = new Object[][]{{"abcde", "JohnJohn"}, {"value", "Doe"}};
        RestResponse response = getMatrix.call(pathParams().addAll(pathParams));
        response.isOk().body("JohnJohn", equalTo("Doe"));
    }

    @Test
    public void supportsPassingIntPathParamsToRequestSpec() {
        Object[][] pathParams = new Object[][]{{"firstName", "John"}, {"lastName", 42}};
        RestResponse response = getUserPathParamsSetByArray(pathParams);
        response.isOk().body("fullName", equalTo("John 42"));
    }

    @Test
    public void canUsePathParamsWithNonStandardChars() {
        final String nonStandardChars = "\\$£@\"){¤$";
        Object[][] pathParams = new Object[][]{{"firstName", nonStandardChars}, {"lastName", "Last"}};
        RestResponse response = getUserPathParamsSetByArray(pathParams);
        response.isOk().body("fullName", equalTo("\\$£@\"){¤$ Last"));
    }

    @Test
    public void urlEncodesPathParamsInMap() {
        final Map<String, String> params = new HashMap<>();
        params.put("firstName", "John: å");
        params.put("lastName", "Doe");
        RestResponse response = getUserPathParamsSetByMap(params);
        response.isOk().body("fullName", equalTo("John: å Doe"));
    }

    @Test
    public void
    canSpecifySpacePathParamsWithoutKey() {
        RestResponse response = getUser.pathParams("John", " ").call();
        response.isOk().body("firstName", equalTo("John")).body("lastName", equalTo(" "));
    }

    @Test
    public void canSpecifyEmptyPath() {
        Object[][] pathParams = new Object[][]{{"firstName", "John"}, {"lastName", ""}};
        RestResponse response = getUserPathParamsSetByArray(pathParams);
        response.assertThat().statusCode(404);
    }

    @Test
    public void
    canSpecifyEmptyPathWithoutKey() {
        RestResponse response = getUser.pathParams("John", "").call();
        response.assertThat().statusCode(404);
    }

    @Test
    public void queryParametersWorksWithoutKeys() {
        RestResponse response = searchGoogle.pathParams("query").call();
        response.isOk();
    }

    @Test
    public void passingInSinglePathParamsThatHaveBeenDefinedMultipleTimesWorks() {
        Object[][] pathParams = new Object[][]{{"firstName", "John"}};
        RestResponse response = getUserSameParametersSetByArray(pathParams);
        response.isOk().body("fullName", equalTo("John John"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Invalid number of path parameters. Expected 2, was 1.*")
    public void passingLessNamedPathParamsThanGivenThrowsIAE() {
        Object[][] pathParams = new Object[][]{{"firstName", "John"}};
        getUserPathParamsSetByArray(pathParams);
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Invalid number of path parameters. Expected 2, was 1.*")
    public void passingLessPathParamsThanGivenThrowsIAE() {
        getUser.pathParams("john").call();
    }
}
