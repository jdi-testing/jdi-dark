package com.epam.http.requests.errorhandler;

import com.epam.http.response.ResponseStatus;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;

import static com.epam.http.response.ResponseStatusType.ERROR;
import static com.epam.http.response.ResponseStatusType.SERVER_ERROR;

public class DefaultErrorHandler implements ErrorHandler {
    public DefaultErrorHandler() {
    }

    public boolean hasError(RestResponse restResponse) {
        ResponseStatus status = restResponse.getStatus();
        return hasError(status.code);
    }

    private boolean hasError(int code) {
        return ResponseStatusType.getStatusTypeFromCode(code) == ERROR || ResponseStatusType.getStatusTypeFromCode(code) == SERVER_ERROR;
    }

    public void handleError(RestResponse restResponse) {
        //do nothing
    }
}
