package com.epam.jdi.dto;

import com.jdiai.tools.DataClass;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Greeting extends DataClass<Greeting> {
    public String firstName, lastName;
}