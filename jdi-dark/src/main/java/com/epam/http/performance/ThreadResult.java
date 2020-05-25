package com.epam.http.performance;

import com.epam.http.response.RestResponse;
import lombok.Data;

import static com.epam.http.response.ResponseStatusType.CLIENT_ERROR;
import static com.epam.http.response.ResponseStatusType.SERVER_ERROR;

@Data
public class ThreadResult {

    public long minResponseTime = 0;
    public long maxResponseTime = 0;
    public long averageResponseTime = 0;
    public long numberOfRequests = 0;
    public long numberOfClientFails = 0;
    public long numberOfServerFails = 0;

    /**
     * Construct the results of performance tests.
     * @param response          response
     */
    public void addResult(RestResponse response) {
        long responseTime = response.responseTime();
        minResponseTime = minResponseTime == 0 ? responseTime : Math.min(minResponseTime, responseTime);
        maxResponseTime = Math.max(maxResponseTime, responseTime);
        averageResponseTime = (averageResponseTime * numberOfRequests + response.responseTime())
                / (numberOfRequests + 1);
        numberOfRequests++;
        if (response.getStatus().type == CLIENT_ERROR)
            numberOfClientFails++;
        if (response.getStatus().type == SERVER_ERROR)
            numberOfServerFails++;
    }
}
