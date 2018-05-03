
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
 *         &lt;element name="GetNextMatchByLeagueTeamResult" type="{http://msiggi.de/Sportsdata/Webservices}Matchdata"/&gt;
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
    "getNextMatchByLeagueTeamResult"
})
@XmlRootElement(name = "GetNextMatchByLeagueTeamResponse")
public class GetNextMatchByLeagueTeamResponse {

    @XmlElement(name = "GetNextMatchByLeagueTeamResult", required = true)
    protected Matchdata getNextMatchByLeagueTeamResult;

    /**
     * Gets the value of the getNextMatchByLeagueTeamResult property.
     * 
     * @return
     *     possible object is
     *     {@link Matchdata }
     *     
     */
    public Matchdata getGetNextMatchByLeagueTeamResult() {
        return getNextMatchByLeagueTeamResult;
    }

    /**
     * Sets the value of the getNextMatchByLeagueTeamResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Matchdata }
     *     
     */
    public void setGetNextMatchByLeagueTeamResult(Matchdata value) {
        this.getNextMatchByLeagueTeamResult = value;
    }

}
