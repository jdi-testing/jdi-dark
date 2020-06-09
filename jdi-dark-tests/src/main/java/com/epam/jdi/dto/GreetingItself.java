package com.epam.jdi.dto;

import com.epam.jdi.tools.DataClass;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GreetingItself extends DataClass<GreetingItself> {
    public String greeting;
}