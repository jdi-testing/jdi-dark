package com.epam.http.requests;

import com.epam.http.requests.components.JDIHeaders;
import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MapArray;
import com.epam.jdi.tools.map.MultiMap;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.internal.MapCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.restassured.specification.MultiPartSpecification;
import java.io.File;
import io.restassured.http.Header;

import java.util.ArrayList;

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
    public String contentType = null;
    public JDIHeaders headers = new JDIHeaders();
    public MultiMap<String, String> pathParams = new MultiMap<>();
    public MultiMap<String, String> queryParams = new MultiMap<>();
    public Cookies cookies = new Cookies();
    public ArrayList<MultiPartSpecification> multiPartSpecifications = new ArrayList<>();

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
     * Set content type to request data.
     *
     * @param contentType  content type as string
     */
    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    /**
     * Set content type to request data.
     *
     * @param contentType  content type as ContentType
     */
    public void setContentType(ContentType contentType){
        this.contentType = contentType.toString();
    }

    /**
     * Set multipart parameters to request data.
     *
     * @param multiPartSpecBuilder  MultiPartSpecBuilder
     */
    public void setMultiPart(MultiPartSpecBuilder multiPartSpecBuilder) {
        multiPartSpecifications.add(multiPartSpecBuilder.build());
    }

    /**
     * Set multipart parameters to request data.
     *
     * @param file  File parameter
     */
    public void setMultiPart(File file) {
        multiPartSpecifications.add(new MultiPartSpecBuilder(file).build());
    }

    /**
     * Clean Custom user Request data to avoid using old data in new requests
     */
    public void clear() {
        headers.clear();
        pathParams.clear();
        queryParams.clear();
        cookies = new Cookies();
        body = null;
        path = null;
        uri = null;
        contentType = null;
        empty = true;
        multiPartSpecifications.clear();
    }

    /**
     * Adds cookies to HTTP request
     *
     * @param objects pairs of cookies name and value
     * @return generated request data with provided cookies
     */
    public RequestData addCookies(Object[][] objects) {
        List<Cookie> cookieList = new ArrayList<>();
        for (Object[] cookie : objects) {
            cookieList.add(new Cookie.Builder(cookie[0].toString(), cookie[1].toString()).build());
        }
        cookies = new Cookies(cookieList);
        return this;
    }

    /**
     * Adds cookie without value to HTTP request
     *
     * @param name of cookie
     * @return generated request data with provided cookie
     */
    public RequestData addCookie(String name) {
        return addCookie(name, "");
    }

    /**
     * Adds cookie with multiple values to HTTP request
     *
     * @param name             of cookie
     * @param value            of cookie
     * @param additionalValues of cookie
     * @return generated request data with provided cookie
     */
    public RequestData addCookie(String name, String value, String... additionalValues) {
        List<Cookie> cookieList = new ArrayList<>(cookies.asList());
        cookieList.add(new Cookie.Builder(name, value).build());
        for (String cookieValue : additionalValues) {
            cookieList.add(new Cookie.Builder(name, cookieValue).build());
        }
        cookies = new Cookies(cookieList);
        return this;
    }

    /**
     * Adds multiple cookies to HTTP request
     *
     * @param name                 of cookie
     * @param value                of cookie
     * @param cookieNameValuePairs additional pairs of name and value
     * @return generated request data with provided cookies
     */
    public RequestData addCookies(String name, Object value, Object... cookieNameValuePairs) {
        Map<String, Object> map = MapCreator.createMapFromParams(MapCreator.CollisionStrategy.OVERWRITE, name, value, cookieNameValuePairs);
        return addCookies(map);
    }

    /**
     * Adds cookies from Map to HTTP request
     *
     * @param map of cookies
     * @return generated request data with provided cookies
     */
    public RequestData addCookies(Map map) {
        List<Cookie> cookieList = new ArrayList<>(cookies.asList());
        for (Object cookie : map.keySet()) {
            cookieList.add(new Cookie.Builder(cookie.toString(), map.get(cookie).toString()).build());
        }
        cookies = new Cookies(cookieList);
        return this;
    }

    /**
     * Adds cookies from MapArray to HTTP request
     *
     * @param mapArray of cookies
     * @return generated request data with provided cookies
     */
    public RequestData addCookies(MapArray mapArray) {
        Map map = mapArray.toMap();
        return addCookies(map);
    }
}
