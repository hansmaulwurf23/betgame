
package de.msiggi.sportsdata.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GoalGetter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GoalGetter"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="goalGetterBirthday" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="goalGetterGoalCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="goalGetterID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="goalGetterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goalGetterNationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GoalGetter", propOrder = {
    "goalGetterBirthday",
    "goalGetterGoalCount",
    "goalGetterID",
    "goalGetterName",
    "goalGetterNationality"
})
public class GoalGetter {

    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar goalGetterBirthday;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer goalGetterGoalCount;
    protected int goalGetterID;
    protected String goalGetterName;
    protected String goalGetterNationality;

    /**
     * Gets the value of the goalGetterBirthday property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGoalGetterBirthday() {
        return goalGetterBirthday;
    }

    /**
     * Sets the value of the goalGetterBirthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGoalGetterBirthday(XMLGregorianCalendar value) {
        this.goalGetterBirthday = value;
    }

    /**
     * Gets the value of the goalGetterGoalCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGoalGetterGoalCount() {
        return goalGetterGoalCount;
    }

    /**
     * Sets the value of the goalGetterGoalCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGoalGetterGoalCount(Integer value) {
        this.goalGetterGoalCount = value;
    }

    /**
     * Gets the value of the goalGetterID property.
     * 
     */
    public int getGoalGetterID() {
        return goalGetterID;
    }

    /**
     * Sets the value of the goalGetterID property.
     * 
     */
    public void setGoalGetterID(int value) {
        this.goalGetterID = value;
    }

    /**
     * Gets the value of the goalGetterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoalGetterName() {
        return goalGetterName;
    }

    /**
     * Sets the value of the goalGetterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoalGetterName(String value) {
        this.goalGetterName = value;
    }

    /**
     * Gets the value of the goalGetterNationality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoalGetterNationality() {
        return goalGetterNationality;
    }

    /**
     * Sets the value of the goalGetterNationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoalGetterNationality(String value) {
        this.goalGetterNationality = value;
    }

}
