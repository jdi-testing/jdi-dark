package com.epam.http.requests;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MapArray;
import io.restassured.http.ContentType;

import static io.restassured.http.ContentType.ANY;

public class RequestData extends DataClass<RequestData> {
    public String url = null;
    public String body = null;
    public ContentType contentType = ANY;
    public MapArray<String, String> headers = new MapArray<>();
    public MapArray<String, String> pathParams = new MapArray<>();
    public MapArray<String,String> queryParams = new MapArray<>();


    public static RequestData requestData(JAction1<RequestData> valueFunc) {
        return new RequestData().set(valueFunc);
    }
    public static RequestData requestBody(String body) {
        return new RequestData().set(rd -> rd.body = body);
    }
    public static RequestData requestParams(Object[][] params) {
        return new RequestData().set(rd -> rd.pathParams = new MapArray<>(params));
    }
    public static RequestData requestParams(String paramName, String paramValue) {
        return requestParams(new Object[][] { {paramName, paramValue}});
    }
}
