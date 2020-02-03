package com.epam.http.requests;

import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MapArray;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.ANY;

/**
 * Represents all HTTP request data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RequestData extends DataClass<RequestData> {
    public String url = null;
    public String body = null;
    public ContentType contentType = ANY;
    public MapArray<String, String> headers = new MapArray<>();
    public MapArray<String, String> pathParams = new MapArray<>();
    public MapArray<String, String> queryParams = new MapArray<>();
    public MapArray<String, String> cookies = new MapArray<>();

    public Map<String, String> commonQueryParams = new HashMap<>();
    public List<Header> commonHeaders = new ArrayList<>();
    /**
     * Set request data fields based on lambda function.
     *
     * @param valueFunc     lambda to set values for fields
     * @return              generated request data with all provided values
     */
    public static RequestData requestData(JAction1<RequestData> valueFunc) {
        return new RequestData().set(valueFunc);
    }

    /**
     * Set request body to request data.
     * @param body          as formatted String
     * @return              generated request data with provided request body
     */
    public static RequestData requestBody(String body) {
        return new RequestData().set(rd -> rd.body = body);
    }

    /**
     * Set path parameters required to be inserted in URL.
     * @param params        path parameters
     * @return              generated request data with provided path parameters
     */
    public static RequestData requestPathParams(Object[][] params) {
        return new RequestData().set(rd -> rd.pathParams = new MapArray<>(params));
    }

    /**
     * Set path parameters required to be inserted in URL.
     * @param paramName     path parameter name
     * @param paramValue    path parameter value
     * @return              generated request data with provided path parameters
     */
    public static RequestData requestPathParams(String paramName, String paramValue) {
        return requestPathParams(new Object[][] { {paramName, paramValue}});
    }
}
