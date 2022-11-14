package com.epam.jdi.httptests.examples.requestsretry;

import com.epam.jdi.services.RetryingService;
import com.epam.jdi.httptests.support.WithRetryService;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class RetryTest extends WithRetryService {

    @Test
    public void testDefaultRetrying() {
        getRetryingService().get502.call()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testWithOverrideAttemptsCount() {
        getRetryingService().get503.call()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testWithOverrideStatusCodeAndAttemptCount() {
        getRetryingService().get451.call()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testIgnoreOption() {
        getRetryingService().ignoreRetrying.call()
                .assertThat()
                .statusCode(502);
    }

    @Test
    public void testIgnoreOfUnspecifiedStatuses() {
        getRetryingService().ignoreUnspecifiedStatus.call()
                .assertThat()
                .statusCode(502);
    }

    public RetryingService getRetryingService() {
        return init(RetryingService.class);
    }
}
