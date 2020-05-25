package com.epam.http.performance;

import lombok.Data;

import java.util.List;

@Data
public class PerformanceResult {

    public long averageResponseTime = 0;
    public long numberOfRequests = 0;
    public long numberOfFails = 0;
    public long numberOfClientFails = 0;
    public long numberOfServerFails = 0;

    public boolean noFails() {
        return numberOfFails == 0;
    }

    /**
     * Construct the results of performance tests.
     *
     * @param results List of ThreadResult
     */
    public void aggregateResult(List<ThreadResult> results) {
        results.forEach(res -> {
            averageResponseTime = (averageResponseTime * numberOfRequests + res.getAverageResponseTime())
                    / (numberOfRequests + 1);
            numberOfRequests += res.getNumberOfRequests();
            numberOfClientFails += res.getNumberOfClientFails();
            numberOfServerFails += res.getNumberOfServerFails();
            numberOfFails = numberOfClientFails + numberOfServerFails;
        });
    }
}
