package de.betgame


class Team {

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	String name
	String iconUrl
	String code
	String net
	
	/* Automatic timestamping of GORM */
	Date	dateCreated
	Date	lastUpdated
	
//	static	belongsTo	= []	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
//	static	hasMany		= []	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping 
	
    static	mapping = {
		id column: "team_id", generator: "assigned"
		autoTimestamp true
		version false
    }
    
	static	constraints = {
		iconUrl nullable: true
		code nullable: true
		dateCreated nullable: true
		lastUpdated nullable: true
		net nullable: true
    }
	
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
