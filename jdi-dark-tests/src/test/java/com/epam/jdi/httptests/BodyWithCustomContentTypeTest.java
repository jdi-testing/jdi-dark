package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;

public class BodyWithCustomContentTypeTest extends WithJetty {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void
    bodyIsUrlEncodedWhenSettingBody() {
        RestResponse response = JettyService.postGreetXml.call(requestData(rd ->
                rd.body = "firstName=John&lastName=Doe&"
        ));
        response.assertBody(new Object[][]{
                {"greeting.firstName", IsEqual.equalTo("John")},
                {"greeting.lastName", IsEqual.equalTo("Doe")},
        });
    }
}