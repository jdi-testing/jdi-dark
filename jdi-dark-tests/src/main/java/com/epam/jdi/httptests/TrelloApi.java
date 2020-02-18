package com.epam.jdi.httptests;

import com.epam.http.annotations.ServiceDomain;
import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.QueryParameters;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("${trello}")
@QueryParameters({
        @QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db"),
        @QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
})
public class TrelloApi {

        public static final String BOARDS = "/boards";

        @ContentType(JSON) @GET(BOARDS)
        public static RestMethod boardsGet;

        @ContentType(JSON) @POST(BOARDS)
        public static RestMethod boardsPost;

        @ContentType(JSON) @GET("/boards/{board_id}")
        public static RestMethod getBoardById;

        @ContentType(JSON) @GET("/boards/{board_id}/cards")
        public static RestMethod getBoardCardsList;

        @ContentType(JSON) @GET("/boards/{board_id}/cards/{short_card_id}")
        public static RestMethod getBoardCardById;

        @ContentType(JSON) @GET("/members/{user_name}/boards")
        public static RestMethod getAllMemberBoards;

        @ContentType(JSON) @GET("/members")
        public static RestMethod membersGet;

        @ContentType(JSON) @DELETE("/cards")
        public static RestMethod deleteACardFromBoard;

        @ContentType(JSON) @POST("/cards/{card_id}/actions/comments")
        public static RestMethod postNewCommentToCard;

        @QueryParameter(name = "test", value = "test")
        @ContentType(JSON) @GET("/cards/{card_id}")
        public static RestMethod getCardByUniqueId;

}
