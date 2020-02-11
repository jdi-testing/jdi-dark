package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.tools.map.MapArray;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

import static com.epam.http.requests.RequestData.requestData;

import static com.epam.http.requests.RestMethods.GET;
import static com.epam.http.requests.ServiceInit.init;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ContentTypeTests extends WithJetty {
    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test(expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = "1 expectation failed.\nExpected content-type \"something\" doesn't match actual content-type \"application/json;charset=utf-8\".\n")
    public void canValidateResponseContentType() throws Exception {
        RestResponse response = JettyService.getHello.call();
        response.assertThat().assertThat().contentType("something");
    }

    @Test
    public void canValidateResponseContentTypeWithHamcrestMatcher() {
        RestResponse response = JettyService.getHello.call();
        response.isOk().assertThat().contentType("application/json;charset=utf-8");
    }

    @Test
    public void validatesContentTypeEvenWhenItIsA204Response() {
        RestResponse response = JettyService.postReturn204WithContentType.call();
        response.assertThat().contentType(ContentType.JSON).and().assertThat().statusCode(204);
    }

    @Test
    public void noContentTypeIsSentByDefaultWhenUsingGetRequest() {
        RestResponse response = JettyService.getContentTypeAsBody
                .call(requestData(d ->
                        d.queryParams.add("foo", "bar")));
        response.assertThat().body(equalTo("null"));
    }

    @Test
    public void whenFormParamAreSuppliedWithGetRequestAndContentTypeIsExplicitlyDefinedThenContentTypeIsNotAutomatically_set_to_form_encoded() {
        RestResponse response = JettyService.getReturnContentTypeAsBody
                .call(requestData(d -> {
                    d.contentType = ContentType.JSON.toString();
                    d.queryParams.add("firstName", "John");
                    d.queryParams.add("lastName", "Doe");
                }));
        response.isOk().assertThat().body(equalTo(ContentType.JSON.withCharset(config().getEncoderConfig().defaultCharsetForContentType(ContentType.JSON))));
    }

    @Test
    public void nonRegisteredContentTypeContainingPlusTextIsEncodedAsText() {
        String uriList = "http://www.example.com/raindrops-on-roses\n" +
                "ftp://www.example.com/sleighbells\n" +
                "http://www.example.com/crisp-apple-strudel\n" +
                "http://www.example.com/doorbells\n" +
                "tag:foo@example.com,2012-07-01:bright-copper-kettles\n" +
                "urn:isbn:0-061-99881-8";
        RestResponse response = JettyService.postTextUriList
                .call(requestData(d -> {
                    d.contentType = "application/uri-list+text";
                    d.body = uriList;
                }));
        response.isOk().assertThat().body("uris.size()", is(6));
    }

    @Test
    public void nonRegisteredContentTypeStartingWithTextSlashIsEncodedAsText() {
        String uriList = "http://www.example.com/raindrops-on-roses\n" +
                "ftp://www.example.com/sleighbells\n" +
                "http://www.example.com/crisp-apple-strudel\n" +
                "http://www.example.com/doorbells\n" +
                "tag:foo@example.com,2012-07-01:bright-copper-kettles\n" +
                "urn:isbn:0-061-99881-8";
        RestResponse response = JettyService.postTextUriList
                .call(requestData(d -> {
                    d.contentType = "text/uri-list";
                    d.body = uriList;
                }));
        response.isOk().assertThat().body("uris.size()", is(6));
    }

    @Test
    public void contentTypeIsTextPlainWithDefaultCharsetWhenNoContentTypeIsSpecifiedForPutRequests() {
        RestResponse response = JettyService.putReflect
                .call(requestData(d ->
                        d.queryParams.add("foo", "bar")));
        response.isOk().contentType(toJetty9(ContentType.TEXT.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void contentTypeIsApplicationXWwwFormUrlencodedWithDefaultCharsetWhenNoContentTypeIsSpecifiedForPostRequests() {
        RestResponse response = JettyService.postContentTypeAsBody
                .call(requestData(d ->
                        d.queryParams.add("foo", "bar")));
        response.assertThat().body(equalTo(ContentType.URLENC.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void contentTypeValidationIsCaseInsensitive() {
        // Since we provide no content-type (null) Scalatra will return a default content-type which is the
        // same as specified in config().getEncoderConfig().defaultContentCharset() but with charset as lower case.
        RestResponse response = JettyService.getReflect
                .call(requestData(d ->
                        d.queryParams.add("foo", "bar")));
        response.assertThat().contentType(toJetty9(ContentType.TEXT.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void headerWithContentTypeEnumWorks() throws Exception {
        RestResponse response = JettyService.postReturnContentTypeAsBody
                .call(requestData(d ->
                        d.headers.userHeaders = new MapArray <>(new Object[][] {
                                                  {"Content-Type", ContentType.JSON}})));
        response.assertThat().body(equalTo(ContentType.JSON.withCharset(config().getEncoderConfig().defaultCharsetForContentType(ContentType.JSON))));
    }

    @Test
    public void
    doesntSendAContentTypeHeaderWhenThereIsNoBody() {
        RestResponse response = JettyService.getHeadersWithValues.call();
        response.isOk().body("containsKey('Content-Type')", is(false));
    }

    @Test (expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp =
                    "Don't know how to encode encode as a byte stream.\n" +
                    "\n" +
                    "Please use EncoderConfig (EncoderConfig#encodeContentTypeAs) to specify how to serialize data for this content-type.\n" +
                    "For example: \"given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs(\"my-text\", ContentType.TEXT))). ..\"")
    public void
    shows_a_nice_error_message_when_failed_to_encode_content()  throws Exception {
        RestResponse response = JettyService.postTextUriList.call(requestData(d -> {
            d.contentType = "my-text";
            d.body = "encode";}));
    }

    private String toJetty9(String charset) {
        return StringUtils.lowerCase(StringUtils.remove(charset, " "));
    }
}