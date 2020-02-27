package com.epam.jdi.httptests;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.requests.authhandler.BasicAuthHandler;
import com.epam.http.requests.authhandler.DefaultAuthHandler;
import com.epam.http.requests.authhandler.AuthenticationHandler;
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
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanServiceAuthBasic;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class AuthentificationTests {

    @BeforeClass
    public void before() {
        AuthenticationHandler authHandler = new BasicAuthHandler();
        ((BasicAuthHandler)authHandler).setUsername("loh");
        ((BasicAuthHandler)authHandler).setPassword("pidr");
        init(AuthorizationPostman.class, ServiceSettings.builder().authenticationHandler(authHandler).build());
    }

    @Test
    public void authBaseTest() {
        BasicAuthScheme basic = new BasicAuthScheme();
        basic.setUserName("postman");
        basic.setPassword("password");
        RestResponse resp = callPostmanAuthBasic(basic);
        resp.isOk().assertThat().body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }

//    @Test
//    public void authDigestTest() {
//        RestResponse resp =  callPostmanServiceAuthBasic();
//        resp.isOk().assertThat().body("authenticated", equalTo(true));
//        assertEquals(resp.status.code, HttpStatus.SC_OK);
//}
//
//    @Test
//    public void authCustomTest() {
//        OauthCustomAuthScheme oauthScheme = new OauthCustomAuthScheme();
//        oauthScheme.setOauthConsumerKey(PostmanUserCustomAuthExample.oauthConsumerKey);
//        oauthScheme.setOauthSignatureMethod(PostmanUserCustomAuthExample.oauthSignatureMethod);
//        oauthScheme.setOauthTimestamp(PostmanUserCustomAuthExample.oauthTimestamp);
//        oauthScheme.setOauthNonce(PostmanUserCustomAuthExample.oauthNonce);
//        oauthScheme.setOauthVersion(PostmanUserCustomAuthExample.oauthVersion);
//        oauthScheme.setOauthSignature(PostmanUserCustomAuthExample.oauthSignature);
//        RestResponse resp = callPostmanCustomAuth(oauthScheme);
//        resp.isOk().assertThat().
//                body("status", equalTo("pass"));
//        assertEquals(resp.status.code, HttpStatus.SC_OK);
//    }
}



