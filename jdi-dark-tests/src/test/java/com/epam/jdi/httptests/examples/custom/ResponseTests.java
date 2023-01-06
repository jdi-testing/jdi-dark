package com.epam.jdi.httptests.examples.custom;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.RequestDataFactory.*;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.JettyService.getJsonStore;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class ResponseTests extends WithJetty {

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
    public void getSecuredHello() {
        BasicAuthScheme authScheme = new BasicAuthScheme();
        authScheme.setUserName("jetty");
        authScheme.setPassword("jetty");
        RestResponse response = JettyService.getSecuredHello.auth(authScheme).call();
        final String responseInfo = response.toString();
        assertThat(responseInfo, containsString("Response status: 200 OK (OK)"));
        assertThat(responseInfo, containsString("Response body: {\"hello\":\"Hello Secured Scalatra\"}"));
    }

    @Test
    public void getCanReturnBodyAsString() {
        RestResponse response = JettyService.getHello.call();
        String body = response.getBody();
        assertThat(body, containsString("{\"hello\":\"Hello Scalatra\"}"));
        assertThat(response.getBody(), containsString("{\"hello\":\"Hello Scalatra\"}"));
    }

    @Test
    public void whenParamsSpecifiedCanReturnBodyAsString() {
        RestResponse response = JettyService.postGreetXml.call(body("firstName=John&lastName=Doe"));
        final String body = response.getBody();
        assertEquals("<greeting><firstName>John</firstName>\n" +
                "      <lastName>Doe</lastName>\n" +
                "    </greeting>", body);
    }

    @Test
    public void whenNoExpectationsDefinedThenGetCanReturnAStringAsByteArray() {
        final byte[] expected = "{\"hello\":\"Hello Scalatra\"}".getBytes();
        final byte[] actual = JettyService.getHello.call()
                .getRaResponse().asByteArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void postCanReturnBodyAsString() {
        final String body = JettyService.postGreetXml.queryParams("firstName=John&lastName=Doe").call().getBody();
        assertEquals("<greeting><firstName>John</firstName>\n" +
                "      <lastName>Doe</lastName>\n" +
                "    </greeting>", body);
    }

    @Test
    public void putCanReturnBodyAsString() {
        Map<String, Object> cookies = new HashMap<>();
        cookies.put("username", "John");
        cookies.put("token", "1234");
        final String body = JettyService.putCookie.call(cookies().addAll(cookies)).getBody();
        assertEquals("username, token", body);
    }

    @Test
    public void deleteCanReturnBodyAsString() {
        final String body = JettyService.deleteGreet.call(rd -> {
            rd.queryParams.add("firstName", "John");
            rd.queryParams.add("lastName", "Doe");
        }).getBody();
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
        assertEquals(8, response.headers().size());
        assertEquals("text/plain;charset=utf-8", response.header("Content-Type"));
        final String server = response.header("Server");
        assertThat(server, containsString("Jetty"));
    }

    @Test
    public void responseSupportsGettingStatusCode() {
        RestResponse response = JettyService.getHello.call();
        assertThat(response.getStatus().code, equalTo(200));
        assertThat(response.getBody(), equalTo("{\"hello\":\"Hello Scalatra\"}"));
    }

    @Test
    public void responseSupportsGettingStatusLine() {
        RestResponse response = JettyService.getHello.call();
        assertThat(response.getRaResponse().statusLine(), equalTo("HTTP/1.1 200 OK"));
        assertThat(response.getRaResponse().getStatusLine(), equalTo("HTTP/1.1 200 OK"));
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
        final String firstName = JettyService.postGreetXml.queryParams("firstName=John&lastName=Doe").call()
                .getRaResponse().xmlPath().getString("greeting.firstName");
        assertThat(firstName, equalTo("John"));
    }

    @Test
    public void usingXmlPathWithHtmlCompatibilityModeFromTheResponse() {
        String title = JettyService.getTextHtml.call().getRaResponse().xmlPath(HTML).getString("html.head.title");
        assertThat(title, equalTo("my title"));
    }

    @Test
    public void usingHtmlPathToParseHtmlFromTheResponse() {
        String title = JettyService.getTextHtml.call().getRaResponse().htmlPath().getString("html.head.title");
        assertThat(title, equalTo("my title"));
    }

    @Test
    public void usingPathWithContentTypeJsonFromTheResponse() {
        String hello = JettyService.getHello.call().getRaResponse().andReturn().path("hello");
        assertThat(hello, equalTo("Hello Scalatra"));
    }

    @Test
    public void usingPathWithContentTypeXmlFromTheResponse() {
        String firstName = JettyService.postGreetXml.queryParams("firstName=John&lastName=Doe").call()
                .getRaResponse().path("greeting.firstName");
        assertThat(firstName, equalTo("John"));
    }

    @Test
    public void jsonPathReturnedByResponseUsesConfigurationFromRestAssured() {
        RequestSpecification rs = getJsonStore.getInitSpec().
                config(RestAssuredConfig.newConfig().with().
                        jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)));

        RestResponse response = getJsonStore.call(rs);
        assertThat(response.getRaResponse().jsonPath().<BigDecimal>get("store.book.price.min()"),
                is(new BigDecimal("8.95")));
        assertThat(response.getRaResponse().jsonPath().<BigDecimal>get("store.book.price.max()"),
                is(new BigDecimal("22.99")));
    }
}
