package com.epam.http.requests;

import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.epam.http.JdiHttpSettigns.verifyOkStatus;
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
     * @param expectedStatus    of request
     * @return                  response
     */
    public static RestResponse doRequest(
            RestMethodTypes methodType, RequestSpecification spec, ResponseStatusType expectedStatus) {
        Response response;
        long time;
        time = currentTimeMillis();
        response = methodType.method.apply(spec);
        time = currentTimeMillis() - time;
        RestResponse resp = new RestResponse(response, time);
        if (verifyOkStatus)
            resp.isStatus(expectedStatus);
        return resp;
    }
    private static String printRS(RequestSpecification rs) {
        return rs.toString();
    }
}
