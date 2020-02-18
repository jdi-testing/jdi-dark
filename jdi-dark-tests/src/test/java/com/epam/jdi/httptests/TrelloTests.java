package com.epam.jdi.httptests;

import com.epam.jdi.httptests.utils.TrelloDataGenerator;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Organization;
import com.julienvey.trello.domain.TList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

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
        TList tList = TrelloDataGenerator.generateList(createdBoard);
        TList createdList = TrelloService.createList(tList);

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
        Organization organization = TrelloDataGenerator.generateOrganization();
        Organization createOrg = TrelloService.createOrganization(organization);

        //Crate board
        Board board = TrelloDataGenerator.generateBoard();
        board.setIdOrganization(createOrg.getId());
        TrelloService.createBoard(board);

        //Check that organization contains created board
        List<Board> boards = TrelloService.getOrganizationBoards(createOrg);
        Assert.assertTrue(boards.stream().map(Board::getName).collect(Collectors.toList()).contains(board.getName()), "Board wasn't added to organization");

    }
}
