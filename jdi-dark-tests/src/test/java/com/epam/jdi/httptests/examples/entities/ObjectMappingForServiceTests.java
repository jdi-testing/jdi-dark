package com.epam.jdi.httptests.examples.entities;

import com.epam.http.requests.ServiceSettings;
import com.epam.jdi.dto.Hello;
import com.epam.jdi.dto.Message;
import com.epam.jdi.dto.Product;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.services.JettyService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectMappingForServiceTests extends WithJetty {

    private ObjectMapper objectMapper;

    @BeforeClass
    public void before() {
        objectMapper = new Jackson2Mapper((type, s) -> {
            com.fasterxml.jackson.databind.ObjectMapper objectMapper1 = new com.fasterxml.jackson.databind.ObjectMapper();
            objectMapper1.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper1;
        });
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
    public void mapResponseToObjectAsList() {
        List<Product> products = getJettyService().getProductsAsList.callAsData();
        Assert.assertEquals(products.get(1).name, "A blue mouse", "Name of product is incorrect");
    }

    @Test
    public void mapResponseToObjectAsArray() {
        Product[] products = getJettyService().getProductsAsArray.callAsData();
        Assert.assertEquals(products[1].name, "A blue mouse", "Name of product is incorrect");
    }

    @Test
    public void sendObjectToRequest() {
        final Hello object = new Hello();
        object.hello = "Hello world";
        Hello response = getJettyService().postObject.post(object, Hello.class);
        Assert.assertEquals(response.hello, "Hello world", "Response is incorrect");
    }

    protected JettyService getJettyService() {
        return init(JettyService.class, ServiceSettings.builder().objectMapper(objectMapper).build());
    }
}