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
    private String url = null;
    private String path = null;
    private String uri = null;
    private ObjectMapper objectMapper = null;

    public HeaderUpdater header = new HeaderUpdater(this::getData);
    public CookieUpdater cookies = new CookieUpdater(this::getData);
    public QueryParamsUpdater queryParams = new QueryParamsUpdater(this::getData);
    public FormParamsUpdater formParams = new FormParamsUpdater(this::getData);
    public RetryData reTryData;
    private RequestData data;
    private RequestData userData = new RequestData();
    private RestMethodTypes type;
    private ErrorHandler errorHandler = new DefaultErrorHandler();
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

    public RestMethod() {}

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

    public RestMethod restMethod() { return this; };

    public RequestData getData() {
        return data;
    }
    public RequestData getUserData() {
        return userData;
    }
    public RequestSpecification getSpec() {
        return spec;
    }
    public String getUrl() {
        return url;
    }
    public String getPath() {
        return path;
    }
    public RestMethodTypes getType() {
        return type;
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
        getData().getMultiPartSpec().add(mpSpecBuilder.build());
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
            if (rd.getHeaders().exist()) {
                maps.add("\nheaders: " + rd.getHeaders().asList().toString());
            }
            if (rd.getCookies().exist()) {
                maps.add("\ncookies: " + rd.getCookies().asList().toString());
            }
            rd.getMultiPartSpec().forEach(mps -> maps.add("\nmultiPartSpecification: " + mps.toString()));
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
        if (!userData.isEmpty()) {
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
     * @param cl class to make mapping response body to object
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
     * @param cl    type of response body
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
                if (!userData.getQueryParams().has(key)) {
                    userData.setEmpty(false);
                    userData.getQueryParams().add(substringBefore(queryParam, "="), substringAfter(queryParam, "="));
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
            userData.setPath(pathString);
            uri = url + pathString;
            getQueryParams(insertQueryParams(queryString));
        }
    }


    /**
     * Insert path params to uri.
     */
    private void insertPathParams() {
        if (uri.contains("{")) {
            userData.setPath(path);
        }
        uri = insertPathParams.execute(uri, getUserData().getPathParams());
        uri = insertPathParams.execute(uri, getData().getPathParams());
    }

    /**
     * Insert query params to uri.
     */
    private String insertQueryParams(String uri) {
        String mod = insertPathParams.execute(uri, getUserData().getQueryParams());
        mod = insertPathParams.execute(mod, getData().getQueryParams());
        return mod;
    }

    /**
     * Send HTTP request with specific query parameters.
     *
     * @param queryParams additional query parameters
     * @return response
     */
    public RestMethod queryParams(String queryParams) {
        getQueryParams(queryParams);
        return this;
    }

    /**
     * Send HTTP request with specific named path parameters.
     *
     * @param pathParams path parameters
     * @return response
     */
    public RestMethod pathParams(Object... pathParams) {
        if (pathParams.length > 0) {
            String[] namedParams = StringUtils.substringsBetween(path, "{", "}");
            catchPathParametersIllegalArguments(namedParams, pathParams);
            String pathString = substringBefore(path, "?");
            String queryString = substringAfter(path, "?");
            userData.setPath(pathString);
            int index = 0;
            String[] namedPathParams = StringUtils.substringsBetween(pathString, "{", "}");
            if (namedPathParams != null) {
                for (String key : StringUtils.substringsBetween(pathString, "{", "}")) {
                    userData.setEmpty(false);
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
     * @return response
     */
    public RestMethod body(Object body) {
        getData().setBody(body);
        return this;
    }


    /**
     * Send HTTP request with invoked request data.
     *
     * @param requestData requestData
     * @return response
     */
    public RestMethod data(RequestData requestData) {
        userData.setEmpty(false);
        if (!requestData.getPathParams().isEmpty()) {
            userData.setPathParams(requestData.getPathParams());
        }
        if (!requestData.getQueryParams().isEmpty()) {
            userData.queryParamsUpdater().addAll(requestData.getQueryParams());
        }
        if (!requestData.getFormParams().isEmpty()) {
            userData.formParamsUpdater().addAll(requestData.getFormParams());
        }
        if (requestData.getBody() != null) {
            userData.setBody(requestData.getBody());
        }
        if (!requestData.getHeaders().asList().isEmpty()) {
            List<Header> headerList = new ArrayList<>(userData.getHeaders().asList());
            headerList.addAll(requestData.getHeaders().asList());
            userData.setHeaders(new Headers(headerList));
        }
        if (!requestData.getCookies().asList().isEmpty()) {
            List<Cookie> cookieList = new ArrayList<>(userData.getCookies().asList());
            cookieList.addAll(requestData.getCookies().asList());
            userData.setCookies(new Cookies(cookieList));
        }
        if (requestData.getContentType() != null) {
            userData.setContentType(requestData.getContentType());
        }
        if (requestData.getMultiPartSpec().size() > 0) {
            userData.setMultiPartSpec(requestData.getMultiPartSpec());
        }
        if (requestData.getProxySpec() != null) {
            userData.setProxySpec(requestData.getProxySpec());
        }
        if (requestData.getTrustStore() == null) {
            userData.setTrustStore(requestData.getTrustStore());
        }
        if (requestData.getAuthScheme() == null) {
            userData.setAuthScheme(requestData.getAuthScheme());
        }
        return this;
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
        if (data.getUri() != null) {
            spec.baseUri(data.getUri());
        }
        if (data.getPath() != null) {
            spec.basePath(data.getPath());
        }
        if (data.getPathParams().any()) {
            spec.pathParams(data.getPathParams().toMap());
        }
        if (data.getContentType() != null) {
            spec.contentType(data.getContentType());
        }
        if (data.getQueryParams().any()) {
            spec.queryParams(data.getQueryParams().toMap());
        }
        if (data.getFormParams().any()) {
            spec.formParams(data.getFormParams().toMap());
        }
        if (!data.getHeaders().asList().isEmpty()) {
            spec.headers(data.getHeaders());
        }
        if (!data.getCookies().asList().isEmpty()) {
            spec.cookies(data.getCookies());
        }
        if (data.getMultiPartSpec().size() > 0) {
            data.getMultiPartSpec().forEach(spec::multiPart);
        }
        if ((data.getBody() != null) && (objectMapper != null)) {
            spec.body(data.getBody(), objectMapper);
        } else if (data.getBody() != null) {
            spec.body(data.getBody());
        }
        if (data.getProxySpec() != null) {
            spec.proxy(data.getProxySpec());
        }
        if (data.getTrustStore() != null) {
            spec.trustStore(data.getTrustStore().key, data.getTrustStore().value);
        }
        if (data.getAuthScheme() != null) {
            ((RequestSpecificationImpl) spec).setAuthenticationScheme(data.getAuthScheme());
        }
        return spec;
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
        ((MultiPartSpecificationImpl) getData().getMultiPartSpec().get(0)).setContent(multiPartContent);
        return this;
    }

}
