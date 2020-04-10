package com.epam.jdi.soap.https.geoservices_tamu;

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
 *         &lt;element name="GeocodeAddressNonParsedResult" type="{https://geoservices.tamu.edu/}WebServiceGeocodeQueryResultSet" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "geocodeAddressNonParsedResult"
})
@XmlRootElement(name = "GeocodeAddressNonParsedResponse")
public class GeocodeAddressNonParsedResponse {

    @XmlElement(name = "GeocodeAddressNonParsedResult")
    protected WebServiceGeocodeQueryResultSet geocodeAddressNonParsedResult;

    /**
     * Gets the value of the geocodeAddressNonParsedResult property.
     *
     * @return possible object is
     * {@link WebServiceGeocodeQueryResultSet }
     */
    public WebServiceGeocodeQueryResultSet getGeocodeAddressNonParsedResult() {
        return geocodeAddressNonParsedResult;
    }

    /**
     * Sets the value of the geocodeAddressNonParsedResult property.
     *
     * @param value allowed object is
     *              {@link WebServiceGeocodeQueryResultSet }
     */
    public void setGeocodeAddressNonParsedResult(WebServiceGeocodeQueryResultSet value) {
        this.geocodeAddressNonParsedResult = value;
    }

    public GeocodeAddressNonParsedResponse withGeocodeAddressNonParsedResult(WebServiceGeocodeQueryResultSet value) {
        setGeocodeAddressNonParsedResult(value);
        return this;
    }

}
