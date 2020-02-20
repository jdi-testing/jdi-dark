package com.epam.http.performance;

import com.epam.http.response.RestResponse;

import static com.epam.http.response.ResponseStatusType.CLIENT_ERROR;

/**
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class PerformanceResult {

    public long AverageResponseTime = 0;
    public long NumberOfRequests = 0;
    public long NumberOfFails = 0;
    public boolean NoFails() {
        return NumberOfFails == 0;
    }

    /**
     * Construct the results of performance tests.
     * @param response          response
     */
    public void addResult(RestResponse response) {
        AverageResponseTime = (AverageResponseTime * NumberOfRequests + response.responseTime())
                / (NumberOfRequests + 1);
        NumberOfRequests++;
        if (response.status.type == CLIENT_ERROR)
            NumberOfFails++;

    }
}
