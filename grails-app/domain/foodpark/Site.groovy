package foodpark

class Site {
	
	def grailsApplication
	def messageSource

	String editor
	String creator
	Date dateCreated
	Date lastUpdated

	String name
	String title
	String description
	String address

	static hasMany = [
		users: User
	]

	static constraints = {
		editor nullable: true
		creator nullable: true
		name unique: true, blank: false, matches: "[a-zA-Z][a-zA-Z0-9]{3,}"
		description nullable: true
		address nullable: true
	}

	def getGrailsApplication(){
		return grailsApplication
	}

	def getMessageSource(){
		return messageSource
	}

	public String toString(){
		def i18nType = getGrailsApplication().config.grails.i18nType
		Object[] args = [Site]
		"""
		${getMessageSource().getMessage("${i18nType}.site.label", args, Locale.getDefault())}: 
		${name}-${title}
		"""
	}
}
