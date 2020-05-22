package com.epam.http.performance;

import com.epam.http.requests.RestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.currentTimeMillis;

public class RestLoad {

    static class RunnableLoadService implements Runnable {
        long liveTimeInSec;
        Map<RestMethod, Integer> weightRequests;
        RestMethod[] restMethods;
        ThreadResult result = new ThreadResult();

        RunnableLoadService(long liveTimeInSec, RestMethod... restMethods) {
            this.liveTimeInSec = liveTimeInSec;
            this.restMethods = restMethods;
        }

        RunnableLoadService(long liveTimeInSec, Map<RestMethod, Integer> weightRequests) {
            this.liveTimeInSec = liveTimeInSec;
            this.weightRequests = weightRequests;
        }

        RestMethod getRestMethod() {
            Random rnd = new Random();
            if (!weightRequests.isEmpty()) {
                return getRequest(weightRequests, Math.round(rnd.nextFloat() * getLength(weightRequests)));
            }
            return restMethods[rnd.nextInt(restMethods.length)];
        }

        @Override
        public void run() {
            long start = currentTimeMillis();
            do {
                result.addResult(getRestMethod().call());
            } while (currentTimeMillis() - start < liveTimeInSec*1000);
        }
    }

    /**
     * Send HTTP requests and measure the time.
     *
     * @param concurrentThreads number of concurrent threads
     * @param liveTimeInSec time limits requests should succeed
     * @param runnableLoadService    RunnableLoadService
     * @return results of loading the service
     */
    private static PerformanceResult loadService(int concurrentThreads, long liveTimeInSec, RunnableLoadService runnableLoadService) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(concurrentThreads);
        List<RunnableLoadService> loadServices = new ArrayList<>();
        IntStream.rangeClosed(1, concurrentThreads).forEach(e -> {
            loadServices.add(runnableLoadService);
        });
        loadServices.forEach(executor::execute);
        executor.shutdown();
        executor.awaitTermination(liveTimeInSec, TimeUnit.SECONDS);
        PerformanceResult pr = new PerformanceResult();
        pr.aggregateResult(loadServices.stream().map(ls -> ls.result).collect(Collectors.toList()));
        return pr;
    }

    /**
     * Send HTTP requests and measure the time.
     *
     * @param concurrentThreads number of concurrent threads
     * @param liveTimeInSec time limits requests should succeed
     * @param requests    requests
     * @return results of loading the service
     */
    public static PerformanceResult loadService(int concurrentThreads, long liveTimeInSec, RestMethod... requests) throws InterruptedException {
        return loadService(concurrentThreads, liveTimeInSec, new RunnableLoadService(liveTimeInSec, requests));
    }

    public static PerformanceResult loadService(long liveTimeInSec, RestMethod... requests) throws InterruptedException {
        return loadService(1, liveTimeInSec, requests);
    }

    public static PerformanceResult loadService(int concurrentThreads, long liveTimeInSec, Map<RestMethod, Integer> weightRequests) throws InterruptedException {
        return loadService(concurrentThreads, liveTimeInSec, new RunnableLoadService(liveTimeInSec, weightRequests));
    }

    public static PerformanceResult loadService(long liveTimeInSec, Map<RestMethod, Integer> weightRequests) throws InterruptedException {
        return loadService(1, liveTimeInSec, weightRequests);
    }

    private static int getLength(Map<RestMethod, Integer> wightRequests) {
        int Length = 0;
        for (Map.Entry<RestMethod, Integer> pair : wightRequests.entrySet())
            Length += pair.getValue();
        return Length;
    }

    private static RestMethod getRequest(Map<RestMethod, Integer> wightRequests, int num) {
        if (wightRequests == null || wightRequests.size() == 0)
            return null;
        int Sum = 0;
        for (Map.Entry<RestMethod, Integer> pair : wightRequests.entrySet()) {
            Sum += pair.getValue();
            if (Sum >= num)
                return pair.getKey();
        }
        return null;
    }

}
