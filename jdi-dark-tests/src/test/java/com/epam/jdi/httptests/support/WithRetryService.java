package com.epam.jdi.httptests.support;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public abstract class WithRetryService {

    protected Process application;

    @BeforeClass
    public void startSpringBootApplication() throws IOException {
        application = Runtime.getRuntime().exec("java -Dserver.port=8081 -jar src/test/resources/jdi-dark-retry-service.jar");
    }

    @AfterClass
    public void stopSpringBootApplication() {
        application.destroy();
    }
}
