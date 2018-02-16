package com.epam.http.requests;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.http.response.RestResponse;

import static com.epam.http.requests.RestMethodTypes.*;

public class RestMethods {
    public static RestResponse GET(RequestData data) {
        return new RestMethod(GET, data).call();
    }
    public static RestResponse GET(String url) {
        return new RestMethod(GET, url).call();
    }
    public static RestResponse POST(RequestData data) {
        return new RestMethod(POST, data).call();
    }
    public static RestResponse POST(String url) {
        return new RestMethod(POST, url).call();
    }
    public static RestResponse PUT(RequestData data) {
        return new RestMethod(PUT, data).call();
    }
    public static RestResponse PUT(String url) {
        return new RestMethod(PUT, url).call();
    }
    public static RestResponse DELETE(RequestData data) {
        return new RestMethod(DELETE, data).call();
    }
    public static RestResponse DELETE(String url) {
        return new RestMethod(DELETE, url).call();
    }
    public static RestResponse PATCH(RequestData data) {
        return new RestMethod(PATCH, data).call();
    }
    public static RestResponse PATCH(String url) {
        return new RestMethod(PATCH, url).call();
    }
}
