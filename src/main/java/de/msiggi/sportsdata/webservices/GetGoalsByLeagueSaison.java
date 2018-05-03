
package de.msiggi.sportsdata.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="leagueShortcut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="leagueSaison" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "leagueShortcut",
    "leagueSaison"
})
@XmlRootElement(name = "GetGoalsByLeagueSaison")
public class GetGoalsByLeagueSaison {

    protected String leagueShortcut;
    protected String leagueSaison;

    /**
     * Gets the value of the leagueShortcut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeagueShortcut() {
        return leagueShortcut;
    }

    /**
     * Sets the value of the leagueShortcut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeagueShortcut(String value) {
        this.leagueShortcut = value;
    }

    /**
     * Gets the value of the leagueSaison property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeagueSaison() {
        return leagueSaison;
    }

    /**
     * Sets the value of the leagueSaison property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeagueSaison(String value) {
        this.leagueSaison = value;
    }

}
