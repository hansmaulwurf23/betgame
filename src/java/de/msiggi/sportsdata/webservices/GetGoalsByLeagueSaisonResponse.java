
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
 *         &lt;element name="GetGoalsByLeagueSaisonResult" type="{http://msiggi.de/Sportsdata/Webservices}ArrayOfGoal" minOccurs="0"/&gt;
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
    "getGoalsByLeagueSaisonResult"
})
@XmlRootElement(name = "GetGoalsByLeagueSaisonResponse")
public class GetGoalsByLeagueSaisonResponse {

    @XmlElement(name = "GetGoalsByLeagueSaisonResult")
    protected ArrayOfGoal getGoalsByLeagueSaisonResult;

    /**
     * Gets the value of the getGoalsByLeagueSaisonResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGoal }
     *     
     */
    public ArrayOfGoal getGetGoalsByLeagueSaisonResult() {
        return getGoalsByLeagueSaisonResult;
    }

    /**
     * Sets the value of the getGoalsByLeagueSaisonResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGoal }
     *     
     */
    public void setGetGoalsByLeagueSaisonResult(ArrayOfGoal value) {
        this.getGoalsByLeagueSaisonResult = value;
    }

}
