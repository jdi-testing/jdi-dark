package com.epam.jdi.httptests.examples.requestparams;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.JettyService.getContentTypeAsBody;
import static com.epam.jdi.services.JettyService.getHeadersWithValues;
import static com.epam.jdi.services.JettyService.getHello;
import static com.epam.jdi.services.JettyService.getReflect;
import static com.epam.jdi.services.JettyService.getReturnContentTypeAsBody;
import static com.epam.jdi.services.JettyService.postContentTypeAsBody;
import static com.epam.jdi.services.JettyService.postReturn204WithContentType;
import static com.epam.jdi.services.JettyService.postReturnContentTypeAsBody;
import static com.epam.jdi.services.JettyService.postTextUriList;
import static com.epam.jdi.services.JettyService.putReflect;
import static io.restassured.RestAssured.config;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.remove;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ContentTypeTests extends WithJetty {
    @BeforeClass
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
        postReturn204WithContentType.call()
                .assertThat().contentType(ContentType.JSON).and().assertThat().statusCode(204);
    }

    @Test
    public void
    contentTypeIsSentToTheServerWhenUsingAGetRequest() {
        getContentTypeAsBody.call(rd -> {
            rd.queryParams.add("foo", "bar");
            rd.contentType = ContentType.XML.withCharset("utf-8");
        }).assertThat().body(equalTo(ContentType.XML.withCharset("utf-8")));
    }

    @Test
    public void noContentTypeIsSentByDefaultWhenUsingGetRequest() {
        RestResponse response = getContentTypeAsBody
                .call(rd ->
                        rd.queryParams.add("foo", "bar"));
        response.assertThat().body(equalTo("null"));
    }

    @Test
    public void whenFormParamAreSuppliedWithGetRequestAndContentTypeIsExplicitlyDefinedThenContentTypeIsNotAutomaticallySetToFormEncoded() {
        RestResponse response = getReturnContentTypeAsBody
                .call(rd -> {
                    rd.contentType = ContentType.JSON.toString();
                    rd.queryParams.add("firstName", "John");
                    rd.queryParams.add("lastName", "Doe");
                });
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
                .call(rd -> {
                    rd.contentType = "application/uri-list+text";
                    rd.body = uriList;
                });
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
                .call(rd -> {
                    rd.contentType = "text/uri-list";
                    rd.body = uriList;
                });
        response.isOk().assertThat().body("uris.size()", is(6));
    }

    @Test
    public void contentTypeIsTextPlainWithDefaultCharsetWhenNoContentTypeIsSpecifiedForPutRequests() {
        RestResponse response = putReflect.call(queryParams().add("foo", "bar"));
        response.isOk().contentType(toJetty9(ContentType.TEXT.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void contentTypeIsApplicationXWwwFormUrlencodedWithDefaultCharsetWhenNoContentTypeIsSpecifiedForPostRequests() {
        RestResponse response = postContentTypeAsBody
                .call(rd ->
                        rd.queryParams.add("foo", "bar"));
        response.assertThat().body(equalTo(ContentType.URLENC.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void contentTypeValidationIsCaseInsensitive() {
        // Since we provide no content-type (null) Scalatra will return a default content-type which is the
        // same as specified in config().getEncoderConfig().defaultContentCharset() but with charset as lower case.
        getReflect.call(rd -> rd.queryParams.add("foo", "bar"))
                .assertThat().contentType(toJetty9(ContentType.TEXT.withCharset(config().getEncoderConfig().defaultContentCharset())));
    }

    @Test
    public void headerWithContentTypeEnumWorks() {
        postReturnContentTypeAsBody
                .call(rd -> rd.headers = new Headers(new Header("Content-Type", ContentType.JSON.toString())))
                .assertThat().body(equalTo(ContentType.JSON.withCharset(config().getEncoderConfig().defaultCharsetForContentType(ContentType.JSON))));
    }

    @Test
    public void appendsCharsetToNonStreamingContentTypeWhenContentTypeIsExplicitlyDefinedAndEncoderConfigIsConfiguredAccordingly() throws Exception {
        RequestSpecification rs = postReturnContentTypeAsBody.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(true)));
        RestResponse response = postReturnContentTypeAsBody.call(rs.body("something").contentType("application/vnd.com.example-v1+xml"));
        response.isOk().assertThat().body(equalTo("application/vnd.com.example-v1+xml; charset=ISO-8859-1"));
    }

    @Test
    public void customRegisteredEncodingOfContentTypeIsAppliedThroughEncoderConfig() {
        String uriList = "http://www.example.com/raindrops-on-roses\n" +
                "ftp://www.example.com/sleighbells\n" +
                "http://www.example.com/crisp-apple-strudel\n" +
                "http://www.example.com/doorbells\n" +
                "tag:foo@example.com,2012-07-01:bright-copper-kettles\n" +
                "urn:isbn:0-061-99881-8";
        RequestSpecification rs = postTextUriList.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("my-text", ContentType.TEXT)));
        postTextUriList.call(rs.body(uriList).contentType("my-text"))
                .isOk().assertThat().body("uris.size()", is(6));
    }

    @Test
    public void doesntSendAContentTypeHeaderWhenThereIsNoBody() {
        RestResponse response = getHeadersWithValues.call();
        response.isOk().body("containsKey('Content-Type')", is(false));
    }

    private String toJetty9(String charset) {
        return lowerCase(remove(charset, " "));
    }

    @Test
    public void encoderConfigCanSpecifyADefaultCharsetForASpecificContentTypeUsingEnum() {
        RequestSpecification rs = postReturnContentTypeAsBody.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().defaultCharsetForContentType(StandardCharsets.ISO_8859_1.toString(), ContentType.JSON)));
        postReturnContentTypeAsBody.call(rs.contentType(ContentType.JSON))
                .isOk().assertThat().body(equalTo(ContentType.JSON.withCharset(StandardCharsets.ISO_8859_1.toString())));
    }

    @Test
    public void doesntAppendCharsetToNonStreamingContentTypeWhenContentTypeIsExplicitlyDefinedAndEncoderConfigIsConfiguredAccordingly() {
        RequestSpecification rs = postReturnContentTypeAsBody.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        RestResponse response = postReturnContentTypeAsBody.call(rs.body("something").contentType("application/vnd.com.example-v1+json"));
        response.isOk().assertThat().body(equalTo("application/vnd.com.example-v1+json"));
    }

    @Test
    public void doesntOverrideDefinedCharsetForNonStreamingContentTypeWhenContentTypeIsExplicitlyDefinedAndEncoderConfigIsConfiguredAccordingly() throws Exception {
        RequestSpecification rs = postReturnContentTypeAsBody.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(true)));
        postReturnContentTypeAsBody.call(rs.body("something").contentType("application/vnd.com.example-v1+json; charSet=UTF-16"))
                .isOk().assertThat().body(equalTo("application/vnd.com.example-v1+json; charSet=UTF-16"));
    }

    @Test
    public void encoderConfigCanSpecifyADefaultCharsetForASpecificContentTypeUsingString() {
        RequestSpecification rs = postReturnContentTypeAsBody.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().defaultCharsetForContentType(StandardCharsets.ISO_8859_1.toString(), "application/json")));
        postReturnContentTypeAsBody.call(rs.contentType(ContentType.JSON))
                .isOk().assertThat().body(equalTo(ContentType.JSON.withCharset(StandardCharsets.ISO_8859_1.toString())));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp =
                    ".* know how to encode encode as a byte stream.\n\nPlease use EncoderConfig .*" +
                            ".*to specify how to serialize data for this content-type.*")
    public void showsANiceErrorMessageWhenFailedToEncodeContent() throws Exception {
        postTextUriList.call(rd -> {
            rd.contentType = "my-text";
            rd.body = "encode";
        });
    }

    @Test
    public void contentTypeIsSentToTheServerWhenUsingAPostRequest() {
        RequestSpecification rs = postContentTypeAsBody.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        postContentTypeAsBody.call(rs.queryParam("foo", "bar").contentType(ContentType.XML))
                .isOk().assertThat().body(equalTo(ContentType.XML.toString()));
    }

    @Test
    public void contentTypeIsSentToTheServerWhenUsingAPostRequest1() {
        RequestSpecification rs = postContentTypeAsBody.getInitSpec()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        postContentTypeAsBody.call(rs.queryParam("foo", "bar").contentType(ContentType.XML))
                .isOk().assertThat().body(equalTo(ContentType.XML.toString()));
    }
}