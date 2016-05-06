
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
 *         &lt;element name="GetCurrentGroupOrderIDResult" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "getCurrentGroupOrderIDResult"
})
@XmlRootElement(name = "GetCurrentGroupOrderIDResponse")
public class GetCurrentGroupOrderIDResponse {

    @XmlElement(name = "GetCurrentGroupOrderIDResult")
    protected int getCurrentGroupOrderIDResult;

    /**
     * Gets the value of the getCurrentGroupOrderIDResult property.
     * 
     */
    public int getGetCurrentGroupOrderIDResult() {
        return getCurrentGroupOrderIDResult;
    }

    /**
     * Sets the value of the getCurrentGroupOrderIDResult property.
     * 
     */
    public void setGetCurrentGroupOrderIDResult(int value) {
        this.getCurrentGroupOrderIDResult = value;
    }

}
