package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NAACCRCensusTractCertaintyType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NAACCRCensusTractCertaintyType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="ResidenceStreetAddress"/&gt;
 *     &lt;enumeration value="ResidenceZIPPlus4"/&gt;
 *     &lt;enumeration value="ResidenceZIPPlus2"/&gt;
 *     &lt;enumeration value="ResidenceZIP"/&gt;
 *     &lt;enumeration value="POBoxZIP"/&gt;
 *     &lt;enumeration value="ResidenceCityOrZIPWithOneCensusTract"/&gt;
 *     &lt;enumeration value="Missing"/&gt;
 *     &lt;enumeration value="Unmatchable"/&gt;
 *     &lt;enumeration value="NotAttempted"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "NAACCRCensusTractCertaintyType")
@XmlEnum
public enum NAACCRCensusTractCertaintyType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("ResidenceStreetAddress")
    RESIDENCE_STREET_ADDRESS("ResidenceStreetAddress"),
    @XmlEnumValue("ResidenceZIPPlus4")
    RESIDENCE_ZIP_PLUS_4("ResidenceZIPPlus4"),
    @XmlEnumValue("ResidenceZIPPlus2")
    RESIDENCE_ZIP_PLUS_2("ResidenceZIPPlus2"),
    @XmlEnumValue("ResidenceZIP")
    RESIDENCE_ZIP("ResidenceZIP"),
    @XmlEnumValue("POBoxZIP")
    PO_BOX_ZIP("POBoxZIP"),
    @XmlEnumValue("ResidenceCityOrZIPWithOneCensusTract")
    RESIDENCE_CITY_OR_ZIP_WITH_ONE_CENSUS_TRACT("ResidenceCityOrZIPWithOneCensusTract"),
    @XmlEnumValue("Missing")
    MISSING("Missing"),
    @XmlEnumValue("Unmatchable")
    UNMATCHABLE("Unmatchable"),
    @XmlEnumValue("NotAttempted")
    NOT_ATTEMPTED("NotAttempted");
    private final String value;

    NAACCRCensusTractCertaintyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NAACCRCensusTractCertaintyType fromValue(String v) {
        for (NAACCRCensusTractCertaintyType c : NAACCRCensusTractCertaintyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
