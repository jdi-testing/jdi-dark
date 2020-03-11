package com.epam.jdi.httptests;

import com.epam.http.requests.ServiceSettings;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.utils.Defaults.defaultOauthScheme;
import static com.epam.jdi.http.Utils.restResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
        //assertThat(restResponse.get().getStatus(), equalTo("{\"400\":\"200\""));
        assertThat(restResponse.get().getStatus(), equalTo("\"200\""));
    }


}



