package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NAACCRGISCoordinateQualityType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NAACCRGISCoordinateQualityType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="AddressPoint"/&gt;
 *     &lt;enumeration value="GPS"/&gt;
 *     &lt;enumeration value="Parcel"/&gt;
 *     &lt;enumeration value="StreetSegmentInterpolation"/&gt;
 *     &lt;enumeration value="StreetIntersection"/&gt;
 *     &lt;enumeration value="StreetCentroid"/&gt;
 *     &lt;enumeration value="AddressZIPPlus4Centroid"/&gt;
 *     &lt;enumeration value="AddressZIPPlus2Centroid"/&gt;
 *     &lt;enumeration value="ManualLookup"/&gt;
 *     &lt;enumeration value="AddressZIPCentroid"/&gt;
 *     &lt;enumeration value="POBoxZIPCentroid"/&gt;
 *     &lt;enumeration value="CityCentroid"/&gt;
 *     &lt;enumeration value="CountyCentroid"/&gt;
 *     &lt;enumeration value="Unmatchable"/&gt;
 *     &lt;enumeration value="Missing"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "NAACCRGISCoordinateQualityType")
@XmlEnum
public enum NAACCRGISCoordinateQualityType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("AddressPoint")
    ADDRESS_POINT("AddressPoint"),
    GPS("GPS"),
    @XmlEnumValue("Parcel")
    PARCEL("Parcel"),
    @XmlEnumValue("StreetSegmentInterpolation")
    STREET_SEGMENT_INTERPOLATION("StreetSegmentInterpolation"),
    @XmlEnumValue("StreetIntersection")
    STREET_INTERSECTION("StreetIntersection"),
    @XmlEnumValue("StreetCentroid")
    STREET_CENTROID("StreetCentroid"),
    @XmlEnumValue("AddressZIPPlus4Centroid")
    ADDRESS_ZIP_PLUS_4_CENTROID("AddressZIPPlus4Centroid"),
    @XmlEnumValue("AddressZIPPlus2Centroid")
    ADDRESS_ZIP_PLUS_2_CENTROID("AddressZIPPlus2Centroid"),
    @XmlEnumValue("ManualLookup")
    MANUAL_LOOKUP("ManualLookup"),
    @XmlEnumValue("AddressZIPCentroid")
    ADDRESS_ZIP_CENTROID("AddressZIPCentroid"),
    @XmlEnumValue("POBoxZIPCentroid")
    PO_BOX_ZIP_CENTROID("POBoxZIPCentroid"),
    @XmlEnumValue("CityCentroid")
    CITY_CENTROID("CityCentroid"),
    @XmlEnumValue("CountyCentroid")
    COUNTY_CENTROID("CountyCentroid"),
    @XmlEnumValue("Unmatchable")
    UNMATCHABLE("Unmatchable"),
    @XmlEnumValue("Missing")
    MISSING("Missing");
    private final String value;

    NAACCRGISCoordinateQualityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NAACCRGISCoordinateQualityType fromValue(String v) {
        for (NAACCRGISCoordinateQualityType c : NAACCRGISCoordinateQualityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
