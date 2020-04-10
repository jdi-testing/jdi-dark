package com.epam.jdi.soap.com.herongyang.service;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Confirmation" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="guest" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="event" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "confirmation"
})
@XmlRootElement(name = "RegistrationResponse")
public class RegistrationResponse {

    @XmlElement(name = "Confirmation", required = true)
    protected List<RegistrationResponse.Confirmation> confirmation;

    /**
     * Gets the value of the confirmation property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the confirmation property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfirmation().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegistrationResponse.Confirmation }
     */
    public List<RegistrationResponse.Confirmation> getConfirmation() {
        if (confirmation == null) {
            confirmation = new ArrayList<RegistrationResponse.Confirmation>();
        }
        return this.confirmation;
    }

    public RegistrationResponse withConfirmation(RegistrationResponse.Confirmation... values) {
        if (values != null) {
            for (RegistrationResponse.Confirmation value : values) {
                getConfirmation().add(value);
            }
        }
        return this;
    }

    public RegistrationResponse withConfirmation(Collection<RegistrationResponse.Confirmation> values) {
        if (values != null) {
            getConfirmation().addAll(values);
        }
        return this;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="guest" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="event" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Confirmation {

        @XmlAttribute(name = "guest")
        protected String guest;
        @XmlAttribute(name = "event")
        protected String event;

        /**
         * Gets the value of the guest property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getGuest() {
            return guest;
        }

        /**
         * Sets the value of the guest property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setGuest(String value) {
            this.guest = value;
        }

        /**
         * Gets the value of the event property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getEvent() {
            return event;
        }

        /**
         * Sets the value of the event property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setEvent(String value) {
            this.event = value;
        }

        public RegistrationResponse.Confirmation withGuest(String value) {
            setGuest(value);
            return this;
        }

        public RegistrationResponse.Confirmation withEvent(String value) {
            setEvent(value);
            return this;
        }

    }

}
