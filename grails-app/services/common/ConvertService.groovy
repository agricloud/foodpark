package common
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import grails.converters.JSON
class ConvertService {

	def enumService
	def dateService

	def getDomainFields(domainClassName) {
			def fields = []
			def d = new DefaultGrailsDomainClass(domainClassName)
			d.persistentProperties.collect {
				fields << it.name
			}
			fields

	}

	def domainParseMap(domainObject){
		log.info domainObject.class
		def result = [:]
		getDomainFields(domainObject.class).each { field ->
			result[field] = domainObject[field]
		}
		result.id = domainObject.id

		result
	}

	def siteParseJson(site){
		def result = [:]
		result.id = site.id
		result.name = site.name
		result.title = site.title
		result.address = site.address

		result
	}

	def userParseJson(user){
		def result = [:]
		result.id = user.id
		result.username = user.username
		result.password = user.password
		result.enabled = user.enabled
		result.accountExpired = user.accountExpired
		result.accountLocked = user.accountLocked
		result.passwordExpired = user.passwordExpired
		result.fullName = user.fullName
		result.email = user.email
		if(user.site){
			result.site = user.site
			result["site.id"] = user.site.id
			result["site.name"] = user.site.name
			result["site.title"] = user.site.title
		}

		result
	}


}
