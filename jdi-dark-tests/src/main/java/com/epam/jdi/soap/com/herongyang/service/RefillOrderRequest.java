package com.epam.jdi.soap.com.herongyang.service;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for RefillOrderRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RefillOrderRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Patient" type="{http://www.herongyang.com/Service/}PatientType"/&gt;
 *         &lt;element name="Prescription" type="{http://www.herongyang.com/Service/}PrescriptionType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefillOrderRequestType", propOrder = {
        "patient",
        "prescription"
})
@XmlRootElement(name = "RefillOrderRequest")
public class RefillOrderRequest {

    @XmlElement(name = "Patient", required = true)
    protected PatientType patient;
    @XmlElement(name = "Prescription", required = true)
    protected PrescriptionType prescription;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the patient property.
     *
     * @return possible object is
     * {@link PatientType }
     */
    public PatientType getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     *
     * @param value allowed object is
     *              {@link PatientType }
     */
    public void setPatient(PatientType value) {
        this.patient = value;
    }

    /**
     * Gets the value of the prescription property.
     *
     * @return possible object is
     * {@link PrescriptionType }
     */
    public PrescriptionType getPrescription() {
        return prescription;
    }

    /**
     * Sets the value of the prescription property.
     *
     * @param value allowed object is
     *              {@link PrescriptionType }
     */
    public void setPrescription(PrescriptionType value) {
        this.prescription = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    public RefillOrderRequest withPatient(PatientType value) {
        setPatient(value);
        return this;
    }

    public RefillOrderRequest withPrescription(PrescriptionType value) {
        setPrescription(value);
        return this;
    }

    public RefillOrderRequest withVersion(String value) {
        setVersion(value);
        return this;
    }

}
