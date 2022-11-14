package com.epam.jdi.httptests.examples.custom;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.*;
import com.epam.jdi.services.ServiceExample;
import com.epam.jdi.services.TrelloService;
import com.epam.jdi.utils.RetryAnalyzer;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import static com.epam.http.requests.RequestDataFactory.pathParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateBoard;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateOrganization;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateList;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateCard;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

import static com.epam.http.JdiHttpSettings.logger;

public class PreconditionParallelTests {
    public static final String TRELLO_API = "https://api.trello.com/1";
    private final ArrayList<String> createdBoardsId = new ArrayList<>();
    public static final String CSV_DATA_FILE = "src/test/resources/testWithPreconditions.csv";
    private static String newOrgId;

    @BeforeClass
    public void initService() throws IOException {
        new FileWriter(CSV_DATA_FILE, false).close();

        // create Organization as we will get a error during Board creation in case of zero organizations
        Organization org = generateOrganization();
        Organization newOrg = getTrelloService().createOrganization(org);
        newOrgId = newOrg.id;
    }

    @DataProvider(name = "createNewBoards", parallel = true)
    public static Object[][] createNewBoards() {
        return new Object[][] {
                {generateBoard()},
                {generateBoard()},
                {generateBoard()}
        };
    }

    @Test(dataProvider = "createNewBoards", threadPoolSize = 1, skipFailedInvocations = true, retryAnalyzer = RetryAnalyzer.class)
    public void createCardInBoard(Board board) throws IOException {
        TrelloService trello = getTrelloService();

        //Create board
        Board createdBoard = trello.createBoard(board);
        logger.info("Created board with id = %s, name = '%s'", createdBoard.id, createdBoard.name);
        Board gotBoard = trello.getBoard(createdBoard.id);
        createdBoardsId.add(createdBoard.id);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        TrelloList createdList = trello.createList(tList);

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = trello.addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = trello.getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board.name, "Card wasn't added to board");

        writeToCSV(cardBoard);
    }

    @DataProvider(name = "dataProviderFromCSV", parallel = true)
    public static Object[] dataProviderFromCSV() throws IOException {
        Reader in = new FileReader(CSV_DATA_FILE);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader("id", "name", "shortUrl", "url")
                .parse(in);
        ArrayList<Object[]> dataList = new ArrayList<>();
        for (CSVRecord record : records) {
            dataList.add(new Object[] {record.get(0),record.get(1),record.get(2),record.get(3)});
        }
        return dataList.toArray(new Object[dataList.size()][]);
    }

    @Test(dataProvider = "dataProviderFromCSV", threadPoolSize = 3)
    public void getBoardTestWithRequestData(String boardId, String expectedName, String expectedShortUrl, String expectedUrl) {
        TrelloService trello = init(TrelloService.class, ServiceSettings.builder().domain(TRELLO_API).build());
        ServiceExample httpbin = init(ServiceExample.class, ServiceSettings.builder().domain("https://httpbin.org/").build());

        logger.info("Get info about board id = %s", boardId);
        trello.boardId.call(pathParams().add("board_id", boardId))
                .isOk().assertThat().body("name", equalTo(expectedName))
                .body("shortUrl", equalTo(expectedShortUrl))
                .body("url", equalTo(expectedUrl));

        RestResponse info = httpbin.info.call();
        info.isOk().
                body("url", equalTo("https://httpbin.org/get")).
                body("headers.Host", equalTo("httpbin.org")).
                body("headers.Id", equalTo("Test"));
        info.assertThat().header("Connection", "keep-alive");
    }

    private synchronized void writeToCSV(Board board) throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get(CSV_DATA_FILE), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        CSVPrinter printer = CSVFormat.EXCEL.print(writer);
        printer.printRecord(board.id, board.name, board.shortUrl, board.url);
        printer.flush();
        writer.close();
    }

    @AfterClass
    public void clearBoards() {
        TrelloService trello = getTrelloService();

        createdBoardsId.forEach(trello::deleteBoard);
        if (newOrgId != null) {
            trello.deleteOrg(newOrgId);
        }
    }

    private TrelloService getTrelloService() {
        return init(TrelloService.class);
    }
}
