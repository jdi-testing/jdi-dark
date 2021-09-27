package com.epam.jdi.dto;

import com.jdiai.tools.DataClass;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GreetingItself extends DataClass<GreetingItself> {
    public String greeting;
}