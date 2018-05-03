
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
 *         &lt;element name="teamID1" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="teamID2" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "teamID1",
    "teamID2"
})
@XmlRootElement(name = "GetMatchdataByTeams")
public class GetMatchdataByTeams {

    protected int teamID1;
    protected int teamID2;

    /**
     * Gets the value of the teamID1 property.
     * 
     */
    public int getTeamID1() {
        return teamID1;
    }

    /**
     * Sets the value of the teamID1 property.
     * 
     */
    public void setTeamID1(int value) {
        this.teamID1 = value;
    }

    /**
     * Gets the value of the teamID2 property.
     * 
     */
    public int getTeamID2() {
        return teamID2;
    }

    /**
     * Sets the value of the teamID2 property.
     * 
     */
    public void setTeamID2(int value) {
        this.teamID2 = value;
    }

}
