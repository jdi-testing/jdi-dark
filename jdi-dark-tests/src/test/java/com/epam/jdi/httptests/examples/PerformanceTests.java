package com.epam.jdi.httptests.examples;

import com.epam.http.performance.PerformanceResult;
import com.epam.http.performance.RestLoad;
import com.epam.jdi.services.ServiceExample;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;

import static com.epam.http.requests.ServiceInit.init;

public class PerformanceTests {

    @BeforeClass
    public void before(){
        init(ServiceExample.class);
    }

    @Test
    public void isAliveTest() {
        ServiceExample.getInfo.isAlive();
    }

    @Test
    public void printTest() throws InterruptedException, ExecutionException {
        PerformanceResult pr = RestLoad.loadService(10, ServiceExample.getInfo);
        Assert.assertTrue(pr.noFails(), "Number of fails: " + pr.getNumberOfFails());
        System.out.println("Average time: " + pr.getAverageResponseTime() + "ms");
        System.out.println("Requests amount: " + pr.getNumberOfRequests());
    }

    @Test
    public void concurrentTest() throws InterruptedException, ExecutionException {
        PerformanceResult pr = RestLoad.loadService(10, 10, ServiceExample.getInfo);
        Assert.assertTrue(pr.noFails(), "Number of fails: " + pr.getNumberOfFails());
        System.out.println("Average time: " + pr.getAverageResponseTime() + "ms");
        System.out.println("Requests amount: " + pr.getNumberOfRequests());
    }
}
