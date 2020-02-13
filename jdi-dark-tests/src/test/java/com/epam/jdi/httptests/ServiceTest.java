package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.RestMethods.GET;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.http.response.ResponseStatusType.SERVER_ERROR;
import static com.epam.jdi.httptests.ServiceExample.getInfo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

/**
 * Created by Roman_Iovlev on 7/21/2017.
 */
public class ServiceTest {

    private RequestSpecification requestSpecification;
    private ServiceExample service;

    @BeforeClass
    public void before() {
        requestSpecification = given().filter(new AllureRestAssured());
        requestSpecification.auth().basic("user", "password");
        service = init(ServiceExample.class, requestSpecification);
    }

    @Test
    public void simpleRestTest() {
        RestResponse resp = ServiceExample.getInfo.call();
        resp.isOk().
                body("url", equalTo("https://httpbin.org/get")).
                body("headers.Host", equalTo("httpbin.org")).
                body("headers.Id", equalTo("Test"));
        resp.assertThat().header("Connection", "keep-alive");
    }

    @Test
    public void noServiceObjectTest() {
        RestResponse resp = GET(requestData(
                rd -> {
                    rd.uri = "https://httpbin.org/get";
                    rd.addHeaders(new Object[][]{
                            {"Name", "Roman"},
                            {"Id", "TestTest"}
                    });
                }
        ));
        resp.isOk().header("Connection", "keep-alive");
        resp.assertBody(new Object[][]{
                {"url", equalTo("https://httpbin.org/get")},
                {"headers.Host", equalTo("httpbin.org")},
                {"headers.Id", equalTo("TestTest")}
        });
    }

    @Test
    public void entityTest() {
        Info e = getInfo();
        assertEquals(e.url, "https://httpbin.org/get");
        assertEquals(e.headers.Host, "httpbin.org");
        assertEquals(e.headers.Id, "Test");
        assertEquals(e.headers.Name, "Roman");
    }

    @Test
    public void statusTest() {
        RestResponse resp = service.status.call("503");
        assertEquals(resp.status.code, 503);
        assertEquals(resp.status.type, SERVER_ERROR);
        resp.isEmpty();
    }

    @Test
    public void staticServiceInitTest() {
        init(ServiceExample.class);
        RestResponse resp = getInfo.call();
        resp.isOk().assertThat().
                body("url", equalTo("https://httpbin.org/get")).
                body("headers.Host", equalTo("httpbin.org"));
    }

    @Test
    public void serviceInitTest() {
        RestResponse resp = service.postMethod.call();
        resp.isOk().assertThat().
                body("url", equalTo("https://httpbin.org/post")).
                body("headers.Host", equalTo("httpbin.org"));
    }

    @Test
    public void htmlBodyParseTest() {
        RestResponse resp = service.getHTMLMethod.call();
        resp.isOk();
        assertEquals(resp.getFromHtml("html.body.h1"), "Herman Melville - Moby-Dick");
    }

    @Test
    public void cookiesTest() {
        RestResponse response = service.getCookies.call(requestData(requestData ->
                requestData.addCookie("additionalCookie", "test")));
        response.isOk()
                .body("cookies.additionalCookie", equalTo("test"))
                .body("cookies.session_id", equalTo("1234"))
                .body("cookies.hello", equalTo("world"));
    }

    @Test
    public void getWithRaRequestSpecification() {
        service.getWithAuth.call(
                given().auth().basic("user", "password")
        ).assertThat()
                .body("authenticated", equalTo(true))
                .body("user", equalTo("user"));
    }
}
