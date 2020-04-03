package com.epam.jdi.httptests.examples.custom;

import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.Matchers.*;

/**
 * This class is using for advanced validation cases for JettyService
 * Tests are similar to rest assured cases
 */
public class AdvancedValidationTests extends WithJetty {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void groceriesContainsChocolateAndCoffee() {
        JettyService.getShopping.call().isOk().assertThat()
            .body("shopping.category.find { it.@type == 'groceries' }", hasItems("Chocolate", "Coffee"));
    }

    @Test
    public void groceriesContainsChocolateAndCoffeeUsingDoubleStarNotation() {
        JettyService.getShopping.call().isOk().assertThat()
            .body("**.find { it.@type == 'groceries' }", hasItems("Chocolate", "Coffee"));
    }

    @Test
    public void advancedJsonValidation() {
        JettyService.getJsonStore.call().isOk().assertThat()
            .statusCode(allOf(greaterThanOrEqualTo(200), lessThanOrEqualTo(300))).
                rootPath("store.book").
                body("findAll { book -> book.price < 10 }.title", hasItems("Sayings of the Century", "Moby Dick")).
                body("author.collect { it.length() }.sum()", equalTo(53));
    }

    @Test
    public void advancedJsonValidation2() {
        JettyService.getJsonStore.call().isOk().assertThat()
            .statusCode(allOf(greaterThanOrEqualTo(200), lessThanOrEqualTo(300))).
                rootPath("store.book").
                body("findAll { book -> book.price < 10 }.title", hasItems("Sayings of the Century", "Moby Dick")).
                body("price.min()", equalTo(8.95f)).
                body("price.max()", equalTo(22.99f)).
                body("min { it.price }.title", equalTo("Sayings of the Century")).
                body("author*.length().sum()", equalTo(53)).
                body("author*.length().sum(2, { it * 2 })", is(108));
    }

    @Test
    public void products() {
        JettyService.getProducts.call().isOk().assertThat()
            .body("price.sum()", is(38.0d))
            .body("dimensions.width.min()", is(1.0f))
            .body("name.collect { it.length() }.max()", is(16))
            .body("dimensions.multiply(2).height.sum()", is(21.0));
    }
}
