package com.epam.jdi.httptests;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Greeting {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}