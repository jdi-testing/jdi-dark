package com.epam.jdi.bookstore.model.security;

import com.epam.jdi.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token extends DataClass<Token> {

    @JsonProperty("access_token")
    public String accessToken;
}
