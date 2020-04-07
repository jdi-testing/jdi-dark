package com.epam.jdi.dto.yandex.speller.services.spellservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="ArrayOfSpellResult" type="{http://speller.yandex.net/services/spellservice}ArrayOfSpellResult"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "arrayOfSpellResult"
})
@XmlRootElement(name = "CheckTextsResponse")
public class CheckTextsResponse {

    @XmlElement(name = "ArrayOfSpellResult", required = true)
    protected ArrayOfSpellResult arrayOfSpellResult;

    /**
     * Gets the value of the arrayOfSpellResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSpellResult }
     *     
     */
    public ArrayOfSpellResult getArrayOfSpellResult() {
        return arrayOfSpellResult;
    }

    /**
     * Sets the value of the arrayOfSpellResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSpellResult }
     *     
     */
    public void setArrayOfSpellResult(ArrayOfSpellResult value) {
        this.arrayOfSpellResult = value;
    }

    public CheckTextsResponse withArrayOfSpellResult(ArrayOfSpellResult value) {
        setArrayOfSpellResult(value);
        return this;
    }

}
