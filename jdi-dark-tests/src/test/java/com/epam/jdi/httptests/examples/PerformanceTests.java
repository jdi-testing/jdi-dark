package com.epam.jdi.httptests.examples;

import com.epam.http.performance.PerformanceResult;
import com.epam.http.performance.RestLoad;
import com.epam.jdi.httptests.ServiceExample;
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
    public void printTest() {
        init(ServiceExample.class);
        PerformanceResult pr = RestLoad.loadService(5, ServiceExample.getInfo);
        Assert.assertTrue(pr.noFails(), "Number of fails: " + pr.NumberOfFails);
        System.out.println("Average time: " + pr.AverageResponseTime + "ms");
        System.out.println("Requests amount: " + pr.NumberOfRequests);
    }
}
