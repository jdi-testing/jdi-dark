package com.epam.http.requests;

import com.epam.http.response.RestResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.epam.http.response.RestResponse.LOG_RESPONSE;

/**
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RestRequest {
    /**
     * Perform an HTTP request with provided data.
     * Used in call().
     *
     * @param methodType of HTTP request
     * @param spec       Request Specification
     * @param startUuid  uuid
     * @return response
     */
    public static RestResponse doRequest(
            RestMethodTypes methodType, RequestSpecification spec, String startUuid) {
        Response response;
        response = methodType.method.apply(spec);
        RestResponse resp = new RestResponse(response);
        LOG_RESPONSE.execute(resp, startUuid);
        return resp;
    }
}
