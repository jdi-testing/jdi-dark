package com.epam.jdi.httptests.examples.requestparams;


import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class negativeTests extends WithJetty {


    @BeforeClass
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void brokenStringTest(){
        RestResponse response = JettyService.getMultiCookieWithOneName("key`1", "value\1", "value2\n");
        assertThat(response.getStatus().code, equalTo(400));
        assertThat(response.getBody(), containsString("<h1>Bad Message 400</h1><pre>reason: Illegal character"));
    }
}


