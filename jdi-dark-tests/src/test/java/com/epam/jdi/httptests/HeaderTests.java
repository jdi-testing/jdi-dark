package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.tools.map.MultiMap;
import io.qameta.allure.restassured.AllureRestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getHeader;
import static com.epam.jdi.httptests.JettyService.getHello;
import static com.epam.jdi.httptests.JettyService.getLotto;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class HeaderTests extends WithJetty {

    @BeforeTest
    public void before() {
        requestSpecification = given().filter(new AllureRestAssured());
        requestSpecification.header("CommonHeader", "CommonHeaderValue");
        init(JettyService.class, requestSpecification);
    }


    @Test
    public void requestDataAllowsSpecifyingHeader() {
        RestResponse response = getHeader.call(
                requestData(requestData ->
                        requestData.headers = new MultiMap<>(new Object[][]{
                                {"MyHeader", "TestValue"}
                        })));
        response.isOk();
        response.assertThat().body(containsString("MyHeader"));
    }

    @Test
    public void requestDataAllowsSpecifyingMultipleHeaders() {
        RestResponse response = getHeader.call(
                requestData(requestData ->
                        requestData.headers = new MultiMap<>(new Object[][]{
                                {"MyHeader", "TestValue"}, {"SecondHeader", "SecondHeaderTestValue"}
                        })));
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

}