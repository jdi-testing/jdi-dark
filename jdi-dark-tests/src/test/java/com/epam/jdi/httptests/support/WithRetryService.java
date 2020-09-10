package com.epam.jdi.httptests.support;

import com.epam.http.logger.ILogger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.epam.http.logger.HTTPLogger.instance;

public abstract class WithRetryService {
    public static ILogger logger = instance("JDI_WithRetryService");

    protected Process application;

    @BeforeClass
    public void startSpringBootApplication() throws IOException {
        final String startRetryServiceCommand = "java -jar src/test/resources/jdi-dark-retry-service.jar";
        application = Runtime.getRuntime().exec(startRetryServiceCommand);
        InputStream in = application.getInputStream();
        BufferedReader reader = new BufferedReader (new InputStreamReader(in));
        String line = reader.readLine();
        final String successfullyStartedApp = "Started App in";
        final String failedAppStart = "Stopping service";
        while (line != null && !(line.trim().contains(successfullyStartedApp) || line.trim().contains(failedAppStart))) {
            logger.debug("Retrying service stdout: " + line);
            line = reader.readLine();
        }
    }

    @AfterClass
    public void stopSpringBootApplication() {
        application.destroy();
    }
}
