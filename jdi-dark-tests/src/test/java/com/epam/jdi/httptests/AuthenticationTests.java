package com.epam.jdi.httptests;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.requests.authhandler.BasicAuthHandler;
import com.epam.http.requests.authhandler.AuthenticationHandler;
import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.utils.OauthCustomAuthScheme;
import io.restassured.authentication.BasicAuthScheme;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanAuthBasic;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanCustomAuth;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanServiceAuthBasic;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class AuthenticationTests {

    @BeforeClass
    public void before() {
        AuthenticationHandler authHandler = new BasicAuthHandler();
        ((BasicAuthHandler)authHandler).setUsername("postman");
        ((BasicAuthHandler)authHandler).setPassword("password");
        init(AuthorizationPostman.class, ServiceSettings.builder().authenticationHandler(authHandler).build());
    }

    @Test
    public void authBaseTest() {
        RestResponse resp = callPostmanServiceAuthBasic();
        resp.isOk().assertThat().body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }

    @Test
    public void authBaseTestOverrideCredetialsWithCustomAuthScheme() {
        BasicAuthScheme basic = new BasicAuthScheme();
        basic.setUserName("wrongName");
        basic.setPassword("wrongPassword");
        RestResponse resp = callPostmanAuthBasic(basic);
        assertEquals(resp.status.code, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void authDigestTest() {
        RestResponse resp =  callPostmanServiceAuthBasic();
        resp.isOk().assertThat().body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
}

    @Test
    public void authCustomAuthSchemeTest() {
        OauthCustomAuthScheme oauthScheme = new OauthCustomAuthScheme();
        oauthScheme.setOauthConsumerKey("\"RKCGzna7bv9YD57c\"");
        oauthScheme.setOauthSignatureMethod("\"HMAC-SHA1\"");
        oauthScheme.setOauthTimestamp("\"1472121255\"");
        oauthScheme.setOauthNonce("\"e5VR16\"");
        oauthScheme.setOauthVersion("\"1.0\"");
        oauthScheme.setOauthSignature("\"Or%2F2PqPg21wp967CASJtoo%2BF5Kk%3D\"");
        RestResponse resp = callPostmanCustomAuth(oauthScheme);
        resp.isOk().assertThat().
                body("status", equalTo("pass"));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }
}



