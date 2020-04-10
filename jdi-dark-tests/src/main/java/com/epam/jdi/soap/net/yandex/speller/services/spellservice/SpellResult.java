package com.epam.jdi.soap.net.yandex.speller.services.spellservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for SpellResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpellResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="error" type="{http://speller.yandex.net/services/spellservice}SpellError" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpellResult", propOrder = {
        "error"
})
public class SpellResult {

    protected List<SpellError> error;

    /**
     * Gets the value of the error property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the error property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getError().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpellError }
     */
    public List<SpellError> getError() {
        if (error == null) {
            error = new ArrayList<SpellError>();
        }
        return this.error;
    }

    public SpellResult withError(SpellError... values) {
        if (values != null) {
            for (SpellError value : values) {
                getError().add(value);
            }
        }
        return this;
    }

    public SpellResult withError(Collection<SpellError> values) {
        if (values != null) {
            getError().addAll(values);
        }
        return this;
    }

}
