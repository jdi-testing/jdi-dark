package com.epam.jdi.httptests.utils;

import com.epam.jdi.dto.Board;
import com.epam.jdi.dto.Card;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.dto.TrelloList;

import java.util.Random;

public class TrelloDataGenerator {

    public static Board generateBoard() {
        Board board = new Board();
        board.setName("JDI Test Board number " + new Random().nextInt(Integer.MAX_VALUE));
        return board;
    }

    public static TrelloList generateList(Board board) {
        TrelloList tList = new TrelloList();
        tList.setName("JDI Test List number " + new Random().nextInt(Integer.MAX_VALUE));
        tList.setIdBoard(board.getId());
        return tList;
    }

    public static Card generateCard(Board board, TrelloList tList) {
        Card card = new Card();
        card.setName("JDI Test Card number " + new Random().nextInt(Integer.MAX_VALUE));
        card.setIdBoard(board.getId());
        card.setIdList(tList.getId());
        return card;
    }

    public static Organization generateOrganization() {
        Organization organization = new Organization();
        organization.setName("JDI Org " + new Random().nextInt(Integer.MAX_VALUE));
        organization.setDisplayName(organization.getName());
        return organization;
    }

}
