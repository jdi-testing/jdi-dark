package com.epam.jdi.httptests.examples.custom;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.TrelloService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataFactory.pathParams;
import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * This class is using for error handling cases for TrelloAPI
 */
public class ErrorMessageTrelloTests {

    public static final String INVALID_BOARD_ID = "456";
    public static final String NON_EXISTENT_BOARD_ID = "5a27e3b62fef5d3a74dca59a";
    public static final String CARD_UNIQUE_ID = "5a27e722e2f04f3ab6924931";
    public static final String NON_EXISTENT_CARD_ID = "5a27e722e2f04f3ab6924991";
    public static final int NOT_FOUND_CODE = 404;
    public static final int ERROR_CODE = 400;

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void getBoardByNotExistsId() {
        RestResponse response = getTrelloService().getBoardById.call(pathParams().add("board_id", NON_EXISTENT_BOARD_ID));
        response.hasErrors()
                .statusCode(NOT_FOUND_CODE);
        assertEquals(response.getBody(), "The requested resource was not found.");
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void getBoardByInvalidId() {
        RestResponse response = getTrelloService().getBoardById.call(pathParams().add("board_id", INVALID_BOARD_ID));
        response.hasErrors()
                .statusCode(ERROR_CODE);
        assertEquals(response.getBody(), "invalid id");
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void postInvalidCommentToCard() {
        String invalidComment = "";
        RestResponse response = getTrelloService().postNewCommentToCard
                .call(pathParams().add("card_id", CARD_UNIQUE_ID)
                        .queryParamsUpdater().add("text", invalidComment));
        response.hasErrors()
                .statusCode(ERROR_CODE);
        assertEquals(response.getBody(), "invalid value for text");
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void deleteNotExistsCardFromBoard() {
        RestResponse response = getTrelloService().deleteACardFromBoard
                .call(queryParams().add("card_id", NON_EXISTENT_CARD_ID));
        response.hasErrors()
                .statusCode(NOT_FOUND_CODE);
        assertTrue(response.getBody().contains("Cannot DELETE"));
        response.isEmpty();
    }

    private TrelloService getTrelloService() {
        return init(TrelloService.class);
    }
}
