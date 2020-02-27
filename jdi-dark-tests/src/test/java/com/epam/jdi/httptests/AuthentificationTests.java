package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.authroles.PostmanUserBasicAuth;
import com.epam.jdi.httptests.authroles.PostmanUserCustomAuthExample;
import com.epam.jdi.httptests.utils.OauthCustomAuthScheme;
import io.restassured.authentication.BasicAuthScheme;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanAuthBasic;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanCustomAuth;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanDigestAuth;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class AuthentificationTests {

    @BeforeClass
    public void before() {
        init(AuthorizationPostman.class);
    }

    @Test
    public void authBaseTest() {
        BasicAuthScheme postmanUserBasicAuthScheme = new BasicAuthScheme();
        postmanUserBasicAuthScheme.setUserName(PostmanUserBasicAuth.username);
        postmanUserBasicAuthScheme.setPassword(PostmanUserBasicAuth.password);
        RestResponse resp = callPostmanAuthBasic(postmanUserBasicAuthScheme);
        resp.isOk().assertThat().body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }

    @Test
    public void authDigestTest() {
        BasicAuthScheme postmanUserBasicAuthScheme = new BasicAuthScheme();
        postmanUserBasicAuthScheme.setUserName(PostmanUserBasicAuth.username);
        postmanUserBasicAuthScheme.setPassword(PostmanUserBasicAuth.password);
        RestResponse resp = callPostmanDigestAuth(postmanUserBasicAuthScheme);
        resp.isOk().assertThat().body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
}

    @Test
    public void authCustomTest() {
        OauthCustomAuthScheme oauthScheme = new OauthCustomAuthScheme();
        oauthScheme.setOauthConsumerKey(PostmanUserCustomAuthExample.oauthConsumerKey);
        oauthScheme.setOauthSignatureMethod(PostmanUserCustomAuthExample.oauthSignatureMethod);
        oauthScheme.setOauthTimestamp(PostmanUserCustomAuthExample.oauthTimestamp);
        oauthScheme.setOauthNonce(PostmanUserCustomAuthExample.oauthNonce);
        oauthScheme.setOauthVersion(PostmanUserCustomAuthExample.oauthVersion);
        oauthScheme.setOauthSignature(PostmanUserCustomAuthExample.oauthSignature);
        RestResponse resp = callPostmanCustomAuth(oauthScheme);
        resp.isOk().assertThat().
                body("status", equalTo("pass"));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }
}



