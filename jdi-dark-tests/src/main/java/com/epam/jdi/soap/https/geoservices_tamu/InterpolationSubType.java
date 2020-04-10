package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InterpolationSubType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InterpolationSubType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="LinearInterpolationAddressRange"/&gt;
 *     &lt;enumeration value="LinearInterpolationUniformLot"/&gt;
 *     &lt;enumeration value="LinearInterpolationActualLot"/&gt;
 *     &lt;enumeration value="LinearInterpolationMidPoint"/&gt;
 *     &lt;enumeration value="ArealInterpolationBoundingBoxCentroid"/&gt;
 *     &lt;enumeration value="ArealInterpolationConvexHullCentroid"/&gt;
 *     &lt;enumeration value="ArealInterpolationGeometricCentroid"/&gt;
 *     &lt;enumeration value="None"/&gt;
 *     &lt;enumeration value="NotAttempted"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "InterpolationSubType")
@XmlEnum
public enum InterpolationSubType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("LinearInterpolationAddressRange")
    LINEAR_INTERPOLATION_ADDRESS_RANGE("LinearInterpolationAddressRange"),
    @XmlEnumValue("LinearInterpolationUniformLot")
    LINEAR_INTERPOLATION_UNIFORM_LOT("LinearInterpolationUniformLot"),
    @XmlEnumValue("LinearInterpolationActualLot")
    LINEAR_INTERPOLATION_ACTUAL_LOT("LinearInterpolationActualLot"),
    @XmlEnumValue("LinearInterpolationMidPoint")
    LINEAR_INTERPOLATION_MID_POINT("LinearInterpolationMidPoint"),
    @XmlEnumValue("ArealInterpolationBoundingBoxCentroid")
    AREAL_INTERPOLATION_BOUNDING_BOX_CENTROID("ArealInterpolationBoundingBoxCentroid"),
    @XmlEnumValue("ArealInterpolationConvexHullCentroid")
    AREAL_INTERPOLATION_CONVEX_HULL_CENTROID("ArealInterpolationConvexHullCentroid"),
    @XmlEnumValue("ArealInterpolationGeometricCentroid")
    AREAL_INTERPOLATION_GEOMETRIC_CENTROID("ArealInterpolationGeometricCentroid"),
    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("NotAttempted")
    NOT_ATTEMPTED("NotAttempted");
    private final String value;

    InterpolationSubType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InterpolationSubType fromValue(String v) {
        for (InterpolationSubType c : InterpolationSubType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
