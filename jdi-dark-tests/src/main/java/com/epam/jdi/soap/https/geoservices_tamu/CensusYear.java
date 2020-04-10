package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CensusYear.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CensusYear"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="NineteenNinety"/&gt;
 *     &lt;enumeration value="TwoThousand"/&gt;
 *     &lt;enumeration value="TwoThousandTen"/&gt;
 *     &lt;enumeration value="AllAvailable"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "CensusYear")
@XmlEnum
public enum CensusYear {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("NineteenNinety")
    NINETEEN_NINETY("NineteenNinety"),
    @XmlEnumValue("TwoThousand")
    TWO_THOUSAND("TwoThousand"),
    @XmlEnumValue("TwoThousandTen")
    TWO_THOUSAND_TEN("TwoThousandTen"),
    @XmlEnumValue("AllAvailable")
    ALL_AVAILABLE("AllAvailable");
    private final String value;

    CensusYear(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CensusYear fromValue(String v) {
        for (CensusYear c : CensusYear.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
