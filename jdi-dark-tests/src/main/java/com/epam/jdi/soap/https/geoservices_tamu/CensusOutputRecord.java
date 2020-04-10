package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for CensusOutputRecord complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CensusOutputRecord"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CensusYear" type="{https://geoservices.tamu.edu/}CensusYear"/&gt;
 *         &lt;element name="CensusTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusStateFips" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusCountyFips" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusTract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusBlockGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusBlock" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusPlaceFips" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusMcdFips" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusMsaFips" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusMetDivFips" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusCbsaFips" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusCbsaMicro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusStateFipsTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusCountyFipsTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusTractTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusBlockGroupTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusBlockTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusPlaceFipsTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusMcdFipsTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusMsaFipsTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusMetDivFipsTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusCbsaFipsTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CensusCbsaMicroTimeTaken" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="ExceptionOccurred" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ExceptionMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CensusOutputRecord", propOrder = {
        "censusYear",
        "censusTimeTaken",
        "censusStateFips",
        "censusCountyFips",
        "censusTract",
        "censusBlockGroup",
        "censusBlock",
        "censusPlaceFips",
        "censusMcdFips",
        "censusMsaFips",
        "censusMetDivFips",
        "censusCbsaFips",
        "censusCbsaMicro",
        "censusStateFipsTimeTaken",
        "censusCountyFipsTimeTaken",
        "censusTractTimeTaken",
        "censusBlockGroupTimeTaken",
        "censusBlockTimeTaken",
        "censusPlaceFipsTimeTaken",
        "censusMcdFipsTimeTaken",
        "censusMsaFipsTimeTaken",
        "censusMetDivFipsTimeTaken",
        "censusCbsaFipsTimeTaken",
        "censusCbsaMicroTimeTaken",
        "exceptionOccurred",
        "exceptionMessage"
})
public class CensusOutputRecord {

    @XmlElement(name = "CensusYear", required = true)
    @XmlSchemaType(name = "string")
    protected CensusYear censusYear;
    @XmlElement(name = "CensusTimeTaken")
    protected double censusTimeTaken;
    @XmlElement(name = "CensusStateFips")
    protected String censusStateFips;
    @XmlElement(name = "CensusCountyFips")
    protected String censusCountyFips;
    @XmlElement(name = "CensusTract")
    protected String censusTract;
    @XmlElement(name = "CensusBlockGroup")
    protected String censusBlockGroup;
    @XmlElement(name = "CensusBlock")
    protected String censusBlock;
    @XmlElement(name = "CensusPlaceFips")
    protected String censusPlaceFips;
    @XmlElement(name = "CensusMcdFips")
    protected String censusMcdFips;
    @XmlElement(name = "CensusMsaFips")
    protected String censusMsaFips;
    @XmlElement(name = "CensusMetDivFips")
    protected String censusMetDivFips;
    @XmlElement(name = "CensusCbsaFips")
    protected String censusCbsaFips;
    @XmlElement(name = "CensusCbsaMicro")
    protected String censusCbsaMicro;
    @XmlElement(name = "CensusStateFipsTimeTaken")
    protected double censusStateFipsTimeTaken;
    @XmlElement(name = "CensusCountyFipsTimeTaken")
    protected double censusCountyFipsTimeTaken;
    @XmlElement(name = "CensusTractTimeTaken")
    protected double censusTractTimeTaken;
    @XmlElement(name = "CensusBlockGroupTimeTaken")
    protected double censusBlockGroupTimeTaken;
    @XmlElement(name = "CensusBlockTimeTaken")
    protected double censusBlockTimeTaken;
    @XmlElement(name = "CensusPlaceFipsTimeTaken")
    protected double censusPlaceFipsTimeTaken;
    @XmlElement(name = "CensusMcdFipsTimeTaken")
    protected double censusMcdFipsTimeTaken;
    @XmlElement(name = "CensusMsaFipsTimeTaken")
    protected double censusMsaFipsTimeTaken;
    @XmlElement(name = "CensusMetDivFipsTimeTaken")
    protected double censusMetDivFipsTimeTaken;
    @XmlElement(name = "CensusCbsaFipsTimeTaken")
    protected double censusCbsaFipsTimeTaken;
    @XmlElement(name = "CensusCbsaMicroTimeTaken")
    protected double censusCbsaMicroTimeTaken;
    @XmlElement(name = "ExceptionOccurred")
    protected boolean exceptionOccurred;
    @XmlElement(name = "ExceptionMessage")
    protected String exceptionMessage;

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
     * Gets the value of the censusTimeTaken property.
     */
    public double getCensusTimeTaken() {
        return censusTimeTaken;
    }

    /**
     * Sets the value of the censusTimeTaken property.
     */
    public void setCensusTimeTaken(double value) {
        this.censusTimeTaken = value;
    }

    /**
     * Gets the value of the censusStateFips property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusStateFips() {
        return censusStateFips;
    }

    /**
     * Sets the value of the censusStateFips property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusStateFips(String value) {
        this.censusStateFips = value;
    }

    /**
     * Gets the value of the censusCountyFips property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusCountyFips() {
        return censusCountyFips;
    }

    /**
     * Sets the value of the censusCountyFips property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusCountyFips(String value) {
        this.censusCountyFips = value;
    }

    /**
     * Gets the value of the censusTract property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusTract() {
        return censusTract;
    }

    /**
     * Sets the value of the censusTract property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusTract(String value) {
        this.censusTract = value;
    }

    /**
     * Gets the value of the censusBlockGroup property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusBlockGroup() {
        return censusBlockGroup;
    }

    /**
     * Sets the value of the censusBlockGroup property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusBlockGroup(String value) {
        this.censusBlockGroup = value;
    }

    /**
     * Gets the value of the censusBlock property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusBlock() {
        return censusBlock;
    }

    /**
     * Sets the value of the censusBlock property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusBlock(String value) {
        this.censusBlock = value;
    }

    /**
     * Gets the value of the censusPlaceFips property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusPlaceFips() {
        return censusPlaceFips;
    }

    /**
     * Sets the value of the censusPlaceFips property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusPlaceFips(String value) {
        this.censusPlaceFips = value;
    }

    /**
     * Gets the value of the censusMcdFips property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusMcdFips() {
        return censusMcdFips;
    }

    /**
     * Sets the value of the censusMcdFips property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusMcdFips(String value) {
        this.censusMcdFips = value;
    }

    /**
     * Gets the value of the censusMsaFips property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusMsaFips() {
        return censusMsaFips;
    }

    /**
     * Sets the value of the censusMsaFips property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusMsaFips(String value) {
        this.censusMsaFips = value;
    }

    /**
     * Gets the value of the censusMetDivFips property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusMetDivFips() {
        return censusMetDivFips;
    }

    /**
     * Sets the value of the censusMetDivFips property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusMetDivFips(String value) {
        this.censusMetDivFips = value;
    }

    /**
     * Gets the value of the censusCbsaFips property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusCbsaFips() {
        return censusCbsaFips;
    }

    /**
     * Sets the value of the censusCbsaFips property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusCbsaFips(String value) {
        this.censusCbsaFips = value;
    }

    /**
     * Gets the value of the censusCbsaMicro property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusCbsaMicro() {
        return censusCbsaMicro;
    }

    /**
     * Sets the value of the censusCbsaMicro property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusCbsaMicro(String value) {
        this.censusCbsaMicro = value;
    }

    /**
     * Gets the value of the censusStateFipsTimeTaken property.
     */
    public double getCensusStateFipsTimeTaken() {
        return censusStateFipsTimeTaken;
    }

    /**
     * Sets the value of the censusStateFipsTimeTaken property.
     */
    public void setCensusStateFipsTimeTaken(double value) {
        this.censusStateFipsTimeTaken = value;
    }

    /**
     * Gets the value of the censusCountyFipsTimeTaken property.
     */
    public double getCensusCountyFipsTimeTaken() {
        return censusCountyFipsTimeTaken;
    }

    /**
     * Sets the value of the censusCountyFipsTimeTaken property.
     */
    public void setCensusCountyFipsTimeTaken(double value) {
        this.censusCountyFipsTimeTaken = value;
    }

    /**
     * Gets the value of the censusTractTimeTaken property.
     */
    public double getCensusTractTimeTaken() {
        return censusTractTimeTaken;
    }

    /**
     * Sets the value of the censusTractTimeTaken property.
     */
    public void setCensusTractTimeTaken(double value) {
        this.censusTractTimeTaken = value;
    }

    /**
     * Gets the value of the censusBlockGroupTimeTaken property.
     */
    public double getCensusBlockGroupTimeTaken() {
        return censusBlockGroupTimeTaken;
    }

    /**
     * Sets the value of the censusBlockGroupTimeTaken property.
     */
    public void setCensusBlockGroupTimeTaken(double value) {
        this.censusBlockGroupTimeTaken = value;
    }

    /**
     * Gets the value of the censusBlockTimeTaken property.
     */
    public double getCensusBlockTimeTaken() {
        return censusBlockTimeTaken;
    }

    /**
     * Sets the value of the censusBlockTimeTaken property.
     */
    public void setCensusBlockTimeTaken(double value) {
        this.censusBlockTimeTaken = value;
    }

    /**
     * Gets the value of the censusPlaceFipsTimeTaken property.
     */
    public double getCensusPlaceFipsTimeTaken() {
        return censusPlaceFipsTimeTaken;
    }

    /**
     * Sets the value of the censusPlaceFipsTimeTaken property.
     */
    public void setCensusPlaceFipsTimeTaken(double value) {
        this.censusPlaceFipsTimeTaken = value;
    }

    /**
     * Gets the value of the censusMcdFipsTimeTaken property.
     */
    public double getCensusMcdFipsTimeTaken() {
        return censusMcdFipsTimeTaken;
    }

    /**
     * Sets the value of the censusMcdFipsTimeTaken property.
     */
    public void setCensusMcdFipsTimeTaken(double value) {
        this.censusMcdFipsTimeTaken = value;
    }

    /**
     * Gets the value of the censusMsaFipsTimeTaken property.
     */
    public double getCensusMsaFipsTimeTaken() {
        return censusMsaFipsTimeTaken;
    }

    /**
     * Sets the value of the censusMsaFipsTimeTaken property.
     */
    public void setCensusMsaFipsTimeTaken(double value) {
        this.censusMsaFipsTimeTaken = value;
    }

    /**
     * Gets the value of the censusMetDivFipsTimeTaken property.
     */
    public double getCensusMetDivFipsTimeTaken() {
        return censusMetDivFipsTimeTaken;
    }

    /**
     * Sets the value of the censusMetDivFipsTimeTaken property.
     */
    public void setCensusMetDivFipsTimeTaken(double value) {
        this.censusMetDivFipsTimeTaken = value;
    }

    /**
     * Gets the value of the censusCbsaFipsTimeTaken property.
     */
    public double getCensusCbsaFipsTimeTaken() {
        return censusCbsaFipsTimeTaken;
    }

    /**
     * Sets the value of the censusCbsaFipsTimeTaken property.
     */
    public void setCensusCbsaFipsTimeTaken(double value) {
        this.censusCbsaFipsTimeTaken = value;
    }

    /**
     * Gets the value of the censusCbsaMicroTimeTaken property.
     */
    public double getCensusCbsaMicroTimeTaken() {
        return censusCbsaMicroTimeTaken;
    }

    /**
     * Sets the value of the censusCbsaMicroTimeTaken property.
     */
    public void setCensusCbsaMicroTimeTaken(double value) {
        this.censusCbsaMicroTimeTaken = value;
    }

    /**
     * Gets the value of the exceptionOccurred property.
     */
    public boolean isExceptionOccurred() {
        return exceptionOccurred;
    }

    /**
     * Sets the value of the exceptionOccurred property.
     */
    public void setExceptionOccurred(boolean value) {
        this.exceptionOccurred = value;
    }

    /**
     * Gets the value of the exceptionMessage property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    /**
     * Sets the value of the exceptionMessage property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setExceptionMessage(String value) {
        this.exceptionMessage = value;
    }

    public CensusOutputRecord withCensusYear(CensusYear value) {
        setCensusYear(value);
        return this;
    }

    public CensusOutputRecord withCensusTimeTaken(double value) {
        setCensusTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusStateFips(String value) {
        setCensusStateFips(value);
        return this;
    }

    public CensusOutputRecord withCensusCountyFips(String value) {
        setCensusCountyFips(value);
        return this;
    }

    public CensusOutputRecord withCensusTract(String value) {
        setCensusTract(value);
        return this;
    }

    public CensusOutputRecord withCensusBlockGroup(String value) {
        setCensusBlockGroup(value);
        return this;
    }

    public CensusOutputRecord withCensusBlock(String value) {
        setCensusBlock(value);
        return this;
    }

    public CensusOutputRecord withCensusPlaceFips(String value) {
        setCensusPlaceFips(value);
        return this;
    }

    public CensusOutputRecord withCensusMcdFips(String value) {
        setCensusMcdFips(value);
        return this;
    }

    public CensusOutputRecord withCensusMsaFips(String value) {
        setCensusMsaFips(value);
        return this;
    }

    public CensusOutputRecord withCensusMetDivFips(String value) {
        setCensusMetDivFips(value);
        return this;
    }

    public CensusOutputRecord withCensusCbsaFips(String value) {
        setCensusCbsaFips(value);
        return this;
    }

    public CensusOutputRecord withCensusCbsaMicro(String value) {
        setCensusCbsaMicro(value);
        return this;
    }

    public CensusOutputRecord withCensusStateFipsTimeTaken(double value) {
        setCensusStateFipsTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusCountyFipsTimeTaken(double value) {
        setCensusCountyFipsTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusTractTimeTaken(double value) {
        setCensusTractTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusBlockGroupTimeTaken(double value) {
        setCensusBlockGroupTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusBlockTimeTaken(double value) {
        setCensusBlockTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusPlaceFipsTimeTaken(double value) {
        setCensusPlaceFipsTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusMcdFipsTimeTaken(double value) {
        setCensusMcdFipsTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusMsaFipsTimeTaken(double value) {
        setCensusMsaFipsTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusMetDivFipsTimeTaken(double value) {
        setCensusMetDivFipsTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusCbsaFipsTimeTaken(double value) {
        setCensusCbsaFipsTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withCensusCbsaMicroTimeTaken(double value) {
        setCensusCbsaMicroTimeTaken(value);
        return this;
    }

    public CensusOutputRecord withExceptionOccurred(boolean value) {
        setExceptionOccurred(value);
        return this;
    }

    public CensusOutputRecord withExceptionMessage(String value) {
        setExceptionMessage(value);
        return this;
    }

}
