package com.epam.http.performance;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.http.response.RestResponse;

import static com.epam.http.response.ResponseStatusType.ERROR;

public class PerformanceResult {

    public long AverageResponseTime = 0;
    public long NumberOfRequests = 0;
    public long NumberOfFails = 0;
    public boolean NoFails() {
        return NumberOfFails == 0;
    }

    public void addResult(RestResponse response) {
        AverageResponseTime = (AverageResponseTime * NumberOfRequests + response.responseTime())
                / (NumberOfRequests + 1);
        NumberOfRequests++;
        if (response.status.type == ERROR)
            NumberOfFails++;

    }
}
