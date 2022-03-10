package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestDataMethod;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.dto.TrelloList;

import static java.util.Arrays.asList;

import java.util.List;

import static com.epam.http.requests.RequestDataFactory.pathParams;
import static io.restassured.http.ContentType.JSON;

@ServiceDomain("${trello}")
@QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db")
@QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
public class TrelloService {
    public static final String BOARDS = "/boards";

    @ContentType(JSON)
    @GET(BOARDS)
    public RestMethod boardsGet;

    @ContentType(JSON)
    @POST(BOARDS)
    public RestDataMethod<Board> boardsPost;

    public synchronized Board createBoard(Board board) {
        return boardsPost.postAsData(board);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}")
    public RestMethod getBoardById;

    @ContentType(JSON)
    @GET("/boards/{board_id}")
    public RestMethod boardId;

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

    @GET("/members/me")
    public RestMethod membersMeGet;

    @ContentType(JSON)
    @DELETE("/cards")
    public RestMethod deleteACardFromBoard;

    @ContentType(JSON)
    @POST("/cards")
    public RestMethod addNewCardToBoard;

    public Card addNewCardToBoard(Card card) {
        return addNewCardToBoard.body(card).callAsData(Card.class);
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

    public synchronized TrelloList createList(TrelloList list) {
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

    public List<Board> getOrganizationBoards(String orgId) {
        return asList(getOrganizationBoards
                .call(pathParams().add("id", orgId))
                .getRaResponse()
                .as(Board[].class));
    }

    @ContentType(JSON)
    @DELETE("/boards/{board_id}")
    public RestMethod deleteBoard;
    @ContentType(JSON)
    @DELETE("/organizations/{org_id}")
    public RestMethod deleteOrg;

    public RestResponse deleteBoard(String boardId) {
        return deleteBoard.call(pathParams().add("board_id", boardId));
    }

    public RestResponse deleteOrg(String orgId) {
        return deleteOrg.call(pathParams().add("org_id", orgId));
    }
}
