package com.epam.http.requests;

import com.epam.http.requests.components.JDICookies;
import com.epam.http.requests.components.JDIHeaders;
import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MapArray;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.internal.MapCreator;

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
    public JDIHeaders headers = new JDIHeaders();

    public MapArray<String, String> pathParams = new MapArray<>();
    public MapArray<String, String> queryParams = new MapArray<>();
    public JDICookies cookies = new JDICookies();

    public Map<String, String> commonQueryParams = new HashMap<>();

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
        return new RequestData().set(rd -> rd.pathParams = new MapArray<>(params));
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
     * Clean Custom user Request data to avoid using old data in new requests
     */
    public void clear() {
        headers.userHeaders.clear();
    }

    public RequestData addCookies(Object[][] objects) {
        List<Cookie> cookieList = new ArrayList<>();
        for (Object[] cookie : objects) {
            cookieList.add(new Cookie.Builder(cookie[0].toString(), cookie[1].toString()).build());
        }
        cookies.userCookies = new Cookies(cookieList);
        return this;
    }

    public RequestData addCookie(String name) {
        return addCookie(name, "");
    }

    public RequestData addCookie(String name, String value, String... additionalValues) {
        List<Cookie> cookieList = new ArrayList<>(cookies.userCookies.asList());
        cookieList.add(new Cookie.Builder(name, value).build());
        for (String cookieValue : additionalValues) {
            cookieList.add(new Cookie.Builder(name, cookieValue).build());
        }
        cookies.userCookies = new Cookies(cookieList);
        return this;
    }

    public RequestData addCookies(String name, Object value, Object... cookieNameValuePairs) {
        Map<String, Object> map = MapCreator.createMapFromParams(MapCreator.CollisionStrategy.OVERWRITE, name, value, cookieNameValuePairs);
        return addCookies(map);
    }

    public RequestData addCookies(Map map) {
        List<Cookie> cookieList = new ArrayList<>(cookies.userCookies.asList());
        for (Object cookie : map.keySet()) {
            cookieList.add(new Cookie.Builder(cookie.toString(), map.get(cookie).toString()).build());
        }
        cookies.userCookies = new Cookies(cookieList);
        return this;
    }

    public RequestData addCookies(MapArray mapArray) {
        Map map = mapArray.toMap();
        return addCookies(map);
    }
}
