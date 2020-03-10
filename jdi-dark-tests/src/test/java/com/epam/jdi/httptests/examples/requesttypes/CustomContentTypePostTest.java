package com.epam.jdi.httptests.examples.requesttypes;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataInfo.requestBody;
import static com.epam.http.requests.ServiceInit.init;

public class CustomContentTypePostTest extends WithJetty {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void
    bodyIsUrlEncodedWhenSettingBody() {
        RestResponse response = JettyService.postGreetXml.call(requestBody("firstName=John&lastName=Doe&"));
        response.assertBody(new Object[][]{
            {"greeting.firstName", IsEqual.equalTo("John")},
            {"greeting.lastName", IsEqual.equalTo("Doe")},
        });
    }
}