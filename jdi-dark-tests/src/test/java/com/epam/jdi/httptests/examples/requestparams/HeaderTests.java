package com.epam.jdi.httptests.examples.requestparams;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.http.requests.RequestDataFactory.headers;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.JettyService.getHello;
import static com.epam.jdi.services.JettyService.getLotto;
import static com.epam.jdi.services.JettyService.getMultiHeaderReflect;
import static com.epam.jdi.services.JettyService.getMultiValueHeader;
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
        init(JettyService.class);
    }

    /**
     * Here are test examples for headers.
     * Below this test you can find examples without call of service object methods.
     */

    @Test
    public void requestDataAllowsSpecifyingHeader() {
        RestResponse response = JettyService.getWithSingleHeader("MyHeader", "TestValue");
        response.isOk();
        response.assertThat().header("MyHeader", equalTo("TestValue"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultiValueHeaders() {
        Header header1 = new Header("MyHeader", "Something");
        Header header2 = new Header("MyHeader", "SomethingElse");
        RestResponse response = JettyService.getWithMultipleHeaders(header1, header2);
        response.isOk();
        assertThat(response.headers().getValues("MyHeader").size(), is(2));
        assertThat(response.headers().getValues("MyHeader"), hasItems("Something", "SomethingElse"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleHeadersAsObjectArray() {
        RestResponse response = JettyService.getWithMultipleHeaders(
                new Object[][]{{"Header_01", "Value_01"}, {"Header_02", "Value_02"},
                        {"Header_03", "Value_03"}});
        response.isOk();
        response.assertThat().header("Header_01", equalTo("Value_01"))
                .header("Header_02", equalTo("Value_02"))
                .header("Header_03", equalTo("Value_03"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleValueHeaderWithServiceObjectMethod() {
        RestResponse response = JettyService.getWithSingleHeader(
                "Header_Name", "Header_Value", "Header_Next_Value", "Header_Next_Next_Value");
        response.isOk();
        final List<String> headerListString = response.headers().getValues("Header_Name");
        assertThat(headerListString.size(), is(3));
        assertThat(headerListString, hasItems("Header_Value", "Header_Next_Value", "Header_Next_Next_Value"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleHeaders() {
        Header header1 = new Header("MyHeader1", "MyValue1");
        Header header2 = new Header("MyHeader2", "MyValue2");
        Header header3 = new Header("MyHeader3", "MyValue3");
        RestResponse response = JettyService.getWithMultipleHeaders(
                header1, header2, header3);
        response.isOk();
        response.assertThat().header("MyHeader1", equalTo("MyValue1"))
                .header("MyHeader2", equalTo("MyValue2"))
                .header("MyHeader3", equalTo("MyValue3"));
    }

    /**
     * Here are previous test examples without using Service Object methods.
     * Request data here is modified using functional interface.
     * We do not recommend this method for tests writing, however we left these
     * test as examples.
     */

    @Test
    public void requestDataAllowsSpecifyingHeaderWithoutServiceObjectMethods() {
        RestResponse response = getMultiHeaderReflect
                .call(headers().add("MyHeader", "TestValue"));
        response.isOk().assertThat().header("MyHeader", equalTo("TestValue"));
    }

    @Test
    public void requestSpecificationAllowsSpecifyingMultiValueHeadersWithoutServiceObjectMethod() {
        RestResponse response = getMultiHeaderReflect
                .call(headers().addAll(new Object[][]{
                        {"MyHeader", "Something"},
                        {"MyHeader", "SomethingElse"}}));
        response.isOk();
        assertThat(response.headers().getValues("MyHeader").size(), is(2));
        assertThat(response.headers().getValues("MyHeader"), hasItems("Something", "SomethingElse"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleHeadersAsObjectArrayWithoutServiceObjectMethod() {
        RestResponse response = getMultiHeaderReflect
                .call(headers().addAll(new Object[][]{
                        {"Header_01", "Value_01"},
                        {"Header_02", "Value_02"},
                        {"Header_03", "Value_03"}}));
        response.isOk();
        response.assertThat().header("Header_01", equalTo("Value_01"))
                .header("Header_02", equalTo("Value_02"))
                .header("Header_03", equalTo("Value_03"));
    }

//    TODO
    @Test
    public void requestDataAllowsSpecifyingMultipleValueHeaderWithoutServiceObjectMethod() {
        RestResponse response = getMultiHeaderReflect
                .call(headers().add("Header_Name", "Header_Value", "Header_Next_Value"));
        response.isOk();
        final List<String> headerListString = response.headers().getValues("Header_Name");
        assertThat(headerListString.size(), is(2));
        assertThat(headerListString, hasItems("Header_Value", "Header_Next_Value"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleHeadersWithoutServiceObjectMethods() {
        Header header1 = new Header("MyHeader1", "MyValue1");
        Header header2 = new Header("MyHeader2", "MyValue2");
        Header header3 = new Header("MyHeader3", "MyValue3");
        RestResponse response = getMultiHeaderReflect.call(headers().addAll(header1, header2, header3));
        response.isOk();
        response.assertThat().header("MyHeader1", equalTo("MyValue1"))
                .header("MyHeader2", equalTo("MyValue2"))
                .header("MyHeader3", equalTo("MyValue3"));
    }

    /**
     * Below are examples of tests similar to RestAssured tests.
     * This examples mostly contain complex assertions and exception handling.
     */

    @Test
    public void allowsSupplyingMappingFunction() {
        RestResponse response = getHello.call();
        response.isOk();
        response.assertThat().header("Content-Length", Integer::parseInt, lessThanOrEqualTo(100));
    }

    @Test(expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = ".*Expected header .* was not a value greater than .* was .* Headers are:.*")
    public void headerExceptionCanFailWhenUsingMappingFunction() {
        RestResponse response = getHello.call();
        response.isOk();
        response.assertThat().header("Content-Length", Integer::parseInt, greaterThan(1000));
    }

    @Test
    public void supportsHeaderStringMatching() {
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
    public void orderIsMaintainedForMultiValueHeaders() {
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
}