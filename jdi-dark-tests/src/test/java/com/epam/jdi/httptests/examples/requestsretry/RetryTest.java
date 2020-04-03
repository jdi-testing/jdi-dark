package com.epam.jdi.httptests.examples.requestsretry;

import com.epam.jdi.services.RetryingService;
import com.epam.jdi.httptests.support.WithRetryService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class RetryTest extends WithRetryService {

    @BeforeClass
    public void serviceInit() {
        init(RetryingService.class);
    }

    @Test
    public void testDefaultRetrying() {
        RetryingService.get502.call()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testWithOverrideAttemptsCount() {
        RetryingService.get503.call()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testWithOverrideStatusCodeAndAttemptCount() {
        RetryingService.get451.call()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testIgnoreOption() {
        RetryingService.ignoreRetrying.call()
                .assertThat()
                .statusCode(502);
    }

    @Test
    public void testIgnoreOfUnspecifiedStatuses() {
        RetryingService.ignoreUnspecifiedStatus.call()
                .assertThat()
                .statusCode(502);
    }
}
