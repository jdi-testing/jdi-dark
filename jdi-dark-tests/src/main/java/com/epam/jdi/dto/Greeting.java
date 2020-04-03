package com.epam.jdi.dto;

import com.epam.jdi.tools.DataClass;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Greeting extends DataClass<Greeting> {
    public String firstName, lastName;
}