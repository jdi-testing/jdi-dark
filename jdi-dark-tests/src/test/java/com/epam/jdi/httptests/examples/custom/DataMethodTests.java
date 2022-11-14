package com.epam.jdi.httptests.examples.custom;

import com.epam.jdi.dto.GreetingItself;
import com.epam.jdi.dto.Hello;
import com.epam.jdi.dto.User;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.authentication.BasicAuthScheme;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataFactory.auth;
import static org.testng.Assert.assertEquals;

public class DataMethodTests extends WithJetty {

    @Test
    public void queryParamsReturnsGreetingObject() {
        GreetingItself greetingItself = getJettyService().getGreetingItselfObject.queryParams("firstName=Davy&lastName=Jones").callAsData();
        assertEquals(greetingItself.greeting, "Greetings Davy Jones", "Greeting is not as expected");
    }

    @Test
    public void pathParamsReturnsUserObject() {
        User user = getJettyService().getUserObject.pathParams("Davy", "Jones").callAsData();
        assertEquals(user.fullName, "Davy Jones", "The fullName is not as expected");
    }

    @Test
    public void basicAuthReturnsHelloObject() {
        BasicAuthScheme basic = new BasicAuthScheme();
        basic.setUserName("jetty");
        basic.setPassword("jetty");
        Hello hello = getJettyService().getSecuredHelloObject.data(auth(basic)).callAsData();
        assertEquals(hello.hello, "Hello Secured Scalatra");
    }
}
