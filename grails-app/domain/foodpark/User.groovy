package foodpark

class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	def grailsApplication
	def messageSource

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	/**
	 * Full Name (Detail)
	 */
	String fullName

	/**
	 * E-Mail
	 */
	String email

	static belongsTo = [
		site: Site		
	]

	

	User(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	@Override
	int hashCode() {
		username?.hashCode() ?: 0
	}

	@Override
	boolean equals(other) {
		is(other) || (other instanceof User && other.username == username)
	}

	@Override
	String toString() {
		username
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username unique: true, blank: false, matches: "[a-zA-Z][a-zA-Z0-9]{3,}"
		password blank: false
		fullName blank: true
		email nullable: true, email: true
	}

	static mapping = {
		password column: '`password`'
	}
}
