
package de.msiggi.sportsdata.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Team complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Team"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="teamID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="teamName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="teamIconURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Team", propOrder = {
    "teamID",
    "teamName",
    "teamIconURL"
})
public class Team {

    protected int teamID;
    protected String teamName;
    protected String teamIconURL;

    /**
     * Gets the value of the teamID property.
     * 
     */
    public int getTeamID() {
        return teamID;
    }

    /**
     * Sets the value of the teamID property.
     * 
     */
    public void setTeamID(int value) {
        this.teamID = value;
    }

    /**
     * Gets the value of the teamName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Sets the value of the teamName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamName(String value) {
        this.teamName = value;
    }

    /**
     * Gets the value of the teamIconURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamIconURL() {
        return teamIconURL;
    }

    /**
     * Sets the value of the teamIconURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamIconURL(String value) {
        this.teamIconURL = value;
    }

}
