package com.epam.jdi.httptests;

import com.epam.jdi.httptests.support.WithJetty;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectMappingForWholeProjectTests extends WithJetty {

    @BeforeClass
    public void before() {
        init(JettyService.class);
    }

    @Test(priority = 0)
    public void mapResponseToObjectXml() {
        Greeting greetingObject = JettyService.getGreetXml.callAsData(Greeting.class);
        assertThat(greetingObject.getFirstName(), equalTo("John"));
        assertThat(greetingObject.getLastName(), equalTo("Doe"));
    }

    @Test
    public void contentTypesEndingWithPlusForJsonObjectMapping() {
        Message messageObject = JettyService.getMimeType.callAsData(Message.class);
        assertThat(messageObject.getMessage(), equalTo("It works"));
    }

    @Test
    public void mapResponseToObjectJson() {
        Product[] products = JettyService.getProducts.callAsData(Product[].class);
        Assert.assertEquals(products.length, 2, "Number of products is incorrect");
    }

    @Test
    public void sendObjectToRequest() {
        final Hello object = new Hello();
        object.setHello("Hello world");
        Hello response = JettyService.postObject.post(object, Hello.class);
        Assert.assertEquals(response.getHello(), "Hello world", "Response is incorrect");
    }
}