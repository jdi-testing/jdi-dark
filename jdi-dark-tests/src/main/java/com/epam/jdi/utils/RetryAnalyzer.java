package com.epam.jdi.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.net.ConnectException;

public class RetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    int retryLimit = 4;

    @Override
    public boolean retry(ITestResult result) {

        if ((counter < retryLimit) && (result.getThrowable() instanceof ConnectException))
        {
            counter++;
            return true;
        }
        return false;
    }
}