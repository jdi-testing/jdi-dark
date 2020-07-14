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

import java.util.Arrays;

import static com.epam.jdi.bookstore.restassured.base.Token.TOKEN;
import static io.restassured.RestAssured.given;
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
    private Book book7;

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
                .body("isbn", equalTo(book1.getIsbn()));
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
                .body("errors[0]", equalTo("ISBN is mandatory"));
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
                .body("errors[0]", equalTo("Price is mandatory"));
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
                .body("errors[0]", equalTo("Quantity is mandatory"));
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
                .body("errors[0]", equalTo("Quantity is mandatory"));
    }

    private void initBooks() {
        book1 = Book.builder()
                .isbn("9781400164240")
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .publicationYear("2009")
                .price("12.34")
                .quantity(1)
                .genres(Arrays.asList(
                        Genre.builder().id(5L).type("Satire").build(),
                        Genre.builder().type("Tragedy").build(),
                        Genre.builder().type("Modernism").build(),
                        Genre.builder().type("Realism").build()
                )).build();

        book2 = Book.builder()
                .isbn("9780140864168")
                .title("Lord of the Flies")
                .author("William Golding")
                .publicationYear("1997")
                .price("14.06")
                .quantity(1)
                .genres(Arrays.asList(
                        Genre.builder().type("Dystopian fiction").build()
                )).build();

        book3 = Book.builder()
                .isbn("9786055532666")
                .title("Neuromancer")
                .author("William Gibson")
                .publicationYear("2016")
                .price("19.71")
                .quantity(1)
                .genres(Arrays.asList(
                        Genre.builder().type("Some unknown type").build()
                )).build();

        book4 = Book.builder()
                .title("Neuromancer")
                .author("William Gibson")
                .publicationYear("2016")
                .price("19.71")
                .quantity(1)
                .genres(Arrays.asList(
                        Genre.builder().id(2L).build()
                )).build();

        book5 = Book.builder()
                .isbn("9786055532666")
                .title("Neuromancer")
                .author("William Gibson")
                .publicationYear("2016")
                .quantity(1)
                .genres(Arrays.asList(
                        Genre.builder().id(2L).build()
                )).build();

        book6 = Book.builder()
                .isbn("9786055532666")
                .title("Neuromancer")
                .author("William Gibson")
                .publicationYear("2016")
                .price("19.71")
                .genres(Arrays.asList(
                        Genre.builder().id(2L).build()
                )).build();

        book7 = Book.builder()
                .isbn("9780393355949")
                .quantity(1)
                .price("24.96")
                .build();
    }
}
