package com.epam.jdi.dto;

import com.jdiai.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloList extends DataClass<TrelloList> {
    public String id, name, idBoard;
    public List<Card> cards = new ArrayList<>();
    public boolean closed, subscribed;
    public int pos;
}
