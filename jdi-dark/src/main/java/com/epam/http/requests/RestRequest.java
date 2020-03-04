package com.epam.http.requests;

import com.epam.http.logger.AllureLogger;
import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.func.JAction2;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static com.epam.http.JdiHttpSettigns.logger;
import static com.epam.http.response.RestResponse.LOG_RESPONSE;
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
        LOG_RESPONSE.execute(resp);
        return resp;
    }
    private static String printRS(RequestSpecification rs) {
        return rs.toString();
    }
}
