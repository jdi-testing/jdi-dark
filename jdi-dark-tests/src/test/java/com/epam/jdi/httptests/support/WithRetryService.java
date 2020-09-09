package com.epam.jdi.httptests.support;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public abstract class WithRetryService {

    protected Process application;

    @BeforeClass
    public void startSpringBootApplication() throws IOException {
        final String startRetryServiceCommand = "java -jar src/test/resources/jdi-dark-retry-service.jar";
        application = Runtime.getRuntime().exec(startRetryServiceCommand);
        if (!application.isAlive()) {
            application = Runtime.getRuntime().exec(startRetryServiceCommand);
        }
    }

    @AfterClass
    public void stopSpringBootApplication() {
        application.destroy();
    }
}
