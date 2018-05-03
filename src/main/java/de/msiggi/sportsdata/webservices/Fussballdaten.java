
package de.msiggi.sportsdata.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Fussballdaten complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Fussballdaten"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SpielID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Spieltag" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Spieldatum" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="Team1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Team2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErgebnisTeam1" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ErgebnisTeam2" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="lastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fussballdaten", propOrder = {
    "spielID",
    "spieltag",
    "spieldatum",
    "team1",
    "team2",
    "ergebnisTeam1",
    "ergebnisTeam2",
    "lastUpdate"
})
public class Fussballdaten {

    @XmlElement(name = "SpielID")
    protected int spielID;
    @XmlElement(name = "Spieltag")
    protected int spieltag;
    @XmlElement(name = "Spieldatum", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar spieldatum;
    @XmlElement(name = "Team1")
    protected String team1;
    @XmlElement(name = "Team2")
    protected String team2;
    @XmlElement(name = "ErgebnisTeam1")
    protected int ergebnisTeam1;
    @XmlElement(name = "ErgebnisTeam2")
    protected int ergebnisTeam2;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;

    /**
     * Gets the value of the spielID property.
     * 
     */
    public int getSpielID() {
        return spielID;
    }

    /**
     * Sets the value of the spielID property.
     * 
     */
    public void setSpielID(int value) {
        this.spielID = value;
    }

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
     * Gets the value of the spieldatum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSpieldatum() {
        return spieldatum;
    }

    /**
     * Sets the value of the spieldatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSpieldatum(XMLGregorianCalendar value) {
        this.spieldatum = value;
    }

    /**
     * Gets the value of the team1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeam1() {
        return team1;
    }

    /**
     * Sets the value of the team1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeam1(String value) {
        this.team1 = value;
    }

    /**
     * Gets the value of the team2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeam2() {
        return team2;
    }

    /**
     * Sets the value of the team2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeam2(String value) {
        this.team2 = value;
    }

    /**
     * Gets the value of the ergebnisTeam1 property.
     * 
     */
    public int getErgebnisTeam1() {
        return ergebnisTeam1;
    }

    /**
     * Sets the value of the ergebnisTeam1 property.
     * 
     */
    public void setErgebnisTeam1(int value) {
        this.ergebnisTeam1 = value;
    }

    /**
     * Gets the value of the ergebnisTeam2 property.
     * 
     */
    public int getErgebnisTeam2() {
        return ergebnisTeam2;
    }

    /**
     * Sets the value of the ergebnisTeam2 property.
     * 
     */
    public void setErgebnisTeam2(int value) {
        this.ergebnisTeam2 = value;
    }

    /**
     * Gets the value of the lastUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the value of the lastUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

}
