package com.epam.jdi.soap.net.yandex.speller.services.spellservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for SpellError complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpellError"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="word" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="s" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="code" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="pos" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="row" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="col" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="len" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpellError", propOrder = {
        "word",
        "s"
})
public class SpellError {

    @XmlElement(required = true)
    protected String word;
    protected List<String> s;
    @XmlAttribute(name = "code", required = true)
    protected int code;
    @XmlAttribute(name = "pos", required = true)
    protected int pos;
    @XmlAttribute(name = "row", required = true)
    protected int row;
    @XmlAttribute(name = "col", required = true)
    protected int col;
    @XmlAttribute(name = "len", required = true)
    protected int len;

    /**
     * Gets the value of the word property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the value of the word property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWord(String value) {
        this.word = value;
    }

    /**
     * Gets the value of the s property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the s property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getS().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getS() {
        if (s == null) {
            s = new ArrayList<String>();
        }
        return this.s;
    }

    /**
     * Gets the value of the code property.
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the value of the pos property.
     */
    public int getPos() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     */
    public void setPos(int value) {
        this.pos = value;
    }

    /**
     * Gets the value of the row property.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the value of the row property.
     */
    public void setRow(int value) {
        this.row = value;
    }

    /**
     * Gets the value of the col property.
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the value of the col property.
     */
    public void setCol(int value) {
        this.col = value;
    }

    /**
     * Gets the value of the len property.
     */
    public int getLen() {
        return len;
    }

    /**
     * Sets the value of the len property.
     */
    public void setLen(int value) {
        this.len = value;
    }

    public SpellError withWord(String value) {
        setWord(value);
        return this;
    }

    public SpellError withS(String... values) {
        if (values != null) {
            for (String value : values) {
                getS().add(value);
            }
        }
        return this;
    }

    public SpellError withS(Collection<String> values) {
        if (values != null) {
            getS().addAll(values);
        }
        return this;
    }

    public SpellError withCode(int value) {
        setCode(value);
        return this;
    }

    public SpellError withPos(int value) {
        setPos(value);
        return this;
    }

    public SpellError withRow(int value) {
        setRow(value);
        return this;
    }

    public SpellError withCol(int value) {
        setCol(value);
        return this;
    }

    public SpellError withLen(int value) {
        setLen(value);
        return this;
    }

}
