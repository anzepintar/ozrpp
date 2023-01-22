//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.22 at 06:52:45 PM CET 
//


package com.anzepintar.ozrpp.savedprogress;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.NormalizedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "noteOrProp",
    "tuv"
})
@XmlRootElement(name = "tu")
public class Tu {

    @XmlAttribute(name = "tuid")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String tuid;
    @XmlAttribute(name = "o-encoding")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String oEncoding;
    @XmlAttribute(name = "datatype")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String datatype;
    @XmlAttribute(name = "usagecount")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String usagecount;
    @XmlAttribute(name = "lastusagedate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String lastusagedate;
    @XmlAttribute(name = "creationtool")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String creationtool;
    @XmlAttribute(name = "creationtoolversion")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String creationtoolversion;
    @XmlAttribute(name = "creationdate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String creationdate;
    @XmlAttribute(name = "creationid")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String creationid;
    @XmlAttribute(name = "changedate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String changedate;
    @XmlAttribute(name = "segtype")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String segtype;
    @XmlAttribute(name = "changeid")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String changeid;
    @XmlAttribute(name = "o-tmf")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String oTmf;
    @XmlAttribute(name = "srclang")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String srclang;
    @XmlElements({
        @XmlElement(name = "note", type = Note.class),
        @XmlElement(name = "prop", type = Prop.class)
    })
    protected List<Object> noteOrProp;
    @XmlElement(required = true)
    protected List<Tuv> tuv;

    /**
     * Gets the value of the tuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTuid() {
        return tuid;
    }

    /**
     * Sets the value of the tuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTuid(String value) {
        this.tuid = value;
    }

    /**
     * Gets the value of the oEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOEncoding() {
        return oEncoding;
    }

    /**
     * Sets the value of the oEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOEncoding(String value) {
        this.oEncoding = value;
    }

    /**
     * Gets the value of the datatype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatatype() {
        return datatype;
    }

    /**
     * Sets the value of the datatype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatatype(String value) {
        this.datatype = value;
    }

    /**
     * Gets the value of the usagecount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsagecount() {
        return usagecount;
    }

    /**
     * Sets the value of the usagecount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsagecount(String value) {
        this.usagecount = value;
    }

    /**
     * Gets the value of the lastusagedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastusagedate() {
        return lastusagedate;
    }

    /**
     * Sets the value of the lastusagedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastusagedate(String value) {
        this.lastusagedate = value;
    }

    /**
     * Gets the value of the creationtool property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationtool() {
        return creationtool;
    }

    /**
     * Sets the value of the creationtool property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationtool(String value) {
        this.creationtool = value;
    }

    /**
     * Gets the value of the creationtoolversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationtoolversion() {
        return creationtoolversion;
    }

    /**
     * Sets the value of the creationtoolversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationtoolversion(String value) {
        this.creationtoolversion = value;
    }

    /**
     * Gets the value of the creationdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationdate() {
        return creationdate;
    }

    /**
     * Sets the value of the creationdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationdate(String value) {
        this.creationdate = value;
    }

    /**
     * Gets the value of the creationid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationid() {
        return creationid;
    }

    /**
     * Sets the value of the creationid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationid(String value) {
        this.creationid = value;
    }

    /**
     * Gets the value of the changedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangedate() {
        return changedate;
    }

    /**
     * Sets the value of the changedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangedate(String value) {
        this.changedate = value;
    }

    /**
     * Gets the value of the segtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegtype() {
        return segtype;
    }

    /**
     * Sets the value of the segtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegtype(String value) {
        this.segtype = value;
    }

    /**
     * Gets the value of the changeid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeid() {
        return changeid;
    }

    /**
     * Sets the value of the changeid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeid(String value) {
        this.changeid = value;
    }

    /**
     * Gets the value of the oTmf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOTmf() {
        return oTmf;
    }

    /**
     * Sets the value of the oTmf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOTmf(String value) {
        this.oTmf = value;
    }

    /**
     * Gets the value of the srclang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrclang() {
        return srclang;
    }

    /**
     * Sets the value of the srclang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrclang(String value) {
        this.srclang = value;
    }

    /**
     * Gets the value of the noteOrProp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the noteOrProp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNoteOrProp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Note }
     * {@link Prop }
     * 
     * 
     */
    public List<Object> getNoteOrProp() {
        if (noteOrProp == null) {
            noteOrProp = new ArrayList<Object>();
        }
        return this.noteOrProp;
    }

    /**
     * Gets the value of the tuv property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tuv property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTuv().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tuv }
     * 
     * 
     */
    public List<Tuv> getTuv() {
        if (tuv == null) {
            tuv = new ArrayList<Tuv>();
        }
        return this.tuv;
    }

}
