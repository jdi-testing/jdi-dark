package com.epam.jdi.websockettest;

import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.TrelloList;
import com.epam.jdi.services.TrelloService;
import com.epam.jdi.services.TrelloSocket;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;

import javax.websocket.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateBoard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateCard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateList;
import static com.epam.jdi.services.TrelloService.*;
import static org.testng.Assert.assertEquals;

public class TrelloTest {
    private TrelloSocket trelloSocket = new TrelloSocket();
    String createdBoardId, token, createdListId, createdCardId;

    @BeforeClass
    public void initService() {
        init(TrelloService.class);
    }

    @Test
    private void checkMessages() throws IOException, InterruptedException, DeploymentException, URISyntaxException {
        RestResponse restResponse = membersMeGet.call();
        token = restResponse.getRaResponse().jsonPath().getString("id");

        //Create board
        Board board = generateBoard();
        Board createdBoard = createBoard(board);
        createdBoardId = createdBoard.id;
        Board gotBoard = getBoard(createdBoard.id);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        TrelloList createdList = createList(tList);
        createdListId = createdList.id;

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = addNewCardToBoard(card);
        createdCardId = createdCard.id;


        trelloSocket.connect("wss://trello.com/1/Session/socket?token=" + token);
        trelloSocket.sendMessage("{\"type\":\"subscribe\",\"modelType\":\"Member\",\"idModel\":\"5e8ef65b384f806fbb911f5d\",\"tags\":[\"messages\",\"updates\"],\"invitationTokens\":[],\"reqid\":5}");
        trelloSocket.waitNewMessage(180);
        Assertions.assertThat(trelloSocket.receivedMessage.toString()).isNotEmpty();

        //TODO: update card name
        // get message {"notify":{"event":"updateModels","typeName":"Card","deltas":[{"id":"5e8ef7089cd6a57c05381d35","name":"My card new","idBoard":"5e8ef687c723e95d23f15176","idList":"5e8ef6874feeca8b5d166ce7","dateLastActivity":"2020-04-13T18:39:02.720Z","closed":false}],"tags":["updates"],"idBoard":"5e8ef687c723e95d23f15176"},"idModelChannel":"5e8ef687c723e95d23f15176","ixLastUpdateChannel":42}
    }

    @AfterClass
    public void clearBoards() {
        deleteBoard(createdBoardId);
    }

}
