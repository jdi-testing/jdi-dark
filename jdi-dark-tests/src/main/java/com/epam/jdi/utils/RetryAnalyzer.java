package com.epam.jdi.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.net.ConnectException;

import static com.epam.http.JdiHttpSettings.logger;

public class RetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    int retryLimit = 4;

    @Override
    public boolean retry(ITestResult result) {
        logger.info("Test %s failed %d times with error %s (%s)",
                result.getName(), counter,
                result.getThrowable().getClass().getName(), result.getThrowable().getMessage());
        if ((counter < retryLimit))
        {
            counter++;
            return true;
        }
        return false;
    }
}