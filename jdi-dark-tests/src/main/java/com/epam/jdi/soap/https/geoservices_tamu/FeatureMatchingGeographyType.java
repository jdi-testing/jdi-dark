package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FeatureMatchingGeographyType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FeatureMatchingGeographyType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="GPS"/&gt;
 *     &lt;enumeration value="BuildingCentroid"/&gt;
 *     &lt;enumeration value="BuildingDoor"/&gt;
 *     &lt;enumeration value="Parcel"/&gt;
 *     &lt;enumeration value="StreetSegment"/&gt;
 *     &lt;enumeration value="StreetIntersection"/&gt;
 *     &lt;enumeration value="StreetCentroid"/&gt;
 *     &lt;enumeration value="USPSZipPlus5"/&gt;
 *     &lt;enumeration value="USPSZipPlus4"/&gt;
 *     &lt;enumeration value="USPSZipPlus3"/&gt;
 *     &lt;enumeration value="USPSZipPlus2"/&gt;
 *     &lt;enumeration value="USPSZipPlus1"/&gt;
 *     &lt;enumeration value="USPSZip"/&gt;
 *     &lt;enumeration value="ZCTAPlus5"/&gt;
 *     &lt;enumeration value="ZCTAPlus4"/&gt;
 *     &lt;enumeration value="ZCTAPlus3"/&gt;
 *     &lt;enumeration value="ZCTAPlus2"/&gt;
 *     &lt;enumeration value="ZCTAPlus1"/&gt;
 *     &lt;enumeration value="ZCTA"/&gt;
 *     &lt;enumeration value="City"/&gt;
 *     &lt;enumeration value="ConsolidatedCity"/&gt;
 *     &lt;enumeration value="MinorCivilDivision"/&gt;
 *     &lt;enumeration value="CountySubRegion"/&gt;
 *     &lt;enumeration value="County"/&gt;
 *     &lt;enumeration value="State"/&gt;
 *     &lt;enumeration value="Country"/&gt;
 *     &lt;enumeration value="Unmatchable"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "FeatureMatchingGeographyType")
@XmlEnum
public enum FeatureMatchingGeographyType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    GPS("GPS"),
    @XmlEnumValue("BuildingCentroid")
    BUILDING_CENTROID("BuildingCentroid"),
    @XmlEnumValue("BuildingDoor")
    BUILDING_DOOR("BuildingDoor"),
    @XmlEnumValue("Parcel")
    PARCEL("Parcel"),
    @XmlEnumValue("StreetSegment")
    STREET_SEGMENT("StreetSegment"),
    @XmlEnumValue("StreetIntersection")
    STREET_INTERSECTION("StreetIntersection"),
    @XmlEnumValue("StreetCentroid")
    STREET_CENTROID("StreetCentroid"),
    @XmlEnumValue("USPSZipPlus5")
    USPS_ZIP_PLUS_5("USPSZipPlus5"),
    @XmlEnumValue("USPSZipPlus4")
    USPS_ZIP_PLUS_4("USPSZipPlus4"),
    @XmlEnumValue("USPSZipPlus3")
    USPS_ZIP_PLUS_3("USPSZipPlus3"),
    @XmlEnumValue("USPSZipPlus2")
    USPS_ZIP_PLUS_2("USPSZipPlus2"),
    @XmlEnumValue("USPSZipPlus1")
    USPS_ZIP_PLUS_1("USPSZipPlus1"),
    @XmlEnumValue("USPSZip")
    USPS_ZIP("USPSZip"),
    @XmlEnumValue("ZCTAPlus5")
    ZCTA_PLUS_5("ZCTAPlus5"),
    @XmlEnumValue("ZCTAPlus4")
    ZCTA_PLUS_4("ZCTAPlus4"),
    @XmlEnumValue("ZCTAPlus3")
    ZCTA_PLUS_3("ZCTAPlus3"),
    @XmlEnumValue("ZCTAPlus2")
    ZCTA_PLUS_2("ZCTAPlus2"),
    @XmlEnumValue("ZCTAPlus1")
    ZCTA_PLUS_1("ZCTAPlus1"),
    ZCTA("ZCTA"),
    @XmlEnumValue("City")
    CITY("City"),
    @XmlEnumValue("ConsolidatedCity")
    CONSOLIDATED_CITY("ConsolidatedCity"),
    @XmlEnumValue("MinorCivilDivision")
    MINOR_CIVIL_DIVISION("MinorCivilDivision"),
    @XmlEnumValue("CountySubRegion")
    COUNTY_SUB_REGION("CountySubRegion"),
    @XmlEnumValue("County")
    COUNTY("County"),
    @XmlEnumValue("State")
    STATE("State"),
    @XmlEnumValue("Country")
    COUNTRY("Country"),
    @XmlEnumValue("Unmatchable")
    UNMATCHABLE("Unmatchable");
    private final String value;

    FeatureMatchingGeographyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FeatureMatchingGeographyType fromValue(String v) {
        for (FeatureMatchingGeographyType c : FeatureMatchingGeographyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
