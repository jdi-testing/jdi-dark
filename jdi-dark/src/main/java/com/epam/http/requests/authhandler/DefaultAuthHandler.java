package com.epam.http.requests.authhandler;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.NoAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class DefaultAuthHandler implements AuthenticationHandler {
    private AuthenticationScheme authScheme;

    public DefaultAuthHandler() {
        this.authScheme = new NoAuthScheme();
    }

    public DefaultAuthHandler(AuthenticationScheme authenticationScheme) {
        this.authScheme = authenticationScheme;
    }

//    public void setAuthenticationScheme(AuthenticationScheme authenticationScheme) {
//        this.authScheme = authenticationScheme;
//    }

    @Override
    public RequestSpecification handleAuth(RequestSpecification requestSpecification) {
        RequestSpecification spec = new RequestSpecBuilder().setAuth(this.authScheme).build();
        RequestSpecification result = requestSpecification.spec(spec);
        return result;
//        return spec.spec(requestSpecification);
    }
}
