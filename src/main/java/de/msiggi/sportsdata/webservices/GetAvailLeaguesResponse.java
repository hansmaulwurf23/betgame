
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
 *         &lt;element name="GetAvailLeaguesResult" type="{http://msiggi.de/Sportsdata/Webservices}ArrayOfLeague" minOccurs="0"/&gt;
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
    "getAvailLeaguesResult"
})
@XmlRootElement(name = "GetAvailLeaguesResponse")
public class GetAvailLeaguesResponse {

    @XmlElement(name = "GetAvailLeaguesResult")
    protected ArrayOfLeague getAvailLeaguesResult;

    /**
     * Gets the value of the getAvailLeaguesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLeague }
     *     
     */
    public ArrayOfLeague getGetAvailLeaguesResult() {
        return getAvailLeaguesResult;
    }

    /**
     * Sets the value of the getAvailLeaguesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLeague }
     *     
     */
    public void setGetAvailLeaguesResult(ArrayOfLeague value) {
        this.getAvailLeaguesResult = value;
    }

}
