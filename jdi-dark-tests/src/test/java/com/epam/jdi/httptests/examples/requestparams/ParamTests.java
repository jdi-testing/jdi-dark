package com.epam.jdi.httptests.examples.requestparams;

import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.builder.MultiPartSpecBuilder;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.Matchers.*;

/**
 * This class is using for param cases for JettyService
 * Tests are similar to rest assured cases
 */
public class ParamTests extends WithJetty {

    private static final String PARAM_NAME = "some";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME_VALUE = "John";
    private static final String LAST_NAME_VALUE = "Doe";

    @BeforeClass
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void noValueParamWhenUsingQueryParamWithGetRequest() {
        JettyService.getNoValueParamWithKeyValueQueryParam(PARAM_NAME, StringUtils.EMPTY)
            .isOk()
            .assertThat()
            .body(equalTo("Params: some="));
    }

    @Test
    public void whenLastParamInGetRequestEndsWithEqualItsTreatedAsANoValueParam() {
        Map<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put(FIRST_NAME, FIRST_NAME_VALUE);
        queryParamsMap.put(LAST_NAME, StringUtils.EMPTY);

        JettyService.getGreetWithMapOfQueryParams(queryParamsMap)
                .isOk()
                .assertThat()
                .body("greeting", equalTo("Greetings John "));
    }

    @Test
    public void whenFirstParamInGetRequestEndsWithEqualItsTreatedAsANoValueParam() {
        Map<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put(FIRST_NAME, StringUtils.EMPTY);
        queryParamsMap.put(LAST_NAME, LAST_NAME_VALUE);

        JettyService.getGreetWithMapOfQueryParams(queryParamsMap)
            .isOk()
            .assertThat()
            .body("greeting", equalTo("Greetings  Doe"));
    }

    @Test
    public void multipleNoValueQueryParamWhenUsingQueryParamInUrlForGetRequest() {
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        JettyService.getNoValueParamWithStringQueryParams("some&some1")
                .isOk()
                .assertThat()
                .body(anyOf(is("Params: some=some1="), is("Params: some1=some=")));
    }

    @Test
    public void singleNoValueQueryParamWhenUsingQueryParamInUrlForGetRequest() {
        JettyService.getNoValueParamWithStringQueryParams("some")
                .isOk()
                .assertThat()
                .body(is("Params: some="));
    }

    @Test
    public void mixingStartingNoValueQueryParamWhenUsingQueryParamInUrlForGetRequest() {
        JettyService.getNoValueParamWithStringQueryParams("some1&some2=one")
                .isOk()
                .assertThat()
                .body(is("Params: some1=some2=one"));
    }

    @Test
    public void mixingEndingNoValueQueryParamWhenUsingQueryParamInUrlForGetRequest() {
        JettyService.getNoValueParamWithStringQueryParams("some1=one&some2")
                .isOk().assertThat().body(is("Params: some1=onesome2="));
    }

    @Test
    public void formParamsAreUrlEncodedWithUtf8WhenCharsetDefinedWithNoEqualSign() {
        Map<String, String> formParamsMap = new HashMap<>();
        formParamsMap.put(FIRST_NAME, "Some & firstname");
        formParamsMap.put(LAST_NAME, "<lastname>");

        JettyService.greetPostWithContentTypeAndMapOfFormParams("application/x-www-form-urlencoded; charset", formParamsMap)
                .isOk()
                .assertThat()
                .body("greeting", equalTo("Greetings Some & firstname <lastname>"));
    }

    @Test
    public void charsetIsReallyDefined() {
        Map<String, String> formParamsMap = new HashMap<>();
        formParamsMap.put(FIRST_NAME, "Some & firstname");
        formParamsMap.put(LAST_NAME, "<lastname>");

        JettyService.greetPostWithContentTypeAndMapOfFormParams("application/x-www-form-urlencoded; charset=ISO-8859-1", formParamsMap)
                .isOk()
                .assertThat()
                .body("greeting", equalTo("Greetings Some & firstname <lastname>"));
    }

    @Test
    public void formParamsAreUrlEncodedWithDefinedCharset() {
        JettyService.postCharEncodingWithContentTypeAndKeyValueFormParam("application/x-www-form-urlencoded; charset=ISO-8859-1",
                "ikk", "&&&")
                .isOk()
                .assertThat()
                .body(is("iso-8859-1"));
    }

    @Test
    public void noValueParamWhenUsingFormParamWithPutRequest() {
        JettyService.putNoValueParamWithKeyValueFormParam("some", StringUtils.EMPTY)
                .isOk()
                .assertThat()
                .body(is("OK"));
    }

    @Test
    public void noValueParamWhenUsingFormParamWithPostRequest() {
        JettyService.postNoValueParamWithKeyValueFormParam("some", StringUtils.EMPTY)
                .isOk().assertThat().body(is("Params: some="));
    }

    @Test
    public void multipleNoValueParamWhenUsingFormParamWithPostRequest() {
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        Map<String, String> formParamsMap = new HashMap<>();
        formParamsMap.put("some", StringUtils.EMPTY);
        formParamsMap.put("some1", StringUtils.EMPTY);

        JettyService.postNoValueParamWithMapOfFormParams(formParamsMap)
                .isOk()
                .assertThat()
                .body(anyOf(is("Params: some=some1="), is("Params: some1=some=")));
    }

    @Test
    public void mixingNoValueAndValueParamWhenUsingFormParamWithPostRequest() {
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        Map<String, String> formParamsMap = new HashMap<>();
        formParamsMap.put("some", StringUtils.EMPTY);
        formParamsMap.put("some1", "one");

        JettyService.postNoValueParamWithMapOfFormParams(formParamsMap)
                .isOk()
                .assertThat()
                .body(anyOf(is("Params: some=some1=one"), is("Params: some1=onesome=")));
    }

    @Test
    public void mixingNoValueAndValueParamWhenUsingFormParamWithPostRequestAnnotationDefinedFormParam() {
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        JettyService.postNoValueParamWithPreDefinedFormParamAndNewKeyValueParam("some", StringUtils.EMPTY)
                .isOk()
                .assertThat()
                .body(anyOf(is("Params: some=some1=one"), is("Params: some1=onesome=")));
    }

    @Test
    public void multiPartUploadingWorksForFormParamsAndByteArray() {
        Map<String, String> formParamsMap = new HashMap<>();
        formParamsMap.put("formParam1", StringUtils.EMPTY);
        formParamsMap.put("formParam2", "formParamValue");

        JettyService.postMultiPartMultipleWithFormParamsAndMPBuilders(formParamsMap,
                new MultiPartSpecBuilder("juX").controlName("file"),
                new MultiPartSpecBuilder("body").controlName("string"))
                .assertThat()
                .statusCode(HttpStatus.OK_200)
                .body(containsString("formParam1 -> WrappedArray()"));
    }
}
