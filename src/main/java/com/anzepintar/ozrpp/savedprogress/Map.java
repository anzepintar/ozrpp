//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.22 at 06:52:45 PM CET 
//


package com.anzepintar.ozrpp.savedprogress;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.NormalizedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "map")
public class Map {

    @XmlAttribute(name = "unicode", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String unicode;
    @XmlAttribute(name = "code")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String code;
    @XmlAttribute(name = "ent")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String ent;
    @XmlAttribute(name = "subst")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String subst;

    /**
     * Gets the value of the unicode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnicode() {
        return unicode;
    }

    /**
     * Sets the value of the unicode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnicode(String value) {
        this.unicode = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the ent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnt() {
        return ent;
    }

    /**
     * Sets the value of the ent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnt(String value) {
        this.ent = value;
    }

    /**
     * Gets the value of the subst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubst() {
        return subst;
    }

    /**
     * Sets the value of the subst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubst(String value) {
        this.subst = value;
    }

}
