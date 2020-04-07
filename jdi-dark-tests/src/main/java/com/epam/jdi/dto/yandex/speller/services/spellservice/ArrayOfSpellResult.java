package com.epam.jdi.dto.yandex.speller.services.spellservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSpellResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSpellResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SpellResult" type="{http://speller.yandex.net/services/spellservice}SpellResult" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSpellResult", propOrder = {
    "spellResult"
})
public class ArrayOfSpellResult {

    @XmlElement(name = "SpellResult")
    protected List<SpellResult> spellResult;

    /**
     * Gets the value of the spellResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spellResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpellResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpellResult }
     * 
     * 
     */
    public List<SpellResult> getSpellResult() {
        if (spellResult == null) {
            spellResult = new ArrayList<SpellResult>();
        }
        return this.spellResult;
    }

    public ArrayOfSpellResult withSpellResult(SpellResult... values) {
        if (values!= null) {
            for (SpellResult value: values) {
                getSpellResult().add(value);
            }
        }
        return this;
    }

    public ArrayOfSpellResult withSpellResult(Collection<SpellResult> values) {
        if (values!= null) {
            getSpellResult().addAll(values);
        }
        return this;
    }

}
