package com.epam.jdi.httptests.examples.customsettings;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.services.AuthorizationPostman;
import io.restassured.authentication.BasicAuthScheme;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.AuthorizationPostman.*;
import static com.epam.jdi.httptests.utils.Defaults.defaultOauthScheme;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class AuthenticationTests {

/**
 * In @BeforeClass we can setup our Authentication Scheme.
 * This scheme should be passed to Service Settings to affect the whole service.
 */

    @BeforeClass
    public void before() {
        BasicAuthScheme authScheme = new BasicAuthScheme();
        authScheme.setUserName("postman");
        authScheme.setPassword("password");
        init(AuthorizationPostman.class, ServiceSettings.builder().authenticationScheme(authScheme).build());
    }

    /**
     * This test represents basic authorization using service settings.
     */
    @Test
    public void authBaseTest() {
        RestResponse resp = callPostmanServiceAuthBasic();
        resp.isOk().assertThat().body("authenticated", equalTo(true));
        assertEquals(resp.getStatus().code, HttpStatus.SC_OK);

    }

    /**
     *  In this test we override service setting using our custom Authentication Scheme.
     *  Such scheme must implement AuthenticationScheme interface.
     *  It will basically populate request with required data.
     */
    @Test
    public void authBaseTestOverrideCredentialsWithCustomAuthScheme() {
        BasicAuthScheme basic = new BasicAuthScheme();
        basic.setUserName("wrongName");
        basic.setPassword("wrongPassword");
        RestResponse resp = callPostmanAuthBasic(basic);
        assertEquals(resp.getStatus().code, HttpStatus.SC_UNAUTHORIZED);
    }

    /**
     * This test shows digest authorization in work
     */
    @Test
    public void authDigestTest() {
        RestResponse resp =  callPostmanServiceAuthBasic();
        resp.isOk().assertThat().body("authenticated", equalTo(true));
        assertEquals(resp.getStatus().code, HttpStatus.SC_OK);
    }

    /**
     * In this test we are overriding service settings to pass custom Authentication Scheme.
     */

    @Test
    public void authCustomAuthSchemeTest() {
        RestResponse resp = callPostmanCustomAuth(defaultOauthScheme());
        resp.isOk().assertThat().
            body("status", equalTo("pass"));
        assertEquals(resp.getStatus().code, HttpStatus.SC_OK);
    }
}



