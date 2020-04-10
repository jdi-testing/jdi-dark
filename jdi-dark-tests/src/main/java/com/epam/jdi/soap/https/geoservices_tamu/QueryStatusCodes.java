package com.epam.jdi.soap.https.geoservices_tamu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryStatusCodes.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QueryStatusCodes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="Success"/&gt;
 *     &lt;enumeration value="Failure"/&gt;
 *     &lt;enumeration value="InternalError"/&gt;
 *     &lt;enumeration value="APIKeyError"/&gt;
 *     &lt;enumeration value="APIKeyMissing"/&gt;
 *     &lt;enumeration value="APIKeyInvalid"/&gt;
 *     &lt;enumeration value="APIKeyNotActivated"/&gt;
 *     &lt;enumeration value="NonProfitError"/&gt;
 *     &lt;enumeration value="NonProfitNotConfirmed"/&gt;
 *     &lt;enumeration value="QueryError"/&gt;
 *     &lt;enumeration value="QueryParameterMissing"/&gt;
 *     &lt;enumeration value="QueryRestrictedCoverage"/&gt;
 *     &lt;enumeration value="QuotaExceededError"/&gt;
 *     &lt;enumeration value="QuotaExceededAnonymous"/&gt;
 *     &lt;enumeration value="QuotaExceededPaid"/&gt;
 *     &lt;enumeration value="VersionInvalid"/&gt;
 *     &lt;enumeration value="VersionMissing"/&gt;
 *     &lt;enumeration value="VersionOutdated"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "QueryStatusCodes")
@XmlEnum
public enum QueryStatusCodes {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("InternalError")
    INTERNAL_ERROR("InternalError"),
    @XmlEnumValue("APIKeyError")
    API_KEY_ERROR("APIKeyError"),
    @XmlEnumValue("APIKeyMissing")
    API_KEY_MISSING("APIKeyMissing"),
    @XmlEnumValue("APIKeyInvalid")
    API_KEY_INVALID("APIKeyInvalid"),
    @XmlEnumValue("APIKeyNotActivated")
    API_KEY_NOT_ACTIVATED("APIKeyNotActivated"),
    @XmlEnumValue("NonProfitError")
    NON_PROFIT_ERROR("NonProfitError"),
    @XmlEnumValue("NonProfitNotConfirmed")
    NON_PROFIT_NOT_CONFIRMED("NonProfitNotConfirmed"),
    @XmlEnumValue("QueryError")
    QUERY_ERROR("QueryError"),
    @XmlEnumValue("QueryParameterMissing")
    QUERY_PARAMETER_MISSING("QueryParameterMissing"),
    @XmlEnumValue("QueryRestrictedCoverage")
    QUERY_RESTRICTED_COVERAGE("QueryRestrictedCoverage"),
    @XmlEnumValue("QuotaExceededError")
    QUOTA_EXCEEDED_ERROR("QuotaExceededError"),
    @XmlEnumValue("QuotaExceededAnonymous")
    QUOTA_EXCEEDED_ANONYMOUS("QuotaExceededAnonymous"),
    @XmlEnumValue("QuotaExceededPaid")
    QUOTA_EXCEEDED_PAID("QuotaExceededPaid"),
    @XmlEnumValue("VersionInvalid")
    VERSION_INVALID("VersionInvalid"),
    @XmlEnumValue("VersionMissing")
    VERSION_MISSING("VersionMissing"),
    @XmlEnumValue("VersionOutdated")
    VERSION_OUTDATED("VersionOutdated");
    private final String value;

    QueryStatusCodes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QueryStatusCodes fromValue(String v) {
        for (QueryStatusCodes c : QueryStatusCodes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
