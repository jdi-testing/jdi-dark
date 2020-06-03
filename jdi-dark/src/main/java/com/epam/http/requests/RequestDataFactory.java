package com.epam.http.requests;

import com.epam.http.requests.updaters.CookieUpdater;
import com.epam.http.requests.updaters.FormParamsUpdater;
import com.epam.http.requests.updaters.HeaderUpdater;
import com.epam.http.requests.updaters.PathParamsUpdater;
import com.epam.http.requests.updaters.QueryParamsUpdater;
import com.epam.jdi.tools.func.JAction1;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.MultiPartSpecBuilder;

/**
 * Represents all HTTP request data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RequestDataFactory {

    //Collections
    public static CookieUpdater cookies() {
        return new CookieUpdater();
    }

    public static HeaderUpdater headers() {
        return new HeaderUpdater();
    }

    public static FormParamsUpdater formParams() {
        return new FormParamsUpdater();
    }

    public static QueryParamsUpdater queryParams() {
        return new QueryParamsUpdater();
    }

    public static PathParamsUpdater pathParams() {
        return new PathParamsUpdater();
    }

    //single entities
    public static RequestData body(Object body) {
        return new RequestData().setBody(body);
    }

    public static RequestData multipart(MultiPartSpecBuilder multiPartSpecBuilder) {
        return new RequestData().setMultiPart(multiPartSpecBuilder);
    }

    public static RequestData auth(AuthenticationScheme authScheme) {
        return new RequestData().setAuthScheme(authScheme);
    }

    public static RequestData trustStore(String pathToJks, String password) {
        return new RequestData().setTrustStore(pathToJks, password);
    }

    //general
    public static RequestData requestData(JAction1<RequestData> valueFunc) {
        return new RequestData().set(valueFunc);
    }
}
