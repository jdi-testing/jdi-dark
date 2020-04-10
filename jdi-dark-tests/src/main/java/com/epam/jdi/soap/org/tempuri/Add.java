package com.epam.jdi.soap.org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="intA" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="intB" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "intA",
        "intB"
})
@XmlRootElement(name = "Add")
public class Add {

    protected int intA;
    protected int intB;

    /**
     * Gets the value of the intA property.
     */
    public int getIntA() {
        return intA;
    }

    /**
     * Sets the value of the intA property.
     */
    public void setIntA(int value) {
        this.intA = value;
    }

    /**
     * Gets the value of the intB property.
     */
    public int getIntB() {
        return intB;
    }

    /**
     * Sets the value of the intB property.
     */
    public void setIntB(int value) {
        this.intB = value;
    }

    public Add withIntA(int value) {
        setIntA(value);
        return this;
    }

    public Add withIntB(int value) {
        setIntB(value);
        return this;
    }

}
