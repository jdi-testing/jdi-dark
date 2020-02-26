package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.utils.OauthCustomAuthScheme;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.authentication.OAuthScheme;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.AuthorizationPostman.postmanAuthBasic;
import static com.epam.jdi.httptests.AuthorizationPostman.postmanAuthCustom;
import static com.epam.jdi.httptests.PostmanAuth.oauth;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class AuthTests {

    @BeforeClass
    public void before() {
        init(AuthorizationPostman.class);
    }

    @Test
    public void authBaseTest() {
        AuthenticationScheme postmanUserBasicAuthScheme = new BasicAuthScheme();
        ((BasicAuthScheme) postmanUserBasicAuthScheme).setUserName("postman");
        ((BasicAuthScheme) postmanUserBasicAuthScheme).setPassword("password");
        RestResponse resp = postmanAuthBasic.call(requestData(requestData -> requestData.setAuth(postmanUserBasicAuthScheme)));
        resp.isOk().assertThat().
                body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }

    @Test
    public void authCustomTest() {
        OauthCustomAuthScheme oauthScheme = new OauthCustomAuthScheme();
        RestResponse resp = postmanAuthCustom.call(requestData(requestData -> requestData.setAuth(oauthScheme)));
        resp.isOk().assertThat().
                body("OAuth-1.0a signature verification was successful", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }
//
//    @Test
//    public void oauthTest() {
//        String key = "RKCGzna7bv9YD57c";
//        String nonce = "R6MyHe5WCRx";
//        RestResponse resp = oauth.call(requestData(rd ->
//                rd.addHeaders(new Object[][]{
//                        {"Authorization", "OAuth oauth_consumer_key=\"" + key + "\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1580379117\", oauth_nonce=\"" + nonce + "\", oauth_version=\"1.0\", oauth_signature=\"hzZRrfQkn4ux9qSbmDJFPKj3P8w%3D\""}
//                })));
//        resp.isOk().assertThat()
//                .statusCode(HttpStatus.SC_OK)
//                .body("status", equalTo("pass"))
//                .body("message", equalTo("OAuth-1.0a signature verification was successful"));
//    }


}



