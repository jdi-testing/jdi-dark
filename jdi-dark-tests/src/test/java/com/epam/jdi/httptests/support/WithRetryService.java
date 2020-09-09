package com.epam.jdi.httptests.support;

import com.epam.http.logger.ILogger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.epam.http.logger.HTTPLogger.instance;

public abstract class WithRetryService {
    public static ILogger logger = instance("JDI_WithRetryService");

    protected Process application;

    @BeforeClass
    public void startSpringBootApplication() throws IOException, InterruptedException {
        final String startRetryServiceCommand = "java -jar src/test/resources/jdi-dark-retry-service.jar";
        application = Runtime.getRuntime().exec(startRetryServiceCommand);
        logger.info("Inside startSpringBootApplication(): " + application.getOutputStream().toString());
        TimeUnit.SECONDS.sleep(10);
        for (int i = 0; i < 60 && !application.isAlive(); ++i) {
            TimeUnit.SECONDS.sleep(10);
            logger.info("Waiting for Retry Service to start: " + i);
        }
    }

    @AfterClass
    public void stopSpringBootApplication() {
        application.destroy();
    }
}
