package com.epam.jdi.httptests.performance;

import com.epam.http.performance.PerformanceResult;
import com.epam.http.performance.RestLoad;
import com.epam.jdi.services.ServiceExample;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class PerformanceTests {

    @BeforeClass
    public void before() {
        init(ServiceExample.class);
    }

    @Test
    public void isAliveTest() {
        ServiceExample.getInfo.isAlive();
    }

    @Test
    public void printTest() throws InterruptedException {
        PerformanceResult pr = RestLoad.loadService(5, ServiceExample.getInfo);
        Assertions.assertThat(pr.noFails()).describedAs("Fails found").isTrue();
    }

    @Test
    public void concurrentTest() throws InterruptedException {
        PerformanceResult pr = RestLoad.loadService(3, 5, ServiceExample.getInfo);
        Assertions.assertThat(pr.numberOfFails).describedAs("Fails found").isEqualTo(0);
        Assertions.assertThat(pr.averageResponseTime).describedAs("The average response time is greater than expected.").isLessThan(2000);
        Assertions.assertThat(pr.minResponseTime).describedAs("The maximum response time is greater than expected.").isLessThan(3000);
    }
}
