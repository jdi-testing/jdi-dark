package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FeatureMatchingSelectionMethod.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FeatureMatchingSelectionMethod"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="FeatureClassBased"/&gt;
 *     &lt;enumeration value="UncertaintySingleFeatureArea"/&gt;
 *     &lt;enumeration value="UncertaintyMultiFeatureGraviational"/&gt;
 *     &lt;enumeration value="UncertaintyMultiFeatureTopological"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "FeatureMatchingSelectionMethod")
@XmlEnum
public enum FeatureMatchingSelectionMethod {

    @XmlEnumValue("FeatureClassBased")
    FEATURE_CLASS_BASED("FeatureClassBased"),
    @XmlEnumValue("UncertaintySingleFeatureArea")
    UNCERTAINTY_SINGLE_FEATURE_AREA("UncertaintySingleFeatureArea"),
    @XmlEnumValue("UncertaintyMultiFeatureGraviational")
    UNCERTAINTY_MULTI_FEATURE_GRAVIATIONAL("UncertaintyMultiFeatureGraviational"),
    @XmlEnumValue("UncertaintyMultiFeatureTopological")
    UNCERTAINTY_MULTI_FEATURE_TOPOLOGICAL("UncertaintyMultiFeatureTopological");
    private final String value;

    FeatureMatchingSelectionMethod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FeatureMatchingSelectionMethod fromValue(String v) {
        for (FeatureMatchingSelectionMethod c : FeatureMatchingSelectionMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
