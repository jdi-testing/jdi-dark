package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for ArrayOfWebServiceGeocodeQueryResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfWebServiceGeocodeQueryResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WebServiceGeocodeQueryResult" type="{https://geoservices.tamu.edu/}WebServiceGeocodeQueryResult" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWebServiceGeocodeQueryResult", propOrder = {
        "webServiceGeocodeQueryResult"
})
public class ArrayOfWebServiceGeocodeQueryResult {

    @XmlElement(name = "WebServiceGeocodeQueryResult", nillable = true)
    protected List<WebServiceGeocodeQueryResult> webServiceGeocodeQueryResult;

    /**
     * Gets the value of the webServiceGeocodeQueryResult property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the webServiceGeocodeQueryResult property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWebServiceGeocodeQueryResult().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WebServiceGeocodeQueryResult }
     */
    public List<WebServiceGeocodeQueryResult> getWebServiceGeocodeQueryResult() {
        if (webServiceGeocodeQueryResult == null) {
            webServiceGeocodeQueryResult = new ArrayList<WebServiceGeocodeQueryResult>();
        }
        return this.webServiceGeocodeQueryResult;
    }

    public ArrayOfWebServiceGeocodeQueryResult withWebServiceGeocodeQueryResult(WebServiceGeocodeQueryResult... values) {
        if (values != null) {
            for (WebServiceGeocodeQueryResult value : values) {
                getWebServiceGeocodeQueryResult().add(value);
            }
        }
        return this;
    }

    public ArrayOfWebServiceGeocodeQueryResult withWebServiceGeocodeQueryResult(Collection<WebServiceGeocodeQueryResult> values) {
        if (values != null) {
            getWebServiceGeocodeQueryResult().addAll(values);
        }
        return this;
    }

}
