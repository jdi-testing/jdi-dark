package com.epam.http.requests;

import com.epam.http.annotations.FormParameter;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MapArray;
import com.google.gson.Gson;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.JdiHttpSettigns.getDomain;
import static com.epam.http.JdiHttpSettigns.logger;
import static com.epam.http.requests.RestRequest.doRequest;
import static com.epam.http.response.ResponseStatusType.OK;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.commons.lang3.time.StopWatch.createStarted;

/**
 * Class for HTTP request formation.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RestMethod<T> {

    private RequestSpecification spec = given().filter(new AllureRestAssured());
    private String url = null;
    private String path = null;
    private RequestData data;
    private RequestData userData = new RequestData();
    private RestMethodTypes type;
    private Gson gson = new Gson();
    private ResponseStatusType expectedStatus = OK;

    public RestMethod() {
    }

    public RestMethod(JAction1<RequestSpecification> specFunc, RestMethodTypes type) {
        specFunc.execute(spec);
        this.type = type;
    }

    /**
     * Constructor for forming HTTP request.
     *
     * @param type of HTTP request
     * @param data of request
     */
    public RestMethod(RestMethodTypes type, RequestData data) {
        this.data = data;
        this.type = type;
    }

    /**
     * Constructor for forming HTTP request.
     *
     * @param type of HTTP request
     * @param path to send HTTP request to
     */
    public RestMethod(RestMethodTypes type, String path) {
        this(type, getDomain(), path, new RequestData());
    }

    /**
     * Constructor for forming HTTP request.
     *
     * @param type of HTTP request
     * @param path to send HTTP request to
     */
    public RestMethod(RestMethodTypes type, String url, String path) {
        this(type, url, path, new RequestData());
    }

    /**
     * Constructor for forming HTTP request.
     *
     * @param type of HTTP request
     * @param data of request
     */
    public RestMethod(RestMethodTypes type, String url, String path, RequestData data) {
        this.type = type;
        this.url = url;
        this.path = path;
        this.data = data;
    }

    /**
     * Constructor for forming HTTP request with given Rest Assured Request Specification.
     *
     * @param type                 of HTTP request
     * @param path                 of request
     * @param requestSpecification Rest Assured request specification
     */
    public RestMethod(RestMethodTypes type, String path, RequestSpecification requestSpecification) {
        this(type, path);
        if (requestSpecification != null) {
            this.spec = spec.spec(requestSpecification);
        }
    }

    /**
     * Constructor for forming HTTP request with given Rest Assured Request Specification.
     *
     * @param type                 of HTTP request
     * @param url                  of request
     * @param requestSpecification Rest Assured request specification
     */
    public RestMethod(RestMethodTypes type, String path, String url, RequestSpecification requestSpecification) {
        this(type, path, url);
        if (requestSpecification != null) {
            this.spec = spec.spec(requestSpecification);
        }
    }

    public RequestSpecification getInitSpec() {
        return given().filter(new AllureRestAssured()).spec(spec).spec(getDataSpec(data));
    }

    /**
     * Set header to HTTP request.
     *
     * @param header field name and value presented as annotation
     */
    public void addHeader(com.epam.http.annotations.Header header) {
        addHeader(header.name(), header.value());
    }

    /**
     * Set header to HTTP request.
     *
     * @param headers pairs of field name and value presented as annotation
     */
    public void addHeaders(com.epam.http.annotations.Header... headers) {
        for (com.epam.http.annotations.Header header : headers) {
            addHeader(header);
        }
    }

    /**
     * Set header to HTTP request.
     *
     * @param name  of header
     * @param value of header
     */
    public void addHeader(String name, String value) {
        List<Header> headerList = new ArrayList<>(data.headers.asList());
        headerList.add(new Header(name, value));
        data.headers = new Headers(headerList);
    }

    /**
     * Adds header without value to HTTP request
     *
     * @param name of header
     * @return generated request data with provided header
     */
    public void addHeader(String name) {
        addHeader(name, "");
    }

    /**
     * Adds header with multiple values to HTTP request
     *
     * @param name             of header
     * @param value            of header
     * @param additionalValues of header
     * @return generated request data with provided header
     */
    public void addHeader(String name, String value, String... additionalValues) {
        List<Header> headerList = new ArrayList<>(data.headers.asList());
        headerList.add(new Header(name, value));
        for (String headerValue : additionalValues) {
            headerList.add(new Header(name, headerValue));
        }
        data.headers = new Headers(headerList);
    }

    /**
     * Set Content-Type to HTTP request.
     *
     * @param ct Rest Assured Content-Type
     */
    public void setContentType(ContentType ct) {
        data.contentType = ct.toString();
    }

    /**
     * Set cookie to HTTP request.
     *
     * @param cookie field name and value presented as annotation
     */
    public void addCookie(com.epam.http.annotations.Cookie cookie) {
        addCookie(cookie.name(), cookie.value());
    }

    /**
     * Set cookie to HTTP request.
     *
     * @param cookies pairs of field name and value presented as annotation
     */
    public void addCookies(com.epam.http.annotations.Cookie... cookies) {
        for (com.epam.http.annotations.Cookie cookie : cookies) {
            addCookie(cookie);
        }
    }

    /**
     * Set cookie to HTTP request.
     *
     * @param name  of cookie
     * @param value of cookie
     */
    public void addCookie(String name, String value) {
        List<Cookie> cookieList = new ArrayList<>(data.cookies.asList());
        cookieList.add(new Cookie.Builder(name, value).build());
        data.cookies = new Cookies(cookieList);
    }

    /**
     * Set cookie without value to HTTP request.
     *
     * @param name of cookie
     */
    public void addCookie(String name) {
        addCookie(name, "");
    }

    /**
     * Set cookie with multiple values to HTTP request.
     *
     * @param name             of cookie
     * @param value            of cookie
     * @param additionalValues additional values of the cookie
     */
    public void addCookie(String name, String value, String[] additionalValues) {
        List<Cookie> cookieList = new ArrayList<>(data.cookies.asList());
        cookieList.add(new Cookie.Builder(name, value).build());
        if (additionalValues != null) {
            for (String cookieValue : additionalValues) {
                cookieList.add(new Cookie.Builder(name, cookieValue).build());
            }
        }
        data.cookies = new Cookies(cookieList);
    }

    public RestMethod expectStatus(ResponseStatusType status) {
        expectedStatus = status;
        return this;
    }

    /**
     * Set query parameters to HTTP request.
     *
     * @param params collection of query parameters from HTTP method field annotation
     */
    public void addQueryParameters(QueryParameter... params) {
        data.queryParams.addAll(new MapArray<>(params,
                QueryParameter::name, QueryParameter::value));
    }

    public void addFormParameters(FormParameter... params) {
        data.formParams.addAll(new MapArray<>(params,
                FormParameter::name, FormParameter::value));
    }

    private void logRequest(RequestData... rds) {
        ArrayList<String> maps = new ArrayList<>();
        for (RequestData rd : rds) {
            maps.addAll(rd.getFields().filter((k, v) -> !k.equals("empty") && v != null && !v.toString().equals("[]") && !v.toString().isEmpty()).map((k, v) -> "\n" + k + ": " + v));
        }
        logger.info(format("Do %s request: %s%s %s", type, url != null ? url : "", path != null ? path : "", maps));
    }

    /**
     * Send HTTP request
     *
     * @return response
     */
    public RestResponse call() {
        if (type == null) {
            throw exception("HttpMethodType not specified");
        }
        RequestSpecification runSpec = getInitSpec();
        if (!userData.empty) {
            runSpec.spec(getDataSpec(userData));
        }
        logRequest(data, userData);
        userData.clear();
        return doRequest(type, runSpec, expectedStatus);
    }

    /**
     * Send HTTP request with Rest Assured Request Specification.
     *
     * @param requestSpecification Rest Assured request specification
     * @return response
     */
    public RestResponse call(RequestSpecification requestSpecification) {
        if (type == null) {
            throw exception("HttpMethodType not specified");
        }
        RequestSpecification runSpec = getInitSpec().spec(requestSpecification).baseUri(url).basePath(path);
        logRequest(data);
        return doRequest(type, runSpec, expectedStatus);
    }

    /**
     * Send HTTP request and map response to Java object.
     *
     * @param c class to make mapping to
     * @return Java object
     */
    public T callAsData(Class<T> c) {
        return call().getRaResponse().body().as(c);
    }

    public T asData(Class<T> c) {
        return callAsData(c);
    }

    public RestResponse postData(T data) {
        this.data.body = gson.toJson(data);
        return call();
    }

    /**
     * Send HTTP request with specific path parameters.
     *
     * @param params path parameters
     * @return response
     */
    public RestResponse call(String... params) {
        if (path.contains("%s") && params.length > 0) {
            path = format(path, params);
        }
        return call();
    }

    public RestResponse post(String body) {
        return call(new RequestData().set(rd -> rd.body = body));
    }

    /**
     * Send HTTP request with invoked request data.
     *
     * @param requestData
     * @return response
     */
    public RestResponse call(RequestData requestData) {
        userData.empty = false;
        if (!requestData.pathParams.isEmpty()) {
            userData.pathParams = requestData.pathParams;
        }
        if (!requestData.queryParams.isEmpty()) {
            userData.queryParams.addAll(requestData.queryParams);
        }
        if (!requestData.formParams.isEmpty()) {
            userData.formParams.addAll(requestData.formParams);
        }
        if (requestData.body != null) {
            userData.body = requestData.body;
        }
        if (!requestData.headers.asList().isEmpty()) {
            List<Header> headerList = new ArrayList<>(userData.headers.asList());
            headerList.addAll(requestData.headers.asList());
            userData.headers = new Headers(headerList);
        }
        if (!requestData.cookies.asList().isEmpty()) {
            List<Cookie> cookieList = new ArrayList<>(userData.cookies.asList());
            cookieList.addAll(requestData.cookies.asList());
            userData.cookies = new Cookies(cookieList);
        }
        if (requestData.contentType != null) {
            userData.contentType = requestData.contentType;
        }
        if (requestData.multiPartSpecifications.size() > 0) {
            userData.multiPartSpecifications = requestData.multiPartSpecifications;
        }
        return call();
    }

    /**
     * Get request specification to be able to log it.
     *
     * @return request specification
     */
    public RequestSpecification getDataSpec(RequestData data) {
        RequestSpecification spec = new RequestSpecBuilder().build();
        if (url != null) {
            spec.baseUri(url);
        }
        if (path != null) {
            spec.basePath(path);
        }
        if (data == null) {
            return spec;
        }
        if (data.uri != null) {
            spec.baseUri(data.uri);
        }
        if (data.path != null) {
            spec.basePath(data.path);
        }
        if (data.pathParams.any()) {
            spec.pathParams(data.pathParams.toMap());
        }
        if (data.contentType != null) {
            spec.contentType(data.contentType);
        }
        if (data.queryParams.any()) {
            spec.queryParams(data.queryParams.toMap());
        }
        if (data.formParams.any()) {
            spec.formParams(data.formParams.toMap());
        }
        if (data.body != null) {
            spec.body(data.body);
        }
        if (!data.headers.asList().isEmpty()) {
            spec.headers(data.headers);
        }
        if (!data.cookies.asList().isEmpty()) {
            spec.cookies(data.cookies);
        }
        if (data.multiPartSpecifications.size() > 0) {
            data.multiPartSpecifications.forEach(spec::multiPart);
        }

        return spec;
    }

    /**
     * Check response time is less than 2000msec.
     */
    public void isAlive() {
        isAlive(2000);
    }

    /**
     * Check response time is less than the parameter value.
     *
     * @param liveTimeMSec Msec value
     */
    public void isAlive(int liveTimeMSec) {
        StopWatch watch = createStarted();
        ResponseStatusType status;
        do {
            status = call().getStatus().type;
        } while (status != OK && watch.getTime() < liveTimeMSec);
        call().isOk();
    }
}
