
package de.msiggi.sportsdata.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Spieltag" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Liga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Saison" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Userkennung" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "spieltag",
    "liga",
    "saison",
    "userkennung"
})
@XmlRootElement(name = "GetFusballdaten")
public class GetFusballdaten {

    @XmlElement(name = "Spieltag")
    protected int spieltag;
    @XmlElement(name = "Liga")
    protected String liga;
    @XmlElement(name = "Saison")
    protected int saison;
    @XmlElement(name = "Userkennung")
    protected String userkennung;

    /**
     * Gets the value of the spieltag property.
     * 
     */
    public int getSpieltag() {
        return spieltag;
    }

    /**
     * Sets the value of the spieltag property.
     * 
     */
    public void setSpieltag(int value) {
        this.spieltag = value;
    }

    /**
     * Gets the value of the liga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiga() {
        return liga;
    }

    /**
     * Sets the value of the liga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiga(String value) {
        this.liga = value;
    }

    /**
     * Gets the value of the saison property.
     * 
     */
    public int getSaison() {
        return saison;
    }

    /**
     * Sets the value of the saison property.
     * 
     */
    public void setSaison(int value) {
        this.saison = value;
    }

    /**
     * Gets the value of the userkennung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserkennung() {
        return userkennung;
    }

    /**
     * Sets the value of the userkennung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserkennung(String value) {
        this.userkennung = value;
    }

}
