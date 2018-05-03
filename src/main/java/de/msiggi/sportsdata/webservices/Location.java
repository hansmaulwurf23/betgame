
package de.msiggi.sportsdata.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Location complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Location"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="locationCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="locationID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="locationStadium" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location", propOrder = {
    "locationCity",
    "locationID",
    "locationStadium"
})
public class Location {

    protected String locationCity;
    protected int locationID;
    protected String locationStadium;

    /**
     * Gets the value of the locationCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCity() {
        return locationCity;
    }

    /**
     * Sets the value of the locationCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCity(String value) {
        this.locationCity = value;
    }

    /**
     * Gets the value of the locationID property.
     * 
     */
    public int getLocationID() {
        return locationID;
    }

    /**
     * Sets the value of the locationID property.
     * 
     */
    public void setLocationID(int value) {
        this.locationID = value;
    }

    /**
     * Gets the value of the locationStadium property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationStadium() {
        return locationStadium;
    }

    /**
     * Sets the value of the locationStadium property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationStadium(String value) {
        this.locationStadium = value;
    }

}
