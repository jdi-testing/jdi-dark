package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.greetPost;
import static com.epam.jdi.httptests.JettyService.postCharEncoding;
import static com.epam.jdi.httptests.JettyService.postNoValueParam;
import static com.epam.jdi.httptests.JettyService.postNoValueParamDefinedFormParam;
import static com.epam.jdi.httptests.JettyService.putNoValueParam;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

/**
 * This class is using for param cases for JettyService
 * Tests are similar to rest assured cases
 */
public class ParamTest extends WithJetty {

    private static final String PARAM_NAME = "some";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME_VALUE = "John";
    private static final String LAST_NAME_VALUE = "Doe";

    @BeforeMethod
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void noValueParamWhenUsingQueryParamWithGetRequest() {
        RestResponse response = JettyService.getNoValueParam.call(requestData(d -> {
            d.queryParams.add(PARAM_NAME, "");
        }));
        assertEquals(response.body, "Params: some=");
    }

    @Test
    public void whenLastParamInGetRequestEndsWithEqualItsTreatedAsANoValueParam() {
        JettyService.getGreet.call(requestData(d -> {
            d.queryParams.add(FIRST_NAME, FIRST_NAME_VALUE);
            d.queryParams.add(LAST_NAME, "");
        })).isOk().assertThat().body("greeting", equalTo("Greetings John "));
    }

    @Test
    public void whenFirstParamInGetRequestEndsWithEqualItsTreatedAsANoValueParam() {
        JettyService.getGreet.call(requestData(d -> {
            d.queryParams.add(FIRST_NAME, "");
            d.queryParams.add(LAST_NAME, LAST_NAME_VALUE);
        })).isOk().assertThat().body("greeting", equalTo("Greetings  Doe"));
    }

    @Test
    public void multipleNoValueQueryParamWhenUsingQueryParamInUrlForGetRequest() {
        // JettyService service = init(JettyService.class);
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        /*RestResponse resp = GET(requestData(d -> {
            d.uri = ("http://localhost:8080/noValueParam");
            d.path = ("?some&some1");

        }));*/

        RestResponse resp1 =

                JettyService.getNoValueParamWithParamInUrl.call("some1=one&some2=two");
        //   .isOk().assertThat().body(anyOf(is("Params: some=some1="), is("Params: some1=some=")));
        //assertEquals(response.body, "Params: some=");

        //requestData(
        //                rd -> { rd.url = "some&some1";
        //                })

    }

    @Test
    public void formParamsAreUrlEncodedWithUtf8WhenCharsetDefinedWithNoEqualSign() {
        RestResponse resp = greetPost.call(requestData(rd -> {
            rd.contentType = "application/x-www-form-urlencoded; charset";
            rd.formParams.add(FIRST_NAME, "Some & firstname");
            rd.formParams.add(LAST_NAME, "<lastname>");
        }));
        resp.isOk().assertThat()
                .body("greeting", equalTo("Greetings Some & firstname <lastname>"));
    }

    @Test
    public void charsetIsReallyDefined() {
        RestResponse resp = greetPost.call(requestData(rd -> {
            rd.contentType = "application/x-www-form-urlencoded; charset=ISO-8859-1";
            rd.formParams.add("firstName", "Some & firstname");
            rd.formParams.add("lastName", "<lastname>");
        }));
        resp.isOk().assertThat().body("greeting", equalTo("Greetings Some & firstname <lastname>"));
    }

    @Test
    public void formParamsAreUrlEncodedWithDefinedCharset() {
        RestResponse resp = postCharEncoding.call(requestData(rd -> {
            rd.contentType = "application/x-www-form-urlencoded; charset=ISO-8859-1";
            rd.formParams.add("ikk", "&&&");
        }));
        resp.isOk().assertThat().body(is("iso-8859-1"));
    }

    @Test
    public void noValueParamWhenUsingFormParamWithPutRequest() {
        RestResponse resp = putNoValueParam.call(requestData(rd -> {
            rd.formParams.add("some", "");
        }));
        resp.isOk().assertThat().body(is("OK"));
    }

    @Test
    public void noValueParamWhenUsingFormParamWithPostRequest() {
        RestResponse resp = postNoValueParam.call(requestData(rd -> {
            rd.formParams.add("some", "");
        }));
        resp.isOk().assertThat().body(is("Params: some="));
    }

    @Test
    public void multipleNoValueParamWhenUsingFormParamWithPostRequest() {
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        RestResponse resp = postNoValueParam.call(requestData(rd -> {
            rd.formParams.add("some", "");
            rd.formParams.add("some1", "");
        }));
        resp.isOk().assertThat().body(anyOf(is("Params: some=some1="), is("Params: some1=some=")));
    }

    @Test
    public void mixingNoValueAndValueParamWhenUsingFormParamWithPostRequest() {
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        RestResponse resp = postNoValueParam.call(requestData(rd -> {
            rd.formParams.add("some", "");
            rd.formParams.add("some1", "one");
        }));
        resp.isOk().assertThat().body(anyOf(is("Params: some=some1=one"), is("Params: some1=onesome=")));
    }

    @Test
    public void mixingNoValueAndValueParamWhenUsingFormParamWithPostRequestAnnotationDefinedFormParam() {
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        RestResponse resp = postNoValueParamDefinedFormParam.call(requestData(rd -> {
            rd.formParams.add("some", "");
        }));
        resp.isOk().assertThat().body(anyOf(is("Params: some=some1=one"), is("Params: some1=onesome=")));
    }
}
