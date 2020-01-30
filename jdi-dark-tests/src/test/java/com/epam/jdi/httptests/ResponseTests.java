package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.map.MapArray;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.path.xml.exception.XmlPathException;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.epam.http.requests.RequestData.requestBody;
import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
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
        final String body = JettyService.deleteGreet.call().body;
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
    public void responseSupportsIsOkVerification() {
        RestResponse response = JettyService.getHello.call();
        response.isOk();
    }

    @Test
    public void whenNoExpectationsDefinedThenGetCanReturnBodyAsInputStream() throws IOException {
        InputStream inputStream = JettyService.getHello.call().raResponse().asInputStream();
        assertThat(IOUtils.toString(inputStream, Charset.defaultCharset()), equalTo("{\"hello\":\"Hello Scalatra\"}"));
    }

    @Test
    public void usingJsonPathViewFromTheResponse() {
        final String hello = JettyService.getHello.call().raResponse().jsonPath().getString("hello");
        assertThat(hello, equalTo("Hello Scalatra"));
    }

    @Test
    public void usingXmlPathViewFromTheResponse() {
        final String firstName = JettyService.postGreetXml.call(requestBody("firstName=John&lastName=Doe")).raResponse().xmlPath().getString("greeting.firstName");
        assertThat(firstName, equalTo("John"));
    }

    @Test
    public void usingXmlPathWithHtmlCompatibilityModeFromTheResponse() {
        final String title = JettyService.getTextHtml.call().raResponse().xmlPath(HTML).getString("html.head.title");
        assertThat(title, equalTo("my title"));
    }

    @Test
    public void usingHtmlPathToParseHtmlFromTheResponse() {
        final String title = JettyService.getTextHtml.call().raResponse().htmlPath().getString("html.head.title");
        assertThat(title, equalTo("my title"));
    }

    @Test
    public void usingPathWithContentTypeJsonFromTheResponse() {
        final String hello = JettyService.getHello.call().raResponse().path("hello");
        assertThat(hello, equalTo("Hello Scalatra"));
    }

    @Test
    public void usingPathWithParameters() {
        final String hello = JettyService.getHello.call().raResponse().path("hel%s", "lo");
        assertThat(hello, equalTo("Hello Scalatra"));
    }

    @Test
    public void usingPathWithContentTypeXmlFromTheResponse() {
        final String firstName = JettyService.postGreetXml.call(requestBody("firstName=John&lastName=Doe")).raResponse().path("greeting.firstName");
        assertThat(firstName, equalTo("John"));
    }

    @Test
    public void pathWorksForMultipleInvocationsWithJson() {
        RestResponse response = JettyService.getJsonStore.call();
        float minPrice = response.raResponse().path("store.book.price.min()");
        float maxPrice = response.raResponse().path("store.book.price.max()");
        assertThat(minPrice, is(8.95f));
        assertThat(maxPrice, is(22.99f));
    }

    @Test(expectedExceptions = XmlPathException.class, expectedExceptionsMessageRegExp = "Failed to parse the XML document")
    public void pathThrowsExceptionWhenTryingToUseXmlPathAfterHavingUsedJsonPath() {
        RestResponse response = JettyService.getJsonStore.call();
        response.raResponse().path("store.book.price.min()");
        response.raResponse().xmlPath().get("store.book.price.min()");
    }

    @Test
    public void pathWorksForMultipleInvocationsWithXml() {
        RestResponse response = JettyService.getVideos.call();
        String title = response.raResponse().path("videos.music[0].title.toString().trim()");
        String artist = response.raResponse().path("videos.music[0].artist.toString().trim()");
        assertThat(title, equalTo("Video Title 1"));
        assertThat(artist, equalTo("Artist 1"));
    }

    @Test(expectedExceptions = JsonPathException.class, expectedExceptionsMessageRegExp = "Failed to parse the JSON document")
    public void pathThrowsExceptionWhenTryingToUseJsonPathAfterHavingUsedXmlPath() {
        RestResponse response = JettyService.getVideos.call();
        response.raResponse().path("videos.music[0].title.toString().trim()");
        response.raResponse().jsonPath().get("videos");
    }

    @Test
    public void canParsePathAfterPrettyPrint() {
        RestResponse response = JettyService.getVideos.call();
        response.raResponse().prettyPrint();
        String title = response.raResponse().path("videos.music[0].title.toString().trim()");
        String artist = response.raResponse().path("videos.music[0].artist.toString().trim()");

        assertThat(title, equalTo("Video Title 1"));
        assertThat(artist, equalTo("Artist 1"));
    }

    @Test
    public void canParsePathAfterPrint() {
        RestResponse response = JettyService.getVideos.call();
        response.raResponse().print();
        String title = response.raResponse().path("videos.music[0].title.toString().trim()");
        String artist = response.raResponse().path("videos.music[0].artist.toString().trim()");

        assertThat(title, equalTo("Video Title 1"));
        assertThat(artist, equalTo("Artist 1"));
    }

    @Test
    public void canGetAsStringMultipleTimes() {
        RestResponse response = JettyService.getVideos.call();
        response.raResponse().asString();
        String string = response.raResponse().asString();
        assertThat(string, not(nullValue()));
    }

    @Test
    public void canGetAsByteArrayMultipleTimes() {
        RestResponse response = JettyService.getVideos.call();
        response.raResponse().asByteArray();
        final byte[] bytes = response.raResponse().asByteArray();
        assertThat(bytes, not(nullValue()));
    }

    @Test
    public void canCombineAsByteArrayWithPrettyPrintAndAsString() {
        RestResponse response = JettyService.getVideos.call();
        response.raResponse().asByteArray();
        response.raResponse().prettyPrint();
        String string = response.raResponse().asString();
        assertThat(string, not(nullValue()));
    }

    @Test
    public void canCombineAsStringWithPrettyPrintAndAsByteArray() {
        RestResponse response = JettyService.getVideos.call();
        response.raResponse().asString();
        response.raResponse().prettyPrint();
        byte[] bytes = response.raResponse().asByteArray();
        assertThat(bytes, not(nullValue()));
    }
}
