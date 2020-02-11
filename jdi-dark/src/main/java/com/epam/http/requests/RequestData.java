package com.epam.http.requests;

import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MultiMap;
import io.restassured.http.ContentType;

/**
 * Represents all HTTP request data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RequestData extends DataClass<RequestData> {
    public boolean empty = true;
    public String uri = null;
    public String path = null;
    public String body = null;
    public ContentType contentType = null;
    public MultiMap<String, String> headers = new MultiMap<>();
    public MultiMap<String, String> pathParams = new MultiMap<>();
    public MultiMap<String, String> queryParams = new MultiMap<>();
    public MultiMap<String, String> cookies = new MultiMap<>();

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
     * Set request body to request data.
     *
     * @param body as formatted String
     * @return generated request data with provided request body
     */
    public static RequestData requestBody(String body) {
        return new RequestData().set(rd -> rd.body = body);
    }

    /**
     * Set path parameters required to be inserted in URL.
     *
     * @param params path parameters
     * @return generated request data with provided path parameters
     */
    public static RequestData requestPathParams(Object[][] params) {
        return new RequestData().set(rd -> rd.pathParams = new MultiMap<>(params));
    }

    /**
     * Set path parameters required to be inserted in URL.
     *
     * @param paramName  path parameter name
     * @param paramValue path parameter value
     * @return generated request data with provided path parameters
     */
    public static RequestData requestPathParams(String paramName, String paramValue) {
        return requestPathParams(new Object[][]{{paramName, paramValue}});
    }

    /**
     * Set query parameters in request
     *
     * @param params query parameters
     * @return generated request data with provided query parameters
     */
    public static RequestData requestQueryParams(Object[][] params) {
        return new RequestData().set(rd -> rd.queryParams = new MultiMap<>(params));
    }

    /**
     * @param paramName  query parameter name
     * @param paramValue query parameter value
     * @return generated request data with provided query parameters
     */
    public static RequestData requestQueryParams(String paramName, String paramValue) {
        return requestQueryParams(new Object[][]{{paramName, paramValue}});
    }

    /**
     * Clean Custom user Request data to avoid using old data in new requests
     */
    public void clear() {
        headers.clear();
        pathParams.clear();
        queryParams.clear();
        cookies.clear();
        body = null;
        path = null;
        uri = null;
        contentType = null;
        empty = true;
    }
}
