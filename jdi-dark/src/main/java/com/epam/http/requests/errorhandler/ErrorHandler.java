package com.epam.http.requests.errorhandler;

import com.epam.http.response.RestResponse;

public interface ErrorHandler {
    boolean hasError(RestResponse restResponse);

    void handleError(RestResponse restResponse);
}