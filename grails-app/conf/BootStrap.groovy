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

				def site
				def role1 = Role.findOrSaveByAuthority('ROLE_ADMIN')
				def user1 = User.findByUsername('admin')

				(Site.findByName('site1')) ? (site = Site.findByName('site1')) : (site = new Site(name: 'site1', title: 'site1').save(failOnError:true, flush:true))
				
				if (!user1) {
					user1 = new User(username: 'admin', fullName:'admin', password: 'admin', site:site, enabled: true).save(failOnError:true, flush:true)
					UserRole.create(user1, role1)
				}

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
