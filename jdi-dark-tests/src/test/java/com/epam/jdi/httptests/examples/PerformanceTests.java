package com.epam.jdi.httptests.examples;

import com.epam.http.performance.PerformanceResult;
import com.epam.http.performance.RestLoad;
import com.epam.jdi.services.ServiceExample;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

class PerformanceTests {

    @Test
    public void isAliveTest() {
        init(ServiceExample.class);
        ServiceExample.getInfo.isAlive();
    }
    @Test
    public void printTest() throws InterruptedException {
        init(ServiceExample.class);
        PerformanceResult pr = RestLoad.loadService(10, ServiceExample.getInfo);
        Assert.assertTrue(pr.noFails(), "Number of fails: " + pr.getNumberOfFails());
        System.out.println("Average time: " + pr.getAverageResponseTime() + "ms");
        System.out.println("Requests amount: " + pr.getNumberOfRequests());
    }
}
