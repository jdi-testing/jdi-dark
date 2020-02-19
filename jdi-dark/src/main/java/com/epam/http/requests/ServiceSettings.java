package com.epam.http.requests;

import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;

public class ServiceSettings {

    private final RequestSpecification requestSpecification;
    private final ObjectMapper objectMapper;
    private final ErrorHandler errorHandler;

    private ServiceSettings(RequestSpecification requestSpecification, ObjectMapper objectMapper, ErrorHandler errorHandler) {
        this.requestSpecification = requestSpecification;
        this.objectMapper = objectMapper;
        this.errorHandler = errorHandler;
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

    public static Builder builder() {
        return new ServiceSettings.Builder();
    }

    public static class Builder {

        private RequestSpecification requestSpecification;
        private ObjectMapper objectMapper;
        private ErrorHandler errorHandler;

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

        public ServiceSettings build() {
            return new ServiceSettings(requestSpecification, objectMapper, errorHandler);
        }
    }
}
