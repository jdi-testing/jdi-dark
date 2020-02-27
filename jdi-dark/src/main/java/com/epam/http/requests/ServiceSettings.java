package com.epam.http.requests;

import com.epam.http.requests.authhandler.AuthenticationHandler;
import com.epam.http.requests.errorhandler.ErrorHandler;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;

public class ServiceSettings {

    private final RequestSpecification requestSpecification;
    private final ObjectMapper objectMapper;
    private final ErrorHandler errorHandler;
    private final AuthenticationHandler authenticationHandler;

    private ServiceSettings(RequestSpecification requestSpecification, ObjectMapper objectMapper, ErrorHandler errorHandler, AuthenticationHandler authenticationHandler) {
        this.requestSpecification = requestSpecification;
        this.objectMapper = objectMapper;
        this.errorHandler = errorHandler;
        this.authenticationHandler = authenticationHandler;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public AuthenticationHandler getAuthenticationHandler() { return authenticationHandler; }

    public static Builder builder() {
        return new ServiceSettings.Builder();
    }

    public static class Builder {

        private RequestSpecification requestSpecification;
        private ObjectMapper objectMapper;
        private ErrorHandler errorHandler;
        private AuthenticationHandler authenticationHandler;

        public Builder requestSpecification(RequestSpecification requestSpecification) {
            this.requestSpecification = requestSpecification;
            return this;
        }

        public Builder objectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public Builder errorHandler(ErrorHandler errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        public Builder authenticationHandler(AuthenticationHandler authenticationHandler) {
            this.authenticationHandler = authenticationHandler;
            return this;
        }

        public ServiceSettings build() {
            return new ServiceSettings(requestSpecification, objectMapper, errorHandler, authenticationHandler);
        }
    }
}
