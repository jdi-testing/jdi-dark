package com.epam.http.requests;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.JdiHttpSettigns.verifyOkStatus;
import static java.lang.System.currentTimeMillis;

public class RestRequest {
    public static RestResponse doRequest(
            RestMethodTypes methodType, RequestSpecification spec, ResponseStatusType excpecedtStatus) {
        Response response;
        long time;
        try {
            time = currentTimeMillis();
            response = methodType.method.apply(spec);
            time = currentTimeMillis() - time;
        } catch (Exception ex) { throw exception("Request failed"); }
        RestResponse resp = new RestResponse(response, time);
        if (verifyOkStatus)
            resp.isStatus(excpecedtStatus);
        return resp;
    }
    private static String printRS(RequestSpecification rs) {
        return rs.toString();
    }
}
