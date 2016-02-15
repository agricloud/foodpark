package common

import foodpark.Site

class ExtJSFilters {

	def springSecurityService
	def dateService

	def filters = {
		create(controller:'*', action:'save') {
			before = {
				def user = springSecurityService.currentUser
				if(user){
					params["creator"] = user.username
				}
			}
		}
		all(controller:'*', action:'*') {
			before = {
				params.each {
					key, value ->
					// Transform value from Ext JS to Grails date style 
					// 時區 (\+|\-)\d\d:\d\d
					if (value ==~ /^\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d$/) {

						params[key] = dateService.parseToUTC("yyyy-MM-dd'T'HH:mm:ss", value)
						log.info "Found ${value} is a Ext JS date format, transform into Grails style"

					}else if (value ==~ /^\d\d\d\d\-\d\d\-\d\d$/) {
						params[key] = dateService.parseToUTC('yyyy-MM-dd', value)
						log.info "Found ${value} is a Ext JS date format, transform into Grails style"
					}

					// 處理科學記號數字
					if(value ==~ /^[-]?(\d.)?[\d]+[e][+-][\d]+$/){
						params[key] = Double.parseDouble(value)
					}

					// 參考連結 http://grails.org/doc/latest/guide/single.html#dataBinding
					// 其中：An association property can be set to null by passing the literal String "null".
					// 可能風險，null 值是真的要作為 null 值，而不是文字的 'null' 值 
					if(!value && key.endsWith(".id")){
						value = "null"
					}
					else{//for sencha touch
						if(params.senchaEnv=="touch" && value=="null"){
							params[key] = null
						}
					}
					//for sencha touch date format: Sat Jul 25 2015 00:00:00 GMT+0800 (CST)
					if(params.senchaEnv=="touch" && value==~/^\w\w\w \w\w\w \d\d \d\d\d\d \d\d:\d\d:\d\d GMT[+-]\d\d\d\d \(CST\)$/){
						params[key] = dateService.parseCSTToUTC(value)
					}

				}

				// 如果從前端 extjs 傳進來需要進行分頁處理，轉換為 grails 處理分頁之 params
				if(params.start && params.limit){
					params.offset = params.int('start')?:0
					params.max = params.int('limit')?:50
				}

				if(params.sort){
					params.sortJson = grails.converters.JSON.parse(params.sort)
					if(params.sortJson.size()==1){
						params.sort = params.sortJson[0].property
						params.order = params.sortJson[0].direction
					}
					else
						params.remove('sort')
				}
				
				def user = springSecurityService.currentUser
				if(user){
					params["site.id"] = user.site?.id
					params["editor"] = user.username
				}

				params.criteria = {

					if(params.controller != 'site'){
						eq('site', Site.get(params["site.id"]))
					}
					else{
						eq('id', params["site.id"])
					}
					
					if(params.filter){                           
						def filterJson = grails.converters.JSON.parse(params.filter)
						filterJson.each{

							if(it.type == 'string'){
								it.value = it.value+"%"
							}else if(it.type == 'numeric'){
								 it.value = it.value.toDouble()
							}else if(it.type == 'date'){
								it.value = dateService.parseToUTC('MM/dd/yyyy', it.value)
							}

							if(it.comparison){

								if(it.comparison == 'lt') lt(it.field, it.value)
								else if(it.comparison == 'gt')gt(it.field, it.value)
								else eq(it.field, it.value)

							}else like(it.field, it.value)

						}
					}
					if(params.nameLike){
						like('name', params.nameLike+"%")
					}

					if(params.sortJson && params.sortJson.size()>1){
						params.sortJson.each{
							order(it.property, it.direction)
						}
					}

				}//end params.criteria
			}
			after = { Map model ->

			}
			afterView = { Exception e ->

			}
		}
	}
}
