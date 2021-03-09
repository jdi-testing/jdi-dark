package com.epam.http.performance;

import com.epam.jdi.tools.DataClass;

import java.util.List;

import static com.epam.http.JdiHttpSettings.logger;

public class PerformanceResult extends DataClass<PerformanceResult> {

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
            minResponseTime = minResponseTime == 0 ? res.minResponseTime : Math.min(minResponseTime, res.minResponseTime);
            maxResponseTime = Math.max(maxResponseTime, res.minResponseTime);
            averageResponseTime = (averageResponseTime * numberOfRequests + res.averageResponseTime)
                    / (numberOfRequests + 1);
            numberOfRequests += res.numberOfRequests;
            numberOfClientFails += res.numberOfClientFails;
            numberOfServerFails += res.numberOfServerFails;
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
