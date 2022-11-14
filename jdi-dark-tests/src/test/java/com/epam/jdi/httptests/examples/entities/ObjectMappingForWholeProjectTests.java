package com.epam.jdi.httptests.examples.entities;

import com.epam.jdi.dto.Greeting;
import com.epam.jdi.dto.Hello;
import com.epam.jdi.dto.Message;
import com.epam.jdi.dto.Product;
import com.epam.jdi.httptests.support.WithJetty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectMappingForWholeProjectTests extends WithJetty {

    @BeforeClass
    public void preconditions() {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                new Jackson2ObjectMapperFactory() {
                    @Override
                    public ObjectMapper create(Type type, String s) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        return objectMapper;
                    }
                }
        ));
    }

    @Test(priority = 0)
    public void mapResponseToObjectXml() {
        Greeting greetingObject = getJettyService().getGreetXml.callAsData(Greeting.class);
        assertThat(greetingObject.firstName, equalTo("John"));
        assertThat(greetingObject.lastName, equalTo("Doe"));
    }

    @Test
    public void contentTypesEndingWithPlusForJsonObjectMapping() {
        Message messageObject = getJettyService().getMimeType.callAsData(Message.class);
        assertThat(messageObject.getMessage(), equalTo("It works"));
    }

    @Test
    public void mapResponseToObjectJson() {
        Product[] products = getJettyService().getProducts.callAsData(Product[].class);
        Assert.assertEquals(products.length, 2, "Number of products is incorrect");
    }

    @Test
    public void sendObjectToRequest() {
        final Hello object = new Hello();
        object.hello = "Hello world";
        new RequestSpecBuilder().build().body(object);
        Hello response = getJettyService().postObject.post(object, Hello.class);
        Assert.assertEquals(response.hello, "Hello world", "Response is incorrect");
    }
}