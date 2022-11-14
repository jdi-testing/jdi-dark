package com.epam.jdi.httptests.examples.entities;

import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.dto.TrelloList;
import com.epam.jdi.services.TrelloService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateBoard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateCard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateList;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateOrganization;
import static com.jdiai.tools.LinqUtils.map;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TrelloTests {
    private String createdBoardId, createdBoardId2, createdOrgId;

    @BeforeClass
    public void initService() {
        // Create organization to use.
        // It's impossible to create a board in case of no organizations exists
        Organization organization = generateOrganization();
        Organization createOrg = getTrelloService().createOrganization(organization);
        createdOrgId = createOrg.id;
    }

    @Test
    public void createCardInBoard() {
        TrelloService trello = getTrelloService();
        //Create board
        Board board = generateBoard();
        Board createdBoard = trello.createBoard(board);
        createdBoardId = createdBoard.id;
        Board gotBoard = trello.getBoard(createdBoardId);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        TrelloList createdList =trello.createList(tList);

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = trello.addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = trello.getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board.name, "Card wasn't added to board");
    }

    @Test
    public void assignBoardToOrganization() {
        TrelloService trello = getTrelloService();
        //Create board
        Board board = generateBoard();
        board.idOrganization = createdOrgId;
        Board createdBoard = trello.createBoard(board);
        createdBoardId2 = createdBoard.id;

        //Check that organization contains created board
        List<Board> boards = trello.getOrganizationBoards(createdOrgId);
        assertTrue(map(boards, b -> b.name).contains(board.name), "Board wasn't added to organization");
    }

    @AfterClass
    public void clearBoards() {
        TrelloService trello = getTrelloService();

        trello.deleteBoard(createdBoardId);
        trello.deleteBoard(createdBoardId2);
        trello.deleteOrg(createdOrgId);
    }

    private TrelloService getTrelloService() {
        return init(TrelloService.class);
    }
}
