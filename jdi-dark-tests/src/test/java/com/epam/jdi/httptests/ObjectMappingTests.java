package com.epam.jdi.httptests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectMappingTests {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void mapResponseToObjectJson() {
        Hello helloObject = JettyService.getHello.asData(Hello.class);
        assertThat(helloObject.getHello(), equalTo("Hello Scalatra"));
    }

    @Test
    public void mapResponseToObjectXml() {
        Greeting greetingObject = JettyService.getGreetXml.asData(Greeting.class);
        assertThat(greetingObject.getFirstName(), equalTo("John"));
        assertThat(greetingObject.getLastName(), equalTo("Doe"));
    }

    @Test
    public void contentTypesEndingWithPlusForJsonObjectMapping() {
        Message messageObject = JettyService.getMimeType.asData(Message.class);
        assertThat(messageObject.getMessage(), equalTo("It works"));
    }
}