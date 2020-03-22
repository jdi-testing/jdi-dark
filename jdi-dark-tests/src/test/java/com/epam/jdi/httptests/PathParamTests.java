package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.tools.pairs.Pair;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.RequestDataInfo.pathParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.*;
import static org.hamcrest.Matchers.equalTo;

public class PathParamTests extends WithJetty {
    @BeforeClass
    public void initService() {
        init(JettyService.class);
    }

    @Test
    public void supportsPassingPathParamsToRequestSpec() {
        Object[][] pathParams = new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}};
        RestResponse response = getUserPathParamsSetByArray(pathParams);
        response.isOk().body("fullName", equalTo("John Doe"));
    }

    @Test
    public void urlEncodesPathParams() {
        Object[][] pathParams = new Object[][]{{"firstName", "John:()"}, {"lastName", "Doe"}};
        RestResponse response = getUserPathParamsSetByArray(pathParams);
        response.isOk().body("fullName", equalTo("John:() Doe"));
    }

    @Test
    public synchronized void doesntUrlEncodesPathParamsWhenUrlEncodingIsDisabled() throws Exception {
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
        RestResponse response = getNamedParamBeforePath("path", "something");
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void unnamedPathParametersCanBeAppendedBeforeSubPath() {
        RestResponse response = getParamBeforePath.callWithNamedParams("something");
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void namedPathParametersCanBeAppendedAfterSubPath() {
        RestResponse response = getNamedParamAfterPath("format", "json");
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public void unnamedPathParametersCanBeAppendedAfterSubPath() {
        RestResponse response = getParamAfterPath.callWithNamedParams("json");
        response.isOk().body("value", equalTo("something"));
    }

    @Test
    public synchronized void doesntUrlEncodePathParamsInMapWhenUrlEncodingIsDisabled() {
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
        RestResponse response = getMixedparam.callWithNamedParams("games", "http://myurl.com");
        response.assertThat().statusCode(404);
    }

    @Test
    public void mixingPathParametersAndQueryParametersWorks() {
        Pair<String, String> pathParams = new Pair<>("channelName", "games");
        Pair<String, String> queryParams = new Pair<>("url", "http://myurl.com");

        RestResponse response = getMixedParam(pathParams, queryParams);
        response.assertThat().statusCode(404);
    }

    @Test
    public void usePathParametersLongerTheTemplateName() {
        Object[][] pathParams = new Object[][]{{"abcde", "JohnJohn"}, {"value", "Doe"}};
        RestResponse response = getMatrixPathParamsSetByArray(pathParams);
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
        RestResponse response = getUser.callWithNamedParams("John", " ");
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
        RestResponse response = getUser.callWithNamedParams("John", "");
        response.assertThat().statusCode(404);
    }

    @Test
    public void queryParametersWorksWithoutKeys() {
        RestResponse response = searchGoogleSpecificParam("query");
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
        getUser.callWithNamedParams("john");
    }
}
