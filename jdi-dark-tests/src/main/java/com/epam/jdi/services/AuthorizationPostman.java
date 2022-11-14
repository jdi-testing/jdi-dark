package com.epam.jdi.services;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import io.restassured.authentication.AuthenticationScheme;

import static com.epam.http.requests.RequestDataFactory.auth;
import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://postman-echo.com/")
public class AuthorizationPostman {

    @ContentType(JSON)
    @GET("basic-auth")
    public RestMethod postmanAuthBasic;

    public RestResponse callPostmanServiceAuthBasic() {
        return postmanAuthBasic.call();
    }

    public RestResponse callPostmanAuthBasic(AuthenticationScheme authenticationScheme) {
        return postmanAuthBasic.data(auth(authenticationScheme)).call();
    }

    @ContentType(JSON)
    @GET("oauth1")
    public RestMethod postmanAuthCustom;

    public RestResponse callPostmanServiceCustomAuth() {
        return postmanAuthCustom.call();
    }

    public RestResponse callPostmanCustomAuth(AuthenticationScheme authenticationScheme) {
        return postmanAuthCustom.data(auth(authenticationScheme)).call();
    }

    @ContentType(JSON)
    @GET("digest-auth")
    public RestMethod postmanAuthDigest;

    public RestResponse callPostmanDigestAuth(AuthenticationScheme authenticationScheme) {
        return postmanAuthBasic.data(auth(authenticationScheme)).call();
    }
}
