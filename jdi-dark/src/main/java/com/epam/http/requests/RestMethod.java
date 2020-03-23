package com.epam.http.requests;

import com.epam.http.annotations.MultiPart;
import com.epam.http.annotations.Proxy;
import com.epam.http.annotations.TrustStore;
import com.epam.http.requests.errorhandler.DefaultErrorHandler;
import com.epam.http.requests.errorhandler.ErrorHandler;
import com.epam.http.requests.updaters.CookieUpdater;
import com.epam.http.requests.updaters.FormParamsUpdater;
import com.epam.http.requests.updaters.HeaderUpdater;
import com.epam.http.requests.updaters.QueryParamsUpdater;
import com.epam.http.requests.util.WaitUtils;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.func.*;
import com.epam.jdi.tools.pairs.Pair;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.*;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.JdiHttpSettigns.getDomain;
import static com.epam.http.JdiHttpSettigns.logger;
import static com.epam.http.logger.AllureLogger.startStep;
import static com.epam.http.requests.RestRequest.doRequest;
import static com.epam.http.response.ResponseStatusType.OK;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOfRange;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.time.StopWatch.createStarted;

/**
 * Class for HTTP request formation.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RestMethod {

    private RequestSpecification spec = given();
    private String url = null;
    private String path = null;
    private ObjectMapper objectMapper = null;

    public RequestData getData() {
        return data;
    }

    public HeaderUpdater header = new HeaderUpdater(this::getData);
    public CookieUpdater cookies = new CookieUpdater(this::getData);
    public QueryParamsUpdater queryParams = new QueryParamsUpdater(this::getData);
    public FormParamsUpdater formParams = new FormParamsUpdater(this::getData);
    public RetryData reTryData;
    private RequestData data;
    private RequestData userData = new RequestData();
    private RestMethodTypes type;

    private ErrorHandler errorHandler = new DefaultErrorHandler();

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
        if (requestSpecification == null) return;
        this.spec = spec.spec(requestSpecification);
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
        if (requestSpecification == null) return;
        this.spec = spec.spec(requestSpecification);
    }

    public void setup(RestMethodTypes type, String path, String url, RequestSpecification requestSpecification) {
        this.type = type;
        this.path = path;
        this.url = url;
        this.data = new RequestData();
        if (requestSpecification == null) return;
        this.spec = spec.spec(requestSpecification);
    }

    public RequestSpecification getInitSpec() {
        return given().spec(spec).spec(getDataSpec(data));
    }

    public RequestSpecification getDataSpec() {
        return getDataSpec(data);
    }

    /**
     * Set ObjectMapper for HTTP request
     *
     * @param objectMapper object mapper which will be used for body serialization/deserialization
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        if (objectMapper == null) return;
        this.objectMapper = objectMapper;
    }

    /**
     * Set custome error handler
     *
     * @param errorHandler error Handler
     */
    public void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) return;
        this.errorHandler = errorHandler;
    }

    public void setAuthenticationScheme(AuthenticationScheme authenticationScheme) {
        if (authenticationScheme == null) return;
        data.authenticationScheme = authenticationScheme;
    }

    /**
     * Set Content-Type to HTTP request.
     *
     * @param ct Rest Assured Content-Type
     */
    public void setContentType(ContentType ct) {
        if (ct == null) return;
        data.contentType = ct.toString();
    }

    //    /**
//     * Set trustStore parameters to the request.
//     *
//     * @param trustStore trustStore
//     */

    public void setTrustStore(TrustStore trustStore) {
        data.trustStore = new Pair(trustStore.pathToJks(), trustStore.password());
    }

    /**
     * Set proxy parameters to the request.
     *
     * @param proxyParams proxyParams
     */
    public void setProxy(Proxy proxyParams) {
        data.setProxySpecification(proxyParams.scheme(), proxyParams.host(), proxyParams.port());
    }

    public void addMultiPartParams(MultiPart multiPartParams) {
        data.multiPartSpecifications.add(new MultiPartSpecBuilder("").controlName(multiPartParams.controlName()).fileName(multiPartParams.fileName()).build());
    }

    public static JFunc2<RestMethod, List<RequestData>, String> LOG_REQUEST = RestMethod::logRequest;
    public static JFunc3<RestMethod, List<RequestData>, Integer, String> LOG_RETRY_REQUEST = RestMethod::logReTryRequest;

    public String logRequest(List<RequestData> rds) {
        ArrayList<String> maps = new ArrayList<>();
        for (RequestData rd : rds) {
            maps.addAll(rd.fields().filter((k, v) -> !k.equals("empty") && v != null && !v.toString().equals("[]") && !v.toString().isEmpty()).map((k, v) -> "\n" + k + ": " + v));
        }
        logger.info(format("Do %s request: %s%s %s", type, url != null ? url : "", path != null ? path : "", maps));
        return startStep(format("%s %s%s", type, url != null ? url : "", path != null ? path : ""),
                format("%s %s%s  %s", type, url != null ? url : "", path != null ? path : "", maps));
    }

    private String logReTryRequest(List<RequestData> requestData, Integer i) {
        logger.info("================================> RETRY REQUEST ATTEMPT " + (i + 1) + "/" + reTryData.getNumberOfRetryAttempts() + ":");
        return logRequest(requestData);
    }

    /**
     * Send HTTP request
     *
     * @return response
     */
    public synchronized RestResponse call() {
        if (type == null) {
            throw exception("HttpMethodType not specified");
        }
        getQueryParamsFromPath();
        RequestSpecification runSpec = getInitSpec();
        if (!userData.empty) {
            runSpec.spec(getDataSpec(userData));
        }
        String startUuid = LOG_REQUEST.execute(this, asList(data, userData));
        userData.clear();
        RestResponse response = doRequest(type, runSpec, startUuid);
        handleResponse(response);
        response = handleRetrying(runSpec, response);
        return response;
    }

    /**
     * Sends HTTP request until server response status different from indicated
     * or max number of attempts was reached
     *
     * @param runSpec       - request specification
     * @param firstResponse - result of first request
     */
    private RestResponse handleRetrying(RequestSpecification runSpec, RestResponse firstResponse) {
        if (reTryData == null || reTryData.getNumberOfRetryAttempts() <= 0) return firstResponse;
        List<Integer> errorCodes = reTryData.getErrorCodes();

        boolean failure = errorCodes.contains(firstResponse.getStatus().code);
        if (failure) {
            for (int attempt = 0; attempt < reTryData.getNumberOfRetryAttempts(); attempt++) {
                WaitUtils.makeDelayFor(reTryData.getUnit(), reTryData.getDelay());
                String startUuidRetry = LOG_RETRY_REQUEST.execute(this, asList(data, userData), attempt);
                RestResponse retryingResponse = doRequest(type, runSpec, startUuidRetry);
                if (!errorCodes.contains(retryingResponse.getStatus().code)) return retryingResponse;
            }
        }
        return firstResponse;
    }


    private void handleResponse(RestResponse restResponse) {
        boolean hasError = errorHandler.hasError(restResponse);
        if (hasError) {
            errorHandler.handleError(restResponse);
        }
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
        String startUuid = LOG_REQUEST.execute(this, singletonList(data));
        RestResponse response = doRequest(type, runSpec, startUuid);
        response = handleRetrying(runSpec, response);
        return response;
    }

    /**
     * Send HTTP request with Rest Assured Request Specification with specific config
     *
     * @param restAssuredConfig Rest Assured config
     * @return response
     */
    public RestResponse call(RestAssuredConfig restAssuredConfig) {
        if (type == null) {
            throw exception("HttpMethodType not specified");
        }
        RequestSpecification runSpec = getInitSpec().config(restAssuredConfig);
        String startUuid = LOG_REQUEST.execute(this, singletonList(data));
        RestResponse response = doRequest(type, runSpec, startUuid);
        response = handleRetrying(runSpec, response);
        return response;
    }

    /**
     * Send HTTP request and map response to Java object.
     *
     * @param c class to make mapping response body to object
     * @return Java object
     */
    public <T> T callAsData(Class<T> c) {
        return objectMapper == null
                ? call().getRaResponse().body().as(c)
                : call().getRaResponse().body().as(c, objectMapper);
    }

    /**
     * Moved all query params from path to request data query params.
     */
    private void getQueryParamsFromPath() {
        if (data.path != null && data.path.contains("?")) {
            String pathString = substringBefore(path, "?");
            String queryString = substringAfter(path, "?");
            data.path = pathString;
            if (!queryString.isEmpty()) {
                String[] queryParams = path.split(",");
                for (String queryParam : queryParams) {
                    userData.empty = false;
                    userData.queryParams.add(substringBefore(queryParam, "="), substringAfter(queryParam, "="));
                }
            }
        }
    }

    /**
     * Send HTTP request with specific query parameters.
     *
     * @param queryParams additional query parameters
     * @return response
     */
    public synchronized RestResponse call(String queryParams) {
        if (!queryParams.isEmpty()) {
            String[] queryParamsArr = queryParams.split("&");
            for (String queryParam : queryParamsArr) {
                userData.empty = false;
                userData.queryParams.add(substringBefore(queryParam, "="), substringAfter(queryParam, "="));
            }
        }
        return call();
    }

    /**
     * Send HTTP request with specific named path parameters.
     *
     * @param namedParams path parameters
     * @return response
     */
    public synchronized RestResponse callWithNamedParams(String... namedParams) {
        if (namedParams.length > 0) {
            String pathString = substringBefore(path, "?");
            String queryString = substringAfter(path, "?");
            data.path = pathString;
            String[] pathParams = StringUtils.substringsBetween(pathString, "{", "}");
            catchPathParametersIllegalArguments(pathParams, namedParams, queryString);
            int index = 0;
            for (String key : pathParams) {
                userData.empty = false;
                userData.pathParams.add(key, namedParams[index]);
                index++;
            }
            if (!queryString.isEmpty()) {
                String[] queryParams = StringUtils.substringsBetween(queryString, "{", "}");
                for (String key : queryParams) {
                    userData.empty = false;
                    userData.queryParams.add(key, namedParams[index]);
                    index++;
                }
            }
        }
        return call();
    }

    /**
     * Catch errors when wrong count path parameters were specified.
     */
    private static void catchPathParametersIllegalArguments(String[] pathParams, String[] namedParams, String queryString) {
        if (namedParams.length > pathParams.length && queryString.isEmpty()) {
            String[] redundant_values = copyOfRange(namedParams, pathParams.length, namedParams.length);
            throw exception("Invalid number of path parameters. Expected %s , was %s.\nRedundant param values : %s",
                    pathParams.length, namedParams.length, asList(redundant_values));
        }
        if (namedParams.length < pathParams.length) {
            String[] missing_params = copyOfRange(pathParams, namedParams.length, pathParams.length);
            throw exception("Invalid number of path parameters. Expected %s, was %s.\nMissing params : %s",
                    pathParams.length, namedParams.length, asList(missing_params));
        }
    }

    /**
     * Send HTTP request with body.
     *
     * @param body request body
     * @return response
     */
    public RestResponse post(Object body) {
        return call(new RequestData().set(rd -> rd.body = body));
    }

    /**
     * Send HTTP request with body and parse result to object
     *
     * @param body request body
     * @param c    type of response body
     * @return response body as object
     */
    public <T> T post(Object body, Class<T> c) {
        return call(new RequestData().set(rd -> rd.body = body)).getRaResponse().as(c);
    }


    /**
     * Send HTTP request with invoked request data.
     *
     * @param requestData requestData
     * @return response
     */
    public synchronized RestResponse call(RequestData requestData) {
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
        if (requestData.proxySpecification != null) {
            userData.proxySpecification = requestData.proxySpecification;
        }
        userData.trustStore = requestData.trustStore == null
                ? data.trustStore
                : requestData.trustStore;
        userData.authenticationScheme = requestData.authenticationScheme == null
                ? data.authenticationScheme
                : requestData.authenticationScheme;
        return call();
    }

    public RestResponse call(JAction1<RequestData> action) {
        RequestData rd = new RequestData();
        action.execute(rd);
        return call(rd);
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
        if (!data.headers.asList().isEmpty()) {
            spec.headers(data.headers);
        }
        if (!data.cookies.asList().isEmpty()) {
            spec.cookies(data.cookies);
        }
        if (data.multiPartSpecifications.size() > 0) {
            data.multiPartSpecifications.forEach(spec::multiPart);
        }
        if ((data.body != null) && (objectMapper != null)) {
            spec.body(data.body, objectMapper);
        } else if (data.body != null) {
            spec.body(data.body);
        }
        if (data.proxySpecification != null) {
            spec.proxy(data.proxySpecification);
        }
        if (data.trustStore != null) {
            spec.trustStore(data.trustStore.key, data.trustStore.value);
        }
        if (data.authenticationScheme != null) {
            ((RequestSpecificationImpl) spec).setAuthenticationScheme(data.authenticationScheme);
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
            status = call().getResponseStatus().type;
        } while (status != OK && watch.getTime() < liveTimeMSec);
        call().isOk();
    }

    /**
     * Reset predefined request specification to default state.
     * <p>
     * return rest method
     */
    public RestMethod resetInitSpec() {
        spec = given();
        return this;
    }

    public String getType() {
        return type.toString();
    }

    public String getUrl() {
        return url;
    }
}
