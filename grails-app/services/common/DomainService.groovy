package common
import org.springframework.transaction.annotation.Transactional

class DomainService {

	def grailsApplication
	def messageSource

	@Transactional
	def save(domainObject) {

		def i18nType = grailsApplication.config.grails.i18nType
		def success
		def errors=[:]
		def msg
		Object[] args = [domainObject]

		if (!domainObject) {

			msg = messageSource.getMessage("${i18nType}.default.message.notfound", args, Locale.getDefault())
			
			return [success: false, message: msg]
			
		}else if (domainObject.validate() && domainObject.save()) {

			msg = messageSource.getMessage("${i18nType}.default.message.save.success", args, Locale.getDefault())
			success = true;

		}else{
			//batchRoute update operator時，雖然validate false仍會儲存，加入discard()指令避免儲存更新。
			domainObject.discard()

			domainObject.errors.allErrors.each{ 
				
				it.codes.eachWithIndex{ code, i ->
					it.codes[i] = i18nType+"."+code
				}

				if(it.field == 'operation' || it.field == 'workstation' || it.field == 'item')
					errors[it.field+".name"]=messageSource.getMessage(it, Locale.getDefault())
				else errors[it.field]=messageSource.getMessage(it, Locale.getDefault())
				log.error messageSource.getMessage(it, Locale.getDefault())
			}

			msg = messageSource.getMessage("${i18nType}.default.message.save.failed", args, Locale.getDefault())
			success = false;
		}

		return [success: success, message: msg, errors: errors]

	}

	@Transactional
	def delete(domainObject) {

		def i18nType = grailsApplication.config.grails.i18nType
		Object[] args = [domainObject, null]
		def msg


		if (!domainObject) {
			msg = messageSource.getMessage("${i18nType}.default.message.notfound", args, Locale.getDefault())

			return [success: false, message: msg]
			
		}

		try {
			
			domainObject.delete(flush: true)
			msg = messageSource.getMessage("${i18nType}.default.message.delete.success", args, Locale.getDefault())
			return [success: true, message: msg]

		}
		catch (e) {
			log.error e
			throw e
		}

	}

}
