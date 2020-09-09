package com.epam.jdi.httptests.support;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class WithRetryService {

    protected Process application;

    @BeforeClass
    public void startSpringBootApplication() throws IOException, InterruptedException {
        final String startRetryServiceCommand = "java -jar src/test/resources/jdi-dark-retry-service.jar";
        application = Runtime.getRuntime().exec(startRetryServiceCommand);
        for (int i = 0; i < 5000 && !application.isAlive(); ++i) {
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    @AfterClass
    public void stopSpringBootApplication() {
        application.destroy();
    }
}
