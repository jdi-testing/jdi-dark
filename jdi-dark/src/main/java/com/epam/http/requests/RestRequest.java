package com.epam.http.requests;

import com.epam.http.response.RestResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static java.lang.System.currentTimeMillis;

/**
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RestRequest {
    /**
     * Perform an HTTP request with provided data.
     * Used in call().
     * @param methodType        of HTTP request
     * @param spec              Request Specification
     * @return                  response
     */
    public static RestResponse doRequest(
            RestMethodTypes methodType, RequestSpecification spec) {
        Response response;
        long time = currentTimeMillis();
        response = methodType.method.apply(spec);
        time = currentTimeMillis() - time;
        RestResponse resp = new RestResponse(response, time);
        return resp;
    }
    private static String printRS(RequestSpecification rs) {
        return rs.toString();
    }
}
