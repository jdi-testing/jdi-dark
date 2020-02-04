package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.map.MapArray;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestBody;
import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class ResponseTests {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void getCanReturnResponseDataAsString() {
        RestResponse response = JettyService.getHello.call();
        final String responseInfo = response.toString();
        assertThat(responseInfo, containsString("Response status: 200 OK (OK)"));
        assertThat(responseInfo, containsString("Response body: {\"hello\":\"Hello Scalatra\"}"));
    }

    @Test
    public void getCanReturnBodyAsString() {
        RestResponse response = JettyService.getHello.call();
        final String body = response.body;
        assertThat(body, containsString("{\"hello\":\"Hello Scalatra\"}"));
    }

    @Test
    public void postCanReturnBodyAsString() {
        final String body = JettyService.postGreetXml.call(requestBody("firstName=John&lastName=Doe")).body;
        assertEquals("<greeting><firstName>John</firstName>\n" +
                "      <lastName>Doe</lastName>\n" +
                "    </greeting>", body);
    }

    @Test
    public void putCanReturnBodyAsString() {
        final String body = JettyService.putCookie.call(requestData(requestData ->
                requestData.cookies = new MapArray<>(new Object[][]{
                        {"username", "John"},
                        {"token", "1234"}
                }))).body;
        assertEquals("username, token", body);
    }

    @Test
    public void deleteCanReturnBodyAsString() {
        final String body = JettyService.deleteGreet.call(requestData(d -> {
            d.queryParams.add("firstName", "John");
            d.queryParams.add("lastName", "Doe");
        })).body;
        assertEquals("{\"greeting\":\"Greetings John Doe\"}", body);
    }

    @Test
    public void responseSupportsGettingCookies() {
        RestResponse response = JettyService.setCookies.call();
        assertEquals(3, response.cookies().size());
        assertEquals("value1", response.cookie("key1"));
        assertEquals("value2", response.cookie("key2"));
    }

    @Test
    public void responseSupportsGettingHeaders() {
        RestResponse response = JettyService.setCookies.call();
        assertEquals(7, response.headers().size());
        assertEquals("text/plain;charset=utf-8", response.header("Content-Type"));
        final String server = response.header("Server");
        assertThat(server, containsString("Jetty"));
    }

    @Test
    public void responseSupportsGettingStatusCode() {
        RestResponse response = JettyService.getHello.call();
        assertThat(response.status.code, equalTo(200));
    }

    @Test
    public void responseSupportsIsOkVerification() {
        RestResponse response = JettyService.getHello.call();
        response.isOk();
    }

    @Test
    public void usingJsonPathViewFromTheResponse() {
        final String hello = JettyService.getHello.call().getRaResponse().jsonPath().getString("hello");
        assertThat(hello, equalTo("Hello Scalatra"));
    }

    @Test
    public void usingXmlPathViewFromTheResponse() {
        final String firstName = JettyService.postGreetXml.call(requestBody("firstName=John&lastName=Doe")).getRaResponse().xmlPath().getString("greeting.firstName");
        assertThat(firstName, equalTo("John"));
    }
}
