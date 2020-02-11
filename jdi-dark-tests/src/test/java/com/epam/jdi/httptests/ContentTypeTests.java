package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.tools.map.MapArray;
import com.epam.jdi.tools.map.MultiMap;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

import static com.epam.http.requests.RequestData.requestData;

import static com.epam.http.requests.RestMethods.GET;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.JettyService.getContentTypeAsBody;
import static com.epam.jdi.httptests.JettyService.getHeadersWithValues;
import static com.epam.jdi.httptests.JettyService.getHello;
import static com.epam.jdi.httptests.JettyService.getReflect;
import static com.epam.jdi.httptests.JettyService.getReturnContentTypeAsBody;
import static com.epam.jdi.httptests.JettyService.postContentTypeAsBody;
import static com.epam.jdi.httptests.JettyService.postReturn204WithContentType;
import static com.epam.jdi.httptests.JettyService.postReturnContentTypeAsBody;
import static com.epam.jdi.httptests.JettyService.postTextUriList;
import static com.epam.jdi.httptests.JettyService.putReflect;
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
        RestResponse response = getHello.call();
        response.assertThat().assertThat().contentType("something");
    }

    @Test
    public void canValidateResponseContentTypeWithHamcrestMatcher() {
        RestResponse response = getHello.call();
        response.isOk().assertThat().contentType("application/json;charset=utf-8");
    }

    @Test
    public void validatesContentTypeEvenWhenItIsA204Response() {
        RestResponse response = postReturn204WithContentType.call();
        response.assertThat().contentType(ContentType.JSON).and().assertThat().statusCode(204);
    }

    @Test
    public void noContentTypeIsSentByDefaultWhenUsingGetRequest() {
        RestResponse response = getContentTypeAsBody
                .call(requestData(d ->
                        d.queryParams.add("foo", "bar")));
        response.assertThat().body(equalTo("null"));
    }

    @Test
    public void whenFormParamAreSuppliedWithGetRequestAndContentTypeIsExplicitlyDefinedThenContentTypeIsNotAutomaticallySetToFormEncoded() {
        RestResponse response = getReturnContentTypeAsBody
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
        RestResponse response = postTextUriList
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
        RestResponse response = postTextUriList
                .call(requestData(d -> {
                    d.contentType = "text/uri-list";
                    d.body = uriList;
                }));
        response.isOk().assertThat().body("uris.size()", is(6));
    }

    @Test
    public void contentTypeIsTextPlainWithDefaultCharsetWhenNoContentTypeIsSpecifiedForPutRequests() {
        RestResponse response = putReflect
                .call(requestData(d ->
                        d.queryParams.add("foo", "bar")));
        response.isOk().contentType(toJetty9(ContentType.TEXT.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void contentTypeIsApplicationXWwwFormUrlencodedWithDefaultCharsetWhenNoContentTypeIsSpecifiedForPostRequests() {
        RestResponse response = postContentTypeAsBody
                .call(requestData(d ->
                        d.queryParams.add("foo", "bar")));
        response.assertThat().body(equalTo(ContentType.URLENC.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void contentTypeValidationIsCaseInsensitive() {
        // Since we provide no content-type (null) Scalatra will return a default content-type which is the
        // same as specified in config().getEncoderConfig().defaultContentCharset() but with charset as lower case.
        getReflect.call(requestData(d ->
                d.queryParams.add("foo", "bar")))
                .assertThat().contentType(toJetty9(ContentType.TEXT.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void headerWithContentTypeEnumWorks() {
        postReturnContentTypeAsBody
                .call(requestData(d ->
                        d.headers.add("Content-Type", ContentType.JSON.toString())))
                .assertThat().body(equalTo(ContentType.JSON.withCharset(config().getEncoderConfig().defaultCharsetForContentType(ContentType.JSON))));
    }

    @Test
    public void appendsCharsetToNonStreamingContentTypeWhenContentTypeIsExplicitlyDefinedAndEncoderConfigIsConfiguredAccordingly() throws Exception {
        RequestSpecification rs = postReturnContentTypeAsBody.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(true)));
        postReturnContentTypeAsBody.setContentType("application/vnd.com.example-v1+xml");
        RestResponse response = postReturnContentTypeAsBody.call(rs.body("something"));
        response.isOk().assertThat().body(equalTo("application/vnd.com.example-v1+xml; charset=ISO-8859-1"));
    }

    @Test public void
    custom_registered_encoding_of_content_type_is_applied_through_encoder_config() {
        String uriList = "http://www.example.com/raindrops-on-roses\n" +
                "ftp://www.example.com/sleighbells\n" +
                "http://www.example.com/crisp-apple-strudel\n" +
                "http://www.example.com/doorbells\n" +
                "tag:foo@example.com,2012-07-01:bright-copper-kettles\n" +
                "urn:isbn:0-061-99881-8";
        RequestSpecification rs = postTextUriList.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("my-text", ContentType.TEXT)));
        postTextUriList.setContentType("my-text");
        postTextUriList.call(rs.body(uriList))
                .isOk().assertThat().body("uris.size()", is(6));
    }

    @Test
    public void doesntSendAContentTypeHeaderWhenThereIsNoBody() {
        RestResponse response = getHeadersWithValues.call();
        response.isOk().body("containsKey('Content-Type')", is(false));
    }

    private String toJetty9(String charset) {
        return StringUtils.lowerCase(StringUtils.remove(charset, " "));
    }
}