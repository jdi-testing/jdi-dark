package com.epam.http.requests.authhandler;

import io.restassured.specification.RequestSpecification;

public class BasicAuthHandler implements AuthenticationHandler{

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RequestSpecification handleAuth(RequestSpecification requestSpecification) {
        return requestSpecification.auth().basic(username, password);
    }


}
