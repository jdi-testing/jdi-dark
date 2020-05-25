package com.epam.jdi.httptests.examples.custom;

import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Board;
import com.epam.jdi.services.TrelloService;
import org.apache.commons.csv.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.epam.http.requests.RequestDataFactory.pathParams;
import static com.epam.http.requests.RequestDataFactory.requestBody;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.TrelloService.boardsPost;
import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;

public class PreconditionTests {
    public static final String CSV_DATA_FILE = "src/test/resources/testWithPreconditions.csv";
    private ArrayList<String> createdBoardsId = new ArrayList<String>();

    @DataProvider(name = "createNewBoards")
    public static Object[][] createNewBoards() {
        return new Object[][]{
                {"Board B1-" + LocalDateTime.now()},
                {"Board B2-" + LocalDateTime.now()},
                {"Board B3-" + LocalDateTime.now()}
        };
    }

    @DataProvider(name = "dataProviderFromCSV")
    public static Object[] dataProviderFromCSV() throws IOException {
        Reader in = new FileReader(CSV_DATA_FILE);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader("id", "name", "shortUrl", "url")
                .parse(in);
        ArrayList<Object[]> dataList = new ArrayList<>();
        for (CSVRecord record : records) {
            dataList.add(new Object[]{record.get(0), record.get(1), record.get(2), record.get(3)});
        }
        return dataList.toArray(new Object[dataList.size()][]);
    }

    @BeforeClass
    public void initService() throws IOException {
        init(TrelloService.class);
        new FileWriter(CSV_DATA_FILE, false).close();
    }

    @Test(dataProvider = "createNewBoards")
    public void createNewBoardTest(String boardName) throws IOException {
        RestResponse response = boardsPost.call(requestBody(format("{\"name\": \"%s\"}", boardName)));
        response.isOk().body("name", equalTo(boardName));
        Board board = response.getRaResponse().as(Board.class);
        writeToCSV(board);
    }

    @Test(dataProvider = "dataProviderFromCSV")
    public void getBoardTestWithRequestData(String boardId, String expectedName, String expectedShortUrl, String expectedUrl) {
        TrelloService.getBoardById.call(pathParams().add("board_id", boardId))
                .isOk().assertThat().body("name", equalTo(expectedName))
                .body("shortUrl", equalTo(expectedShortUrl))
                .body("url", equalTo(expectedUrl));
    }

    @Test(dataProvider = "dataProviderFromCSV")
    public void getBoardTest(String boardId, String expectedName, String expectedShortUrl, String expectedUrl) {
        Board gotBoard = TrelloService.getBoard(boardId);
        Assert.assertEquals(gotBoard.name, expectedName, "Actual Board Name doesn't correspond expected");
        Assert.assertEquals(gotBoard.shortUrl, expectedShortUrl, "Actual Board ShortUrl doesn't correspond expected");
        Assert.assertEquals(gotBoard.url, expectedUrl, "Actual Board URL doesn't correspond expected");
    }

    private void writeToCSV(Board board) throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get(CSV_DATA_FILE), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        CSVPrinter printer = CSVFormat.EXCEL.print(writer);
        printer.printRecord(board.id, board.name, board.shortUrl, board.url);
        printer.flush();
        writer.close();
    }

    @AfterClass
    public void clearBoards() {
        createdBoardsId.forEach(TrelloService::deleteBoard);
    }
}
