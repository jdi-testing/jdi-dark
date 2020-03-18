package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.annotations.QueryParameters;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.DataMethod;
import com.epam.http.requests.RestMethod;
import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.dto.TrelloList;

import java.util.List;

import static com.epam.http.requests.RequestDataInfo.pathParams;
import static io.restassured.http.ContentType.JSON;
import static java.util.Arrays.asList;

@ServiceDomain("${trello}")
@QueryParameters({
        @QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db"),
        @QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
})
public class TrelloServiceNoStatic {
    public static final String BOARDS = "/boards";

    @ContentType(JSON)
    @GET(BOARDS)
    public RestMethod boardsGet;

    @ContentType(JSON)
    @POST(BOARDS)
    public DataMethod<Board> boardsPost;

    public Board createBoard(Board board) {
        System.out.println("createBoard method: " + board.name + ". Thread id is: " + Thread.currentThread().getId());
        return boardsPost.callObject(board);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}")
    public RestMethod getBoardById;

    public Board getBoard(String boardId) {
        return getBoardById.call(pathParams().add("board_id", boardId)).getRaResponse().as(Board.class);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}/cards/{short_card_id}")
    public RestMethod getBoardCardById;

    @ContentType(JSON)
    @GET("/members/{user_name}/boards")
    public RestMethod getAllMemberBoards;

    @ContentType(JSON)
    @GET("/members")
    public RestMethod membersGet;

    @ContentType(JSON)
    @DELETE("/cards")
    public RestMethod deleteACardFromBoard;

    @ContentType(JSON)
    @POST("/cards")
    public RestMethod addNewCardToBoard;

    public Card addNewCardToBoard(Card card) {
        return addNewCardToBoard.post(card, Card.class);
    }

    @ContentType(JSON)
    @GET("/cards/{id}/board")
    public RestMethod getCardBoard;

    public Board getCardBoard(String cardId) {
        return getCardBoard.call(pathParams().add("id", cardId)).getRaResponse().as(Board.class);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}/cards")
    public RestMethod getBoardCardsList;

    @ContentType(JSON)
    @POST("/lists")
    public RestMethod createList;

    public TrelloList createList(TrelloList list) {
        return createList.post(list, TrelloList.class);
    }

    @ContentType(JSON)
    @POST("/cards/{card_id}/actions/comments")
    public RestMethod postNewCommentToCard;

    @QueryParameter(name = "test", value = "test")
    @ContentType(JSON)
    @GET("/cards/{card_id}")
    public RestMethod getCardByUniqueId;

    @ContentType(JSON)
    @POST("/organizations")
    public RestMethod createOrganization;

    public Organization createOrganization(Organization organization) {
        return createOrganization.post(organization, Organization.class);
    }

    @ContentType(JSON)
    @GET("/organizations/{id}/boards")
    public RestMethod getOrganizationBoards;

    public List<Board> getOrganizationBoards(Organization organization) {
        return asList(getOrganizationBoards.call(pathParams().add("id", organization.id)).getRaResponse().as(Board[].class));
    }
}
