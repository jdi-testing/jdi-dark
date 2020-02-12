package com.epam.jdi.httptests;

import com.epam.jdi.httptests.support.WithJetty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectMappingTests extends WithJetty {

    @BeforeTest
    public void before() {

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

    @Test
    public void getResponseAsObject() {
        Product[] products = JettyService.getProducts.asData(Product[].class);
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