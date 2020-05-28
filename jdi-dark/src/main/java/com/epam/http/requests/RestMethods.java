package com.epam.http.requests;

import com.epam.http.response.RestResponse;
import io.restassured.specification.RequestSpecification;

import static com.epam.http.requests.RestMethodTypes.*;

/**
 * <p>
 * Supported HTTP methods that might be called directly from tests.
 * Contains methods to be called with different types of data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
@SuppressWarnings({"PMD.ClassNamingConventions", "PMD.MethodNamingConventions"})
public class RestMethods {
    public static RestResponse GET(RequestData data) {
        return new RestMethod(GET, data).call();
    }

    public static RestResponse GET(String path) {
        return new RestMethod(GET, path).call();
    }

    public static RestResponse GET(String path, RequestSpecification requestSpecification) {
        return new RestMethod(GET, path, requestSpecification).call();
    }

    public static RestResponse POST(RequestData data) {
        return new RestMethod(POST, data).call();
    }

    public static RestResponse POST(String path) {
        return new RestMethod(POST, path).call();
    }

    public static RestResponse POST(String path, RequestSpecification requestSpecification) {
        return new RestMethod(POST, path, requestSpecification).call();
    }

    public static RestResponse PUT(RequestData data) {
        return new RestMethod(PUT, data).call();
    }

    public static RestResponse PUT(String path) {
        return new RestMethod(PUT, path).call();
    }

    public static RestResponse PUT(String path, RequestSpecification requestSpecification) {
        return new RestMethod(PUT, path, requestSpecification).call();
    }

    public static RestResponse DELETE(RequestData data) {
        return new RestMethod(DELETE, data).call();
    }

    public static RestResponse DELETE(String path) {
        return new RestMethod(DELETE, path).call();
    }

    public static RestResponse DELETE(String path, RequestSpecification requestSpecification) {
        return new RestMethod(DELETE, path, requestSpecification).call();
    }

    public static RestResponse PATCH(RequestData data) {
        return new RestMethod(PATCH, data).call();
    }

    public static RestResponse PATCH(String path) {
        return new RestMethod(PATCH, path).call();
    }

    public static RestResponse PATCH(String path, RequestSpecification requestSpecification) {
        return new RestMethod(PATCH, path, requestSpecification).call();
    }

    public static RestResponse OPTIONS(RequestData data) {
        return new RestMethod(OPTIONS, data).call();
    }

    public static RestResponse OPTIONS(String path) {
        return new RestMethod(OPTIONS, path).call();
    }

    public static RestResponse OPTIONS(String path, RequestSpecification requestSpecification) {
        return new RestMethod(OPTIONS, path, requestSpecification).call();
    }

    public static RestResponse HEAD(RequestData data) {
        return new RestMethod(HEAD, data).call();
    }

    public static RestResponse HEAD(String path) {
        return new RestMethod(HEAD, path).call();
    }

    public static RestResponse HEAD(String path, RequestSpecification requestSpecification) {
        return new RestMethod(HEAD, path, requestSpecification).call();
    }
}
