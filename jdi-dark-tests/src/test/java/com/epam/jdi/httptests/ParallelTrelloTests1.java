package com.epam.jdi.httptests;

import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.dto.TrelloList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static com.epam.http.requests.RequestDataInfo.pathParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateBoard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateCard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateList;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateOrganization;
import static com.epam.jdi.tools.LinqUtils.map;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ParallelTrelloTests1 {
    public TrelloServiceNoStatic service;
    public static final String CSV_DATA_FILE = "src/test/resources/testWithPreconditions.csv";

    @BeforeClass
    public void initService() {
        service = init(TrelloServiceNoStatic.class);
    }

    @Test
    public void createCardInBoard2() {
        //Crate board
        System.out.println("Simple test-method 2. Thread id is: " + Thread.currentThread().getId());
        Board board = generateBoard();
        System.out.println("Board2: " + board.name + ", " + board.id);
        Board createdBoard = service.createBoard(board);
        System.out.println("createdBoard2: " + createdBoard.name + ", " + createdBoard.id);
        Board gotBoard = service.getBoard(createdBoard.id);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        System.out.println("2-createdBoard2: " + createdBoard.name + ", " + createdBoard.id);
        System.out.println("2-tList2: " + tList.name + ", " + tList.idBoard);
        TrelloList createdList = service.createList(tList);
        System.out.println("2-createdList2: " + createdList.name + ", " + createdList.idBoard);

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = service.addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = service.getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board.name, "Card wasn't added to board");
    }

    @Test
    public void assignBoardToOrganization() {

        //Create organization
        Organization organization = generateOrganization();
        Organization createOrg = service.createOrganization(organization);

        //Crate board
        Board board = generateBoard();
        board.idOrganization = createOrg.id;
        service.createBoard(board);

        //Check that organization contains created board
        List<Board> boards = service.getOrganizationBoards(createOrg);
        assertTrue(map(boards, b -> b.name).contains(board.name), "Board wasn't added to organization");

    }

    @Test
    public void createCardInBoard3() {
        //Crate board
        System.out.println("Simple test-method Two. Thread id is: " + Thread.currentThread().getId());
        Board board = generateBoard();
        System.out.println("Board2: " + board.name + ", " + board.id);
        Board createdBoard = service.createBoard(board);
        System.out.println("createdBoard2: " + createdBoard.name + ", " + createdBoard.id);
        Board gotBoard = service.getBoard(createdBoard.id);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        System.out.println("2-createdBoard2: " + createdBoard.name + ", " + createdBoard.id);
        System.out.println("2-tList2: " + tList.name + ", " + tList.idBoard);
        TrelloList createdList = service.createList(tList);
        System.out.println("2-createdList2: " + createdList.name + ", " + createdList.idBoard);

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = service.addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = service.getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board.name, "Card wasn't added to board");
    }

    @Test
    public void createCardInBoard4() {
        //Crate board
        System.out.println("Simple test-method Two. Thread id is: " + Thread.currentThread().getId());
        Board board = generateBoard();
        System.out.println("Board2: " + board.name + ", " + board.id);
        Board createdBoard = service.createBoard(board);
        System.out.println("createdBoard2: " + createdBoard.name + ", " + createdBoard.id);
        Board gotBoard = service.getBoard(createdBoard.id);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        System.out.println("2-createdBoard2: " + createdBoard.name + ", " + createdBoard.id);
        System.out.println("2-tList2: " + tList.name + ", " + tList.idBoard);
        TrelloList createdList = service.createList(tList);
        System.out.println("2-createdList2: " + createdList.name + ", " + createdList.idBoard);

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = service.addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = service.getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board.name, "Card wasn't added to board");
    }

    @Test
    public void createCardInBoard5() {
        //Crate board
        System.out.println("Simple test-method 5. Thread id is: " + Thread.currentThread().getId());
        Board board5 = generateBoard();
        System.out.println("Board5: " + board5.name + ", " + board5.id);
        Board createdBoard5 = service.createBoard(board5);
        System.out.println("createdBoard5: " + createdBoard5.name + ", " + createdBoard5.id);
        Board gotBoard5 = service.getBoard(createdBoard5.id);
        assertEquals(gotBoard5.name, createdBoard5.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard5);
        System.out.println("2-createdBoard5: " + createdBoard5.name + ", " + createdBoard5.id);
        System.out.println("2-tList5: " + tList.name + ", " + tList.idBoard);
        TrelloList createdList5 = service.createList(tList);
        System.out.println("2-createdList5: " + createdList5.name + ", " + createdList5.idBoard);

        //Create Card
        Card card = generateCard(createdBoard5, createdList5);
        Card createdCard = service.addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = service.getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board5.name, "Card wasn't added to board");
    }
}
