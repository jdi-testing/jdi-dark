package com.epam.jdi.dto;

import com.epam.jdi.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership extends DataClass<Membership> {
    public String id, idMember, memberType;
    public boolean unconfirmed;
}
