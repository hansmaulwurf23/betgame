package de.betgame

class NameUtil {

	/**
	 * buildNameMap creates a map for unique names out of a collection  
	 * @param data must be a collection of maps, holding givenname and surname
	 * @return map [ OriginalEntry : UniqueName ]
	 */
	static buildNameMap(data) {
		def nameMap = [:]
		
		// build map with givenname occurence counter
		def nameCounter = data*.givenname.countBy { it }
		// filter all ppl with unique givenname
		def nonProblemos = nameCounter.findAll { it.value == 1 }*.key
		// add them simply to nameMap
		data.findAll { it.givenname in nonProblemos }.each {
			nameMap[it] = it.givenname
		}
		
		// now get the problems
		def problemos = nameCounter*.key - nonProblemos
		// for each non unique givenname get the list of surnames and use as many characters of the surname as necessary
		problemos.each { probname ->
			def surnames = data.findAll { it.givenname == probname }*.surname
			def worked = false
			
			(1..(surnames*.size().min())).each { l ->
				if (!worked && surnames.size() == surnames.collect { it[0..(l-1)] }.toSet().size()) {
					worked = true
					data.findAll { it.givenname == probname }.each { p ->
						nameMap[p] = "${p.givenname} ${p.surname[0..(l-1)]}"
					}
				}
			}
			
			if (!worked) {
				// simply add givenname + surname combination, or find other friends ;-)
				data.findAll { it.givenname == probname }.each { p ->
					nameMap[p] = "${p.givenname} ${p.surname}"
				}
			}
		}
		
		return nameMap
	}
	
}
