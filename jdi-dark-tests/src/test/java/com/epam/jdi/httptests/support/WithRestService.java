package com.epam.jdi.httptests.support;

import com.epam.jdi.services.RestService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.epam.http.requests.ServiceInit.init;
import static java.lang.Thread.sleep;

public abstract class WithRestService {

    protected static Process webService;
    protected static ExecutorService executorService;

    @BeforeClass(alwaysRun = true)
    public void startSpringBootApplication() throws InterruptedException {
        init(RestService.class);
        File applicationJar = new File("target" + File.separator + "rest-service"
                + File.separator + "jdi-dark-rest-service.jar");
        if (applicationJar.exists()) {
            executorService = Executors.newSingleThreadExecutor();
            executorService.submit(WithRestService::buildProcess);
            waitUntilServiceIsReady();
        } else {
            throw new RuntimeException("Couldn't found jar file to start");
        }
    }

    private static void buildProcess() {
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", "jdi-dark-rest-service.jar");
        builder.directory(new File("target/rest-service"));
        builder.redirectErrorStream(true);
        try {
            webService = builder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(webService.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitUntilServiceIsReady() throws InterruptedException {
        long timeout = 60000;
        long startPoint = System.currentTimeMillis();
        while (!getServiceStatus()) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - startPoint) > timeout) {
                throw new RuntimeException("Timeout waiting for service to start");
            }
            sleep(500);
        }
    }

    private static boolean getServiceStatus() {
        String url = "http://localhost:8081/status";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            return (responseCode == 200);
        } catch (IOException exception) {
            return false;
        }
    }

    @AfterClass
    public void stopSpringBootApplication() {
        System.out.println("Shutting down the service");
        RestService.shutDown.call();
        executorService.shutdown();
    }
}
