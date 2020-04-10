package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TieHandlingStrategyType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TieHandlingStrategyType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="RevertToHierarchy"/&gt;
 *     &lt;enumeration value="FlipACoin"/&gt;
 *     &lt;enumeration value="DynamicFeatureComposition"/&gt;
 *     &lt;enumeration value="RegionalCharacteristics"/&gt;
 *     &lt;enumeration value="ReturnAll"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "TieHandlingStrategyType")
@XmlEnum
public enum TieHandlingStrategyType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("RevertToHierarchy")
    REVERT_TO_HIERARCHY("RevertToHierarchy"),
    @XmlEnumValue("FlipACoin")
    FLIP_A_COIN("FlipACoin"),
    @XmlEnumValue("DynamicFeatureComposition")
    DYNAMIC_FEATURE_COMPOSITION("DynamicFeatureComposition"),
    @XmlEnumValue("RegionalCharacteristics")
    REGIONAL_CHARACTERISTICS("RegionalCharacteristics"),
    @XmlEnumValue("ReturnAll")
    RETURN_ALL("ReturnAll");
    private final String value;

    TieHandlingStrategyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TieHandlingStrategyType fromValue(String v) {
        for (TieHandlingStrategyType c : TieHandlingStrategyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
