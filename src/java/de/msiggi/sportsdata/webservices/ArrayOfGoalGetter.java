
package de.msiggi.sportsdata.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfGoalGetter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfGoalGetter"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GoalGetter" type="{http://msiggi.de/Sportsdata/Webservices}GoalGetter" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfGoalGetter", propOrder = {
    "goalGetter"
})
public class ArrayOfGoalGetter {

    @XmlElement(name = "GoalGetter")
    protected List<GoalGetter> goalGetter;

    /**
     * Gets the value of the goalGetter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the goalGetter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoalGetter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GoalGetter }
     * 
     * 
     */
    public List<GoalGetter> getGoalGetter() {
        if (goalGetter == null) {
            goalGetter = new ArrayList<GoalGetter>();
        }
        return this.goalGetter;
    }

}
