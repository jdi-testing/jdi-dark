package com.epam.jdi.httptests.examples.authorization;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.AuthorizationPostman;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.AuthorizationPostman.callPostmanServiceCustomAuth;
import static com.epam.jdi.httptests.utils.Defaults.defaultOauthScheme;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

/**
 * This test shows the usage of custom
 */
public class CustomAuthenticationTests {

    @BeforeClass
    public void before() {
        init(AuthorizationPostman.class, ServiceSettings.builder().authenticationScheme(defaultOauthScheme()).build());
    }

    @Test
    public void authCustomAuthSchemeTest() {
        RestResponse resp = callPostmanServiceCustomAuth();
        resp.isOk().assertThat().
                body("status", equalTo("pass"));
        assertEquals(resp.status.code, HttpStatus.SC_OK);
    }
}



