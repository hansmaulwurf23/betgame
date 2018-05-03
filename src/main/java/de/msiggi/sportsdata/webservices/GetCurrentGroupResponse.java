
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
 *         &lt;element name="GetCurrentGroupResult" type="{http://msiggi.de/Sportsdata/Webservices}Group"/&gt;
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
    "getCurrentGroupResult"
})
@XmlRootElement(name = "GetCurrentGroupResponse")
public class GetCurrentGroupResponse {

    @XmlElement(name = "GetCurrentGroupResult", required = true)
    protected Group getCurrentGroupResult;

    /**
     * Gets the value of the getCurrentGroupResult property.
     * 
     * @return
     *     possible object is
     *     {@link Group }
     *     
     */
    public Group getGetCurrentGroupResult() {
        return getCurrentGroupResult;
    }

    /**
     * Sets the value of the getCurrentGroupResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Group }
     *     
     */
    public void setGetCurrentGroupResult(Group value) {
        this.getCurrentGroupResult = value;
    }

}
