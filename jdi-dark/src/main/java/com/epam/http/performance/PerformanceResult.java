package com.epam.http.performance;

import lombok.Data;

import java.util.List;

import static com.epam.http.JdiHttpSettings.logger;

@Data
public class PerformanceResult {

    public long minResponseTime = 0;
    public long maxResponseTime = 0;
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
            minResponseTime = minResponseTime == 0 ? res.getMinResponseTime() : Math.min(minResponseTime, res.getMinResponseTime());
            maxResponseTime = Math.max(maxResponseTime, res.getMaxResponseTime());
            averageResponseTime = (averageResponseTime * numberOfRequests + res.getAverageResponseTime())
                    / (numberOfRequests + 1);
            numberOfRequests += res.getNumberOfRequests();
            numberOfClientFails += res.getNumberOfClientFails();
            numberOfServerFails += res.getNumberOfServerFails();
            numberOfFails = numberOfClientFails + numberOfServerFails;
        });
        logger.info("Performance test results:");
        logger.info("Threads count: " + results.size());
        logger.info("Requests count: " + numberOfRequests);
        logger.info("Average response time: " + averageResponseTime);
        logger.info("Minimum response time: " + minResponseTime);
        logger.info("Maximum response time: " + maxResponseTime);
        if (!noFails()) {
            logger.info("Fails count: " + numberOfFails);
            logger.info("Client fails count: " + numberOfClientFails);
            logger.info("Server fails count: " + numberOfServerFails);
        }
    }
}
