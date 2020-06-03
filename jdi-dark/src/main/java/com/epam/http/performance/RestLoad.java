package com.epam.http.performance;

import com.epam.http.requests.RestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static com.epam.http.JdiHttpSettings.logger;
import static java.lang.System.currentTimeMillis;

public class RestLoad {

    static class RunnableLoadService implements Callable<ThreadResult>, Cloneable {
        private final long liveTimeInSec;
        private Map<RestMethod, Integer> weightRequests;
        private RestMethod[] restMethods;

        RunnableLoadService(long liveTimeInSec, RestMethod... restMethods) {
            this.liveTimeInSec = liveTimeInSec;
            this.restMethods = restMethods;
        }

        RunnableLoadService(long liveTimeInSec, Map<RestMethod, Integer> weightRequests) {
            this.liveTimeInSec = liveTimeInSec;
            this.weightRequests = weightRequests;
        }

        @Override
        public RunnableLoadService clone() {
            try {
                return (RunnableLoadService) super.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }

        RestMethod getRestMethod() {
            Random rnd = new Random();
            if (weightRequests != null) {
                return getRequest(weightRequests, Math.round(rnd.nextFloat() * getLength(weightRequests)));
            }
            return restMethods[rnd.nextInt(restMethods.length)];
        }

        @Override
        public ThreadResult call() {
            logger.info(Thread.currentThread().getName() + " started.");
            ThreadResult result = new ThreadResult();
            long start = currentTimeMillis();
            do {
                result.addResult(getRestMethod().call());
            } while (currentTimeMillis() - start < liveTimeInSec * 1000);
            logger.info(Thread.currentThread().getName() + " finished.");
            return result;
        }
    }

    /**
     * Send HTTP requests and measure the time.
     *
     * @param concurrentThreads   number of concurrent threads
     * @param runnableLoadService RunnableLoadService
     * @return results of loading the service
     */
    private static PerformanceResult loadService(int concurrentThreads, RunnableLoadService runnableLoadService) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(concurrentThreads);
        Collection<Callable<ThreadResult>> tasks = new ArrayList<>();
        List<ThreadResult> threadResults = new ArrayList<>();
        IntStream.rangeClosed(1, concurrentThreads).forEach(e -> tasks.add(runnableLoadService.clone()));
        List<Future<ThreadResult>> results = executor.invokeAll(tasks);
        PerformanceResult pr = new PerformanceResult();
        executor.shutdown();
        for (Future<ThreadResult> result : results) {
            try {
                threadResults.add(result.get());
            } catch (ExecutionException e) {
                logger.error(e.getMessage());
            }
        }
        pr.aggregateResult(threadResults);
        return pr;
    }

    /**
     * Send HTTP requests and measure the time.
     *
     * @param concurrentThreads number of concurrent threads
     * @param liveTimeInSec     time limits requests should succeed
     * @param requests          requests
     * @return results of loading the service
     * @throws InterruptedException Interrupted Exception
     */
    public static PerformanceResult loadService(int concurrentThreads, long liveTimeInSec, RestMethod... requests) throws InterruptedException {
        return loadService(concurrentThreads, new RunnableLoadService(liveTimeInSec, requests));
    }

    public static PerformanceResult loadService(long liveTimeInSec, RestMethod... requests) throws InterruptedException {
        return loadService(1, liveTimeInSec, requests);
    }

    public static PerformanceResult loadService(int concurrentThreads, long liveTimeInSec, Map<RestMethod, Integer> weightRequests) throws InterruptedException {
        return loadService(concurrentThreads, new RunnableLoadService(liveTimeInSec, weightRequests));
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
