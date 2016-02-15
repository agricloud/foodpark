import foodpark.*
import common.*
import grails.converters.JSON

class BootStrap {

	def init = { servletContext ->

		// 預設時區，避免 json 轉換自動扣除 8 小時(台灣 +8:00)
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
		jsonParseDefine()

		environments {
			development {

			}
		}

	}

	def destroy = {
		
	}

	private jsonParseDefine(){
		// JSON.registerObjectMarshaller(User) {
		// 	convertService.userParseJson(it)
		// }
	}
}
