
package de.msiggi.sportsdata.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Matchdata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Matchdata"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="matchDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="groupID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="leagueID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="nameTeam1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nameTeam2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="iconUrlTeam1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="iconUrlTeam2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TimeZoneID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchDateTimeUTC" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="groupOrderID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="leagueSaison" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="leagueShortcut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idTeam1" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idTeam2" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="pointsTeam1" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="pointsTeam2" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="lastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="matchResults" type="{http://msiggi.de/Sportsdata/Webservices}ArrayOfMatchResult" minOccurs="0"/&gt;
 *         &lt;element name="NumberOfViewers" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="matchID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="leagueName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchIsFinished" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="goals" type="{http://msiggi.de/Sportsdata/Webservices}ArrayOfGoal" minOccurs="0"/&gt;
 *         &lt;element name="location" type="{http://msiggi.de/Sportsdata/Webservices}Location"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Matchdata", propOrder = {
    "matchDateTime",
    "groupID",
    "leagueID",
    "nameTeam1",
    "nameTeam2",
    "iconUrlTeam1",
    "iconUrlTeam2",
    "timeZoneID",
    "matchDateTimeUTC",
    "groupOrderID",
    "groupName",
    "leagueSaison",
    "leagueShortcut",
    "idTeam1",
    "idTeam2",
    "pointsTeam1",
    "pointsTeam2",
    "lastUpdate",
    "matchResults",
    "numberOfViewers",
    "matchID",
    "leagueName",
    "matchIsFinished",
    "goals",
    "location"
})
public class Matchdata {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar matchDateTime;
    protected int groupID;
    protected int leagueID;
    protected String nameTeam1;
    protected String nameTeam2;
    protected String iconUrlTeam1;
    protected String iconUrlTeam2;
    @XmlElement(name = "TimeZoneID")
    protected String timeZoneID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar matchDateTimeUTC;
    protected int groupOrderID;
    protected String groupName;
    protected String leagueSaison;
    protected String leagueShortcut;
    protected int idTeam1;
    protected int idTeam2;
    protected int pointsTeam1;
    protected int pointsTeam2;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    protected ArrayOfMatchResult matchResults;
    @XmlElement(name = "NumberOfViewers", required = true, type = Integer.class, nillable = true)
    protected Integer numberOfViewers;
    protected int matchID;
    protected String leagueName;
    protected boolean matchIsFinished;
    protected ArrayOfGoal goals;
    @XmlElement(required = true)
    protected Location location;

    /**
     * Gets the value of the matchDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMatchDateTime() {
        return matchDateTime;
    }

    /**
     * Sets the value of the matchDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMatchDateTime(XMLGregorianCalendar value) {
        this.matchDateTime = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     */
    public void setGroupID(int value) {
        this.groupID = value;
    }

    /**
     * Gets the value of the leagueID property.
     * 
     */
    public int getLeagueID() {
        return leagueID;
    }

    /**
     * Sets the value of the leagueID property.
     * 
     */
    public void setLeagueID(int value) {
        this.leagueID = value;
    }

    /**
     * Gets the value of the nameTeam1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameTeam1() {
        return nameTeam1;
    }

    /**
     * Sets the value of the nameTeam1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameTeam1(String value) {
        this.nameTeam1 = value;
    }

    /**
     * Gets the value of the nameTeam2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameTeam2() {
        return nameTeam2;
    }

    /**
     * Sets the value of the nameTeam2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameTeam2(String value) {
        this.nameTeam2 = value;
    }

    /**
     * Gets the value of the iconUrlTeam1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconUrlTeam1() {
        return iconUrlTeam1;
    }

    /**
     * Sets the value of the iconUrlTeam1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconUrlTeam1(String value) {
        this.iconUrlTeam1 = value;
    }

    /**
     * Gets the value of the iconUrlTeam2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconUrlTeam2() {
        return iconUrlTeam2;
    }

    /**
     * Sets the value of the iconUrlTeam2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconUrlTeam2(String value) {
        this.iconUrlTeam2 = value;
    }

    /**
     * Gets the value of the timeZoneID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneID() {
        return timeZoneID;
    }

    /**
     * Sets the value of the timeZoneID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneID(String value) {
        this.timeZoneID = value;
    }

    /**
     * Gets the value of the matchDateTimeUTC property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMatchDateTimeUTC() {
        return matchDateTimeUTC;
    }

    /**
     * Sets the value of the matchDateTimeUTC property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMatchDateTimeUTC(XMLGregorianCalendar value) {
        this.matchDateTimeUTC = value;
    }

    /**
     * Gets the value of the groupOrderID property.
     * 
     */
    public int getGroupOrderID() {
        return groupOrderID;
    }

    /**
     * Sets the value of the groupOrderID property.
     * 
     */
    public void setGroupOrderID(int value) {
        this.groupOrderID = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
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
     * Gets the value of the idTeam1 property.
     * 
     */
    public int getIdTeam1() {
        return idTeam1;
    }

    /**
     * Sets the value of the idTeam1 property.
     * 
     */
    public void setIdTeam1(int value) {
        this.idTeam1 = value;
    }

    /**
     * Gets the value of the idTeam2 property.
     * 
     */
    public int getIdTeam2() {
        return idTeam2;
    }

    /**
     * Sets the value of the idTeam2 property.
     * 
     */
    public void setIdTeam2(int value) {
        this.idTeam2 = value;
    }

    /**
     * Gets the value of the pointsTeam1 property.
     * 
     */
    public int getPointsTeam1() {
        return pointsTeam1;
    }

    /**
     * Sets the value of the pointsTeam1 property.
     * 
     */
    public void setPointsTeam1(int value) {
        this.pointsTeam1 = value;
    }

    /**
     * Gets the value of the pointsTeam2 property.
     * 
     */
    public int getPointsTeam2() {
        return pointsTeam2;
    }

    /**
     * Sets the value of the pointsTeam2 property.
     * 
     */
    public void setPointsTeam2(int value) {
        this.pointsTeam2 = value;
    }

    /**
     * Gets the value of the lastUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the value of the lastUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

    /**
     * Gets the value of the matchResults property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMatchResult }
     *     
     */
    public ArrayOfMatchResult getMatchResults() {
        return matchResults;
    }

    /**
     * Sets the value of the matchResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMatchResult }
     *     
     */
    public void setMatchResults(ArrayOfMatchResult value) {
        this.matchResults = value;
    }

    /**
     * Gets the value of the numberOfViewers property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfViewers() {
        return numberOfViewers;
    }

    /**
     * Sets the value of the numberOfViewers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfViewers(Integer value) {
        this.numberOfViewers = value;
    }

    /**
     * Gets the value of the matchID property.
     * 
     */
    public int getMatchID() {
        return matchID;
    }

    /**
     * Sets the value of the matchID property.
     * 
     */
    public void setMatchID(int value) {
        this.matchID = value;
    }

    /**
     * Gets the value of the leagueName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * Sets the value of the leagueName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeagueName(String value) {
        this.leagueName = value;
    }

    /**
     * Gets the value of the matchIsFinished property.
     * 
     */
    public boolean isMatchIsFinished() {
        return matchIsFinished;
    }

    /**
     * Sets the value of the matchIsFinished property.
     * 
     */
    public void setMatchIsFinished(boolean value) {
        this.matchIsFinished = value;
    }

    /**
     * Gets the value of the goals property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGoal }
     *     
     */
    public ArrayOfGoal getGoals() {
        return goals;
    }

    /**
     * Sets the value of the goals property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGoal }
     *     
     */
    public void setGoals(ArrayOfGoal value) {
        this.goals = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

}
