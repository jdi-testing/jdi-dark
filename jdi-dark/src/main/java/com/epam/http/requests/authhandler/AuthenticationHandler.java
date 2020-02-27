package com.epam.http.requests.authhandler;

import io.restassured.specification.RequestSpecification;

public interface AuthenticationHandler {
    RequestSpecification handleAuth(RequestSpecification requestSpecification);
}
