package com.epam.jdi.httptests.examples;

import com.epam.http.requests.RestMethods;
import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Info;
import com.epam.jdi.services.ServiceExample;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataFactory.cookies;
import static com.epam.http.requests.RequestDataFactory.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

/**
 * Created by Roman_Iovlev on 7/21/2017.
 */
public class ServiceTests {

    private RequestSpecification requestSpecification;

    @BeforeClass
    public void before() {
        requestSpecification = given().filter(new AllureRestAssured());
        requestSpecification.auth().basic("user", "password");
    }

    @Test
    public void simpleRestTest() {
        RestResponse resp = getService().getInfo.call();
        resp.isOk().
                body("url", equalTo("https://httpbin.org/get")).
                body("headers.Host", equalTo("httpbin.org")).
                body("headers.Id", equalTo("Test"));
        resp.assertThat().header("Connection", "keep-alive");
    }

    @Test
    public void noServiceObjectTest() {
        RestResponse resp = RestMethods.GET(requestData(rd -> {
                    rd.uri = "https://httpbin.org/get";
                    rd.headerUpdater().addAll(new Object[][]{
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
        Info e = getService().getInfo();
        assertEquals(e.url, "https://httpbin.org/get");
        assertEquals(e.headers.Host, "httpbin.org");
        assertEquals(e.headers.Id, "Test");
        assertEquals(e.headers.Name, "Roman");
    }

    @Test
    public void statusTest() {
        RestResponse resp = getService().status.pathParams("503").call();
        assertEquals(resp.getStatus().code, 503);
        resp.isEmpty();
    }

    @Test
    public void statusTestWithQueryInPath() {
        RestResponse resp = getService().statusWithQuery.pathParams("503", "some").call();
        assertEquals(resp.getStatus().code, 503);
        resp.isEmpty();
    }

    @Test
    public void staticServiceInitTest() {
        init(ServiceExample.class);
        RestResponse resp = getService().getInfo.call();
        resp.isOk().assertThat().
                body("url", equalTo("https://httpbin.org/get")).
                body("headers.Host", equalTo("httpbin.org"));
    }

    @Test
    public void serviceInitTest() {
        RestResponse resp = getService().postMethod.call();
        resp.isOk().assertThat().
                body("url", equalTo("https://httpbin.org/post")).
                body("headers.Host", equalTo("httpbin.org"));
    }

    @Test
    public void htmlBodyParseTest() {
        RestResponse resp = getService().getHTMLMethod.call();
        resp.isOk();
        assertEquals(resp.getFromHtml("html.body.h1"), "Herman Melville - Moby-Dick");
    }

    @Test
    public void cookiesTest() {
        RestResponse response = getService().getCookies.call(cookies().add("additionalCookie", "test"));
        response.isOk()
                .body("cookies.additionalCookie", equalTo("test"))
                .body("cookies.session_id", equalTo("1234"))
                .body("cookies.hello", equalTo("world"));
    }

    @Test
    public void getWithRaRequestSpecification() {
        getService().getWithAuth.call(
                given().auth().basic("user", "password")
        ).assertThat()
                .body("authenticated", equalTo(true))
                .body("user", equalTo("user"));
    }

    private ServiceExample getService() {
        return init(ServiceExample.class, ServiceSettings.builder().requestSpecification(requestSpecification).build());
    }
}
