package com.epam.jdi.httptests.examples.custom;

import com.epam.jdi.dto.GreetingItself;
import com.epam.jdi.dto.Hello;
import com.epam.jdi.dto.User;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.services.JettyService;
import io.restassured.authentication.BasicAuthScheme;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataFactory.auth;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.JettyService.getGreetingItselfObject;
import static com.epam.jdi.services.JettyService.getUserObject;
import static com.epam.jdi.services.JettyService.getSecuredHelloObject;
import static org.testng.Assert.assertEquals;

public class DataMethodTests extends WithJetty {

    @BeforeClass
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void queryParamsReturnsGreetingObject() {
        GreetingItself greetingItself = getGreetingItselfObject.queryParams("firstName=Davy&lastName=Jones").callAsData();
        assertEquals(greetingItself.greeting, "Greetings Davy Jones", "Greeting is not as expected");
    }

    @Test
    public void pathParamsReturnsUserObject() {
        User user = getUserObject.pathParams("Davy", "Jones").callAsData();
        assertEquals(user.fullName, "Davy Jones", "The fullName is not as expected");
    }

    @Test
    public void basicAuthReturnsHelloObject() {
        BasicAuthScheme basic = new BasicAuthScheme();
        basic.setUserName("jetty");
        basic.setPassword("jetty");
        Hello hello = getSecuredHelloObject.data(auth(basic)).callAsData();
        assertEquals(hello.hello, "Hello Secured Scalatra");
    }
}
