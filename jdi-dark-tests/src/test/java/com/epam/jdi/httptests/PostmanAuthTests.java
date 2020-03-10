package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.wealdtech.hawk.Hawk;
import com.wealdtech.hawk.HawkCredentials;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.PostmanAuth.authBase;
import static com.epam.jdi.httptests.PostmanAuth.authBaseForm;
import static com.epam.jdi.httptests.PostmanAuth.authDigest;
import static com.epam.jdi.httptests.PostmanAuth.authHawk;
import static com.epam.jdi.httptests.PostmanAuth.oauth;
import static com.wealdtech.hawk.Hawk.calculateMAC;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class PostmanAuthTests {

    @BeforeClass
    public void before() {
        init(PostmanAuth.class);
    }

    @Test
    public void authBaseTest() {
        RestResponse resp = authBase.call();
        resp.isOk().assertThat().
                body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }

    @Test
    public void authPutCredentialsInFormTest() {
        RequestSpecification resp2 = authBaseForm
                .getInitSpec()
                .auth()
                .basic("postman", "password");
        RestResponse resp = authBaseForm.call(resp2);
        resp.isOk().assertThat().
                body("authenticated", equalTo(true));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }

    @Test
    public void entityTest() {
        PostmanInfo info = authBase.callAsData(PostmanInfo.class);
        assertEquals(info.authenticated, "true");
    }

    @Test
    public void authBaseFailTest() {
        RequestSpecification spec = authBaseForm.getInitSpec().header("Authorization", "Basic cG9zdG1hbjpwYXNzd29yBB==");
        RestResponse resp = authBaseForm.call(spec);
        resp.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void authDigestTest() {
        RequestSpecification rs = authDigest.getInitSpec()
                .auth().digest("postman", "password");
        RestResponse resp = authDigest.call(rs);
        resp.isOk().assertThat().
                body("authenticated", equalTo(true));
        resp.assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void authDigestFailTest() {
        RestResponse resp = authDigest.call(rd ->
                rd.addHeaders(new Object[][]{
                        {"Authorization", "Digest username=\"postman\", realm=\"Users\", nonce=\"ni1LiL0O37PRRhofWdCLmwFsnEtH1lew\", uri=\"/digest-auth\", response=\"254679099562cf07df9b6f5d8d15db45\", opaque=\"\""}
                }));
        resp.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void authHawkTest() throws URISyntaxException {
        String key = "werxhqb98rpaxn39848xrunpaw3489ruxnpa98w4rxn";
        String id = "dh37fgj492je";
        URI uri = new URI("https://postman-echo.com/auth/hawk");
        HawkCredentials.Builder hc = new HawkCredentials.Builder();
        HawkCredentials hawkCredentials = hc.key(key).keyId(id).algorithm(HawkCredentials.Algorithm.SHA256).build();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long ts = timestamp.getTime() / 1000;
        String mac = calculateMAC(hawkCredentials, Hawk.AuthType.HEADER, ts, uri, "x9Feni", "GET", null, null, null, null);
        RestResponse resp = authHawk.call(rd ->
                rd.addHeaders(new Object[][]{
                        {"Authorization", "Hawk id=\"dh37fgj492je\", ts=\"" + ts + "\", nonce=\"x9Feni\", mac=\"" + mac + "\""}
                }));
        resp.isOk().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("message", equalTo("Hawk Authentication Successful"));
    }

    @Test
    public void authHawkTimeFailTest() throws URISyntaxException {
        String key = "werxhqb98rpaxn39848xrunpaw3489ruxnpa98w4rxn";
        String id = "dh37fgj492je";
        URI uri = new URI("https://postman-echo.com/auth/hawk");
        HawkCredentials.Builder hc = new HawkCredentials.Builder();
        HawkCredentials hawkCredentials = hc.key(key).keyId(id).algorithm(HawkCredentials.Algorithm.SHA256).build();
        long ts = 1234567890;
        String mac = calculateMAC(hawkCredentials, Hawk.AuthType.HEADER, ts, uri, "x9Feni", "GET", null, null, null, null);
        RestResponse resp = authHawk.call(rd ->
                rd.addHeaders(new Object[][]{
                        {"Authorization", "Hawk id=\"dh37fgj492je\", ts=\"" + ts + "\", nonce=\"x9Feni\", mac=\"" + mac + "\""}
                }));
        resp.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("statusCode", equalTo(401))
                .body("error", equalTo("Unauthorized"))
                .body("message", equalTo("Stale timestamp"));
    }

    @Test
    public void authHawkFailTest() {
        Map<String, Object> body = new LinkedHashMap<>();
        Map<String, Object> attr = new LinkedHashMap<>();
        attr.put("error", "Bad mac");
        body.put("statusCode", 401);
        body.put("error", "Unauthorized");
        body.put("message", "Bad mac");
        body.put("attributes", attr);
        RestResponse resp = authHawk.call(rd ->
                rd.addHeaders(new Object[][]{
                        {"Authorization", "Hawk id=\"dh37fgj492je\", ts=\"ts\", nonce=\"x9Feni\", mac=\"mac\""}
                }));
        resp.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("", equalTo(body));
    }

    @Test
    public void oauthTest() {
        String key = "RKCGzna7bv9YD57c";
        String nonce = "R6MyHe5WCRx";
        RestResponse resp = oauth.call(rd ->
                rd.addHeaders(new Object[][]{
                        {"Authorization", "OAuth oauth_consumer_key=\"" + key + "\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1580379117\", oauth_nonce=\"" + nonce + "\", oauth_version=\"1.0\", oauth_signature=\"hzZRrfQkn4ux9qSbmDJFPKj3P8w%3D\""}
                }));
        resp.isOk().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("status", equalTo("pass"))
                .body("message", equalTo("OAuth-1.0a signature verification was successful"));
    }

    @Test
    public void oauthFailTest() {
        String key = "RKCGzna7bv9YD57";
        String nonce = "R6MyHe5WCRx";
        RestResponse resp = oauth.call(rd ->
                rd.addHeaders(new Object[][]{
                        {"Authorization", "OAuth oauth_consumer_key=\"" + key + "\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1580379117\", oauth_nonce=\"" + nonce + "\", oauth_version=\"1.0\", oauth_signature=\"hzZRrfQkn4ux9qSbmDJFPKj3P8w%3D\""}
                }));
        resp.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("status", equalTo("fail"))
                .body("message", equalTo("HMAC-SHA1 verification failed"))
                .body("base_uri", equalTo("https://postman-echo.com/oauth1"));
    }

}
