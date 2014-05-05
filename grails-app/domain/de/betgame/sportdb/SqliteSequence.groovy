package de.betgame.sportdb

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class SqliteSequence implements Serializable {

	String name
	String seq

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append name
		builder.append seq
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append name, other.name
		builder.append seq, other.seq
		builder.isEquals()
	}

	static mapping = {
		id composite: ["name", "seq"]
		version false
	}

	static constraints = {
		name nullable: true, maxSize: 2000000000
		seq nullable: true, maxSize: 2000000000
	}
}
