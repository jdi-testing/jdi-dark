package com.epam.jdi.bookstore.restassured.bookapi;

import com.epam.jdi.bookstore.model.Book;
import com.epam.jdi.bookstore.model.Genre;
import com.epam.jdi.bookstore.restassured.base.BaseTestClass;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.epam.jdi.bookstore.restassured.base.Token.TOKEN;
import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateBookTests extends BaseTestClass {

    @LocalServerPort
    private int port;

    RequestSpecification requestSpec;

    private Book book1;

    @Before
    public void setUp() {
        initBooks();
        PreemptiveOAuth2HeaderScheme oAuth2Scheme = new PreemptiveOAuth2HeaderScheme();
        oAuth2Scheme.setAccessToken(TOKEN);
        requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(port)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .setAuth(oAuth2Scheme)
            .log(LogDetail.ALL)
            .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void updateBook_withValidObject_shouldReturn200() {
        int id = createBookPrecondition(requestSpec, book1);
        book1.genres = asList(
            new Genre().set(g -> g.type = "Science fiction"),
            new Genre().set(g -> g.type = "Dystopian fiction")
        );
        given()
            .spec(requestSpec)
            .body(book1)
            .when()
            .put("/books/" + id)
            .then()
            .assertThat()
            .statusCode(200);

        given()
            .spec(requestSpec)
            .when()
            .get("/books/" + id)
            .then()
            .assertThat()
            .statusCode(200)
            .body("id", equalTo(id))
            .body("genres[0].type", equalTo("Science fiction"))
            .body("genres[1].type", equalTo("Dystopian fiction"));
    }

    private void initBooks() {
        book1 = new Book().set(b -> {
            b.isbn = "9780451524935";
            b.title = "1984";
            b.author = "George Orwell";
            b.publicationYear = "1961";
            b.price = "6.82";
            b.quantity = 2;
            b.genres = asList(new Genre().set(g -> g.type = "Science fiction"));
        });

    }
}
