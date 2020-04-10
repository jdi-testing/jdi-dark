package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PenaltyCodeResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PenaltyCodeResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="matchScore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="inputType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="streetType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="streetName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cityRefs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="zipPenalty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="directionals" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="qualifiers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="distance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="outlierDistance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="censusBlocks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="censusTracts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="county" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="recordCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchScoreSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="inputTypeSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="streetTypeSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="streetNameSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="citySummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cityRefsSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="zipPenaltySummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="directionalsSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="qualifiersSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="distanceSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="outlierDistanceSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="censusBlocksSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="censusTractsSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countySummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="recordCountSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PenaltyCodeResult", propOrder = {
        "matchScore",
        "inputType",
        "streetType",
        "streetName",
        "city",
        "cityRefs",
        "zipPenalty",
        "directionals",
        "qualifiers",
        "distance",
        "outlierDistance",
        "censusBlocks",
        "censusTracts",
        "county",
        "recordCount",
        "matchScoreSummary",
        "inputTypeSummary",
        "streetTypeSummary",
        "streetNameSummary",
        "citySummary",
        "cityRefsSummary",
        "zipPenaltySummary",
        "directionalsSummary",
        "qualifiersSummary",
        "distanceSummary",
        "outlierDistanceSummary",
        "censusBlocksSummary",
        "censusTractsSummary",
        "countySummary",
        "recordCountSummary"
})
public class PenaltyCodeResult {

    protected String matchScore;
    protected String inputType;
    protected String streetType;
    protected String streetName;
    protected String city;
    protected String cityRefs;
    protected String zipPenalty;
    protected String directionals;
    protected String qualifiers;
    protected String distance;
    protected String outlierDistance;
    protected String censusBlocks;
    protected String censusTracts;
    protected String county;
    protected String recordCount;
    protected String matchScoreSummary;
    protected String inputTypeSummary;
    protected String streetTypeSummary;
    protected String streetNameSummary;
    protected String citySummary;
    protected String cityRefsSummary;
    protected String zipPenaltySummary;
    protected String directionalsSummary;
    protected String qualifiersSummary;
    protected String distanceSummary;
    protected String outlierDistanceSummary;
    protected String censusBlocksSummary;
    protected String censusTractsSummary;
    protected String countySummary;
    protected String recordCountSummary;

    /**
     * Gets the value of the matchScore property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMatchScore() {
        return matchScore;
    }

    /**
     * Sets the value of the matchScore property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMatchScore(String value) {
        this.matchScore = value;
    }

    /**
     * Gets the value of the inputType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * Sets the value of the inputType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInputType(String value) {
        this.inputType = value;
    }

    /**
     * Gets the value of the streetType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStreetType() {
        return streetType;
    }

    /**
     * Sets the value of the streetType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStreetType(String value) {
        this.streetType = value;
    }

    /**
     * Gets the value of the streetName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStreetName(String value) {
        this.streetName = value;
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
     * Gets the value of the cityRefs property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCityRefs() {
        return cityRefs;
    }

    /**
     * Sets the value of the cityRefs property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCityRefs(String value) {
        this.cityRefs = value;
    }

    /**
     * Gets the value of the zipPenalty property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getZipPenalty() {
        return zipPenalty;
    }

    /**
     * Sets the value of the zipPenalty property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setZipPenalty(String value) {
        this.zipPenalty = value;
    }

    /**
     * Gets the value of the directionals property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDirectionals() {
        return directionals;
    }

    /**
     * Sets the value of the directionals property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDirectionals(String value) {
        this.directionals = value;
    }

    /**
     * Gets the value of the qualifiers property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getQualifiers() {
        return qualifiers;
    }

    /**
     * Sets the value of the qualifiers property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQualifiers(String value) {
        this.qualifiers = value;
    }

    /**
     * Gets the value of the distance property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Sets the value of the distance property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDistance(String value) {
        this.distance = value;
    }

    /**
     * Gets the value of the outlierDistance property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOutlierDistance() {
        return outlierDistance;
    }

    /**
     * Sets the value of the outlierDistance property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOutlierDistance(String value) {
        this.outlierDistance = value;
    }

    /**
     * Gets the value of the censusBlocks property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusBlocks() {
        return censusBlocks;
    }

    /**
     * Sets the value of the censusBlocks property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusBlocks(String value) {
        this.censusBlocks = value;
    }

    /**
     * Gets the value of the censusTracts property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusTracts() {
        return censusTracts;
    }

    /**
     * Sets the value of the censusTracts property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusTracts(String value) {
        this.censusTracts = value;
    }

    /**
     * Gets the value of the county property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCounty() {
        return county;
    }

    /**
     * Sets the value of the county property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCounty(String value) {
        this.county = value;
    }

    /**
     * Gets the value of the recordCount property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the value of the recordCount property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRecordCount(String value) {
        this.recordCount = value;
    }

    /**
     * Gets the value of the matchScoreSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMatchScoreSummary() {
        return matchScoreSummary;
    }

    /**
     * Sets the value of the matchScoreSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMatchScoreSummary(String value) {
        this.matchScoreSummary = value;
    }

    /**
     * Gets the value of the inputTypeSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getInputTypeSummary() {
        return inputTypeSummary;
    }

    /**
     * Sets the value of the inputTypeSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInputTypeSummary(String value) {
        this.inputTypeSummary = value;
    }

    /**
     * Gets the value of the streetTypeSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStreetTypeSummary() {
        return streetTypeSummary;
    }

    /**
     * Sets the value of the streetTypeSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStreetTypeSummary(String value) {
        this.streetTypeSummary = value;
    }

    /**
     * Gets the value of the streetNameSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStreetNameSummary() {
        return streetNameSummary;
    }

    /**
     * Sets the value of the streetNameSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStreetNameSummary(String value) {
        this.streetNameSummary = value;
    }

    /**
     * Gets the value of the citySummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCitySummary() {
        return citySummary;
    }

    /**
     * Sets the value of the citySummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCitySummary(String value) {
        this.citySummary = value;
    }

    /**
     * Gets the value of the cityRefsSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCityRefsSummary() {
        return cityRefsSummary;
    }

    /**
     * Sets the value of the cityRefsSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCityRefsSummary(String value) {
        this.cityRefsSummary = value;
    }

    /**
     * Gets the value of the zipPenaltySummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getZipPenaltySummary() {
        return zipPenaltySummary;
    }

    /**
     * Sets the value of the zipPenaltySummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setZipPenaltySummary(String value) {
        this.zipPenaltySummary = value;
    }

    /**
     * Gets the value of the directionalsSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDirectionalsSummary() {
        return directionalsSummary;
    }

    /**
     * Sets the value of the directionalsSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDirectionalsSummary(String value) {
        this.directionalsSummary = value;
    }

    /**
     * Gets the value of the qualifiersSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getQualifiersSummary() {
        return qualifiersSummary;
    }

    /**
     * Sets the value of the qualifiersSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQualifiersSummary(String value) {
        this.qualifiersSummary = value;
    }

    /**
     * Gets the value of the distanceSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDistanceSummary() {
        return distanceSummary;
    }

    /**
     * Sets the value of the distanceSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDistanceSummary(String value) {
        this.distanceSummary = value;
    }

    /**
     * Gets the value of the outlierDistanceSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOutlierDistanceSummary() {
        return outlierDistanceSummary;
    }

    /**
     * Sets the value of the outlierDistanceSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOutlierDistanceSummary(String value) {
        this.outlierDistanceSummary = value;
    }

    /**
     * Gets the value of the censusBlocksSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusBlocksSummary() {
        return censusBlocksSummary;
    }

    /**
     * Sets the value of the censusBlocksSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusBlocksSummary(String value) {
        this.censusBlocksSummary = value;
    }

    /**
     * Gets the value of the censusTractsSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCensusTractsSummary() {
        return censusTractsSummary;
    }

    /**
     * Sets the value of the censusTractsSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCensusTractsSummary(String value) {
        this.censusTractsSummary = value;
    }

    /**
     * Gets the value of the countySummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountySummary() {
        return countySummary;
    }

    /**
     * Sets the value of the countySummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountySummary(String value) {
        this.countySummary = value;
    }

    /**
     * Gets the value of the recordCountSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRecordCountSummary() {
        return recordCountSummary;
    }

    /**
     * Sets the value of the recordCountSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRecordCountSummary(String value) {
        this.recordCountSummary = value;
    }

    public PenaltyCodeResult withMatchScore(String value) {
        setMatchScore(value);
        return this;
    }

    public PenaltyCodeResult withInputType(String value) {
        setInputType(value);
        return this;
    }

    public PenaltyCodeResult withStreetType(String value) {
        setStreetType(value);
        return this;
    }

    public PenaltyCodeResult withStreetName(String value) {
        setStreetName(value);
        return this;
    }

    public PenaltyCodeResult withCity(String value) {
        setCity(value);
        return this;
    }

    public PenaltyCodeResult withCityRefs(String value) {
        setCityRefs(value);
        return this;
    }

    public PenaltyCodeResult withZipPenalty(String value) {
        setZipPenalty(value);
        return this;
    }

    public PenaltyCodeResult withDirectionals(String value) {
        setDirectionals(value);
        return this;
    }

    public PenaltyCodeResult withQualifiers(String value) {
        setQualifiers(value);
        return this;
    }

    public PenaltyCodeResult withDistance(String value) {
        setDistance(value);
        return this;
    }

    public PenaltyCodeResult withOutlierDistance(String value) {
        setOutlierDistance(value);
        return this;
    }

    public PenaltyCodeResult withCensusBlocks(String value) {
        setCensusBlocks(value);
        return this;
    }

    public PenaltyCodeResult withCensusTracts(String value) {
        setCensusTracts(value);
        return this;
    }

    public PenaltyCodeResult withCounty(String value) {
        setCounty(value);
        return this;
    }

    public PenaltyCodeResult withRecordCount(String value) {
        setRecordCount(value);
        return this;
    }

    public PenaltyCodeResult withMatchScoreSummary(String value) {
        setMatchScoreSummary(value);
        return this;
    }

    public PenaltyCodeResult withInputTypeSummary(String value) {
        setInputTypeSummary(value);
        return this;
    }

    public PenaltyCodeResult withStreetTypeSummary(String value) {
        setStreetTypeSummary(value);
        return this;
    }

    public PenaltyCodeResult withStreetNameSummary(String value) {
        setStreetNameSummary(value);
        return this;
    }

    public PenaltyCodeResult withCitySummary(String value) {
        setCitySummary(value);
        return this;
    }

    public PenaltyCodeResult withCityRefsSummary(String value) {
        setCityRefsSummary(value);
        return this;
    }

    public PenaltyCodeResult withZipPenaltySummary(String value) {
        setZipPenaltySummary(value);
        return this;
    }

    public PenaltyCodeResult withDirectionalsSummary(String value) {
        setDirectionalsSummary(value);
        return this;
    }

    public PenaltyCodeResult withQualifiersSummary(String value) {
        setQualifiersSummary(value);
        return this;
    }

    public PenaltyCodeResult withDistanceSummary(String value) {
        setDistanceSummary(value);
        return this;
    }

    public PenaltyCodeResult withOutlierDistanceSummary(String value) {
        setOutlierDistanceSummary(value);
        return this;
    }

    public PenaltyCodeResult withCensusBlocksSummary(String value) {
        setCensusBlocksSummary(value);
        return this;
    }

    public PenaltyCodeResult withCensusTractsSummary(String value) {
        setCensusTractsSummary(value);
        return this;
    }

    public PenaltyCodeResult withCountySummary(String value) {
        setCountySummary(value);
        return this;
    }

    public PenaltyCodeResult withRecordCountSummary(String value) {
        setRecordCountSummary(value);
        return this;
    }

}
