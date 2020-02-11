package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getHello;
import static com.epam.jdi.httptests.JettyService.getMultiHeaderReflect;
import static com.epam.jdi.httptests.JettyService.getLotto;
import static com.epam.jdi.httptests.JettyService.getHeader;
import static com.epam.jdi.httptests.JettyService.getMultiValueHeader;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class HeaderTests extends WithJetty {

    @BeforeTest
    public void before() {
        init(JettyService.class, requestSpecification);
    }


    @Test
    public void requestDataAllowsSpecifyingHeader() {
        RestResponse response = getHeader.call(
                requestData(requestData ->
                        requestData.headers = new Headers(new Header("MyHeader", "TestValue"))));
        response.isOk();
        response.assertThat().body(containsString("MyHeader"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleHeaders() {
        RestResponse response = getHeader.call(
                requestData(requestData ->
                        requestData.addHeaders(new Object[][]{{"MyHeader", "MyValue"}, {"SecondHeader", "MyValue2"}})));
        response.isOk();
        response.assertThat().body(containsString("MyHeader"))
                .and().assertThat().body(containsString("SecondHeader"));
    }


    @Test
    public void allowsSupplyingMappingFunction() {
        RestResponse response = getHello.call();
        response.isOk();
        response.assertThat().header("Content-Length", Integer::parseInt, lessThanOrEqualTo(100));
    }

    @Test(expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = ".*Expected header .* was not a value greater than .* was .* Headers are:.*")
    public void headerExceptionCanFailWhenUsingMappingFunction() throws Exception {
        RestResponse response = getHello.call();
        response.isOk();
        response.assertThat().header("Content-Length", Integer::parseInt, greaterThan(1000));
    }

    @Test
    public void supportsHeaderStringMatching(){
        RestResponse response = getLotto.call();
        response.assertThat().header("Content-Type", "application/json;charset=utf-8");
    }

    @Test
    public void multipleHeaderStatementsAreConcatenated() {
        RestResponse response = getLotto.call();
        response.assertThat().header("Content-Type", "application/json;charset=utf-8")
                .and().header("Content-Length", "160");
    }

    @Test
    public void multipleHeadersShortVersionUsingPlainStrings() {
        RestResponse response = getLotto.call();
        response.assertThat().headers("Content-Type", "application/json;charset=utf-8",
                "Content-Length", "160");
    }

    @Test
    public void multipleHeadersShortVersionUsingHamcrestMatching() {
        RestResponse response = getLotto.call();
        response.assertThat().headers("Content-Type", containsString("application/json"),
                "Content-Length", equalTo("160"));
    }

    @Test
    public void multipleHeadersShortVersionUsingMixOfHamcrestMatchingAndStringMatching() {
        RestResponse response = getLotto.call();
        response.assertThat().headers("Content-Type", containsString("application/json"),
                "Content-Length", "160");
    }

    @Test(expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = ".*Expected header .* was not .* was .* Headers are:.*")
    public void whenExpectedHeaderDoesntMatchAnAssertionThenAssertionErrorIsThrown() {
        RestResponse response = getLotto.call();
        response.assertThat().header("Content-Length", "161");
    }

    @Test
    public void multipleHeadersTestExample() {
        Header header1 = new Header("MyHeader1", "MyValue1");
        Header header2 = new Header("MyHeader2", "MyValue2");
        Header header3 = new Header("MyHeader3", "MyValue3");
        RestResponse response = getMultiHeaderReflect.call(
                requestData(requestData ->
                        requestData.headers = new Headers(header1, header2, header3)));
        response.isOk();
        response.assertThat().header("MyHeader1", equalTo("MyValue1"))
                .header("MyHeader2", equalTo("MyValue2"))
                .header("MyHeader3", equalTo("MyValue3"));
    }

    @Test
    public void orderIsMaintainedForMultiValueHeaders() throws Exception {
        RestResponse response = getMultiValueHeader.call();
        response.isOk();
        Headers headers = response.headers();
        final List<String> headerListString = headers.getValues("MultiHeader");
        final String firstValue = headers.getValue("MultiHeader");
        final List<Header> headerListHeader = headers.getList("MultiHeader");
        assertThat(headerListString, hasItems("Value 1", "Value 2"));
        assertThat(headerListHeader, hasItems(new Header("MultiHeader", "Value 1"),
                new Header("MultiHeader", "Value 2")));
        assertThat(firstValue, equalTo("Value 2"));
    }

    @Test
    public void requestSpecificationAllowsSpecifyingMultiValueHeaders() throws Exception {
        Header header1 = new Header("MyHeader", "Something");
        Header header2 = new Header("MyHeader", "SomethingElse");
        RestResponse response = getMultiHeaderReflect.call(
                requestData(requestData ->
                        requestData.headers = new Headers(header1, header2)));
        response.isOk();
        assertThat(response.headers().getValues("MyHeader").size(), is(2));
        assertThat(response.headers().getValues("MyHeader"), hasItems("Something", "SomethingElse"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleHeadersWithMultipleValues() {
        RestResponse response = getMultiHeaderReflect.call(
                requestData(requestData ->
                        requestData.addHeaders(new Object[][]{{"Header_01", "Value_01"}, {"Header_02", "Value_02"},
                                {"MultiValuesHeader", "multiValue_01"}})));
        response.isOk();
        response.assertThat().header("Header_01", equalTo("Value_01"));
        response.assertThat().header("Header_02", equalTo("Value_02"));
        assertThat(response.headers().getValues("MultiValuesHeader").size(), is(3));
        assertThat(response.headers().getValues("MultiValuesHeader"),
                hasItems("multiValue_01", "multiValue_02", "multiValue_03"));
    }
}