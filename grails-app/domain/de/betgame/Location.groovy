package de.betgame


class Location {

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	String city
	String stadium
	String groundKey
	Integer capacity
	
	/* Automatic timestamping of GORM */
	Date	dateCreated
	Date	lastUpdated
	
//	static	belongsTo	= []	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
//	static	hasMany		= []	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping 
	
    static	mapping = {
		id column: "location_id", generator: "assigned"
		autoTimestamp true
		version false
    }
    
	static	constraints = {
		city nullable:true
		stadium nullable:true
		groundKey nullable:true
		capacity nullable:true
		dateCreated nullable:true
		lastUpdated nullable:true
    }
	
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
	public String toString() {
		return "${city} ($stadium)";
	}
}
