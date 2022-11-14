package com.epam.jdi.httptests.performance;

import com.epam.http.performance.PerformanceResult;
import com.epam.http.performance.RestLoad;
import com.epam.jdi.services.ServiceExample;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class PerformanceTests {

    @Test
    public void isAliveTest() {
        getServiceExample().getInfo.isAlive();
    }

    @Test
    public void printTest() throws InterruptedException {
        PerformanceResult pr = RestLoad.loadService(5, getServiceExample().getInfo);
        Assertions.assertThat(pr.noFails()).describedAs("Fails found").isTrue();
    }

    @Test
    public void concurrentTest() throws InterruptedException {
        PerformanceResult pr = RestLoad.loadService(3, 5, getServiceExample().getInfo);
        Assertions.assertThat(pr.getNumberOfFails()).describedAs("Fails found").isEqualTo(0);
        Assertions.assertThat(pr.getAverageResponseTime())
                .describedAs("The average response time is greater than expected.").isLessThan(50000);
        Assertions.assertThat(pr.getMaxResponseTime())
                .describedAs("The maximum response time is greater than expected.").isLessThan(3000);
    }

    public ServiceExample getServiceExample() {
        return init(ServiceExample.class);
    }
}
