package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import io.restassured.authentication.AuthenticationScheme;

import static com.epam.http.requests.RequestDataFacrtory.auth;
import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://postman-echo.com/")
public class AuthorizationPostman {

    @ContentType(JSON)
    @GET("basic-auth")
    public static RestMethod postmanAuthBasic;

    public static RestResponse callPostmanServiceAuthBasic() {
        return postmanAuthBasic.call();
    }

    public static RestResponse callPostmanAuthBasic(AuthenticationScheme authenticationScheme) {
        return postmanAuthBasic.call(auth(authenticationScheme));
    }

    @ContentType(JSON)
    @GET("oauth1")
    public static RestMethod postmanAuthCustom;

    public static RestResponse callPostmanServiceCustomAuth() {
        return postmanAuthCustom.call();
    }

    public static RestResponse callPostmanCustomAuth(AuthenticationScheme authenticationScheme) {
        return postmanAuthCustom.call(auth(authenticationScheme));
    }

    @ContentType(JSON)
    @GET("digest-auth")
    public static RestMethod postmanAuthDigest;

    public static RestResponse callPostmanDigestAuth(AuthenticationScheme authenticationScheme) {
        return postmanAuthCustom.call(auth(authenticationScheme));
    }
}
