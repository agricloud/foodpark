package foodpark

import org.springframework.dao.DataIntegrityViolationException
import grails.transaction.Transactional

class SiteController {

	def grailsApplication
	def domainService

	def index = {
		def list = Site.createCriteria().list(params, params.criteria)
		render (contentType: 'application/json') {
			[data: list, total: list.totalCount]
		}
	}

	def show = {

		def i18nType = grailsApplication.config.grails.i18nType

		def site = Site.get(params.id);  
		if(site){   
			render (contentType: 'application/json') {
				[success: true, data: site]
			}
		}else {
			render (contentType: 'application/json') {
				[success: false, message: message(code: "${i18nType}.default.message.show.failed")]
			}          
		}
	}
	
	def create = {
		def site = new Site()        
		render (contentType: 'application/json') {
			[success: true, data: site]
		}
	}

	def save = {
		def site = new Site(params)
		render (contentType: 'application/json') {
			domainService.save(site)
		}
	}

	def update = {
		def  site = Site.get(params.id)
		site.properties = params
		render (contentType: 'application/json') {
			domainService.save(site)
		}         
	}

	@Transactional
	def delete(){

		def i18nType = grailsApplication.config.grails.i18nType
		
		def site = Site.get(params.id)
		def result
		try {
			
			result = domainService.delete(site)
		
		}catch(DataIntegrityViolationException e){
			log.error e
			def msg = message(code: "${i18nType}.default.message.delete.failed", args: [site, e])
			result = [success: false, message: msg] 
		}
		
		render (contentType: 'application/json') {
			result
		}
	}
}
