//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.11 at 06:31:19 PM CEST 
//


package com.anzepintar.ozrpp.converters.xliffconvert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="canCopy" type="{urn:oasis:names:tc:xliff:document:2.0}yesNo" default="yes" />
 *       &lt;attribute name="canDelete" type="{urn:oasis:names:tc:xliff:document:2.0}yesNo" default="yes" />
 *       &lt;attribute name="canReorder" type="{urn:oasis:names:tc:xliff:document:2.0}yesNoFirstNo" default="yes" />
 *       &lt;attribute name="copyOf" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="disp" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="equiv" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="dataRef" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="subFlows" type="{http://www.w3.org/2001/XMLSchema}NMTOKENS" />
 *       &lt;attribute name="subType" type="{urn:oasis:names:tc:xliff:document:2.0}userDefinedValue" />
 *       &lt;attribute name="type" type="{urn:oasis:names:tc:xliff:document:2.0}attrType_type" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ph")
public class Ph {

    @XmlAttribute(name = "canCopy")
    protected YesNo canCopy;
    @XmlAttribute(name = "canDelete")
    protected YesNo canDelete;
    @XmlAttribute(name = "canReorder")
    protected YesNoFirstNo canReorder;
    @XmlAttribute(name = "copyOf")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String copyOf;
    @XmlAttribute(name = "disp")
    @XmlSchemaType(name = "anySimpleType")
    protected String disp;
    @XmlAttribute(name = "equiv")
    @XmlSchemaType(name = "anySimpleType")
    protected String equiv;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String id;
    @XmlAttribute(name = "dataRef")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String dataRef;
    @XmlAttribute(name = "subFlows")
    @XmlSchemaType(name = "NMTOKENS")
    protected List<String> subFlows;
    @XmlAttribute(name = "subType")
    protected String subType;
    @XmlAttribute(name = "type")
    protected AttrTypeType type;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the canCopy property.
     * 
     * @return
     *     possible object is
     *     {@link YesNo }
     *     
     */
    public YesNo getCanCopy() {
        if (canCopy == null) {
            return YesNo.YES;
        } else {
            return canCopy;
        }
    }

    /**
     * Sets the value of the canCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNo }
     *     
     */
    public void setCanCopy(YesNo value) {
        this.canCopy = value;
    }

    /**
     * Gets the value of the canDelete property.
     * 
     * @return
     *     possible object is
     *     {@link YesNo }
     *     
     */
    public YesNo getCanDelete() {
        if (canDelete == null) {
            return YesNo.YES;
        } else {
            return canDelete;
        }
    }

    /**
     * Sets the value of the canDelete property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNo }
     *     
     */
    public void setCanDelete(YesNo value) {
        this.canDelete = value;
    }

    /**
     * Gets the value of the canReorder property.
     * 
     * @return
     *     possible object is
     *     {@link YesNoFirstNo }
     *     
     */
    public YesNoFirstNo getCanReorder() {
        if (canReorder == null) {
            return YesNoFirstNo.YES;
        } else {
            return canReorder;
        }
    }

    /**
     * Sets the value of the canReorder property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoFirstNo }
     *     
     */
    public void setCanReorder(YesNoFirstNo value) {
        this.canReorder = value;
    }

    /**
     * Gets the value of the copyOf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyOf() {
        return copyOf;
    }

    /**
     * Sets the value of the copyOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyOf(String value) {
        this.copyOf = value;
    }

    /**
     * Gets the value of the disp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisp() {
        return disp;
    }

    /**
     * Sets the value of the disp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisp(String value) {
        this.disp = value;
    }

    /**
     * Gets the value of the equiv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquiv() {
        return equiv;
    }

    /**
     * Sets the value of the equiv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquiv(String value) {
        this.equiv = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the dataRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataRef() {
        return dataRef;
    }

    /**
     * Sets the value of the dataRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataRef(String value) {
        this.dataRef = value;
    }

    /**
     * Gets the value of the subFlows property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subFlows property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubFlows().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSubFlows() {
        if (subFlows == null) {
            subFlows = new ArrayList<String>();
        }
        return this.subFlows;
    }

    /**
     * Gets the value of the subType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubType() {
        return subType;
    }

    /**
     * Sets the value of the subType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubType(String value) {
        this.subType = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AttrTypeType }
     *     
     */
    public AttrTypeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttrTypeType }
     *     
     */
    public void setType(AttrTypeType value) {
        this.type = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
