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
public class CreateBookTests extends BaseTestClass {

    @LocalServerPort
    private int port;

    RequestSpecification requestSpec;

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Book book6;

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
    public void createBook_withValidObject_shouldReturnBookObjectAnd201() {
        given()
            .spec(requestSpec)
            .body(book1)
            .when()
            .post("/books")
            .then()
            .assertThat()
            .statusCode(201)
            .body("isbn", equalTo(book1.isbn));
    }

    @Test
    public void createBook_withExistingRecord_shouldReturn409() {
        createBookPrecondition(requestSpec, book2);
        given()
            .spec(requestSpec)
            .body(book2)
            .when()
            .post("/books")
            .then()
            .assertThat()
            .statusCode(409);
    }

    @Test
    public void createBook_withNonExistingGenre_shouldReturnErrorAnd400() {
        createBookPreconditionNoValidation(requestSpec, book3);
        given()
            .spec(requestSpec)
            .body(book3)
            .when()
            .post("/books")
            .then()
            .assertThat()
            .statusCode(404)
            .body("message", equalTo("Genre with type 'Some unknown type' not found"));
    }

    @Test
    public void createBook_withoutIsbn_shouldReturnErrorAnd400() {
        createBookPreconditionNoValidation(requestSpec, book4);
        given()
            .spec(requestSpec)
            .body(book4)
            .when()
            .post("/books")
            .then()
            .assertThat()
            .statusCode(400)
            .body("errors[0].defaultMessage", equalTo("ISBN is mandatory"));
    }

    @Test
    public void createBook_withoutPrice_shouldReturnErrorAnd400() {
        createBookPreconditionNoValidation(requestSpec, book4);
        given()
            .spec(requestSpec)
            .body(book5)
            .when()
            .post("/books")
            .then()
            .assertThat()
            .statusCode(400)
            .body("errors[0].defaultMessage", equalTo("Price is mandatory"));
    }

    @Test
    public void createBook_withoutQuantity_shouldReturnErrorAnd400() {
        createBookPreconditionNoValidation(requestSpec, book4);
        given()
            .spec(requestSpec)
            .body(book6)
            .when()
            .post("/books")
            .then()
            .assertThat()
            .statusCode(400)
            .body("errors[0].defaultMessage", equalTo("Quantity is mandatory"));
    }

    @Test
    public void createBook_withOnlyMandatoryFields_shouldReturnBookObjectAnd201() {
        createBookPreconditionNoValidation(requestSpec, book4);
        given()
            .spec(requestSpec)
            .body(book6)
            .when()
            .post("/books")
            .then()
            .assertThat()
            .statusCode(400)
            .body("errors[0].defaultMessage", equalTo("Quantity is mandatory"));
    }

    private void initBooks() {
        book1 = new Book().set(b -> {
            b.isbn = "9781400164240";
            b.title = "The Great Gatsby";
            b.author = "F. Scott Fitzgerald";
            b.publicationYear = "2009";
            b.price = "12.34";
            b.quantity = 1;
            b.genres = asList(
                new Genre().set(g -> { g.id = 5L; g.type = "Satire"; }),
                new Genre().set(g -> g.type = "Tragedy"),
                new Genre().set(g -> g.type = "Modernism"),
                new Genre().set(g -> g.type = "Realism")
            );
        });

        book2 = new Book().set(b -> {
            b.isbn = "9780140864168";
            b.title = "Lord of the Flies";
            b.author = "William Golding";
            b.publicationYear = "1997";
            b.price = "14.06";
            b.quantity = 1;
            b.genres = asList(new Genre().set(g -> g.type = "Dystopian fiction"));
        });

        book3 = new Book().set(b -> {
            b.isbn = "9786055532666";
            b.title = "Neuromancer";
            b.author = "William Gibson";
            b.publicationYear = "2016";
            b.price = "19.71";
            b.quantity = 1;
            b.genres = asList(new Genre().set(g -> g.type = "Some unknown type"));
        });

        book4 = new Book().set(b -> {
            b.title = "Neuromancer";
            b.author = "William Gibson";
            b.publicationYear = "2016";
            b.price = "19.71";
            b.quantity = 1;
            b.genres = asList(new Genre().set(g -> g.id = 2L));
        });

        book5 = new Book().set(b -> {
            b.isbn = "9786055532666";
            b.title = "Neuromancer";
            b.author = "William Gibson";
            b.publicationYear = "2016";
            b.quantity = 1;
            b.genres = asList(new Genre().set(g -> g.id = 2L));
        });

        book6 = new Book().set(b -> {
                b.isbn = "9786055532666";
                b.title = "Neuromancer";
                b.author = "William Gibson";
                b.publicationYear = "2016";
                b.price = "19.71";
                b.genres = asList(new Genre().set(g -> g.id = 2L));
        });
    }
}
