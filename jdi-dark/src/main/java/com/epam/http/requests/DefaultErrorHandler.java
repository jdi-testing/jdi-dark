package com.epam.http.requests;

import com.epam.http.response.ResponseStatus;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;

import java.io.IOException;

import static com.epam.http.response.ResponseStatusType.ERROR;
import static com.epam.http.response.ResponseStatusType.SERVER_ERROR;

public class DefaultErrorHandler implements ErrorHandler {
    public DefaultErrorHandler() {
    }

    public boolean hasError(RestResponse restResponse) throws IOException {
        ResponseStatus status = restResponse.getStatus();
        return hasError(status.code);
    }

    private boolean hasError(int code) {
        return ResponseStatusType.getStatusTypeFromCode(code) == ERROR || ResponseStatusType.getStatusTypeFromCode(code) == SERVER_ERROR;
    }

    public void handleError(RestResponse restResponse) throws IOException {
        String message = restResponse.getStatus() + " body: " + restResponse.getBody();
        throw new IOException(message);
    }
}
