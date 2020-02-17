package com.epam.jdi.httptests.businessflowtests;

import com.epam.jdi.httptests.TrelloApi;
import com.julienvey.trello.domain.Board;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class TrelloTests {

    public static final String BOARD_ID = "5a27e3b62fef5d3a74dca48a";

    @BeforeClass
    public void initService() {
        init(TrelloApi.class);
    }

    @Test
    public void getBoardById() {
        Board board = TrelloApi.getBoard(BOARD_ID);
        int i = 1;
    }
}
