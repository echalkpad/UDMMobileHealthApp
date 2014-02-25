//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.23 at 05:26:02 PM EST 
//


package com.udm.health.domain.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="measurementDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="measurementTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="systolic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="diastolic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userId",
    "measurementDate",
    "measurementTime",
    "systolic",
    "diastolic"
})
@XmlRootElement(name = "sendBloodPreasureMeasurementRequest")
public class SendBloodPreasureMeasurementRequest {

    @XmlElement(required = true)
    protected String userId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar measurementDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar measurementTime;
    @XmlElement(required = true)
    protected String systolic;
    @XmlElement(required = true)
    protected String diastolic;

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the measurementDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMeasurementDate() {
        return measurementDate;
    }

    /**
     * Sets the value of the measurementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMeasurementDate(XMLGregorianCalendar value) {
        this.measurementDate = value;
    }

    /**
     * Gets the value of the measurementTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMeasurementTime() {
        return measurementTime;
    }

    /**
     * Sets the value of the measurementTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMeasurementTime(XMLGregorianCalendar value) {
        this.measurementTime = value;
    }

    /**
     * Gets the value of the systolic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystolic() {
        return systolic;
    }

    /**
     * Sets the value of the systolic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystolic(String value) {
        this.systolic = value;
    }

    /**
     * Gets the value of the diastolic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiastolic() {
        return diastolic;
    }

    /**
     * Sets the value of the diastolic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiastolic(String value) {
        this.diastolic = value;
    }

}