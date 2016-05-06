
package de.msiggi.sportsdata.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMatchdata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMatchdata"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Matchdata" type="{http://msiggi.de/Sportsdata/Webservices}Matchdata" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMatchdata", propOrder = {
    "matchdata"
})
public class ArrayOfMatchdata {

    @XmlElement(name = "Matchdata")
    protected List<Matchdata> matchdata;

    /**
     * Gets the value of the matchdata property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the matchdata property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatchdata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Matchdata }
     * 
     * 
     */
    public List<Matchdata> getMatchdata() {
        if (matchdata == null) {
            matchdata = new ArrayList<Matchdata>();
        }
        return this.matchdata;
    }

}
