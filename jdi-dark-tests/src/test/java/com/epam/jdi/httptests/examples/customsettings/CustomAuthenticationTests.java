package com.epam.jdi.httptests.examples.customsettings;

import com.epam.http.requests.ServiceSettings;
import com.epam.jdi.services.AuthorizationPostman;
import org.testng.annotations.Test;
import com.epam.http.response.RestResponse;
import org.apache.http.HttpStatus;
import static com.epam.jdi.httptests.utils.Defaults.defaultOauthScheme;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;


import static com.epam.http.requests.ServiceInit.init;
/**
 * This test shows the usage of custom
 */
public class CustomAuthenticationTests {

    @Test
    public void authCustomAuthSchemeTest() {
        RestResponse resp = init(AuthorizationPostman.class,
                ServiceSettings.builder().authenticationScheme(defaultOauthScheme()).build())
                .callPostmanServiceCustomAuth();
        resp.isOk().assertThat().
                body("status", equalTo("pass"));
        assertEquals(resp.getStatus().code, HttpStatus.SC_OK);
    }


}



