package com.epam.jdi.httptests;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.requests.authhandler.AuthenticationHandler;
import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.utils.CustomAuthHandler;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanServiceCustomAuth;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

/**
 * This test shows the usage of custom
 */
public class CustomAuthenticationTests {

    @BeforeClass
    public void before() {
        AuthenticationHandler authHandler = new CustomAuthHandler();
        ((CustomAuthHandler)authHandler).setOauthConsumerKey("\"RKCGzna7bv9YD57c\"");
        ((CustomAuthHandler)authHandler).setOauthSignatureMethod("\"HMAC-SHA1\"");
        ((CustomAuthHandler)authHandler).setOauthTimestamp("\"1472121255\"");
        ((CustomAuthHandler)authHandler).setOauthNonce("\"e5VR16\"");
        ((CustomAuthHandler)authHandler).setOauthVersion("\"1.0\"");
        ((CustomAuthHandler)authHandler).setOauthSignature("\"Or%2F2PqPg21wp967CASJtoo%2BF5Kk%3D\"");
        init(AuthorizationPostman.class, ServiceSettings.builder().authenticationHandler(authHandler).build());
    }

    @Test
    public void authCustomAuthSchemeTest() {
        RestResponse resp = callPostmanServiceCustomAuth();
        resp.isOk().assertThat().
                body("status", equalTo("pass"));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }
}



