package com.epam.jdi.httptests;

import com.epam.http.requests.components.JDIHeaders;
import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.http.Header;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getHello;
import static com.epam.jdi.httptests.JettyService.getMultiHeaderReflect;
import static com.epam.jdi.httptests.JettyService.getLotto;
import static com.epam.jdi.httptests.JettyService.getHeader;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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
                        requestData.headers = new JDIHeaders(new Header("MyHeader", "TestValue"))));
        response.isOk();
        response.assertThat().body(containsString("MyHeader"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleHeaders() {
        JDIHeaders testHeaders = new JDIHeaders(new String[][]
                {{"MyHeader", "MyValue"}, {"SecondHeader", "MyValue2"}});
        RestResponse response = getHeader.call(
                requestData(requestData ->
                        requestData.headers = testHeaders));
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
                "Content-Length", Matchers.equalTo("160"));
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
    public void multiValueTestExample() {
        Header header1 = new Header("MyHeader1", "MyValue1");
        Header header2 = new Header("MyHeader2", "MyValue2");
        Header header3 = new Header("MyHeader2", "MyValue23");
        RestResponse response = getMultiHeaderReflect.call(
                requestData(requestData ->
                        requestData.headers = new JDIHeaders(header1, header2, header3)));
        response.isOk();
        response.assertThat().header("MyHeader1", containsString("MyValue1"));
        assertThat(response.headers().getValues("MyHeader2").size(), is(2));
        assertThat(response.headers().getValues("MyHeader2"), hasItems("MyValue2", "MyValue23"));
    }

}