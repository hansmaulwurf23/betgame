package de.betgame

class Script {

    String content
    String name
    String mode
    String folder
    String author
    String dsl
    String status
    Date created

    static constraints = {
        content nullable: false
        name nullable: false
        mode nullable: true
        folder nullable: true
        author nullable: true
        dsl nullable: true
        status nullable: true
        created nullable: false
    }

    static mapping = {
        content type:'text'
        table name:'scripts'
        id column:'script_id', generator:'sequence', params:[sequence:'script_seq', initialValue:1]
    }
}
