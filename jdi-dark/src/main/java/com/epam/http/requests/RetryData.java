package com.epam.http.requests;

import com.epam.http.ExceptionHandler;
import com.epam.http.annotations.RetryOnFailure;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RetryData {

    private Integer numberOfRetryAttempts;
    private List<Integer> errorCodes;
    private Long delay;
    private TimeUnit unit;

    public RetryData(RetryOnFailure retryData) {
        this.numberOfRetryAttempts = retryData.numberOfRetryAttempts();
        this.errorCodes = intArrayToList(retryData);
        this.delay = retryData.delay();
        this.unit = retryData.unit();
    }

    public RetryData merge(RetryOnFailure retryData) {
        this.numberOfRetryAttempts = retryData.numberOfRetryAttempts() == (int) getDefaultValueOf("numberOfAttempts") ?
                numberOfRetryAttempts : retryData.numberOfRetryAttempts();
        this.errorCodes = retryData.errorCodes() == getDefaultValueOf("errorCodes") ?
                errorCodes : intArrayToList(retryData);
        this.unit = retryData.unit() == getDefaultValueOf("unit") ? unit : retryData.unit();
        this.delay = retryData.delay() == (long) getDefaultValueOf("delay") ? delay : retryData.delay();
        return this;
    }

    public Integer getNumberOfRetryAttempts() {
        return numberOfRetryAttempts;
    }

    public List<Integer> getErrorCodes() {
        return errorCodes;
    }

    public Long getDelay() {
        return delay;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    private Object getDefaultValueOf(String methodName) {
        try {
            return RetryOnFailure.class.getMethod(methodName).getDefaultValue();
        } catch (NoSuchMethodException e) {
            throw ExceptionHandler.exception("@RetryOnFail doesn't have field " + methodName);
        }
    }

    private List<Integer> intArrayToList(RetryOnFailure retryData) {
        return Arrays.stream(retryData.errorCodes())
                .boxed()
                .collect(Collectors.toList());
    }
}
