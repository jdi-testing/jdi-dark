package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.annotations.QueryParameters;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;
import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.dto.TrelloList;

import java.util.Arrays;
import java.util.List;

import static com.epam.http.requests.RequestData.requestPathParams;
import static io.restassured.http.ContentType.JSON;

@ServiceDomain("${trello}")
@QueryParameters({
        @QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db"),
        @QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
})
public class TrelloService {

    public static final String BOARDS = "/boards";

    @ContentType(JSON)
    @GET(BOARDS)
    public static RestMethod<Board> boardsGet;

    @ContentType(JSON)
    @POST(BOARDS)
    public static RestMethod<Board> boardsPost;

    public static Board createBoard(Board board) {
        return boardsPost.post(board, Board.class);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}")
    public static RestMethod<Board> getBoardById;

    public static Board getBoard(String boardId) {
        return getBoardById.call(requestPathParams("board_id", boardId)).getRaResponse().as(Board.class);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}/cards/{short_card_id}")
    public static RestMethod getBoardCardById;

    @ContentType(JSON)
    @GET("/members/{user_name}/boards")
    public static RestMethod getAllMemberBoards;

    @ContentType(JSON)
    @GET("/members")
    public static RestMethod membersGet;

    @ContentType(JSON)
    @DELETE("/cards")
    public static RestMethod deleteACardFromBoard;

    @ContentType(JSON)
    @POST("/cards")
    public static RestMethod<Card> addNewCardToBoard;

    public static Card addNewCardToBoard(Card card) {
        return addNewCardToBoard.post(card, Card.class);
    }

    @ContentType(JSON)
    @GET("/cards/{id}/board")
    public static RestMethod<Board> getCardBoard;

    public static Board getCardBoard(String cardId) {
        return getCardBoard.call(requestPathParams("id", cardId)).getRaResponse().as(Board.class);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}/cards")
    public static RestMethod getBoardCardsList;

    @ContentType(JSON)
    @POST("/lists")
    public static RestMethod<TrelloList> createList;

    public static TrelloList createList(TrelloList list) {
        return createList.post(list, TrelloList.class);
    }

    @ContentType(JSON)
    @POST("/cards/{card_id}/actions/comments")
    public static RestMethod postNewCommentToCard;

    @QueryParameter(name = "test", value = "test")
    @ContentType(JSON)
    @GET("/cards/{card_id}")
    public static RestMethod getCardByUniqueId;

    @ContentType(JSON)
    @POST("/organizations")
    public static RestMethod<Organization> createOrganization;

    public static Organization createOrganization(Organization organization) {
        return createOrganization.post(organization, Organization.class);
    }

    @ContentType(JSON)
    @GET("/organizations/{id}/boards")
    public static RestMethod<Board[]> getOrganizationBoards;

    public static List<Board> getOrganizationBoards(Organization organization) {
        return Arrays.asList(getOrganizationBoards.call(requestPathParams("id", organization.getId())).getRaResponse().as(Board[].class));
    }

}
