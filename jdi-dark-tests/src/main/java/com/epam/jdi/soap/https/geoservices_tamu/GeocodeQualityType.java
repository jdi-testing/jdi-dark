package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GeocodeQualityType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GeocodeQualityType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unmatchable"/&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="AddressPoint"/&gt;
 *     &lt;enumeration value="GPS"/&gt;
 *     &lt;enumeration value="CountyParcel"/&gt;
 *     &lt;enumeration value="RoofTop"/&gt;
 *     &lt;enumeration value="ParcelCentroid"/&gt;
 *     &lt;enumeration value="PrimaryStructureEntrance"/&gt;
 *     &lt;enumeration value="DrivewayEntrance"/&gt;
 *     &lt;enumeration value="BuildingFrontDoor"/&gt;
 *     &lt;enumeration value="BuildingCentroid"/&gt;
 *     &lt;enumeration value="ExactParcelCentroidPoint"/&gt;
 *     &lt;enumeration value="ExactParcelCentroid"/&gt;
 *     &lt;enumeration value="NearestParcelCentroidPoint"/&gt;
 *     &lt;enumeration value="NearestParcelCentroid"/&gt;
 *     &lt;enumeration value="ActualLotInterpolation"/&gt;
 *     &lt;enumeration value="UniformLotInterpolation"/&gt;
 *     &lt;enumeration value="AddressRangeInterpolation"/&gt;
 *     &lt;enumeration value="StreetIntersection"/&gt;
 *     &lt;enumeration value="StreetCentroid"/&gt;
 *     &lt;enumeration value="ZCTAPlus5Centroid"/&gt;
 *     &lt;enumeration value="ZCTAPlus4Centroid"/&gt;
 *     &lt;enumeration value="ZCTAPlus3Centroid"/&gt;
 *     &lt;enumeration value="ZCTAPlus2Centroid"/&gt;
 *     &lt;enumeration value="ZCTAPlus1Centroid"/&gt;
 *     &lt;enumeration value="ZCTACentroid"/&gt;
 *     &lt;enumeration value="USPSZipPlus5LineCentroid"/&gt;
 *     &lt;enumeration value="USPSZipPlus4LineCentroid"/&gt;
 *     &lt;enumeration value="USPSZipPlus5AreaCentroid"/&gt;
 *     &lt;enumeration value="USPSZipPlus4AreaCentroid"/&gt;
 *     &lt;enumeration value="USPSZipPlus3AreaCentroid"/&gt;
 *     &lt;enumeration value="USPSZipPlus2AreaCentroid"/&gt;
 *     &lt;enumeration value="USPSZipPlus1AreaCentroid"/&gt;
 *     &lt;enumeration value="USPSZipAreaCentroid"/&gt;
 *     &lt;enumeration value="CityCentroid"/&gt;
 *     &lt;enumeration value="ConsolidatedCityCentroid"/&gt;
 *     &lt;enumeration value="CountySubdivisionCentroid"/&gt;
 *     &lt;enumeration value="CountyCentroid"/&gt;
 *     &lt;enumeration value="StateCentroid"/&gt;
 *     &lt;enumeration value="CountryCentroid"/&gt;
 *     &lt;enumeration value="DynamicFeatureCompositionCentroid"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "GeocodeQualityType")
@XmlEnum
public enum GeocodeQualityType {

    @XmlEnumValue("Unmatchable")
    UNMATCHABLE("Unmatchable"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("AddressPoint")
    ADDRESS_POINT("AddressPoint"),
    GPS("GPS"),
    @XmlEnumValue("CountyParcel")
    COUNTY_PARCEL("CountyParcel"),
    @XmlEnumValue("RoofTop")
    ROOF_TOP("RoofTop"),
    @XmlEnumValue("ParcelCentroid")
    PARCEL_CENTROID("ParcelCentroid"),
    @XmlEnumValue("PrimaryStructureEntrance")
    PRIMARY_STRUCTURE_ENTRANCE("PrimaryStructureEntrance"),
    @XmlEnumValue("DrivewayEntrance")
    DRIVEWAY_ENTRANCE("DrivewayEntrance"),
    @XmlEnumValue("BuildingFrontDoor")
    BUILDING_FRONT_DOOR("BuildingFrontDoor"),
    @XmlEnumValue("BuildingCentroid")
    BUILDING_CENTROID("BuildingCentroid"),
    @XmlEnumValue("ExactParcelCentroidPoint")
    EXACT_PARCEL_CENTROID_POINT("ExactParcelCentroidPoint"),
    @XmlEnumValue("ExactParcelCentroid")
    EXACT_PARCEL_CENTROID("ExactParcelCentroid"),
    @XmlEnumValue("NearestParcelCentroidPoint")
    NEAREST_PARCEL_CENTROID_POINT("NearestParcelCentroidPoint"),
    @XmlEnumValue("NearestParcelCentroid")
    NEAREST_PARCEL_CENTROID("NearestParcelCentroid"),
    @XmlEnumValue("ActualLotInterpolation")
    ACTUAL_LOT_INTERPOLATION("ActualLotInterpolation"),
    @XmlEnumValue("UniformLotInterpolation")
    UNIFORM_LOT_INTERPOLATION("UniformLotInterpolation"),
    @XmlEnumValue("AddressRangeInterpolation")
    ADDRESS_RANGE_INTERPOLATION("AddressRangeInterpolation"),
    @XmlEnumValue("StreetIntersection")
    STREET_INTERSECTION("StreetIntersection"),
    @XmlEnumValue("StreetCentroid")
    STREET_CENTROID("StreetCentroid"),
    @XmlEnumValue("ZCTAPlus5Centroid")
    ZCTA_PLUS_5_CENTROID("ZCTAPlus5Centroid"),
    @XmlEnumValue("ZCTAPlus4Centroid")
    ZCTA_PLUS_4_CENTROID("ZCTAPlus4Centroid"),
    @XmlEnumValue("ZCTAPlus3Centroid")
    ZCTA_PLUS_3_CENTROID("ZCTAPlus3Centroid"),
    @XmlEnumValue("ZCTAPlus2Centroid")
    ZCTA_PLUS_2_CENTROID("ZCTAPlus2Centroid"),
    @XmlEnumValue("ZCTAPlus1Centroid")
    ZCTA_PLUS_1_CENTROID("ZCTAPlus1Centroid"),
    @XmlEnumValue("ZCTACentroid")
    ZCTA_CENTROID("ZCTACentroid"),
    @XmlEnumValue("USPSZipPlus5LineCentroid")
    USPS_ZIP_PLUS_5_LINE_CENTROID("USPSZipPlus5LineCentroid"),
    @XmlEnumValue("USPSZipPlus4LineCentroid")
    USPS_ZIP_PLUS_4_LINE_CENTROID("USPSZipPlus4LineCentroid"),
    @XmlEnumValue("USPSZipPlus5AreaCentroid")
    USPS_ZIP_PLUS_5_AREA_CENTROID("USPSZipPlus5AreaCentroid"),
    @XmlEnumValue("USPSZipPlus4AreaCentroid")
    USPS_ZIP_PLUS_4_AREA_CENTROID("USPSZipPlus4AreaCentroid"),
    @XmlEnumValue("USPSZipPlus3AreaCentroid")
    USPS_ZIP_PLUS_3_AREA_CENTROID("USPSZipPlus3AreaCentroid"),
    @XmlEnumValue("USPSZipPlus2AreaCentroid")
    USPS_ZIP_PLUS_2_AREA_CENTROID("USPSZipPlus2AreaCentroid"),
    @XmlEnumValue("USPSZipPlus1AreaCentroid")
    USPS_ZIP_PLUS_1_AREA_CENTROID("USPSZipPlus1AreaCentroid"),
    @XmlEnumValue("USPSZipAreaCentroid")
    USPS_ZIP_AREA_CENTROID("USPSZipAreaCentroid"),
    @XmlEnumValue("CityCentroid")
    CITY_CENTROID("CityCentroid"),
    @XmlEnumValue("ConsolidatedCityCentroid")
    CONSOLIDATED_CITY_CENTROID("ConsolidatedCityCentroid"),
    @XmlEnumValue("CountySubdivisionCentroid")
    COUNTY_SUBDIVISION_CENTROID("CountySubdivisionCentroid"),
    @XmlEnumValue("CountyCentroid")
    COUNTY_CENTROID("CountyCentroid"),
    @XmlEnumValue("StateCentroid")
    STATE_CENTROID("StateCentroid"),
    @XmlEnumValue("CountryCentroid")
    COUNTRY_CENTROID("CountryCentroid"),
    @XmlEnumValue("DynamicFeatureCompositionCentroid")
    DYNAMIC_FEATURE_COMPOSITION_CENTROID("DynamicFeatureCompositionCentroid");
    private final String value;

    GeocodeQualityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GeocodeQualityType fromValue(String v) {
        for (GeocodeQualityType c : GeocodeQualityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
