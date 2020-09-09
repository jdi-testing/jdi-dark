package com.epam.jdi.httptests.support;

import com.epam.http.logger.ILogger;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import static com.epam.http.logger.HTTPLogger.instance;

public abstract class WithRetryService {
    public static ILogger logger = instance("JDI_WithRetryService");

    protected Process application;

    @BeforeClass
    public void startSpringBootApplication() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", "src/test/resources/jdi-dark-retry-service.jar");
        File logFile = new File(System.getProperty("user.dir") + "\\target\\log.txt");
        builder.redirectOutput(logFile);
        application = builder.start();
        logger.info("Inside startSpringBootApplication(): " + application.getOutputStream().toString());
        String log = "";
        while (application.isAlive() && !log.contains("Started App")) {
            TimeUnit.SECONDS.sleep(1);
            log = FileUtils.readFileToString(logFile, Charset.defaultCharset());
        }
    }

    @AfterClass
    public void stopSpringBootApplication() {
        application.destroy();
    }
}
