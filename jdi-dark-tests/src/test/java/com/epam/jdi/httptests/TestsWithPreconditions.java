package com.epam.jdi.httptests;

import com.epam.jdi.dto.Board;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import static com.epam.http.requests.RequestData.requestBody;
import static com.epam.http.requests.RequestData.requestPathParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.TrelloService.boardsPost;
import static java.lang.String.format;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestsWithPreconditions {
    public static final String CSV_DATA_FILE = "src/test/resources/testWithPreconditions.csv";

    @BeforeClass
    public void initService() {
        init(TrelloService.class);
    }

    @DataProvider(name = "createNewBoards")
    public static Object[][] createNewBoards() {
        return new Object[][] {
                { "Board B1-" + random(5, true, true)},
                { "Board B2-" + random(5, true, true)},
                { "Board B3-" + random(5, true, true)}
        };
    }

    @Test (dataProvider = "createNewBoards")
    public void createNewBoardTest(String boardName) {
        boardsPost.call(requestBody(format("{\"name\": \"%s\"}", boardName)))
                .isOk().body("name", equalTo(boardName));
    }

    @DataProvider(name = "dataProviderFromCSV")
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

    @Test (dataProvider = "dataProviderFromCSV")
    public void getBoardTestWithRequestData(String boardId, String expectedName, String expectedShortUrl, String expectedUrl) {
        TrelloService.getBoardById
                .call(requestPathParams("board_id", boardId))
                .isOk().assertThat().body("name", equalTo(expectedName))
                .body("shortUrl",equalTo(expectedShortUrl))
                .body("url",equalTo(expectedUrl));
    }

    @Test (dataProvider = "dataProviderFromCSV")
    public void getBoardTest(String boardId, String expectedName, String expectedShortUrl, String expectedUrl) {
        Board gotBoard = TrelloService.getBoard(boardId);
        Assert.assertEquals(gotBoard.getName(), expectedName, "Actual Board Name doesn't correspond expected");
        Assert.assertEquals(gotBoard.getShortUrl(), expectedShortUrl, "Actual Board ShortUrl doesn't correspond expected");
        Assert.assertEquals(gotBoard.getUrl(), expectedUrl, "Actual Board URL doesn't correspond expected");
    }
}
