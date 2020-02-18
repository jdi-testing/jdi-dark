package com.epam.jdi.httptests.businessflowtests;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.Organization;
import com.julienvey.trello.domain.TList;

import java.util.Random;

public class TrelloDataGenerator {

    public static Board generateBoard() {
        Board board = new Board();
        board.setName("JDI Test Board number " + new Random().nextInt(Integer.MAX_VALUE));
        return board;
    }

    public static TList generateList(Board board) {
        TList tList = new TList();
        tList.setName("JDI Test List number " + new Random().nextInt(Integer.MAX_VALUE));
        tList.setIdBoard(board.getId());
        return tList;
    }

    public static Card generateCard(Board board, TList tList) {
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

    public static Member generateMember() {
        Member member = new Member();
        member.setFullName("JDI_member_" + new Random().nextInt(Integer.MAX_VALUE));
        member.setEmail(member.getFullName() + "@gmail.com");
        member.setMemberType("normal");
        return member;
    }
}
