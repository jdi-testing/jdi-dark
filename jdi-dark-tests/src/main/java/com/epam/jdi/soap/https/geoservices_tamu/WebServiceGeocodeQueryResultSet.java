package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for WebServiceGeocodeQueryResultSet complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="WebServiceGeocodeQueryResultSet"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parcelMatches" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="streetMatches" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="TransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MicroMatchStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PenaltyCodeResult" type="{https://geoservices.tamu.edu/}PenaltyCodeResult" minOccurs="0"/&gt;
 *         &lt;element name="PenaltyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PenaltyCodeSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QueryStatusCodes" type="{https://geoservices.tamu.edu/}QueryStatusCodes"/&gt;
 *         &lt;element name="WebServiceGeocodeQueryResults" type="{https://geoservices.tamu.edu/}ArrayOfWebServiceGeocodeQueryResult" minOccurs="0"/&gt;
 *         &lt;element name="QueryStatusCodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QueryStatusCodeValue" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="TimeTaken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ExceptionOccurred" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IIsPreParsed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="INonParsedStreetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="INumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="INumberFractional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPreDirectional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPreType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPreQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPreArticle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ISuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPostArticle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPostQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPostDirectional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ISuiteType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ISuiteNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ICity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IConsolidatedCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IMinorCivilDivision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ICountySubRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ICounty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IZipPlus1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IZipPlus2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IZipPlus3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IZipPlus4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IZipPlus5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPostOfficeBoxType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IPostOfficeBoxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FeatureMatchingResultType" type="{https://geoservices.tamu.edu/}FeatureMatchingResultType"/&gt;
 *         &lt;element name="FeatureMatchingResultCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebServiceGeocodeQueryResultSet", propOrder = {
        "parcelMatches",
        "streetMatches",
        "version",
        "transactionId",
        "microMatchStatus",
        "penaltyCodeResult",
        "penaltyCode",
        "penaltyCodeSummary",
        "queryStatusCodes",
        "webServiceGeocodeQueryResults",
        "queryStatusCodeName",
        "queryStatusCodeValue",
        "timeTaken",
        "exceptionOccurred",
        "errorMessage",
        "iIsPreParsed",
        "iNonParsedStreetAddress",
        "iNumber",
        "iNumberFractional",
        "iPreDirectional",
        "iPreType",
        "iPreQualifier",
        "iPreArticle",
        "iName",
        "iSuffix",
        "iPostArticle",
        "iPostQualifier",
        "iPostDirectional",
        "iSuiteType",
        "iSuiteNumber",
        "iCity",
        "iConsolidatedCity",
        "iMinorCivilDivision",
        "iCountySubRegion",
        "iCounty",
        "iState",
        "iZip",
        "iZipPlus1",
        "iZipPlus2",
        "iZipPlus3",
        "iZipPlus4",
        "iZipPlus5",
        "iPostOfficeBoxType",
        "iPostOfficeBoxNumber",
        "featureMatchingResultType",
        "featureMatchingResultCount"
})
public class WebServiceGeocodeQueryResultSet {

    protected int parcelMatches;
    protected int streetMatches;
    @XmlElement(name = "Version")
    protected double version;
    @XmlElement(name = "TransactionId")
    protected String transactionId;
    @XmlElement(name = "MicroMatchStatus")
    protected String microMatchStatus;
    @XmlElement(name = "PenaltyCodeResult")
    protected PenaltyCodeResult penaltyCodeResult;
    @XmlElement(name = "PenaltyCode")
    protected String penaltyCode;
    @XmlElement(name = "PenaltyCodeSummary")
    protected String penaltyCodeSummary;
    @XmlElement(name = "QueryStatusCodes", required = true)
    @XmlSchemaType(name = "string")
    protected QueryStatusCodes queryStatusCodes;
    @XmlElement(name = "WebServiceGeocodeQueryResults")
    protected ArrayOfWebServiceGeocodeQueryResult webServiceGeocodeQueryResults;
    @XmlElement(name = "QueryStatusCodeName")
    protected String queryStatusCodeName;
    @XmlElement(name = "QueryStatusCodeValue")
    protected int queryStatusCodeValue;
    @XmlElement(name = "TimeTaken")
    protected String timeTaken;
    @XmlElement(name = "ExceptionOccurred")
    protected boolean exceptionOccurred;
    @XmlElement(name = "ErrorMessage")
    protected String errorMessage;
    @XmlElement(name = "IIsPreParsed")
    protected boolean iIsPreParsed;
    @XmlElement(name = "INonParsedStreetAddress")
    protected String iNonParsedStreetAddress;
    @XmlElement(name = "INumber")
    protected String iNumber;
    @XmlElement(name = "INumberFractional")
    protected String iNumberFractional;
    @XmlElement(name = "IPreDirectional")
    protected String iPreDirectional;
    @XmlElement(name = "IPreType")
    protected String iPreType;
    @XmlElement(name = "IPreQualifier")
    protected String iPreQualifier;
    @XmlElement(name = "IPreArticle")
    protected String iPreArticle;
    @XmlElement(name = "IName")
    protected String iName;
    @XmlElement(name = "ISuffix")
    protected String iSuffix;
    @XmlElement(name = "IPostArticle")
    protected String iPostArticle;
    @XmlElement(name = "IPostQualifier")
    protected String iPostQualifier;
    @XmlElement(name = "IPostDirectional")
    protected String iPostDirectional;
    @XmlElement(name = "ISuiteType")
    protected String iSuiteType;
    @XmlElement(name = "ISuiteNumber")
    protected String iSuiteNumber;
    @XmlElement(name = "ICity")
    protected String iCity;
    @XmlElement(name = "IConsolidatedCity")
    protected String iConsolidatedCity;
    @XmlElement(name = "IMinorCivilDivision")
    protected String iMinorCivilDivision;
    @XmlElement(name = "ICountySubRegion")
    protected String iCountySubRegion;
    @XmlElement(name = "ICounty")
    protected String iCounty;
    @XmlElement(name = "IState")
    protected String iState;
    @XmlElement(name = "IZip")
    protected String iZip;
    @XmlElement(name = "IZipPlus1")
    protected String iZipPlus1;
    @XmlElement(name = "IZipPlus2")
    protected String iZipPlus2;
    @XmlElement(name = "IZipPlus3")
    protected String iZipPlus3;
    @XmlElement(name = "IZipPlus4")
    protected String iZipPlus4;
    @XmlElement(name = "IZipPlus5")
    protected String iZipPlus5;
    @XmlElement(name = "IPostOfficeBoxType")
    protected String iPostOfficeBoxType;
    @XmlElement(name = "IPostOfficeBoxNumber")
    protected String iPostOfficeBoxNumber;
    @XmlList
    @XmlElement(name = "FeatureMatchingResultType", required = true)
    protected List<String> featureMatchingResultType;
    @XmlElement(name = "FeatureMatchingResultCount")
    protected int featureMatchingResultCount;

    /**
     * Gets the value of the parcelMatches property.
     */
    public int getParcelMatches() {
        return parcelMatches;
    }

    /**
     * Sets the value of the parcelMatches property.
     */
    public void setParcelMatches(int value) {
        this.parcelMatches = value;
    }

    /**
     * Gets the value of the streetMatches property.
     */
    public int getStreetMatches() {
        return streetMatches;
    }

    /**
     * Sets the value of the streetMatches property.
     */
    public void setStreetMatches(int value) {
        this.streetMatches = value;
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
     * Gets the value of the transactionId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the microMatchStatus property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMicroMatchStatus() {
        return microMatchStatus;
    }

    /**
     * Sets the value of the microMatchStatus property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMicroMatchStatus(String value) {
        this.microMatchStatus = value;
    }

    /**
     * Gets the value of the penaltyCodeResult property.
     *
     * @return possible object is
     * {@link PenaltyCodeResult }
     */
    public PenaltyCodeResult getPenaltyCodeResult() {
        return penaltyCodeResult;
    }

    /**
     * Sets the value of the penaltyCodeResult property.
     *
     * @param value allowed object is
     *              {@link PenaltyCodeResult }
     */
    public void setPenaltyCodeResult(PenaltyCodeResult value) {
        this.penaltyCodeResult = value;
    }

    /**
     * Gets the value of the penaltyCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPenaltyCode() {
        return penaltyCode;
    }

    /**
     * Sets the value of the penaltyCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPenaltyCode(String value) {
        this.penaltyCode = value;
    }

    /**
     * Gets the value of the penaltyCodeSummary property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPenaltyCodeSummary() {
        return penaltyCodeSummary;
    }

    /**
     * Sets the value of the penaltyCodeSummary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPenaltyCodeSummary(String value) {
        this.penaltyCodeSummary = value;
    }

    /**
     * Gets the value of the queryStatusCodes property.
     *
     * @return possible object is
     * {@link QueryStatusCodes }
     */
    public QueryStatusCodes getQueryStatusCodes() {
        return queryStatusCodes;
    }

    /**
     * Sets the value of the queryStatusCodes property.
     *
     * @param value allowed object is
     *              {@link QueryStatusCodes }
     */
    public void setQueryStatusCodes(QueryStatusCodes value) {
        this.queryStatusCodes = value;
    }

    /**
     * Gets the value of the webServiceGeocodeQueryResults property.
     *
     * @return possible object is
     * {@link ArrayOfWebServiceGeocodeQueryResult }
     */
    public ArrayOfWebServiceGeocodeQueryResult getWebServiceGeocodeQueryResults() {
        return webServiceGeocodeQueryResults;
    }

    /**
     * Sets the value of the webServiceGeocodeQueryResults property.
     *
     * @param value allowed object is
     *              {@link ArrayOfWebServiceGeocodeQueryResult }
     */
    public void setWebServiceGeocodeQueryResults(ArrayOfWebServiceGeocodeQueryResult value) {
        this.webServiceGeocodeQueryResults = value;
    }

    /**
     * Gets the value of the queryStatusCodeName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getQueryStatusCodeName() {
        return queryStatusCodeName;
    }

    /**
     * Sets the value of the queryStatusCodeName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQueryStatusCodeName(String value) {
        this.queryStatusCodeName = value;
    }

    /**
     * Gets the value of the queryStatusCodeValue property.
     */
    public int getQueryStatusCodeValue() {
        return queryStatusCodeValue;
    }

    /**
     * Sets the value of the queryStatusCodeValue property.
     */
    public void setQueryStatusCodeValue(int value) {
        this.queryStatusCodeValue = value;
    }

    /**
     * Gets the value of the timeTaken property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTimeTaken() {
        return timeTaken;
    }

    /**
     * Sets the value of the timeTaken property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTimeTaken(String value) {
        this.timeTaken = value;
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
     * Gets the value of the errorMessage property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the iIsPreParsed property.
     */
    public boolean isIIsPreParsed() {
        return iIsPreParsed;
    }

    /**
     * Sets the value of the iIsPreParsed property.
     */
    public void setIIsPreParsed(boolean value) {
        this.iIsPreParsed = value;
    }

    /**
     * Gets the value of the iNonParsedStreetAddress property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getINonParsedStreetAddress() {
        return iNonParsedStreetAddress;
    }

    /**
     * Sets the value of the iNonParsedStreetAddress property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setINonParsedStreetAddress(String value) {
        this.iNonParsedStreetAddress = value;
    }

    /**
     * Gets the value of the iNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getINumber() {
        return iNumber;
    }

    /**
     * Sets the value of the iNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setINumber(String value) {
        this.iNumber = value;
    }

    /**
     * Gets the value of the iNumberFractional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getINumberFractional() {
        return iNumberFractional;
    }

    /**
     * Sets the value of the iNumberFractional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setINumberFractional(String value) {
        this.iNumberFractional = value;
    }

    /**
     * Gets the value of the iPreDirectional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPreDirectional() {
        return iPreDirectional;
    }

    /**
     * Sets the value of the iPreDirectional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPreDirectional(String value) {
        this.iPreDirectional = value;
    }

    /**
     * Gets the value of the iPreType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPreType() {
        return iPreType;
    }

    /**
     * Sets the value of the iPreType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPreType(String value) {
        this.iPreType = value;
    }

    /**
     * Gets the value of the iPreQualifier property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPreQualifier() {
        return iPreQualifier;
    }

    /**
     * Sets the value of the iPreQualifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPreQualifier(String value) {
        this.iPreQualifier = value;
    }

    /**
     * Gets the value of the iPreArticle property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPreArticle() {
        return iPreArticle;
    }

    /**
     * Sets the value of the iPreArticle property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPreArticle(String value) {
        this.iPreArticle = value;
    }

    /**
     * Gets the value of the iName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIName() {
        return iName;
    }

    /**
     * Sets the value of the iName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIName(String value) {
        this.iName = value;
    }

    /**
     * Gets the value of the iSuffix property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getISuffix() {
        return iSuffix;
    }

    /**
     * Sets the value of the iSuffix property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setISuffix(String value) {
        this.iSuffix = value;
    }

    /**
     * Gets the value of the iPostArticle property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPostArticle() {
        return iPostArticle;
    }

    /**
     * Sets the value of the iPostArticle property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPostArticle(String value) {
        this.iPostArticle = value;
    }

    /**
     * Gets the value of the iPostQualifier property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPostQualifier() {
        return iPostQualifier;
    }

    /**
     * Sets the value of the iPostQualifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPostQualifier(String value) {
        this.iPostQualifier = value;
    }

    /**
     * Gets the value of the iPostDirectional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPostDirectional() {
        return iPostDirectional;
    }

    /**
     * Sets the value of the iPostDirectional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPostDirectional(String value) {
        this.iPostDirectional = value;
    }

    /**
     * Gets the value of the iSuiteType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getISuiteType() {
        return iSuiteType;
    }

    /**
     * Sets the value of the iSuiteType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setISuiteType(String value) {
        this.iSuiteType = value;
    }

    /**
     * Gets the value of the iSuiteNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getISuiteNumber() {
        return iSuiteNumber;
    }

    /**
     * Sets the value of the iSuiteNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setISuiteNumber(String value) {
        this.iSuiteNumber = value;
    }

    /**
     * Gets the value of the iCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getICity() {
        return iCity;
    }

    /**
     * Sets the value of the iCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setICity(String value) {
        this.iCity = value;
    }

    /**
     * Gets the value of the iConsolidatedCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIConsolidatedCity() {
        return iConsolidatedCity;
    }

    /**
     * Sets the value of the iConsolidatedCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIConsolidatedCity(String value) {
        this.iConsolidatedCity = value;
    }

    /**
     * Gets the value of the iMinorCivilDivision property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIMinorCivilDivision() {
        return iMinorCivilDivision;
    }

    /**
     * Sets the value of the iMinorCivilDivision property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIMinorCivilDivision(String value) {
        this.iMinorCivilDivision = value;
    }

    /**
     * Gets the value of the iCountySubRegion property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getICountySubRegion() {
        return iCountySubRegion;
    }

    /**
     * Sets the value of the iCountySubRegion property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setICountySubRegion(String value) {
        this.iCountySubRegion = value;
    }

    /**
     * Gets the value of the iCounty property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getICounty() {
        return iCounty;
    }

    /**
     * Sets the value of the iCounty property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setICounty(String value) {
        this.iCounty = value;
    }

    /**
     * Gets the value of the iState property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIState() {
        return iState;
    }

    /**
     * Sets the value of the iState property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIState(String value) {
        this.iState = value;
    }

    /**
     * Gets the value of the iZip property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIZip() {
        return iZip;
    }

    /**
     * Sets the value of the iZip property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIZip(String value) {
        this.iZip = value;
    }

    /**
     * Gets the value of the iZipPlus1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIZipPlus1() {
        return iZipPlus1;
    }

    /**
     * Sets the value of the iZipPlus1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIZipPlus1(String value) {
        this.iZipPlus1 = value;
    }

    /**
     * Gets the value of the iZipPlus2 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIZipPlus2() {
        return iZipPlus2;
    }

    /**
     * Sets the value of the iZipPlus2 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIZipPlus2(String value) {
        this.iZipPlus2 = value;
    }

    /**
     * Gets the value of the iZipPlus3 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIZipPlus3() {
        return iZipPlus3;
    }

    /**
     * Sets the value of the iZipPlus3 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIZipPlus3(String value) {
        this.iZipPlus3 = value;
    }

    /**
     * Gets the value of the iZipPlus4 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIZipPlus4() {
        return iZipPlus4;
    }

    /**
     * Sets the value of the iZipPlus4 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIZipPlus4(String value) {
        this.iZipPlus4 = value;
    }

    /**
     * Gets the value of the iZipPlus5 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIZipPlus5() {
        return iZipPlus5;
    }

    /**
     * Sets the value of the iZipPlus5 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIZipPlus5(String value) {
        this.iZipPlus5 = value;
    }

    /**
     * Gets the value of the iPostOfficeBoxType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPostOfficeBoxType() {
        return iPostOfficeBoxType;
    }

    /**
     * Sets the value of the iPostOfficeBoxType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPostOfficeBoxType(String value) {
        this.iPostOfficeBoxType = value;
    }

    /**
     * Gets the value of the iPostOfficeBoxNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIPostOfficeBoxNumber() {
        return iPostOfficeBoxNumber;
    }

    /**
     * Sets the value of the iPostOfficeBoxNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIPostOfficeBoxNumber(String value) {
        this.iPostOfficeBoxNumber = value;
    }

    /**
     * Gets the value of the featureMatchingResultType property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureMatchingResultType property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureMatchingResultType().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getFeatureMatchingResultType() {
        if (featureMatchingResultType == null) {
            featureMatchingResultType = new ArrayList<String>();
        }
        return this.featureMatchingResultType;
    }

    /**
     * Gets the value of the featureMatchingResultCount property.
     */
    public int getFeatureMatchingResultCount() {
        return featureMatchingResultCount;
    }

    /**
     * Sets the value of the featureMatchingResultCount property.
     */
    public void setFeatureMatchingResultCount(int value) {
        this.featureMatchingResultCount = value;
    }

    public WebServiceGeocodeQueryResultSet withParcelMatches(int value) {
        setParcelMatches(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withStreetMatches(int value) {
        setStreetMatches(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withVersion(double value) {
        setVersion(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withTransactionId(String value) {
        setTransactionId(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withMicroMatchStatus(String value) {
        setMicroMatchStatus(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withPenaltyCodeResult(PenaltyCodeResult value) {
        setPenaltyCodeResult(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withPenaltyCode(String value) {
        setPenaltyCode(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withPenaltyCodeSummary(String value) {
        setPenaltyCodeSummary(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withQueryStatusCodes(QueryStatusCodes value) {
        setQueryStatusCodes(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withWebServiceGeocodeQueryResults(ArrayOfWebServiceGeocodeQueryResult value) {
        setWebServiceGeocodeQueryResults(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withQueryStatusCodeName(String value) {
        setQueryStatusCodeName(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withQueryStatusCodeValue(int value) {
        setQueryStatusCodeValue(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withTimeTaken(String value) {
        setTimeTaken(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withExceptionOccurred(boolean value) {
        setExceptionOccurred(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withErrorMessage(String value) {
        setErrorMessage(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIIsPreParsed(boolean value) {
        setIIsPreParsed(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withINonParsedStreetAddress(String value) {
        setINonParsedStreetAddress(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withINumber(String value) {
        setINumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withINumberFractional(String value) {
        setINumberFractional(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPreDirectional(String value) {
        setIPreDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPreType(String value) {
        setIPreType(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPreQualifier(String value) {
        setIPreQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPreArticle(String value) {
        setIPreArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIName(String value) {
        setIName(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withISuffix(String value) {
        setISuffix(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPostArticle(String value) {
        setIPostArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPostQualifier(String value) {
        setIPostQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPostDirectional(String value) {
        setIPostDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withISuiteType(String value) {
        setISuiteType(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withISuiteNumber(String value) {
        setISuiteNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withICity(String value) {
        setICity(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIConsolidatedCity(String value) {
        setIConsolidatedCity(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIMinorCivilDivision(String value) {
        setIMinorCivilDivision(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withICountySubRegion(String value) {
        setICountySubRegion(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withICounty(String value) {
        setICounty(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIState(String value) {
        setIState(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIZip(String value) {
        setIZip(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIZipPlus1(String value) {
        setIZipPlus1(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIZipPlus2(String value) {
        setIZipPlus2(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIZipPlus3(String value) {
        setIZipPlus3(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIZipPlus4(String value) {
        setIZipPlus4(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIZipPlus5(String value) {
        setIZipPlus5(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPostOfficeBoxType(String value) {
        setIPostOfficeBoxType(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withIPostOfficeBoxNumber(String value) {
        setIPostOfficeBoxNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResultSet withFeatureMatchingResultType(String... values) {
        if (values != null) {
            for (String value : values) {
                getFeatureMatchingResultType().add(value);
            }
        }
        return this;
    }

    public WebServiceGeocodeQueryResultSet withFeatureMatchingResultType(Collection<String> values) {
        if (values != null) {
            getFeatureMatchingResultType().addAll(values);
        }
        return this;
    }

    public WebServiceGeocodeQueryResultSet withFeatureMatchingResultCount(int value) {
        setFeatureMatchingResultCount(value);
        return this;
    }

}
