package com.epam.jdi.soap.com.herongyang.service;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for RefillOrderResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RefillOrderResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrderStatus" type="{http://www.herongyang.com/Service/}OrderStatusType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefillOrderResponseType", propOrder = {
        "orderStatus"
})
@XmlRootElement(name = "RefillOrderResponse")
public class RefillOrderResponse {

    @XmlElement(name = "OrderStatus", required = true)
    protected OrderStatusType orderStatus;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the orderStatus property.
     *
     * @return possible object is
     * {@link OrderStatusType }
     */
    public OrderStatusType getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the value of the orderStatus property.
     *
     * @param value allowed object is
     *              {@link OrderStatusType }
     */
    public void setOrderStatus(OrderStatusType value) {
        this.orderStatus = value;
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

    public RefillOrderResponse withOrderStatus(OrderStatusType value) {
        setOrderStatus(value);
        return this;
    }

    public RefillOrderResponse withVersion(String value) {
        setVersion(value);
        return this;
    }

}
