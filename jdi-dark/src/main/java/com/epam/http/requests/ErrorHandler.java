package com.epam.http.requests;

import com.epam.http.response.RestResponse;

import java.io.IOException;

public interface ErrorHandler {
    boolean hasError(RestResponse restResponse) throws IOException;

    void handleError(RestResponse restResponse) throws IOException;
}