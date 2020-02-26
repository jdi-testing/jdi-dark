package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.authRoles.PostmanBasicUser;
import com.epam.jdi.httptests.utils.OauthCustomAuthScheme;
import io.restassured.authentication.BasicAuthScheme;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanAuthBasic;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanCustomAuth;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class AuthTests {

    @BeforeClass
    public void before() {
        init(AuthorizationPostman.class);
    }

    @Test
    public void authBaseTest() {
        BasicAuthScheme postmanUserBasicAuthScheme = new BasicAuthScheme();
        postmanUserBasicAuthScheme.setUserName("postman");
        postmanUserBasicAuthScheme.setPassword("password");
        RestResponse resp = callPostmanAuthBasic(postmanUserBasicAuthScheme);
        resp.isOk().assertThat().
                body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }

    @Test
    public void authCustomTest() {
        OauthCustomAuthScheme oauthScheme = new OauthCustomAuthScheme();
        oauthScheme.setOauth_consumer_key(PostmanBasicUser.oauth_consumer_key);
        oauthScheme.setOauth_signature_method(PostmanBasicUser.oauth_signature_method);
        oauthScheme.setOauth_timestamp(PostmanBasicUser.oauth_timestamp);
        oauthScheme.setOauth_nonce(PostmanBasicUser.oauth_nonce);
        oauthScheme.setOauth_version(PostmanBasicUser.oauth_version);
        oauthScheme.setOauth_signature(PostmanBasicUser.oauth_signature);
        RestResponse resp = callPostmanCustomAuth(oauthScheme);
        resp.isOk().assertThat().
                body("status", equalTo("pass"));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }
}



