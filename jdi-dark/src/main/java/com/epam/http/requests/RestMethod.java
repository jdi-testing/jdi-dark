package com.epam.http.requests;

import com.epam.http.annotations.MultiPart;
import com.epam.http.requests.errorhandler.DefaultErrorHandler;
import com.epam.http.requests.errorhandler.ErrorHandler;
import com.epam.http.requests.updaters.CookieUpdater;
import com.epam.http.requests.updaters.FormParamsUpdater;
import com.epam.http.requests.updaters.HeaderUpdater;
import com.epam.http.requests.updaters.QueryParamsUpdater;
import com.epam.http.requests.util.WaitUtils;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.func.JFunc2;
import com.epam.jdi.tools.func.JFunc3;
import com.epam.jdi.tools.map.MultiMap;
import com.epam.jdi.tools.pairs.Pair;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.multipart.MultiPartSpecificationImpl;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.JdiHttpSettings.getDomain;
import static com.epam.http.JdiHttpSettings.logger;
import static com.epam.http.logger.AllureLogger.startStep;
import static com.epam.http.requests.RestRequest.doRequest;
import static com.epam.http.response.ResponseStatusType.OK;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOfRange;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.time.StopWatch.createStarted;

/**
 * Class for HTTP request formation.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RestMethod {

    private RequestSpecification callSpec;
    protected String responseType;
    protected Class<?> dataType;
    private RequestSpecification spec = given();
    public String url = null;
    public String path = null;
    public String uri = null;
    public ObjectMapper objectMapper = null;

    public HeaderUpdater header = new HeaderUpdater(this::getData);
    public CookieUpdater cookies = new CookieUpdater(this::getData);
    public QueryParamsUpdater queryParams = new QueryParamsUpdater(this::getData);
    public FormParamsUpdater formParams = new FormParamsUpdater(this::getData);
    public RetryData reTryData;
    public RequestData data;
    public RequestData userData = new RequestData();
    public RestMethodTypes type;
    public ErrorHandler errorHandler = new DefaultErrorHandler();
    public static JFunc2<RestMethod, List<RequestData>, String> LOG_REQUEST = RestMethod::logRequest;
    public static JFunc3<RestMethod, List<RequestData>, Integer, String> LOG_RETRY_REQUEST = RestMethod::logReTryRequest;
    private final static JFunc2<RestMethod, List<RequestData>, String> LOG_REQUEST_DEFAULT = LOG_REQUEST;
    private final static JFunc3<RestMethod, List<RequestData>, Integer, String> LOG_RETRY_REQUEST_DEFAULT = LOG_RETRY_REQUEST;
    private final static JFunc2<String, MultiMap<String, String>, String> insertPathParams = (s, mm) -> {
        for (Pair<String, String> pathParam : mm) {
            s = s.replace("{" + pathParam.key + "}", pathParam.value);
        }
        return s;
    };

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
     * @param url  of HTTP request
     * @param path to send HTTP request to
     */
    public RestMethod(RestMethodTypes type, String url, String path) {
        this(type, url, path, new RequestData());
    }

    /**
     * Constructor for forming HTTP request.
     *
     * @param type of HTTP request
     * @param url  of HTTP request
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
     * @param path                 of request
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

    public RestMethod restMethod() {
        return this;
    }

    public RequestData getData() {
        return data;
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
     * @return RestMethod Rest method
     */
    public RestMethod setObjectMapper(ObjectMapper objectMapper) {
        if (objectMapper == null) return this;
        this.objectMapper = objectMapper;
        return this;
    }

    /**
     * Set custome error handler
     *
     * @param errorHandler error Handler
     * @return RestMethod
     */
    public RestMethod setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) return this;
        this.errorHandler = errorHandler;
        return this;
    }


    void addMultiPartParams(MultiPart multiPartParams) {
        String path = multiPartParams.filePath();
        MultiPartSpecBuilder mpSpecBuilder = new MultiPartSpecBuilder(path.isEmpty() ? "" :
                new File(path.contains(":") ? path : System.getProperty("user.dir")
                        .concat(path.startsWith("/") ? "" : "/").concat(path)));
        if (!multiPartParams.controlName().isEmpty())
            mpSpecBuilder.controlName(multiPartParams.controlName());
        if (!multiPartParams.fileName().isEmpty())
            mpSpecBuilder.fileName(multiPartParams.fileName());
        if (!multiPartParams.mimeType().isEmpty())
            mpSpecBuilder.mimeType(multiPartParams.mimeType());
        data.multiPartSpec.add(mpSpecBuilder.build());
    }

    public static void resetLogRequest() {
        LOG_REQUEST = LOG_REQUEST_DEFAULT;
        LOG_RETRY_REQUEST = LOG_RETRY_REQUEST_DEFAULT;
    }

    public String logRequest(List<RequestData> rds) {
        ArrayList<String> maps = new ArrayList<>();
        for (RequestData rd : rds) {
            maps.addAll(rd.fields().filter((k, v) -> !k.equals("multiPartSpecifications") &&
                    !k.equals("headers") &&
                    !k.equals("cookies") &&
                    !k.equals("empty") &&
                    v != null && !v.toString().equals("[]") && !v.toString().isEmpty())
                    .map((k, v) -> "\n" + k + ": " +
                            (v instanceof MultiMap ? ((MultiMap) v).map((km, vm) -> km + "=" + vm) : v)));
            if (rd.headers.exist()) {
                maps.add("\nheaders: " + rd.headers.asList().toString());
            }
            if (rd.cookies.exist()) {
                maps.add("\ncookies: " + rd.cookies.asList().toString());
            }
            rd.multiPartSpec.forEach(mps -> maps.add("\nmultiPartSpecification: " + mps.toString()));
        }
        logger.info(format("Do %s request: %s %s", type, uri != null ? uri : "", maps));
        return startStep(format("%s %s%s", type, url != null ? url : "", path != null ? path : ""),
                format("%s %s%s  %s", type, url != null ? url : "", path != null ? path : "", maps));
    }

    private String logReTryRequest(List<RequestData> requestData, Integer i) {
        logger.info("================================> RETRY REQUEST ATTEMPT " + (i + 1) + "/" +
                reTryData.getNumberOfRetryAttempts() + ":");
        return logRequest(requestData);
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
        uri = (url != null) ? url + path : data.uri;
        getQueryParamsFromPath();
        insertPathParams();
        RequestSpecification runSpec = (callSpec != null) ? callSpec : getInitSpec();
        if (!userData.empty) {
            runSpec.spec(getDataSpec(userData));
        }
        String startUuid = LOG_REQUEST.execute(this, asList(data, userData));
        userData.clear();
        RestResponse response = doRequest(type, runSpec, startUuid);
        handleResponse(response);
        response = handleRetrying(runSpec, response);
        callSpec = null;
        return response;
    }

    public RestResponse call(JAction1<RequestData> action) {
        RequestData rd = new RequestData();
        action.execute(rd);
        return call(rd);
    }

    /**
     * Send HTTP request with request data.
     *
     * @param requestData requestData
     * @return response
     */
    public RestResponse call(RequestData requestData) {
        return data(requestData).call();
    }

    /**
     * Send HTTP request with Rest Assured Request Specification.
     *
     * @param requestSpecification Rest Assured request specification
     * @return response
     */
    public RestResponse call(RequestSpecification requestSpecification) {
        callSpec = getInitSpec().spec(requestSpecification).baseUri(url).basePath(path);
        return call();
    }

    /**
     * Send HTTP request based on Rest Assured Request Specification.
     *
     * @param requestSpecification Rest Assured request specification
     * @return response
     */
    public RestResponse callBasedOnSpec(RequestSpecification requestSpecification) {
        callSpec = requestSpecification.spec(getInitSpec());
        return call();
    }

    /**
     * Send HTTP request with Rest Assured Request Specification with specific config
     *
     * @param restAssuredConfig Rest Assured config
     * @return response
     */
    public RestResponse call(RestAssuredConfig restAssuredConfig) {
        callSpec = getInitSpec().config(restAssuredConfig);
        return call();
    }

    /**
     * Send HTTP request and map response to Java object.
     *
     * @param cl  class to make mapping response body to object
     * @param <T> type
     * @return Java object
     */
    public <T> T callAsData(Class<T> cl) {
        return objectMapper == null
                ? call().asData(cl)
                : call().asData(cl, objectMapper);
    }

    public <T> T callAsData() {
        return (T) call().asData(dataType, responseType);
    }

    /**
     * Send HTTP request with body.
     *
     * @param body request body
     * @return response
     */
    public RestResponse post(Object body) {
        return body(body).call();
    }

    /**
     * Send HTTP request with body and parse result to object
     *
     * @param body request body
     * @param cl   type of response body
     * @param <T>  type
     * @return response body as object
     */
    public <T> T post(Object body, Class<T> cl) {
        return body(body).callAsData(cl);
    }

    public <T> T postAsData(Object object) {
        return (T) body(object).call().asData(dataType, responseType);
    }

    private void getQueryParams(String queryString) {
        if (!queryString.isEmpty()) {
            String[] queryParams = queryString.split("&");
            for (String queryParam : queryParams) {
                String key = substringBefore(queryParam, "=");
                if (!userData.queryParams.has(key)) {
                    userData.empty = false;
                    userData.queryParams.add(substringBefore(queryParam, "="), substringAfter(queryParam, "="));
                }
            }
        }
    }

    /**
     * Moved all query params from path to request data query params.
     */
    private void getQueryParamsFromPath() {
        if (path != null && path.contains("?")) {
            String pathString = substringBefore(path, "?");
            String queryString = substringAfter(path, "?");
            userData.empty = false;
            userData.path = pathString;
            uri = url + pathString;
            getQueryParams(insertQueryParams(queryString));
        }
    }


    /**
     * Insert path params to uri.
     */
    private void insertPathParams() {
        if (uri.contains("{")) {
            userData.empty = false;
            userData.path = path;
        }
        uri = insertPathParams.execute(uri, userData.pathParams);
        uri = insertPathParams.execute(uri, data.pathParams);
    }

    /**
     * Insert query params to uri.
     */
    private String insertQueryParams(String uri) {
        String mod = insertPathParams.execute(uri, userData.queryParams);
        mod = insertPathParams.execute(mod, data.queryParams);
        return mod;
    }

    /**
     * Send HTTP request with specific query parameters.
     *
     * @param queryParams additional query parameters
     * @return RestMethod Rest method
     */
    public RestMethod queryParams(String queryParams) {
        getQueryParams(queryParams);
        return this;
    }

    /**
     * Send HTTP request with specific named path parameters.
     *
     * @param pathParams path parameters
     * @return RestMethod Rest method
     */
    public RestMethod pathParams(Object... pathParams) {
        if (pathParams.length > 0) {
            String[] namedParams = StringUtils.substringsBetween(path, "{", "}");
            catchPathParametersIllegalArguments(namedParams, pathParams);
            String pathString = substringBefore(path, "?");
            String queryString = substringAfter(path, "?");
            userData.empty = false;
            userData.path = pathString;
            int index = 0;
            String[] namedPathParams = StringUtils.substringsBetween(pathString, "{", "}");
            if (namedPathParams != null) {
                for (String key : StringUtils.substringsBetween(pathString, "{", "}")) {
                    userData.empty = false;
                    userData.pathParamsUpdater().add(key, pathParams[index].toString());
                    index++;
                }
            }
            if (!queryString.isEmpty()) {
                return queryParams(substPathParams(queryString, copyOfRange(pathParams, index, pathParams.length)));
            }
        }
        return this;
    }

    private static String substPathParams(String path, Object[] pathParams) {
        String[] namedParams = StringUtils.substringsBetween(path, "{", "}");
        String substPath = path;
        for (int i = 0; i < namedParams.length; i++) {
            substPath = substPath.replace("{" + namedParams[i] + "}", pathParams[i].toString());
        }
        return substPath;
    }

    /**
     * Catch errors when wrong count path parameters were specified.
     */
    private static void catchPathParametersIllegalArguments(String[] namedPathParams, Object[] pathParams) {
        if (pathParams.length > namedPathParams.length) {
            Object[] redundant_values = copyOfRange(pathParams, namedPathParams.length, pathParams.length);
            throw exception("Invalid number of path parameters. Expected %s , was %s.\nRedundant param values : %s",
                    namedPathParams.length, pathParams.length, asList(redundant_values));
        }
        if (pathParams.length < namedPathParams.length) {
            String[] missing_params = copyOfRange(namedPathParams, pathParams.length, namedPathParams.length);
            throw exception("Invalid number of path parameters. Expected %s, was %s.\nMissing params : %s",
                    namedPathParams.length, pathParams.length, asList(missing_params));
        }
    }

    /**
     * Send HTTP request with body.
     *
     * @param body request body
     * @return RestMethod Rest method
     */
    public RestMethod body(Object body) {
        userData.empty = false;
        userData.body = body;
        return this;
    }

    public RestMethod data(JAction1<RequestData> action) {
        RequestData rd = new RequestData();
        action.execute(rd);
        return data(rd);
    }

    /**
     * Send HTTP request with invoked request data.
     *
     * @param requestData requestData
     * @return RestMethod Rest method
     */
    public RestMethod data(RequestData requestData) {
        userData.empty = false;
        if (!requestData.pathParams.isEmpty()) {
            userData.pathParams = requestData.pathParams;
        }
        if (!requestData.queryParams.isEmpty()) {
            userData.queryParamsUpdater().addAll(requestData.queryParams);
        }
        if (!requestData.formParams.isEmpty()) {
            userData.formParamsUpdater().addAll(requestData.formParams);
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
        if (requestData.multiPartSpec.size() > 0) {
            userData.multiPartSpec = requestData.multiPartSpec;
        }
        if (requestData.proxySpec != null) {
            userData.proxySpec = requestData.proxySpec;
        }
        if (requestData.trustStore != null) {
            userData.trustStore = requestData.trustStore;
        }
        if (requestData.authScheme != null) {
            userData.authScheme = requestData.authScheme;
        }
        return this;
    }

    /**
     * Get request specification to be able to log it.
     *
     * @param data RequestData
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
        if (data.multiPartSpec.size() > 0) {
            data.multiPartSpec.forEach(spec::multiPart);
        }
        if ((data.body != null) && (objectMapper != null)) {
            spec.body(data.body, objectMapper);
        } else if (data.body != null) {
            spec.body(data.body);
        }
        if (data.proxySpec != null) {
            spec.proxy(data.proxySpec);
        }
        if (data.trustStore != null) {
            spec.trustStore(data.trustStore.key, data.trustStore.value);
        }
        if (data.authScheme != null) {
            ((RequestSpecificationImpl) spec).setAuthenticationScheme(data.authScheme);
        }
        return spec;
    }

    /**
     * Sends HTTP request until server response status different from indicated
     * or max number of attempts was reached
     *
     * @param runSpec       - request specification
     * @param firstResponse - result of first request
     * @return RestResponse response
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

    public RestMethod multipart(Object multiPartContent) {
        ((MultiPartSpecificationImpl) data.multiPartSpec.get(0)).setContent(multiPartContent);
        return this;
    }

}
