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
 *         &lt;element name="streetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="apiKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="shouldCalculateCensus" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="censusYear" type="{https://geoservices.tamu.edu/}CensusYear"/&gt;
 *         &lt;element name="shouldReturnReferenceGeometry" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="shouldNotStoreTransactionDetails" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "streetAddress",
        "city",
        "state",
        "zip",
        "apiKey",
        "version",
        "shouldCalculateCensus",
        "censusYear",
        "shouldReturnReferenceGeometry",
        "shouldNotStoreTransactionDetails"
})
@XmlRootElement(name = "GeocodeAddressNonParsed")
public class GeocodeAddressNonParsed {

    protected String streetAddress;
    protected String city;
    protected String state;
    protected String zip;
    protected String apiKey;
    protected double version;
    protected boolean shouldCalculateCensus;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CensusYear censusYear;
    protected boolean shouldReturnReferenceGeometry;
    protected boolean shouldNotStoreTransactionDetails;

    /**
     * Gets the value of the streetAddress property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the value of the streetAddress property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStreetAddress(String value) {
        this.streetAddress = value;
    }

    /**
     * Gets the value of the city property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the zip property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * Gets the value of the apiKey property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the value of the apiKey property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setApiKey(String value) {
        this.apiKey = value;
    }

    /**
     * Gets the value of the version property.
     */
    public double getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     */
    public void setVersion(double value) {
        this.version = value;
    }

    /**
     * Gets the value of the shouldCalculateCensus property.
     */
    public boolean isShouldCalculateCensus() {
        return shouldCalculateCensus;
    }

    /**
     * Sets the value of the shouldCalculateCensus property.
     */
    public void setShouldCalculateCensus(boolean value) {
        this.shouldCalculateCensus = value;
    }

    /**
     * Gets the value of the censusYear property.
     *
     * @return possible object is
     * {@link CensusYear }
     */
    public CensusYear getCensusYear() {
        return censusYear;
    }

    /**
     * Sets the value of the censusYear property.
     *
     * @param value allowed object is
     *              {@link CensusYear }
     */
    public void setCensusYear(CensusYear value) {
        this.censusYear = value;
    }

    /**
     * Gets the value of the shouldReturnReferenceGeometry property.
     */
    public boolean isShouldReturnReferenceGeometry() {
        return shouldReturnReferenceGeometry;
    }

    /**
     * Sets the value of the shouldReturnReferenceGeometry property.
     */
    public void setShouldReturnReferenceGeometry(boolean value) {
        this.shouldReturnReferenceGeometry = value;
    }

    /**
     * Gets the value of the shouldNotStoreTransactionDetails property.
     */
    public boolean isShouldNotStoreTransactionDetails() {
        return shouldNotStoreTransactionDetails;
    }

    /**
     * Sets the value of the shouldNotStoreTransactionDetails property.
     */
    public void setShouldNotStoreTransactionDetails(boolean value) {
        this.shouldNotStoreTransactionDetails = value;
    }

    public GeocodeAddressNonParsed withStreetAddress(String value) {
        setStreetAddress(value);
        return this;
    }

    public GeocodeAddressNonParsed withCity(String value) {
        setCity(value);
        return this;
    }

    public GeocodeAddressNonParsed withState(String value) {
        setState(value);
        return this;
    }

    public GeocodeAddressNonParsed withZip(String value) {
        setZip(value);
        return this;
    }

    public GeocodeAddressNonParsed withApiKey(String value) {
        setApiKey(value);
        return this;
    }

    public GeocodeAddressNonParsed withVersion(double value) {
        setVersion(value);
        return this;
    }

    public GeocodeAddressNonParsed withShouldCalculateCensus(boolean value) {
        setShouldCalculateCensus(value);
        return this;
    }

    public GeocodeAddressNonParsed withCensusYear(CensusYear value) {
        setCensusYear(value);
        return this;
    }

    public GeocodeAddressNonParsed withShouldReturnReferenceGeometry(boolean value) {
        setShouldReturnReferenceGeometry(value);
        return this;
    }

    public GeocodeAddressNonParsed withShouldNotStoreTransactionDetails(boolean value) {
        setShouldNotStoreTransactionDetails(value);
        return this;
    }

}
