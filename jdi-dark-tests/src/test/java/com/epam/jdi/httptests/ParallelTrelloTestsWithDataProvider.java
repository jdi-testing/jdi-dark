package com.epam.jdi.httptests;

import com.epam.http.requests.RequestData;
import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
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

import static com.epam.http.requests.RequestDataInfo.pathParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateBoard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateCard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class ParallelTrelloTestsWithDataProvider {
    public TrelloServiceNoStatic service;
    public static final String CSV_DATA_FILE = "src/test/resources/testWithPreconditions.csv";

    @BeforeClass
    public void initService() {
        service = init(TrelloServiceNoStatic.class);
    }

    @DataProvider(name = "createNewBoards", parallel = true)
    public static Object[][] createNewBoards() {
        return new Object[][] {
                {generateBoard()},
                {generateBoard()},
                {generateBoard()}
        };
    }

    @Test(dataProvider = "createNewBoards", threadPoolSize = 3)
    public void createCardInBoard1(Board board) {
        //Crate board
        System.out.println("Board: " + board.name + ". Thread id is: " + Thread.currentThread().getId());
        Board createdBoard = service.createBoard(board);
        System.out.println("createdBoard1: " + createdBoard.name + ", " + createdBoard.id);
        Board gotBoard = service.getBoard(createdBoard.id);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        System.out.println("2-createdBoard1: " + createdBoard.name + ", " + createdBoard.id);
        System.out.println("2-tList1: " + tList.name + ", " + tList.idBoard);
        TrelloList createdList = service.createList(tList);
        System.out.println("2-createdList1: " + createdList.name + ", " + createdList.idBoard);

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = service.addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = service.getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board.name, "Card wasn't added to board");
    }

    @DataProvider(name = "dataProviderFromCSV", parallel = true)
    public static Object[] dataProviderFromCSV() throws IOException {
        Reader in = new FileReader(CSV_DATA_FILE);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader("id", "name", "shortUrl", "url")
                .withFirstRecordAsHeader()
                .parse(in);
        ArrayList<Object[]> dataList = new ArrayList<>();
        for (CSVRecord record : records) {
            dataList.add(new Object[] {record.get(0),record.get(1),record.get(2),record.get(3)});
        }
        return dataList.toArray(new Object[dataList.size()][]);
    }

    @Test (dataProvider = "dataProviderFromCSV", threadPoolSize = 3)
    public void getBoardTestWithRequestData(String boardId, String expectedName, String expectedShortUrl, String expectedUrl) {
        System.out.println("RequestData: " + pathParams().add("board_id", boardId));
        service.getBoardById.call(pathParams().add("board_id", boardId))
                .isOk().assertThat().body("name", equalTo(expectedName))
                .body("shortUrl",equalTo(expectedShortUrl))
                .body("url",equalTo(expectedUrl));
    }


}
