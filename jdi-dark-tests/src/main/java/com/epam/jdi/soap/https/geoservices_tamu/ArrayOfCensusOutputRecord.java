package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for ArrayOfCensusOutputRecord complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfCensusOutputRecord"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CensusOutputRecord" type="{https://geoservices.tamu.edu/}CensusOutputRecord" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCensusOutputRecord", propOrder = {
        "censusOutputRecord"
})
public class ArrayOfCensusOutputRecord {

    @XmlElement(name = "CensusOutputRecord", nillable = true)
    protected List<CensusOutputRecord> censusOutputRecord;

    /**
     * Gets the value of the censusOutputRecord property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the censusOutputRecord property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCensusOutputRecord().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CensusOutputRecord }
     */
    public List<CensusOutputRecord> getCensusOutputRecord() {
        if (censusOutputRecord == null) {
            censusOutputRecord = new ArrayList<CensusOutputRecord>();
        }
        return this.censusOutputRecord;
    }

    public ArrayOfCensusOutputRecord withCensusOutputRecord(CensusOutputRecord... values) {
        if (values != null) {
            for (CensusOutputRecord value : values) {
                getCensusOutputRecord().add(value);
            }
        }
        return this;
    }

    public ArrayOfCensusOutputRecord withCensusOutputRecord(Collection<CensusOutputRecord> values) {
        if (values != null) {
            getCensusOutputRecord().addAll(values);
        }
        return this;
    }

}
