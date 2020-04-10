package com.epam.jdi.soap.com.dataaccess.webservicesserver;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="NumberToDollarsResult" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "numberToDollarsResult"
})
@XmlRootElement(name = "NumberToDollarsResponse")
public class NumberToDollarsResponse {

    @XmlElement(name = "NumberToDollarsResult", required = true)
    protected String numberToDollarsResult;

    /**
     * Gets the value of the numberToDollarsResult property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNumberToDollarsResult() {
        return numberToDollarsResult;
    }

    /**
     * Sets the value of the numberToDollarsResult property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNumberToDollarsResult(String value) {
        this.numberToDollarsResult = value;
    }

    public NumberToDollarsResponse withNumberToDollarsResult(String value) {
        setNumberToDollarsResult(value);
        return this;
    }

}
