package com.epam.jdi.httptests;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Greeting {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Greeting(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}