package com.epam.http.requests;

import com.epam.http.requests.updaters.*;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MultiMap;
import io.restassured.authentication.AuthenticationScheme;

/**
 * Represents all HTTP request data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RequestDataInfo {
    /**
     * Set request data fields based on lambda function.
     *
     * @param valueFunc lambda to set values for fields
     * @return generated request data with all provided values
     */
    public static RequestData requestData(JAction1<RequestData> valueFunc) {
        return new RequestData().set(valueFunc);
    }
    /**
     * Set authentication scheme
     *
     * @param authenticationScheme authentication scheme
     */
    public static RequestData auth(AuthenticationScheme authenticationScheme) {
        return new RequestData().set(rd -> rd.setAuth(authenticationScheme));
    }
    public static CookieUpdater cookies() { return new CookieUpdater(); }
    public static HeaderUpdater headers() { return new HeaderUpdater(); }
    public static MultipartUpdater multiparts() { return new MultipartUpdater(); }
    public static FormParamsUpdater formParams() { return new FormParamsUpdater(); }
    public static QueryParamsUpdater queryParams() { return new QueryParamsUpdater(); }
    public static PathParamsUpdater pathParams() { return new PathParamsUpdater(); }

    /**
     * Set request body to request data.
     *
     * @param body as formatted String
     * @return generated request data with provided request body
     */
    public static RequestData requestBody(Object body) {
        return new RequestData().set(rd -> rd.body = body);
    }

    /**
     * Set form parameters to request
     *
     * @param params form parameters
     * @return generated request data with provided form parameters
     */
    public static RequestData requestFormParams(Object[][] params) {
        return new RequestData().set(rd -> rd.formParams = new MultiMap<>(params));
    }

    /**
     * Set form parameters to request
     *
     * @param paramName  form parameter name
     * @param paramValue form parameter value
     * @return generated request data with provided form parameters
     */
    public static RequestData requestFormParams(String paramName, String paramValue) {
        return requestFormParams(new Object[][]{{paramName, paramValue}});
    }
}
