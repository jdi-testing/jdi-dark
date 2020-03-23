package com.epam.jdi.httptests.examples.entities;

import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.dto.TrelloList;
import com.epam.jdi.httptests.TrelloService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.TrelloService.*;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.*;
import static com.epam.jdi.tools.LinqUtils.map;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TrelloTests {

    @BeforeClass
    public void initService() {
        init(TrelloService.class);
    }

    @Test
    public void createCardInBoard() {
        System.out.println("Simple test-method One. Thread id is: " + Thread.currentThread().getId());
        //Crate board
        Board board = generateBoard();
        Board createdBoard = createBoard(board);
        Board gotBoard = getBoard(createdBoard.id);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        TrelloList createdList = createList(tList);

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board.name, "Card wasn't added to board");
    }

    @Test
    public void assignBoardToOrganization() {

        //Create organization
        Organization organization = generateOrganization();
        Organization createOrg = createOrganization(organization);

        //Crate board
        Board board = generateBoard();
        board.idOrganization = createOrg.id;
        createBoard(board);

        //Check that organization contains created board
        List<Board> boards = getOrganizationBoards(createOrg);
        assertTrue(map(boards, b -> b.name).contains(board.name), "Board wasn't added to organization");

    }
}
