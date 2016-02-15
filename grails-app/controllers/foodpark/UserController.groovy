package foodpark

import org.springframework.dao.DataIntegrityViolationException
import grails.transaction.Transactional

class UserController {

	static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
	def grailsApplication
	def domainService
	def springSecurityService

	def index = {

		def list = User.createCriteria().list(params, params.criteria)


		render (contentType: 'application/json') {
			[data: list, total: list.totalCount]
		}
		
	}

	def show = {

		def i18nType = grailsApplication.config.grails.i18nType

		def user = User.get(params.id);  
		if(user){   
			render (contentType: 'application/json') {
				[success: true, data: user]
			}
		}else {
			render (contentType: 'application/json') {
				[success: false, message: message(code: "${i18nType}.default.message.show.failed")]
			}          
		}
	}
	def create = {

		def user = new User()
		user.site =  springSecurityService?.currentUser?.site
		render (contentType: 'application/json') {
			[success: true, data: user]
		}
	}
 
 	@Transactional
	def save(){

		def i18nType = grailsApplication.config.grails.i18nType

		if(params.password && !(params.password ==~ /^[a-zA-Z0-9]{4,}$/)){
			render (contentType: 'application/json') {
				[success: false, message: message(code: "${i18nType}.user.password.invalid")]
			} 
			return 
		}

		if(!params.site?.id){
			def site = new Site(name: params.username, title: params.fullName, creator: params.username)
			def result = domainService.save(site)
			if(result.success)
				params["site.id"] = site.id

			else{
				render (contentType: 'application/json') {
					result
				}
				return
			}

		}		

		def user = new User(params)
		render (contentType: 'application/json') {
			domainService.save(user)
		}
	}

	@Transactional
	def update(){

		def i18nType = grailsApplication.config.grails.i18nType

		if(params.password && !(params.password ==~ /^[a-zA-Z0-9]{4,}$/)){
			render (contentType: 'application/json') {
				[success: false, message: message(code: "${i18nType}.user.password.invalid")]
			} 
			return 
		}

		def user = User.get(params.id)
			user.properties = params
		
		render (contentType: 'application/json') {
			domainService.save(user)
		}      
	}

	@Transactional
	def delete(){

		def i18nType = grailsApplication.config.grails.i18nType
		
		def user = User.get(params.id)
		def result
		try {
			
			result = domainService.delete(user)
		
		}catch(e){
			log.error e
			def msg = message(code: "${i18nType}.default.message.delete.failed", args: [user, e.getMessage()])
			result = [success: false, message: msg] 
		}
		
		render (contentType: 'application/json') {
			result
		}
	}
	
}
