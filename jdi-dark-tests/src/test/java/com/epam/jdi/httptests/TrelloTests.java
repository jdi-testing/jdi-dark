package com.epam.jdi.httptests;

import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.dto.TrelloList;
import com.epam.jdi.httptests.utils.TrelloDataGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class TrelloTests {

    @BeforeClass
    public void initService() {
        init(TrelloService.class);
    }

    @Test
    public void createCardInBoard() {

        //Crate board
        Board board = TrelloDataGenerator.generateBoard();
        Board createdBoard = TrelloService.createBoard(board);
        Board gotBoard = TrelloService.getBoard(createdBoard.getId());
        Assert.assertEquals(gotBoard.getName(), createdBoard.getName(), "Name of created board is incorrect");

        //Create list
        TrelloList tList = TrelloDataGenerator.generateList(createdBoard);
        TrelloList createdList = TrelloService.createList(tList);

        //Create Card
        Card card = TrelloDataGenerator.generateCard(createdBoard, createdList);
        Card createdCard = TrelloService.addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = TrelloService.getCardBoard(createdCard.getId());
        Assert.assertEquals(cardBoard.getName(), board.getName(), "Card wasn't added to board");
    }

    @Test
    public void assignBoardToOrganization() {

        //Create organization
        Board board = TrelloDataGenerator.generateBoard();
        Organization organization = TrelloDataGenerator.generateOrganization();
        Organization createOrg = TrelloService.createOrganization(board);

//        //Crate board
//        Board board = TrelloDataGenerator.generateBoard();
//        board.setIdOrganization(createOrg.getId());
//        TrelloService.createBoard(board);

        //Check that organization contains created board
//        List<Board> boards = TrelloService.getOrganizationBoards(createOrg);
//        Assert.assertTrue(boards.stream().map(Board::getName).collect(Collectors.toList()).contains(board.getName()), "Board wasn't added to organization");

    }
}
