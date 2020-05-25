package com.epam.jdi.httptests.examples;

import com.epam.http.performance.PerformanceResult;
import com.epam.http.performance.RestLoad;
import com.epam.jdi.services.ServiceExample;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThat(pr.noFails()).describedAs("Fails found").isTrue();
    }

    @Test
    public void concurrentTest() throws InterruptedException, ExecutionException {
        PerformanceResult pr = RestLoad.loadService(5, 10, ServiceExample.getInfo);
        Assertions.assertThat(pr.getNumberOfFails()).describedAs("Fails found").isEqualTo(0);
        Assertions.assertThat(pr.getAverageResponseTime()).describedAs("Wrong average response time").isGreaterThan(1);
        Assertions.assertThat(pr.getMaxResponseTime()).describedAs("Wrong maximum response time").isGreaterThan(3);
    }
}
