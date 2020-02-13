package com.epam.jdi.httptests;

import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectMappingTests extends BaseServiceTest {

    private ObjectMapper objectMapper;

    @BeforeClass
    public void before() {
        init(JettyService.class);
        objectMapper = new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public com.fasterxml.jackson.databind.ObjectMapper create(Type type, String s) {
                com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
                return objectMapper;
            }
        });
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

    @Test
    public void mapResponseToObjectJson() {
        Product[] products = JettyService.getProducts.asData(Product[].class);
        Assert.assertEquals(products.length, 2, "Number of products is incorrect");
    }

    @Test
    public void sendObjectToRequest() {
        final Hello object = new Hello();
        object.setHello("Hello world");
        Hello response = JettyService.postObject.post(object, Hello.class, objectMapper);
        Assert.assertEquals(response.getHello(), "Hello world", "Response is incorrect");
    }
}