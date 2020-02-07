package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;

import static com.epam.http.requests.RestMethods.GET;
import static com.epam.http.requests.ServiceInit.init;

public class ContentTypeTests {
    @BeforeMethod
    public void initService() {
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
    public void validatesContentTypeJson() {
        RestResponse response = GET(requestData(d -> {
            d.url = "http://localhost:8080/contentTypeAsContentType";
            d.contentType = ContentType.JSON;}));
        response.assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void validatesContentTypeBinary() {
        RestResponse response = GET(requestData(d -> {
            d.url = "http://localhost:8080/contentTypeAsContentType";
            d.contentType = ContentType.BINARY;}));
        response.assertThat().contentType(ContentType.BINARY);
    }


}