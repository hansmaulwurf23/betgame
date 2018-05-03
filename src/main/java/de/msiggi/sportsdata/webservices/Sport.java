
package de.msiggi.sportsdata.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sport"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sportsID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="sportsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sport", propOrder = {
    "sportsID",
    "sportsName"
})
public class Sport {

    protected int sportsID;
    protected String sportsName;

    /**
     * Gets the value of the sportsID property.
     * 
     */
    public int getSportsID() {
        return sportsID;
    }

    /**
     * Sets the value of the sportsID property.
     * 
     */
    public void setSportsID(int value) {
        this.sportsID = value;
    }

    /**
     * Gets the value of the sportsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSportsName() {
        return sportsName;
    }

    /**
     * Sets the value of the sportsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSportsName(String value) {
        this.sportsName = value;
    }

}
