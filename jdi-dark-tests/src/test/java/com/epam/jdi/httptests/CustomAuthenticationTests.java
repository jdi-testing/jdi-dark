package com.epam.jdi.httptests;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.utils.OauthCustomAuthScheme;
import io.restassured.authentication.AuthenticationScheme;
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
        OauthCustomAuthScheme authScheme = new OauthCustomAuthScheme();
        authScheme.setOauthConsumerKey("\"RKCGzna7bv9YD57c\"");
        authScheme.setOauthSignatureMethod("\"HMAC-SHA1\"");
        authScheme.setOauthTimestamp("\"1472121255\"");
        authScheme.setOauthNonce("\"e5VR16\"");
        authScheme.setOauthVersion("\"1.0\"");
        authScheme.setOauthSignature("\"Or%2F2PqPg21wp967CASJtoo%2BF5Kk%3D\"");
        init(AuthorizationPostman.class, ServiceSettings.builder().authenticationScheme(authScheme).build());
    }

    @Test
    public void authCustomAuthSchemeTest() {
        RestResponse resp = callPostmanServiceCustomAuth();
        resp.isOk().assertThat().
                body("status", equalTo("pass"));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }
}



