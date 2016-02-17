package foodpark

import grails.util.Environment

class ApiController {
	def grailsApplication
	def springSecurityService
	def readSysConfig = { 
		def config = [:]

		config.foodpaintUrl = grailsApplication.config.grails.foodpaint.service.server.url
		config.foodprintUrl = grailsApplication.config.grails.foodprint.service.server.url
		config.environment = Environment.current.name
		config.i18nType = grailsApplication.config.grails.i18nType
		config.username = springSecurityService?.currentUser?.username
		config.fullName = springSecurityService?.currentUser?.fullName

		if(params.senchaEnv=="extjs")
			grailsApplication.config.grails.indexPath = grailsApplication.config.grails.extjsIndexPath
		if(params.senchaEnv=="touch")
			grailsApplication.config.grails.indexPath = grailsApplication.config.grails.touchIndexPath

		log.debug "\n extjs config: \n"+ config 
		render (contentType: "application/json"){
			config
		}

	}
}
