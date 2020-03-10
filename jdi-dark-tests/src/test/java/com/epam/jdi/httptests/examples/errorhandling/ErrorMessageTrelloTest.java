package com.epam.jdi.httptests.examples.errorhandling;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.TrelloService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataInfo.requestPathParams;
import static com.epam.http.requests.RequestDataInfo.requestQueryParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.TrelloService.deleteACardFromBoard;
import static com.epam.jdi.httptests.TrelloService.getBoardById;
import static com.epam.jdi.httptests.TrelloService.postNewCommentToCard;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * This class is using for error handling cases for TrelloAPI
 */
public class ErrorMessageTrelloTest {

    public static final String INVALID_BOARD_ID = "456";
    public static final String NOT_EXISTS_BOARD_ID = "5a27e3b62fef5d3a74dca59a";
    public static final String CARD_UNIQUE_ID = "5a27e722e2f04f3ab6924931";
    public static final String NOT_EXISTS_CARD_ID = "5a27e722e2f04f3ab6924991";
    public static final int NOT_FOUND_CODE = 404;
    public static final int ERROR_CODE = 400;

    @BeforeMethod
    public void initService() {
        init(TrelloService.class);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void getBoardByNotExistsId() {
        RestResponse response = getBoardById
                .call(requestPathParams("board_id", NOT_EXISTS_BOARD_ID));
        response.hasErrors()
                .statusCode(NOT_FOUND_CODE);
        assertEquals(response.body, "The requested resource was not found.");
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void getBoardByInvalidId() {
        RestResponse response = getBoardById
                .call(requestPathParams("board_id", INVALID_BOARD_ID));
        response.hasErrors()
                .statusCode(ERROR_CODE);
        assertEquals(response.body, "invalid id");
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void postInvalidCommentToCard() {
        String invalidComment = "";
        RestResponse response = postNewCommentToCard
                .call(rd -> {
                    rd.pathParams.add("card_id", CARD_UNIQUE_ID);
                    rd.queryParams.add("text", invalidComment);
                });
        response.hasErrors()
                .statusCode(ERROR_CODE);
        assertEquals(response.body, "invalid value for text");
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void deleteNotExistsCardFromBoard() {
        RestResponse response = deleteACardFromBoard
                .call(requestQueryParams("card_id", NOT_EXISTS_CARD_ID));
        response.hasErrors()
                .statusCode(NOT_FOUND_CODE);
        assertTrue(response.body.contains("Cannot DELETE"));
        response.isEmpty();
    }
}
