package com.epam.http.requests;

import com.epam.http.annotations.RetryOnFailure;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RetryData {

    private Integer numberOfAttempts;
    private List<Integer> errorCodes;

    public RetryData(RetryOnFailure maxRetryAttempts) {
        this.numberOfAttempts = maxRetryAttempts.numberOfAttempts();
        this.errorCodes = Arrays.stream(maxRetryAttempts.errorCodes())
                .boxed()
                .collect(Collectors.toList());
    }

    public Integer getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public List<Integer> getErrorCodes() {
        return errorCodes;
    }
}
