package com.epam.jdi.httptests.examples.requestparams;


import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataFactory.cookies;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NegativeTests extends WithJetty {

    @Test(enabled = false)
    // not an invalid char anymore
    public void brokenStringTest(){
        RestResponse response = getJettyService().getMultiCookieRequest.call(
                cookies().add("key`1", "value\1", "value2\n")
        );
        assertThat(response.getStatus().code, equalTo(400));
        assertThat(response.getBody(), containsString("<h1>Bad Message 400</h1><pre>reason: Illegal character"));
    }
}


