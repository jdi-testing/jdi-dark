package com.epam.http.requests;

import com.epam.http.annotations.QueryParameter;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MapArray;
import com.google.gson.Gson;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.JdiHttpSettigns.logger;
import static com.epam.http.requests.RestRequest.doRequest;
import static com.epam.http.response.ResponseStatusType.OK;
import static com.epam.jdi.tools.PrintUtils.formatParams;
import static com.epam.jdi.tools.PrintUtils.print;
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
    private RequestData data;
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
     * @param url  to send HTTP request to
     */
    public RestMethod(RestMethodTypes type, String url) {
        this(type, new RequestData().set(d -> d.url = url));
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
     * Constructor for forming HTTP request with given Rest Assured Request Specification.
     *
     * @param type                 of HTTP request
     * @param url                  of request
     * @param requestSpecification Rest Assured request specification
     */
    public RestMethod(RestMethodTypes type, String url, RequestSpecification requestSpecification) {
        this(type, url);
        if (requestSpecification != null) {
            this.spec = spec.spec(requestSpecification);
            data.headers.commonHeaders = ((FilterableRequestSpecification) spec).getHeaders().asList();
            data.commonQueryParams = ((FilterableRequestSpecification) spec).getQueryParams();
        }
    }

    public RequestSpecification getSpec() {
        return spec;
    }

    /**
     * Set header to HTTP request.
     *
     * @param name  of header field
     * @param value of header field
     */
    public void addHeader(String name, String value) {
        data.headers.serviceHeaders.add(name, value);
    }

    /**
     * Set header to HTTP request or replace the value of header if it had been added before.
     *
     * @param name  of header field
     * @param value of header field
     */
    public void addOrReplaceHeader(String name, String value) {
        data.headers.serviceHeaders.addOrReplace(name, value);
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
     * Set header to HTTP request from given Rest Assured header.
     *
     * @param header Rest Assured header
     */
    public void addHeader(Header header) {
        addHeader(header.getName(), header.getValue());
    }

    /**
     * Set headers to HTTP request from field annotation.
     *
     * @param headers collection of header annotations
     */
    public void addHeaders(com.epam.http.annotations.Header... headers) {
        for (com.epam.http.annotations.Header header : headers)
            addHeader(header);
    }

    /**
     * Set Content-Type to HTTP request.
     *
     * @param ct Rest Assured Content-Type
     */
    public void setContentType(ContentType ct) {
        data.contentType = ct;
    }

    public void addHeaders(Header[] headers) {
        for (Header header : headers)
            addHeader(header);
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
        List<Cookie> cookieList = new ArrayList<>(data.cookies.serviceCookies.asList());
        cookieList.add(new Cookie.Builder(name, value).build());
        data.cookies.serviceCookies = new Cookies(cookieList);
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
     * Set array of rest assured cookies to HTTP request.
     *
     * @param name             of cookie
     * @param value            of cookie
     * @param additionalValues additional values of the cookie
     */
    public void addCookies(String name, String value, String... additionalValues) {
        List<Cookie> cookieList = data.cookies.serviceCookies.asList();
        cookieList.add(new Cookie.Builder(name, value).build());
        for (String cookieValue : additionalValues) {
            cookieList.add(new Cookie.Builder(name, cookieValue).build());
        }
        data.cookies.serviceCookies = new Cookies(cookieList);
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
    void addQueryParameters(QueryParameter... params) {
        data.queryParams.addAll(new MapArray<>(params,
                QueryParameter::name, QueryParameter::value));
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
        RequestSpecification spec = addRestSpecificationData();
        //logger.info(format("Do %s request %s. \nQuery params: %s. \nPath params: %s. \nBody: %s", type, data.url, data.queryParams, data.pathParams, data.body));
        logger.info(format("Do %s request: %s", type, data.getFields().filter((k, v) -> !k.startsWith("common") && v != null && !v.toString().isEmpty()).map((k, v) -> "\n" + k + ": " + v)));
        return doRequest(type, spec, expectedStatus);
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
        addRestSpecificationData().body(this.data.body);
        return call();
    }

    /**
     * Send HTTP request with specific path parameters.
     *
     * @param params path parameters
     * @return response
     */
    public RestResponse call(String... params) {
        if (data.url.contains("%s") && params.length > 0) {
            data.url = format(data.url, params);
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
        if (!requestData.pathParams.isEmpty()) {
            data.pathParams = requestData.pathParams;
        }
        if (!requestData.queryParams.isEmpty()) {
            data.queryParams.addAll(requestData.queryParams);
        }
        if (requestData.body != null) {
            data.body = requestData.body;
        }
        if (!requestData.headers.userHeaders.isEmpty()) {
            data.headers.userHeaders.addAll(requestData.headers.userHeaders);
        }
        if (!requestData.cookies.userCookies.asList().isEmpty()) {
            // TODO - do we need to clear it? try via addAll
            List<Cookie> cookieList = new ArrayList<>(requestData.cookies.userCookies.asList());
            data.cookies.userCookies = new Cookies(cookieList);
        }
        return call();
    }

    /**
     * Send HTTP request with Rest Assured Request Specification.
     *
     * @param requestSpecification Rest Assured request specification
     * @return response
     */
    public RestResponse call(RequestSpecification requestSpecification) {
        this.spec = spec.spec(requestSpecification);
        return call();
    }

    /**
     * Get request specification to be able to log it.
     *
     * @return request specification
     */
    public RequestSpecification addRestSpecificationData() {
        if (data == null) {
            return spec;
        }
        String url = data.url;
        if (data.pathParams.any() && data.url.contains("{")) {
            url = formatParams(url, data.pathParams);
        }
        spec.baseUri(url);

        List<String> keys = ((FilterableRequestSpecification) spec).getQueryParams().keySet().stream()
                .filter(e -> !data.commonQueryParams.containsKey(e)).collect(Collectors.toList());
        for (String key : keys) {
            ((FilterableRequestSpecification) spec).removeQueryParam(key);
        }
        if (data.queryParams.any()) {
            spec.queryParams(data.queryParams.toMap());
            data.url += "?" + print(data.queryParams.toMap(), "&", "{0}={1}");
        }
        if (data.body != null) {
            spec.body(data.body);
        }
        List<Header> headers = ((FilterableRequestSpecification) spec).getHeaders().asList().stream()
                .filter(e -> !data.headers.commonHeaders.contains(e)).collect(Collectors.toList());
        for (Header header : headers) {
            ((FilterableRequestSpecification) spec).removeHeader(header.getName());
        }
        if (data.headers.serviceHeaders.any() || data.headers.userHeaders.any()) {
            spec.headers(data.headers.serviceHeaders.toMap());
            spec.headers(data.headers.userHeaders.toMap());
        }
        spec.contentType(data.contentType);

        List<Cookie> cookies = ((FilterableRequestSpecification) spec).getCookies().asList().stream()
                .filter(cookie -> !data.cookies.commonCookies.asList().contains(cookie)).collect(Collectors.toList());
        for (Cookie cookie : cookies) {
            ((FilterableRequestSpecification) spec).removeCookie(cookie.getName());
        }
        if (!data.cookies.serviceCookies.asList().isEmpty() || !data.cookies.userCookies.asList().isEmpty()) {
            spec.cookies(data.cookies.serviceCookies);
            spec.cookies(data.cookies.userCookies);
        }

        data.clear();
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
            status = call().status.type;
        } while (status != OK && watch.getTime() < liveTimeMSec);
        call().isOk();
    }
}
