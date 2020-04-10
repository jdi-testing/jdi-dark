package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InterpolationType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InterpolationType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="LinearInterpolation"/&gt;
 *     &lt;enumeration value="ArealInterpolation"/&gt;
 *     &lt;enumeration value="None"/&gt;
 *     &lt;enumeration value="NotAttempted"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "InterpolationType")
@XmlEnum
public enum InterpolationType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("LinearInterpolation")
    LINEAR_INTERPOLATION("LinearInterpolation"),
    @XmlEnumValue("ArealInterpolation")
    AREAL_INTERPOLATION("ArealInterpolation"),
    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("NotAttempted")
    NOT_ATTEMPTED("NotAttempted");
    private final String value;

    InterpolationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InterpolationType fromValue(String v) {
        for (InterpolationType c : InterpolationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
