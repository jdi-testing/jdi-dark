package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for WebServiceGeocodeQueryResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="WebServiceGeocodeQueryResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Quality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MatchedLocationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MatchType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FeatureMatchingResultCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FeatureMatchingResultTypeNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FeatureMatchingResultTypeTieBreakingNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TieHandlingStrategyType" type="{https://geoservices.tamu.edu/}TieHandlingStrategyType"/&gt;
 *         &lt;element name="FeatureMatchingSelectionMethod" type="{https://geoservices.tamu.edu/}FeatureMatchingSelectionMethod"/&gt;
 *         &lt;element name="FeatureMatchingSelectionMethodNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QueryStatusCodes" type="{https://geoservices.tamu.edu/}QueryStatusCodes"/&gt;
 *         &lt;element name="GeocodeQualityType" type="{https://geoservices.tamu.edu/}GeocodeQualityType"/&gt;
 *         &lt;element name="NAACCRGISCoordinateQualityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NAACCRGISCoordinateQualityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NAACCRGISCoordinateQualityType" type="{https://geoservices.tamu.edu/}NAACCRGISCoordinateQualityType"/&gt;
 *         &lt;element name="NAACCRCensusTractCertaintyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NAACCRCensusTractCertaintyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NAACCRCensusTractCertaintyType" type="{https://geoservices.tamu.edu/}NAACCRCensusTractCertaintyType"/&gt;
 *         &lt;element name="FeatureMatchingGeographyType" type="{https://geoservices.tamu.edu/}FeatureMatchingGeographyType"/&gt;
 *         &lt;element name="FeatureMatchingResultType" type="{https://geoservices.tamu.edu/}FeatureMatchingResultType"/&gt;
 *         &lt;element name="MatchScore" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="MicroMatchStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PenaltyCodeResult" type="{https://geoservices.tamu.edu/}PenaltyCodeResult" minOccurs="0"/&gt;
 *         &lt;element name="PenaltyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PenaltyCodeSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="InterpolationType" type="{https://geoservices.tamu.edu/}InterpolationType"/&gt;
 *         &lt;element name="InterpolationSubType" type="{https://geoservices.tamu.edu/}InterpolationSubType"/&gt;
 *         &lt;element name="RegionSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RegionSizeUnits" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QueryStatusCodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QueryStatusCodeValue" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ExceptionOccurred" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TimeTaken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CensusRecords" type="{https://geoservices.tamu.edu/}ArrayOfCensusOutputRecord" minOccurs="0"/&gt;
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
 *         &lt;element name="MNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MNumberFractional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPreDirectional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPreType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPreQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPreArticle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPostArticle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPostQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPostDirectional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MSuiteType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MSuiteNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MConsolidatedCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MMinorCivilDivision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MCountySubRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MCounty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MZipPlus1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MZipPlus2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MZipPlus3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MZipPlus4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MZipPlus5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPostOfficeBoxType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MPostOfficeBoxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PNumberFractional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPreDirectional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPreType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPreQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPreArticle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPostArticle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPostQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPostDirectional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PSuiteType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PSuiteNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PConsolidatedCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PMinorCivilDivision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PCountySubRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PCounty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PZipPlus1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PZipPlus2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PZipPlus3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PZipPlus4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PZipPlus5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPostOfficeBoxType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PPostOfficeBoxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FNumberFractional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPreDirectional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPreType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPreQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPreArticle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPostArticle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPostQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPostDirectional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FSuiteType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FSuiteNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FConsolidatedCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FMinorCivilDivision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FCountySubRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FCounty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FZipPlus1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FZipPlus2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FZipPlus3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FZipPlus4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FZipPlus5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPostOfficeBoxType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPostOfficeBoxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FAreaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FGeometrySRID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="FGeometry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FVintage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPrimaryIdField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FPrimaryIdValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FSecondaryIdField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FSecondaryIdValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebServiceGeocodeQueryResult", propOrder = {
        "transactionId",
        "latitude",
        "longitude",
        "version",
        "quality",
        "matchedLocationType",
        "matchType",
        "featureMatchingResultCount",
        "featureMatchingResultTypeNotes",
        "featureMatchingResultTypeTieBreakingNotes",
        "tieHandlingStrategyType",
        "featureMatchingSelectionMethod",
        "featureMatchingSelectionMethodNotes",
        "queryStatusCodes",
        "geocodeQualityType",
        "naaccrgisCoordinateQualityCode",
        "naaccrgisCoordinateQualityName",
        "naaccrgisCoordinateQualityType",
        "naaccrCensusTractCertaintyCode",
        "naaccrCensusTractCertaintyName",
        "naaccrCensusTractCertaintyType",
        "featureMatchingGeographyType",
        "featureMatchingResultType",
        "matchScore",
        "microMatchStatus",
        "penaltyCodeResult",
        "penaltyCode",
        "penaltyCodeSummary",
        "interpolationType",
        "interpolationSubType",
        "regionSize",
        "regionSizeUnits",
        "queryStatusCodeName",
        "queryStatusCodeValue",
        "exceptionOccurred",
        "errorMessage",
        "timeTaken",
        "censusRecords",
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
        "mNumber",
        "mNumberFractional",
        "mPreDirectional",
        "mPreType",
        "mPreQualifier",
        "mPreArticle",
        "mName",
        "mSuffix",
        "mPostArticle",
        "mPostQualifier",
        "mPostDirectional",
        "mSuiteType",
        "mSuiteNumber",
        "mCity",
        "mConsolidatedCity",
        "mMinorCivilDivision",
        "mCountySubRegion",
        "mCounty",
        "mState",
        "mZip",
        "mZipPlus1",
        "mZipPlus2",
        "mZipPlus3",
        "mZipPlus4",
        "mZipPlus5",
        "mPostOfficeBoxType",
        "mPostOfficeBoxNumber",
        "pNumber",
        "pNumberFractional",
        "pPreDirectional",
        "pPreType",
        "pPreQualifier",
        "pPreArticle",
        "pName",
        "pSuffix",
        "pPostArticle",
        "pPostQualifier",
        "pPostDirectional",
        "pSuiteType",
        "pSuiteNumber",
        "pCity",
        "pConsolidatedCity",
        "pMinorCivilDivision",
        "pCountySubRegion",
        "pCounty",
        "pState",
        "pZip",
        "pZipPlus1",
        "pZipPlus2",
        "pZipPlus3",
        "pZipPlus4",
        "pZipPlus5",
        "pPostOfficeBoxType",
        "pPostOfficeBoxNumber",
        "fNumber",
        "fNumberFractional",
        "fPreDirectional",
        "fPreType",
        "fPreQualifier",
        "fPreArticle",
        "fName",
        "fSuffix",
        "fPostArticle",
        "fPostQualifier",
        "fPostDirectional",
        "fSuiteType",
        "fSuiteNumber",
        "fCity",
        "fConsolidatedCity",
        "fMinorCivilDivision",
        "fCountySubRegion",
        "fCounty",
        "fState",
        "fZip",
        "fZipPlus1",
        "fZipPlus2",
        "fZipPlus3",
        "fZipPlus4",
        "fZipPlus5",
        "fPostOfficeBoxType",
        "fPostOfficeBoxNumber",
        "fArea",
        "fAreaType",
        "fGeometrySRID",
        "fGeometry",
        "fSource",
        "fVintage",
        "fPrimaryIdField",
        "fPrimaryIdValue",
        "fSecondaryIdField",
        "fSecondaryIdValue"
})
public class WebServiceGeocodeQueryResult {

    @XmlElement(name = "TransactionId")
    protected String transactionId;
    @XmlElement(name = "Latitude")
    protected double latitude;
    @XmlElement(name = "Longitude")
    protected double longitude;
    @XmlElement(name = "Version")
    protected double version;
    @XmlElement(name = "Quality")
    protected String quality;
    @XmlElement(name = "MatchedLocationType")
    protected String matchedLocationType;
    @XmlElement(name = "MatchType")
    protected String matchType;
    @XmlElement(name = "FeatureMatchingResultCount")
    protected String featureMatchingResultCount;
    @XmlElement(name = "FeatureMatchingResultTypeNotes")
    protected String featureMatchingResultTypeNotes;
    @XmlElement(name = "FeatureMatchingResultTypeTieBreakingNotes")
    protected String featureMatchingResultTypeTieBreakingNotes;
    @XmlElement(name = "TieHandlingStrategyType", required = true)
    @XmlSchemaType(name = "string")
    protected TieHandlingStrategyType tieHandlingStrategyType;
    @XmlElement(name = "FeatureMatchingSelectionMethod", required = true)
    @XmlSchemaType(name = "string")
    protected FeatureMatchingSelectionMethod featureMatchingSelectionMethod;
    @XmlElement(name = "FeatureMatchingSelectionMethodNotes")
    protected String featureMatchingSelectionMethodNotes;
    @XmlElement(name = "QueryStatusCodes", required = true)
    @XmlSchemaType(name = "string")
    protected QueryStatusCodes queryStatusCodes;
    @XmlElement(name = "GeocodeQualityType", required = true)
    @XmlSchemaType(name = "string")
    protected GeocodeQualityType geocodeQualityType;
    @XmlElement(name = "NAACCRGISCoordinateQualityCode")
    protected String naaccrgisCoordinateQualityCode;
    @XmlElement(name = "NAACCRGISCoordinateQualityName")
    protected String naaccrgisCoordinateQualityName;
    @XmlElement(name = "NAACCRGISCoordinateQualityType", required = true)
    @XmlSchemaType(name = "string")
    protected NAACCRGISCoordinateQualityType naaccrgisCoordinateQualityType;
    @XmlElement(name = "NAACCRCensusTractCertaintyCode")
    protected String naaccrCensusTractCertaintyCode;
    @XmlElement(name = "NAACCRCensusTractCertaintyName")
    protected String naaccrCensusTractCertaintyName;
    @XmlElement(name = "NAACCRCensusTractCertaintyType", required = true)
    @XmlSchemaType(name = "string")
    protected NAACCRCensusTractCertaintyType naaccrCensusTractCertaintyType;
    @XmlElement(name = "FeatureMatchingGeographyType", required = true)
    @XmlSchemaType(name = "string")
    protected FeatureMatchingGeographyType featureMatchingGeographyType;
    @XmlList
    @XmlElement(name = "FeatureMatchingResultType", required = true)
    protected List<String> featureMatchingResultType;
    @XmlElement(name = "MatchScore")
    protected double matchScore;
    @XmlElement(name = "MicroMatchStatus")
    protected String microMatchStatus;
    @XmlElement(name = "PenaltyCodeResult")
    protected PenaltyCodeResult penaltyCodeResult;
    @XmlElement(name = "PenaltyCode")
    protected String penaltyCode;
    @XmlElement(name = "PenaltyCodeSummary")
    protected String penaltyCodeSummary;
    @XmlElement(name = "InterpolationType", required = true)
    @XmlSchemaType(name = "string")
    protected InterpolationType interpolationType;
    @XmlElement(name = "InterpolationSubType", required = true)
    @XmlSchemaType(name = "string")
    protected InterpolationSubType interpolationSubType;
    @XmlElement(name = "RegionSize")
    protected String regionSize;
    @XmlElement(name = "RegionSizeUnits")
    protected String regionSizeUnits;
    @XmlElement(name = "QueryStatusCodeName")
    protected String queryStatusCodeName;
    @XmlElement(name = "QueryStatusCodeValue")
    protected int queryStatusCodeValue;
    @XmlElement(name = "ExceptionOccurred")
    protected boolean exceptionOccurred;
    @XmlElement(name = "ErrorMessage")
    protected String errorMessage;
    @XmlElement(name = "TimeTaken")
    protected String timeTaken;
    @XmlElement(name = "CensusRecords")
    protected ArrayOfCensusOutputRecord censusRecords;
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
    @XmlElement(name = "MNumber")
    protected String mNumber;
    @XmlElement(name = "MNumberFractional")
    protected String mNumberFractional;
    @XmlElement(name = "MPreDirectional")
    protected String mPreDirectional;
    @XmlElement(name = "MPreType")
    protected String mPreType;
    @XmlElement(name = "MPreQualifier")
    protected String mPreQualifier;
    @XmlElement(name = "MPreArticle")
    protected String mPreArticle;
    @XmlElement(name = "MName")
    protected String mName;
    @XmlElement(name = "MSuffix")
    protected String mSuffix;
    @XmlElement(name = "MPostArticle")
    protected String mPostArticle;
    @XmlElement(name = "MPostQualifier")
    protected String mPostQualifier;
    @XmlElement(name = "MPostDirectional")
    protected String mPostDirectional;
    @XmlElement(name = "MSuiteType")
    protected String mSuiteType;
    @XmlElement(name = "MSuiteNumber")
    protected String mSuiteNumber;
    @XmlElement(name = "MCity")
    protected String mCity;
    @XmlElement(name = "MConsolidatedCity")
    protected String mConsolidatedCity;
    @XmlElement(name = "MMinorCivilDivision")
    protected String mMinorCivilDivision;
    @XmlElement(name = "MCountySubRegion")
    protected String mCountySubRegion;
    @XmlElement(name = "MCounty")
    protected String mCounty;
    @XmlElement(name = "MState")
    protected String mState;
    @XmlElement(name = "MZip")
    protected String mZip;
    @XmlElement(name = "MZipPlus1")
    protected String mZipPlus1;
    @XmlElement(name = "MZipPlus2")
    protected String mZipPlus2;
    @XmlElement(name = "MZipPlus3")
    protected String mZipPlus3;
    @XmlElement(name = "MZipPlus4")
    protected String mZipPlus4;
    @XmlElement(name = "MZipPlus5")
    protected String mZipPlus5;
    @XmlElement(name = "MPostOfficeBoxType")
    protected String mPostOfficeBoxType;
    @XmlElement(name = "MPostOfficeBoxNumber")
    protected String mPostOfficeBoxNumber;
    @XmlElement(name = "PNumber")
    protected String pNumber;
    @XmlElement(name = "PNumberFractional")
    protected String pNumberFractional;
    @XmlElement(name = "PPreDirectional")
    protected String pPreDirectional;
    @XmlElement(name = "PPreType")
    protected String pPreType;
    @XmlElement(name = "PPreQualifier")
    protected String pPreQualifier;
    @XmlElement(name = "PPreArticle")
    protected String pPreArticle;
    @XmlElement(name = "PName")
    protected String pName;
    @XmlElement(name = "PSuffix")
    protected String pSuffix;
    @XmlElement(name = "PPostArticle")
    protected String pPostArticle;
    @XmlElement(name = "PPostQualifier")
    protected String pPostQualifier;
    @XmlElement(name = "PPostDirectional")
    protected String pPostDirectional;
    @XmlElement(name = "PSuiteType")
    protected String pSuiteType;
    @XmlElement(name = "PSuiteNumber")
    protected String pSuiteNumber;
    @XmlElement(name = "PCity")
    protected String pCity;
    @XmlElement(name = "PConsolidatedCity")
    protected String pConsolidatedCity;
    @XmlElement(name = "PMinorCivilDivision")
    protected String pMinorCivilDivision;
    @XmlElement(name = "PCountySubRegion")
    protected String pCountySubRegion;
    @XmlElement(name = "PCounty")
    protected String pCounty;
    @XmlElement(name = "PState")
    protected String pState;
    @XmlElement(name = "PZip")
    protected String pZip;
    @XmlElement(name = "PZipPlus1")
    protected String pZipPlus1;
    @XmlElement(name = "PZipPlus2")
    protected String pZipPlus2;
    @XmlElement(name = "PZipPlus3")
    protected String pZipPlus3;
    @XmlElement(name = "PZipPlus4")
    protected String pZipPlus4;
    @XmlElement(name = "PZipPlus5")
    protected String pZipPlus5;
    @XmlElement(name = "PPostOfficeBoxType")
    protected String pPostOfficeBoxType;
    @XmlElement(name = "PPostOfficeBoxNumber")
    protected String pPostOfficeBoxNumber;
    @XmlElement(name = "FNumber")
    protected String fNumber;
    @XmlElement(name = "FNumberFractional")
    protected String fNumberFractional;
    @XmlElement(name = "FPreDirectional")
    protected String fPreDirectional;
    @XmlElement(name = "FPreType")
    protected String fPreType;
    @XmlElement(name = "FPreQualifier")
    protected String fPreQualifier;
    @XmlElement(name = "FPreArticle")
    protected String fPreArticle;
    @XmlElement(name = "FName")
    protected String fName;
    @XmlElement(name = "FSuffix")
    protected String fSuffix;
    @XmlElement(name = "FPostArticle")
    protected String fPostArticle;
    @XmlElement(name = "FPostQualifier")
    protected String fPostQualifier;
    @XmlElement(name = "FPostDirectional")
    protected String fPostDirectional;
    @XmlElement(name = "FSuiteType")
    protected String fSuiteType;
    @XmlElement(name = "FSuiteNumber")
    protected String fSuiteNumber;
    @XmlElement(name = "FCity")
    protected String fCity;
    @XmlElement(name = "FConsolidatedCity")
    protected String fConsolidatedCity;
    @XmlElement(name = "FMinorCivilDivision")
    protected String fMinorCivilDivision;
    @XmlElement(name = "FCountySubRegion")
    protected String fCountySubRegion;
    @XmlElement(name = "FCounty")
    protected String fCounty;
    @XmlElement(name = "FState")
    protected String fState;
    @XmlElement(name = "FZip")
    protected String fZip;
    @XmlElement(name = "FZipPlus1")
    protected String fZipPlus1;
    @XmlElement(name = "FZipPlus2")
    protected String fZipPlus2;
    @XmlElement(name = "FZipPlus3")
    protected String fZipPlus3;
    @XmlElement(name = "FZipPlus4")
    protected String fZipPlus4;
    @XmlElement(name = "FZipPlus5")
    protected String fZipPlus5;
    @XmlElement(name = "FPostOfficeBoxType")
    protected String fPostOfficeBoxType;
    @XmlElement(name = "FPostOfficeBoxNumber")
    protected String fPostOfficeBoxNumber;
    @XmlElement(name = "FArea")
    protected String fArea;
    @XmlElement(name = "FAreaType")
    protected String fAreaType;
    @XmlElement(name = "FGeometrySRID")
    protected int fGeometrySRID;
    @XmlElement(name = "FGeometry")
    protected String fGeometry;
    @XmlElement(name = "FSource")
    protected String fSource;
    @XmlElement(name = "FVintage")
    protected String fVintage;
    @XmlElement(name = "FPrimaryIdField")
    protected String fPrimaryIdField;
    @XmlElement(name = "FPrimaryIdValue")
    protected String fPrimaryIdValue;
    @XmlElement(name = "FSecondaryIdField")
    protected String fSecondaryIdField;
    @XmlElement(name = "FSecondaryIdValue")
    protected String fSecondaryIdValue;

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
     * Gets the value of the latitude property.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     */
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     */
    public void setLongitude(double value) {
        this.longitude = value;
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
     * Gets the value of the quality property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getQuality() {
        return quality;
    }

    /**
     * Sets the value of the quality property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQuality(String value) {
        this.quality = value;
    }

    /**
     * Gets the value of the matchedLocationType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMatchedLocationType() {
        return matchedLocationType;
    }

    /**
     * Sets the value of the matchedLocationType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMatchedLocationType(String value) {
        this.matchedLocationType = value;
    }

    /**
     * Gets the value of the matchType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * Sets the value of the matchType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMatchType(String value) {
        this.matchType = value;
    }

    /**
     * Gets the value of the featureMatchingResultCount property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFeatureMatchingResultCount() {
        return featureMatchingResultCount;
    }

    /**
     * Sets the value of the featureMatchingResultCount property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFeatureMatchingResultCount(String value) {
        this.featureMatchingResultCount = value;
    }

    /**
     * Gets the value of the featureMatchingResultTypeNotes property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFeatureMatchingResultTypeNotes() {
        return featureMatchingResultTypeNotes;
    }

    /**
     * Sets the value of the featureMatchingResultTypeNotes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFeatureMatchingResultTypeNotes(String value) {
        this.featureMatchingResultTypeNotes = value;
    }

    /**
     * Gets the value of the featureMatchingResultTypeTieBreakingNotes property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFeatureMatchingResultTypeTieBreakingNotes() {
        return featureMatchingResultTypeTieBreakingNotes;
    }

    /**
     * Sets the value of the featureMatchingResultTypeTieBreakingNotes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFeatureMatchingResultTypeTieBreakingNotes(String value) {
        this.featureMatchingResultTypeTieBreakingNotes = value;
    }

    /**
     * Gets the value of the tieHandlingStrategyType property.
     *
     * @return possible object is
     * {@link TieHandlingStrategyType }
     */
    public TieHandlingStrategyType getTieHandlingStrategyType() {
        return tieHandlingStrategyType;
    }

    /**
     * Sets the value of the tieHandlingStrategyType property.
     *
     * @param value allowed object is
     *              {@link TieHandlingStrategyType }
     */
    public void setTieHandlingStrategyType(TieHandlingStrategyType value) {
        this.tieHandlingStrategyType = value;
    }

    /**
     * Gets the value of the featureMatchingSelectionMethod property.
     *
     * @return possible object is
     * {@link FeatureMatchingSelectionMethod }
     */
    public FeatureMatchingSelectionMethod getFeatureMatchingSelectionMethod() {
        return featureMatchingSelectionMethod;
    }

    /**
     * Sets the value of the featureMatchingSelectionMethod property.
     *
     * @param value allowed object is
     *              {@link FeatureMatchingSelectionMethod }
     */
    public void setFeatureMatchingSelectionMethod(FeatureMatchingSelectionMethod value) {
        this.featureMatchingSelectionMethod = value;
    }

    /**
     * Gets the value of the featureMatchingSelectionMethodNotes property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFeatureMatchingSelectionMethodNotes() {
        return featureMatchingSelectionMethodNotes;
    }

    /**
     * Sets the value of the featureMatchingSelectionMethodNotes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFeatureMatchingSelectionMethodNotes(String value) {
        this.featureMatchingSelectionMethodNotes = value;
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
     * Gets the value of the geocodeQualityType property.
     *
     * @return possible object is
     * {@link GeocodeQualityType }
     */
    public GeocodeQualityType getGeocodeQualityType() {
        return geocodeQualityType;
    }

    /**
     * Sets the value of the geocodeQualityType property.
     *
     * @param value allowed object is
     *              {@link GeocodeQualityType }
     */
    public void setGeocodeQualityType(GeocodeQualityType value) {
        this.geocodeQualityType = value;
    }

    /**
     * Gets the value of the naaccrgisCoordinateQualityCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNAACCRGISCoordinateQualityCode() {
        return naaccrgisCoordinateQualityCode;
    }

    /**
     * Sets the value of the naaccrgisCoordinateQualityCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNAACCRGISCoordinateQualityCode(String value) {
        this.naaccrgisCoordinateQualityCode = value;
    }

    /**
     * Gets the value of the naaccrgisCoordinateQualityName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNAACCRGISCoordinateQualityName() {
        return naaccrgisCoordinateQualityName;
    }

    /**
     * Sets the value of the naaccrgisCoordinateQualityName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNAACCRGISCoordinateQualityName(String value) {
        this.naaccrgisCoordinateQualityName = value;
    }

    /**
     * Gets the value of the naaccrgisCoordinateQualityType property.
     *
     * @return possible object is
     * {@link NAACCRGISCoordinateQualityType }
     */
    public NAACCRGISCoordinateQualityType getNAACCRGISCoordinateQualityType() {
        return naaccrgisCoordinateQualityType;
    }

    /**
     * Sets the value of the naaccrgisCoordinateQualityType property.
     *
     * @param value allowed object is
     *              {@link NAACCRGISCoordinateQualityType }
     */
    public void setNAACCRGISCoordinateQualityType(NAACCRGISCoordinateQualityType value) {
        this.naaccrgisCoordinateQualityType = value;
    }

    /**
     * Gets the value of the naaccrCensusTractCertaintyCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNAACCRCensusTractCertaintyCode() {
        return naaccrCensusTractCertaintyCode;
    }

    /**
     * Sets the value of the naaccrCensusTractCertaintyCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNAACCRCensusTractCertaintyCode(String value) {
        this.naaccrCensusTractCertaintyCode = value;
    }

    /**
     * Gets the value of the naaccrCensusTractCertaintyName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNAACCRCensusTractCertaintyName() {
        return naaccrCensusTractCertaintyName;
    }

    /**
     * Sets the value of the naaccrCensusTractCertaintyName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNAACCRCensusTractCertaintyName(String value) {
        this.naaccrCensusTractCertaintyName = value;
    }

    /**
     * Gets the value of the naaccrCensusTractCertaintyType property.
     *
     * @return possible object is
     * {@link NAACCRCensusTractCertaintyType }
     */
    public NAACCRCensusTractCertaintyType getNAACCRCensusTractCertaintyType() {
        return naaccrCensusTractCertaintyType;
    }

    /**
     * Sets the value of the naaccrCensusTractCertaintyType property.
     *
     * @param value allowed object is
     *              {@link NAACCRCensusTractCertaintyType }
     */
    public void setNAACCRCensusTractCertaintyType(NAACCRCensusTractCertaintyType value) {
        this.naaccrCensusTractCertaintyType = value;
    }

    /**
     * Gets the value of the featureMatchingGeographyType property.
     *
     * @return possible object is
     * {@link FeatureMatchingGeographyType }
     */
    public FeatureMatchingGeographyType getFeatureMatchingGeographyType() {
        return featureMatchingGeographyType;
    }

    /**
     * Sets the value of the featureMatchingGeographyType property.
     *
     * @param value allowed object is
     *              {@link FeatureMatchingGeographyType }
     */
    public void setFeatureMatchingGeographyType(FeatureMatchingGeographyType value) {
        this.featureMatchingGeographyType = value;
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
     * Gets the value of the matchScore property.
     */
    public double getMatchScore() {
        return matchScore;
    }

    /**
     * Sets the value of the matchScore property.
     */
    public void setMatchScore(double value) {
        this.matchScore = value;
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
     * Gets the value of the interpolationType property.
     *
     * @return possible object is
     * {@link InterpolationType }
     */
    public InterpolationType getInterpolationType() {
        return interpolationType;
    }

    /**
     * Sets the value of the interpolationType property.
     *
     * @param value allowed object is
     *              {@link InterpolationType }
     */
    public void setInterpolationType(InterpolationType value) {
        this.interpolationType = value;
    }

    /**
     * Gets the value of the interpolationSubType property.
     *
     * @return possible object is
     * {@link InterpolationSubType }
     */
    public InterpolationSubType getInterpolationSubType() {
        return interpolationSubType;
    }

    /**
     * Sets the value of the interpolationSubType property.
     *
     * @param value allowed object is
     *              {@link InterpolationSubType }
     */
    public void setInterpolationSubType(InterpolationSubType value) {
        this.interpolationSubType = value;
    }

    /**
     * Gets the value of the regionSize property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRegionSize() {
        return regionSize;
    }

    /**
     * Sets the value of the regionSize property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegionSize(String value) {
        this.regionSize = value;
    }

    /**
     * Gets the value of the regionSizeUnits property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRegionSizeUnits() {
        return regionSizeUnits;
    }

    /**
     * Sets the value of the regionSizeUnits property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegionSizeUnits(String value) {
        this.regionSizeUnits = value;
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
     * Gets the value of the censusRecords property.
     *
     * @return possible object is
     * {@link ArrayOfCensusOutputRecord }
     */
    public ArrayOfCensusOutputRecord getCensusRecords() {
        return censusRecords;
    }

    /**
     * Sets the value of the censusRecords property.
     *
     * @param value allowed object is
     *              {@link ArrayOfCensusOutputRecord }
     */
    public void setCensusRecords(ArrayOfCensusOutputRecord value) {
        this.censusRecords = value;
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
     * Gets the value of the mNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMNumber() {
        return mNumber;
    }

    /**
     * Sets the value of the mNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMNumber(String value) {
        this.mNumber = value;
    }

    /**
     * Gets the value of the mNumberFractional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMNumberFractional() {
        return mNumberFractional;
    }

    /**
     * Sets the value of the mNumberFractional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMNumberFractional(String value) {
        this.mNumberFractional = value;
    }

    /**
     * Gets the value of the mPreDirectional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPreDirectional() {
        return mPreDirectional;
    }

    /**
     * Sets the value of the mPreDirectional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPreDirectional(String value) {
        this.mPreDirectional = value;
    }

    /**
     * Gets the value of the mPreType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPreType() {
        return mPreType;
    }

    /**
     * Sets the value of the mPreType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPreType(String value) {
        this.mPreType = value;
    }

    /**
     * Gets the value of the mPreQualifier property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPreQualifier() {
        return mPreQualifier;
    }

    /**
     * Sets the value of the mPreQualifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPreQualifier(String value) {
        this.mPreQualifier = value;
    }

    /**
     * Gets the value of the mPreArticle property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPreArticle() {
        return mPreArticle;
    }

    /**
     * Sets the value of the mPreArticle property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPreArticle(String value) {
        this.mPreArticle = value;
    }

    /**
     * Gets the value of the mName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMName() {
        return mName;
    }

    /**
     * Sets the value of the mName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMName(String value) {
        this.mName = value;
    }

    /**
     * Gets the value of the mSuffix property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMSuffix() {
        return mSuffix;
    }

    /**
     * Sets the value of the mSuffix property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMSuffix(String value) {
        this.mSuffix = value;
    }

    /**
     * Gets the value of the mPostArticle property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPostArticle() {
        return mPostArticle;
    }

    /**
     * Sets the value of the mPostArticle property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPostArticle(String value) {
        this.mPostArticle = value;
    }

    /**
     * Gets the value of the mPostQualifier property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPostQualifier() {
        return mPostQualifier;
    }

    /**
     * Sets the value of the mPostQualifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPostQualifier(String value) {
        this.mPostQualifier = value;
    }

    /**
     * Gets the value of the mPostDirectional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPostDirectional() {
        return mPostDirectional;
    }

    /**
     * Sets the value of the mPostDirectional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPostDirectional(String value) {
        this.mPostDirectional = value;
    }

    /**
     * Gets the value of the mSuiteType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMSuiteType() {
        return mSuiteType;
    }

    /**
     * Sets the value of the mSuiteType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMSuiteType(String value) {
        this.mSuiteType = value;
    }

    /**
     * Gets the value of the mSuiteNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMSuiteNumber() {
        return mSuiteNumber;
    }

    /**
     * Sets the value of the mSuiteNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMSuiteNumber(String value) {
        this.mSuiteNumber = value;
    }

    /**
     * Gets the value of the mCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMCity() {
        return mCity;
    }

    /**
     * Sets the value of the mCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMCity(String value) {
        this.mCity = value;
    }

    /**
     * Gets the value of the mConsolidatedCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMConsolidatedCity() {
        return mConsolidatedCity;
    }

    /**
     * Sets the value of the mConsolidatedCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMConsolidatedCity(String value) {
        this.mConsolidatedCity = value;
    }

    /**
     * Gets the value of the mMinorCivilDivision property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMMinorCivilDivision() {
        return mMinorCivilDivision;
    }

    /**
     * Sets the value of the mMinorCivilDivision property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMMinorCivilDivision(String value) {
        this.mMinorCivilDivision = value;
    }

    /**
     * Gets the value of the mCountySubRegion property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMCountySubRegion() {
        return mCountySubRegion;
    }

    /**
     * Sets the value of the mCountySubRegion property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMCountySubRegion(String value) {
        this.mCountySubRegion = value;
    }

    /**
     * Gets the value of the mCounty property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMCounty() {
        return mCounty;
    }

    /**
     * Sets the value of the mCounty property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMCounty(String value) {
        this.mCounty = value;
    }

    /**
     * Gets the value of the mState property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMState() {
        return mState;
    }

    /**
     * Sets the value of the mState property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMState(String value) {
        this.mState = value;
    }

    /**
     * Gets the value of the mZip property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMZip() {
        return mZip;
    }

    /**
     * Sets the value of the mZip property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMZip(String value) {
        this.mZip = value;
    }

    /**
     * Gets the value of the mZipPlus1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMZipPlus1() {
        return mZipPlus1;
    }

    /**
     * Sets the value of the mZipPlus1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMZipPlus1(String value) {
        this.mZipPlus1 = value;
    }

    /**
     * Gets the value of the mZipPlus2 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMZipPlus2() {
        return mZipPlus2;
    }

    /**
     * Sets the value of the mZipPlus2 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMZipPlus2(String value) {
        this.mZipPlus2 = value;
    }

    /**
     * Gets the value of the mZipPlus3 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMZipPlus3() {
        return mZipPlus3;
    }

    /**
     * Sets the value of the mZipPlus3 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMZipPlus3(String value) {
        this.mZipPlus3 = value;
    }

    /**
     * Gets the value of the mZipPlus4 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMZipPlus4() {
        return mZipPlus4;
    }

    /**
     * Sets the value of the mZipPlus4 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMZipPlus4(String value) {
        this.mZipPlus4 = value;
    }

    /**
     * Gets the value of the mZipPlus5 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMZipPlus5() {
        return mZipPlus5;
    }

    /**
     * Sets the value of the mZipPlus5 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMZipPlus5(String value) {
        this.mZipPlus5 = value;
    }

    /**
     * Gets the value of the mPostOfficeBoxType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPostOfficeBoxType() {
        return mPostOfficeBoxType;
    }

    /**
     * Sets the value of the mPostOfficeBoxType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPostOfficeBoxType(String value) {
        this.mPostOfficeBoxType = value;
    }

    /**
     * Gets the value of the mPostOfficeBoxNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPostOfficeBoxNumber() {
        return mPostOfficeBoxNumber;
    }

    /**
     * Sets the value of the mPostOfficeBoxNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPostOfficeBoxNumber(String value) {
        this.mPostOfficeBoxNumber = value;
    }

    /**
     * Gets the value of the pNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPNumber() {
        return pNumber;
    }

    /**
     * Sets the value of the pNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPNumber(String value) {
        this.pNumber = value;
    }

    /**
     * Gets the value of the pNumberFractional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPNumberFractional() {
        return pNumberFractional;
    }

    /**
     * Sets the value of the pNumberFractional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPNumberFractional(String value) {
        this.pNumberFractional = value;
    }

    /**
     * Gets the value of the pPreDirectional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPreDirectional() {
        return pPreDirectional;
    }

    /**
     * Sets the value of the pPreDirectional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPreDirectional(String value) {
        this.pPreDirectional = value;
    }

    /**
     * Gets the value of the pPreType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPreType() {
        return pPreType;
    }

    /**
     * Sets the value of the pPreType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPreType(String value) {
        this.pPreType = value;
    }

    /**
     * Gets the value of the pPreQualifier property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPreQualifier() {
        return pPreQualifier;
    }

    /**
     * Sets the value of the pPreQualifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPreQualifier(String value) {
        this.pPreQualifier = value;
    }

    /**
     * Gets the value of the pPreArticle property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPreArticle() {
        return pPreArticle;
    }

    /**
     * Sets the value of the pPreArticle property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPreArticle(String value) {
        this.pPreArticle = value;
    }

    /**
     * Gets the value of the pName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPName() {
        return pName;
    }

    /**
     * Sets the value of the pName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPName(String value) {
        this.pName = value;
    }

    /**
     * Gets the value of the pSuffix property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPSuffix() {
        return pSuffix;
    }

    /**
     * Sets the value of the pSuffix property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPSuffix(String value) {
        this.pSuffix = value;
    }

    /**
     * Gets the value of the pPostArticle property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPostArticle() {
        return pPostArticle;
    }

    /**
     * Sets the value of the pPostArticle property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPostArticle(String value) {
        this.pPostArticle = value;
    }

    /**
     * Gets the value of the pPostQualifier property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPostQualifier() {
        return pPostQualifier;
    }

    /**
     * Sets the value of the pPostQualifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPostQualifier(String value) {
        this.pPostQualifier = value;
    }

    /**
     * Gets the value of the pPostDirectional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPostDirectional() {
        return pPostDirectional;
    }

    /**
     * Sets the value of the pPostDirectional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPostDirectional(String value) {
        this.pPostDirectional = value;
    }

    /**
     * Gets the value of the pSuiteType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPSuiteType() {
        return pSuiteType;
    }

    /**
     * Sets the value of the pSuiteType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPSuiteType(String value) {
        this.pSuiteType = value;
    }

    /**
     * Gets the value of the pSuiteNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPSuiteNumber() {
        return pSuiteNumber;
    }

    /**
     * Sets the value of the pSuiteNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPSuiteNumber(String value) {
        this.pSuiteNumber = value;
    }

    /**
     * Gets the value of the pCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPCity() {
        return pCity;
    }

    /**
     * Sets the value of the pCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPCity(String value) {
        this.pCity = value;
    }

    /**
     * Gets the value of the pConsolidatedCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPConsolidatedCity() {
        return pConsolidatedCity;
    }

    /**
     * Sets the value of the pConsolidatedCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPConsolidatedCity(String value) {
        this.pConsolidatedCity = value;
    }

    /**
     * Gets the value of the pMinorCivilDivision property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPMinorCivilDivision() {
        return pMinorCivilDivision;
    }

    /**
     * Sets the value of the pMinorCivilDivision property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPMinorCivilDivision(String value) {
        this.pMinorCivilDivision = value;
    }

    /**
     * Gets the value of the pCountySubRegion property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPCountySubRegion() {
        return pCountySubRegion;
    }

    /**
     * Sets the value of the pCountySubRegion property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPCountySubRegion(String value) {
        this.pCountySubRegion = value;
    }

    /**
     * Gets the value of the pCounty property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPCounty() {
        return pCounty;
    }

    /**
     * Sets the value of the pCounty property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPCounty(String value) {
        this.pCounty = value;
    }

    /**
     * Gets the value of the pState property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPState() {
        return pState;
    }

    /**
     * Sets the value of the pState property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPState(String value) {
        this.pState = value;
    }

    /**
     * Gets the value of the pZip property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPZip() {
        return pZip;
    }

    /**
     * Sets the value of the pZip property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPZip(String value) {
        this.pZip = value;
    }

    /**
     * Gets the value of the pZipPlus1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPZipPlus1() {
        return pZipPlus1;
    }

    /**
     * Sets the value of the pZipPlus1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPZipPlus1(String value) {
        this.pZipPlus1 = value;
    }

    /**
     * Gets the value of the pZipPlus2 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPZipPlus2() {
        return pZipPlus2;
    }

    /**
     * Sets the value of the pZipPlus2 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPZipPlus2(String value) {
        this.pZipPlus2 = value;
    }

    /**
     * Gets the value of the pZipPlus3 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPZipPlus3() {
        return pZipPlus3;
    }

    /**
     * Sets the value of the pZipPlus3 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPZipPlus3(String value) {
        this.pZipPlus3 = value;
    }

    /**
     * Gets the value of the pZipPlus4 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPZipPlus4() {
        return pZipPlus4;
    }

    /**
     * Sets the value of the pZipPlus4 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPZipPlus4(String value) {
        this.pZipPlus4 = value;
    }

    /**
     * Gets the value of the pZipPlus5 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPZipPlus5() {
        return pZipPlus5;
    }

    /**
     * Sets the value of the pZipPlus5 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPZipPlus5(String value) {
        this.pZipPlus5 = value;
    }

    /**
     * Gets the value of the pPostOfficeBoxType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPostOfficeBoxType() {
        return pPostOfficeBoxType;
    }

    /**
     * Sets the value of the pPostOfficeBoxType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPostOfficeBoxType(String value) {
        this.pPostOfficeBoxType = value;
    }

    /**
     * Gets the value of the pPostOfficeBoxNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPPostOfficeBoxNumber() {
        return pPostOfficeBoxNumber;
    }

    /**
     * Sets the value of the pPostOfficeBoxNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPPostOfficeBoxNumber(String value) {
        this.pPostOfficeBoxNumber = value;
    }

    /**
     * Gets the value of the fNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFNumber() {
        return fNumber;
    }

    /**
     * Sets the value of the fNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFNumber(String value) {
        this.fNumber = value;
    }

    /**
     * Gets the value of the fNumberFractional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFNumberFractional() {
        return fNumberFractional;
    }

    /**
     * Sets the value of the fNumberFractional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFNumberFractional(String value) {
        this.fNumberFractional = value;
    }

    /**
     * Gets the value of the fPreDirectional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPreDirectional() {
        return fPreDirectional;
    }

    /**
     * Sets the value of the fPreDirectional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPreDirectional(String value) {
        this.fPreDirectional = value;
    }

    /**
     * Gets the value of the fPreType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPreType() {
        return fPreType;
    }

    /**
     * Sets the value of the fPreType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPreType(String value) {
        this.fPreType = value;
    }

    /**
     * Gets the value of the fPreQualifier property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPreQualifier() {
        return fPreQualifier;
    }

    /**
     * Sets the value of the fPreQualifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPreQualifier(String value) {
        this.fPreQualifier = value;
    }

    /**
     * Gets the value of the fPreArticle property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPreArticle() {
        return fPreArticle;
    }

    /**
     * Sets the value of the fPreArticle property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPreArticle(String value) {
        this.fPreArticle = value;
    }

    /**
     * Gets the value of the fName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFName() {
        return fName;
    }

    /**
     * Sets the value of the fName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFName(String value) {
        this.fName = value;
    }

    /**
     * Gets the value of the fSuffix property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFSuffix() {
        return fSuffix;
    }

    /**
     * Sets the value of the fSuffix property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFSuffix(String value) {
        this.fSuffix = value;
    }

    /**
     * Gets the value of the fPostArticle property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPostArticle() {
        return fPostArticle;
    }

    /**
     * Sets the value of the fPostArticle property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPostArticle(String value) {
        this.fPostArticle = value;
    }

    /**
     * Gets the value of the fPostQualifier property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPostQualifier() {
        return fPostQualifier;
    }

    /**
     * Sets the value of the fPostQualifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPostQualifier(String value) {
        this.fPostQualifier = value;
    }

    /**
     * Gets the value of the fPostDirectional property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPostDirectional() {
        return fPostDirectional;
    }

    /**
     * Sets the value of the fPostDirectional property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPostDirectional(String value) {
        this.fPostDirectional = value;
    }

    /**
     * Gets the value of the fSuiteType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFSuiteType() {
        return fSuiteType;
    }

    /**
     * Sets the value of the fSuiteType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFSuiteType(String value) {
        this.fSuiteType = value;
    }

    /**
     * Gets the value of the fSuiteNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFSuiteNumber() {
        return fSuiteNumber;
    }

    /**
     * Sets the value of the fSuiteNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFSuiteNumber(String value) {
        this.fSuiteNumber = value;
    }

    /**
     * Gets the value of the fCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFCity() {
        return fCity;
    }

    /**
     * Sets the value of the fCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFCity(String value) {
        this.fCity = value;
    }

    /**
     * Gets the value of the fConsolidatedCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFConsolidatedCity() {
        return fConsolidatedCity;
    }

    /**
     * Sets the value of the fConsolidatedCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFConsolidatedCity(String value) {
        this.fConsolidatedCity = value;
    }

    /**
     * Gets the value of the fMinorCivilDivision property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFMinorCivilDivision() {
        return fMinorCivilDivision;
    }

    /**
     * Sets the value of the fMinorCivilDivision property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFMinorCivilDivision(String value) {
        this.fMinorCivilDivision = value;
    }

    /**
     * Gets the value of the fCountySubRegion property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFCountySubRegion() {
        return fCountySubRegion;
    }

    /**
     * Sets the value of the fCountySubRegion property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFCountySubRegion(String value) {
        this.fCountySubRegion = value;
    }

    /**
     * Gets the value of the fCounty property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFCounty() {
        return fCounty;
    }

    /**
     * Sets the value of the fCounty property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFCounty(String value) {
        this.fCounty = value;
    }

    /**
     * Gets the value of the fState property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFState() {
        return fState;
    }

    /**
     * Sets the value of the fState property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFState(String value) {
        this.fState = value;
    }

    /**
     * Gets the value of the fZip property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFZip() {
        return fZip;
    }

    /**
     * Sets the value of the fZip property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFZip(String value) {
        this.fZip = value;
    }

    /**
     * Gets the value of the fZipPlus1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFZipPlus1() {
        return fZipPlus1;
    }

    /**
     * Sets the value of the fZipPlus1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFZipPlus1(String value) {
        this.fZipPlus1 = value;
    }

    /**
     * Gets the value of the fZipPlus2 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFZipPlus2() {
        return fZipPlus2;
    }

    /**
     * Sets the value of the fZipPlus2 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFZipPlus2(String value) {
        this.fZipPlus2 = value;
    }

    /**
     * Gets the value of the fZipPlus3 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFZipPlus3() {
        return fZipPlus3;
    }

    /**
     * Sets the value of the fZipPlus3 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFZipPlus3(String value) {
        this.fZipPlus3 = value;
    }

    /**
     * Gets the value of the fZipPlus4 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFZipPlus4() {
        return fZipPlus4;
    }

    /**
     * Sets the value of the fZipPlus4 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFZipPlus4(String value) {
        this.fZipPlus4 = value;
    }

    /**
     * Gets the value of the fZipPlus5 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFZipPlus5() {
        return fZipPlus5;
    }

    /**
     * Sets the value of the fZipPlus5 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFZipPlus5(String value) {
        this.fZipPlus5 = value;
    }

    /**
     * Gets the value of the fPostOfficeBoxType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPostOfficeBoxType() {
        return fPostOfficeBoxType;
    }

    /**
     * Sets the value of the fPostOfficeBoxType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPostOfficeBoxType(String value) {
        this.fPostOfficeBoxType = value;
    }

    /**
     * Gets the value of the fPostOfficeBoxNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPostOfficeBoxNumber() {
        return fPostOfficeBoxNumber;
    }

    /**
     * Sets the value of the fPostOfficeBoxNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPostOfficeBoxNumber(String value) {
        this.fPostOfficeBoxNumber = value;
    }

    /**
     * Gets the value of the fArea property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFArea() {
        return fArea;
    }

    /**
     * Sets the value of the fArea property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFArea(String value) {
        this.fArea = value;
    }

    /**
     * Gets the value of the fAreaType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFAreaType() {
        return fAreaType;
    }

    /**
     * Sets the value of the fAreaType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFAreaType(String value) {
        this.fAreaType = value;
    }

    /**
     * Gets the value of the fGeometrySRID property.
     */
    public int getFGeometrySRID() {
        return fGeometrySRID;
    }

    /**
     * Sets the value of the fGeometrySRID property.
     */
    public void setFGeometrySRID(int value) {
        this.fGeometrySRID = value;
    }

    /**
     * Gets the value of the fGeometry property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFGeometry() {
        return fGeometry;
    }

    /**
     * Sets the value of the fGeometry property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFGeometry(String value) {
        this.fGeometry = value;
    }

    /**
     * Gets the value of the fSource property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFSource() {
        return fSource;
    }

    /**
     * Sets the value of the fSource property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFSource(String value) {
        this.fSource = value;
    }

    /**
     * Gets the value of the fVintage property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFVintage() {
        return fVintage;
    }

    /**
     * Sets the value of the fVintage property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFVintage(String value) {
        this.fVintage = value;
    }

    /**
     * Gets the value of the fPrimaryIdField property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPrimaryIdField() {
        return fPrimaryIdField;
    }

    /**
     * Sets the value of the fPrimaryIdField property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPrimaryIdField(String value) {
        this.fPrimaryIdField = value;
    }

    /**
     * Gets the value of the fPrimaryIdValue property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFPrimaryIdValue() {
        return fPrimaryIdValue;
    }

    /**
     * Sets the value of the fPrimaryIdValue property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFPrimaryIdValue(String value) {
        this.fPrimaryIdValue = value;
    }

    /**
     * Gets the value of the fSecondaryIdField property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFSecondaryIdField() {
        return fSecondaryIdField;
    }

    /**
     * Sets the value of the fSecondaryIdField property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFSecondaryIdField(String value) {
        this.fSecondaryIdField = value;
    }

    /**
     * Gets the value of the fSecondaryIdValue property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFSecondaryIdValue() {
        return fSecondaryIdValue;
    }

    /**
     * Sets the value of the fSecondaryIdValue property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFSecondaryIdValue(String value) {
        this.fSecondaryIdValue = value;
    }

    public WebServiceGeocodeQueryResult withTransactionId(String value) {
        setTransactionId(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withLatitude(double value) {
        setLatitude(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withLongitude(double value) {
        setLongitude(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withVersion(double value) {
        setVersion(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withQuality(String value) {
        setQuality(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMatchedLocationType(String value) {
        setMatchedLocationType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMatchType(String value) {
        setMatchType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFeatureMatchingResultCount(String value) {
        setFeatureMatchingResultCount(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFeatureMatchingResultTypeNotes(String value) {
        setFeatureMatchingResultTypeNotes(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFeatureMatchingResultTypeTieBreakingNotes(String value) {
        setFeatureMatchingResultTypeTieBreakingNotes(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withTieHandlingStrategyType(TieHandlingStrategyType value) {
        setTieHandlingStrategyType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFeatureMatchingSelectionMethod(FeatureMatchingSelectionMethod value) {
        setFeatureMatchingSelectionMethod(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFeatureMatchingSelectionMethodNotes(String value) {
        setFeatureMatchingSelectionMethodNotes(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withQueryStatusCodes(QueryStatusCodes value) {
        setQueryStatusCodes(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withGeocodeQualityType(GeocodeQualityType value) {
        setGeocodeQualityType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withNAACCRGISCoordinateQualityCode(String value) {
        setNAACCRGISCoordinateQualityCode(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withNAACCRGISCoordinateQualityName(String value) {
        setNAACCRGISCoordinateQualityName(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withNAACCRGISCoordinateQualityType(NAACCRGISCoordinateQualityType value) {
        setNAACCRGISCoordinateQualityType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withNAACCRCensusTractCertaintyCode(String value) {
        setNAACCRCensusTractCertaintyCode(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withNAACCRCensusTractCertaintyName(String value) {
        setNAACCRCensusTractCertaintyName(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withNAACCRCensusTractCertaintyType(NAACCRCensusTractCertaintyType value) {
        setNAACCRCensusTractCertaintyType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFeatureMatchingGeographyType(FeatureMatchingGeographyType value) {
        setFeatureMatchingGeographyType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFeatureMatchingResultType(String... values) {
        if (values != null) {
            for (String value : values) {
                getFeatureMatchingResultType().add(value);
            }
        }
        return this;
    }

    public WebServiceGeocodeQueryResult withFeatureMatchingResultType(Collection<String> values) {
        if (values != null) {
            getFeatureMatchingResultType().addAll(values);
        }
        return this;
    }

    public WebServiceGeocodeQueryResult withMatchScore(double value) {
        setMatchScore(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMicroMatchStatus(String value) {
        setMicroMatchStatus(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPenaltyCodeResult(PenaltyCodeResult value) {
        setPenaltyCodeResult(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPenaltyCode(String value) {
        setPenaltyCode(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPenaltyCodeSummary(String value) {
        setPenaltyCodeSummary(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withInterpolationType(InterpolationType value) {
        setInterpolationType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withInterpolationSubType(InterpolationSubType value) {
        setInterpolationSubType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withRegionSize(String value) {
        setRegionSize(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withRegionSizeUnits(String value) {
        setRegionSizeUnits(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withQueryStatusCodeName(String value) {
        setQueryStatusCodeName(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withQueryStatusCodeValue(int value) {
        setQueryStatusCodeValue(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withExceptionOccurred(boolean value) {
        setExceptionOccurred(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withErrorMessage(String value) {
        setErrorMessage(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withTimeTaken(String value) {
        setTimeTaken(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusRecords(ArrayOfCensusOutputRecord value) {
        setCensusRecords(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusYear(CensusYear value) {
        setCensusYear(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusTimeTaken(double value) {
        setCensusTimeTaken(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusStateFips(String value) {
        setCensusStateFips(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusCountyFips(String value) {
        setCensusCountyFips(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusTract(String value) {
        setCensusTract(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusBlockGroup(String value) {
        setCensusBlockGroup(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusBlock(String value) {
        setCensusBlock(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusPlaceFips(String value) {
        setCensusPlaceFips(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusMcdFips(String value) {
        setCensusMcdFips(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusMsaFips(String value) {
        setCensusMsaFips(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusMetDivFips(String value) {
        setCensusMetDivFips(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusCbsaFips(String value) {
        setCensusCbsaFips(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withCensusCbsaMicro(String value) {
        setCensusCbsaMicro(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIIsPreParsed(boolean value) {
        setIIsPreParsed(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withINonParsedStreetAddress(String value) {
        setINonParsedStreetAddress(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withINumber(String value) {
        setINumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withINumberFractional(String value) {
        setINumberFractional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPreDirectional(String value) {
        setIPreDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPreType(String value) {
        setIPreType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPreQualifier(String value) {
        setIPreQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPreArticle(String value) {
        setIPreArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIName(String value) {
        setIName(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withISuffix(String value) {
        setISuffix(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPostArticle(String value) {
        setIPostArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPostQualifier(String value) {
        setIPostQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPostDirectional(String value) {
        setIPostDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withISuiteType(String value) {
        setISuiteType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withISuiteNumber(String value) {
        setISuiteNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withICity(String value) {
        setICity(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIConsolidatedCity(String value) {
        setIConsolidatedCity(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIMinorCivilDivision(String value) {
        setIMinorCivilDivision(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withICountySubRegion(String value) {
        setICountySubRegion(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withICounty(String value) {
        setICounty(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIState(String value) {
        setIState(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIZip(String value) {
        setIZip(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIZipPlus1(String value) {
        setIZipPlus1(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIZipPlus2(String value) {
        setIZipPlus2(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIZipPlus3(String value) {
        setIZipPlus3(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIZipPlus4(String value) {
        setIZipPlus4(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIZipPlus5(String value) {
        setIZipPlus5(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPostOfficeBoxType(String value) {
        setIPostOfficeBoxType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withIPostOfficeBoxNumber(String value) {
        setIPostOfficeBoxNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMNumber(String value) {
        setMNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMNumberFractional(String value) {
        setMNumberFractional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPreDirectional(String value) {
        setMPreDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPreType(String value) {
        setMPreType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPreQualifier(String value) {
        setMPreQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPreArticle(String value) {
        setMPreArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMName(String value) {
        setMName(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMSuffix(String value) {
        setMSuffix(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPostArticle(String value) {
        setMPostArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPostQualifier(String value) {
        setMPostQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPostDirectional(String value) {
        setMPostDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMSuiteType(String value) {
        setMSuiteType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMSuiteNumber(String value) {
        setMSuiteNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMCity(String value) {
        setMCity(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMConsolidatedCity(String value) {
        setMConsolidatedCity(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMMinorCivilDivision(String value) {
        setMMinorCivilDivision(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMCountySubRegion(String value) {
        setMCountySubRegion(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMCounty(String value) {
        setMCounty(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMState(String value) {
        setMState(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMZip(String value) {
        setMZip(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMZipPlus1(String value) {
        setMZipPlus1(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMZipPlus2(String value) {
        setMZipPlus2(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMZipPlus3(String value) {
        setMZipPlus3(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMZipPlus4(String value) {
        setMZipPlus4(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMZipPlus5(String value) {
        setMZipPlus5(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPostOfficeBoxType(String value) {
        setMPostOfficeBoxType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withMPostOfficeBoxNumber(String value) {
        setMPostOfficeBoxNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPNumber(String value) {
        setPNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPNumberFractional(String value) {
        setPNumberFractional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPreDirectional(String value) {
        setPPreDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPreType(String value) {
        setPPreType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPreQualifier(String value) {
        setPPreQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPreArticle(String value) {
        setPPreArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPName(String value) {
        setPName(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPSuffix(String value) {
        setPSuffix(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPostArticle(String value) {
        setPPostArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPostQualifier(String value) {
        setPPostQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPostDirectional(String value) {
        setPPostDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPSuiteType(String value) {
        setPSuiteType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPSuiteNumber(String value) {
        setPSuiteNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPCity(String value) {
        setPCity(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPConsolidatedCity(String value) {
        setPConsolidatedCity(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPMinorCivilDivision(String value) {
        setPMinorCivilDivision(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPCountySubRegion(String value) {
        setPCountySubRegion(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPCounty(String value) {
        setPCounty(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPState(String value) {
        setPState(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPZip(String value) {
        setPZip(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPZipPlus1(String value) {
        setPZipPlus1(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPZipPlus2(String value) {
        setPZipPlus2(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPZipPlus3(String value) {
        setPZipPlus3(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPZipPlus4(String value) {
        setPZipPlus4(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPZipPlus5(String value) {
        setPZipPlus5(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPostOfficeBoxType(String value) {
        setPPostOfficeBoxType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withPPostOfficeBoxNumber(String value) {
        setPPostOfficeBoxNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFNumber(String value) {
        setFNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFNumberFractional(String value) {
        setFNumberFractional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPreDirectional(String value) {
        setFPreDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPreType(String value) {
        setFPreType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPreQualifier(String value) {
        setFPreQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPreArticle(String value) {
        setFPreArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFName(String value) {
        setFName(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFSuffix(String value) {
        setFSuffix(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPostArticle(String value) {
        setFPostArticle(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPostQualifier(String value) {
        setFPostQualifier(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPostDirectional(String value) {
        setFPostDirectional(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFSuiteType(String value) {
        setFSuiteType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFSuiteNumber(String value) {
        setFSuiteNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFCity(String value) {
        setFCity(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFConsolidatedCity(String value) {
        setFConsolidatedCity(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFMinorCivilDivision(String value) {
        setFMinorCivilDivision(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFCountySubRegion(String value) {
        setFCountySubRegion(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFCounty(String value) {
        setFCounty(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFState(String value) {
        setFState(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFZip(String value) {
        setFZip(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFZipPlus1(String value) {
        setFZipPlus1(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFZipPlus2(String value) {
        setFZipPlus2(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFZipPlus3(String value) {
        setFZipPlus3(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFZipPlus4(String value) {
        setFZipPlus4(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFZipPlus5(String value) {
        setFZipPlus5(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPostOfficeBoxType(String value) {
        setFPostOfficeBoxType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPostOfficeBoxNumber(String value) {
        setFPostOfficeBoxNumber(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFArea(String value) {
        setFArea(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFAreaType(String value) {
        setFAreaType(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFGeometrySRID(int value) {
        setFGeometrySRID(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFGeometry(String value) {
        setFGeometry(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFSource(String value) {
        setFSource(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFVintage(String value) {
        setFVintage(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPrimaryIdField(String value) {
        setFPrimaryIdField(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFPrimaryIdValue(String value) {
        setFPrimaryIdValue(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFSecondaryIdField(String value) {
        setFSecondaryIdField(value);
        return this;
    }

    public WebServiceGeocodeQueryResult withFSecondaryIdValue(String value) {
        setFSecondaryIdValue(value);
        return this;
    }

}
