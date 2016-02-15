package common

import grails.converters.JSON
import java.text.SimpleDateFormat

class DateService {

	static location=foodpark.Country.TAIWAN
	def defaultPattern = 'yyyy-MM-dd HH:mm:ss'

	//取得當地目前時間(String)
	def getStrDate(){
		def strDate = new Date().format(defaultPattern, getTimeZone())
	}

	//取得指定格式的當地目前時間(String)
	def getStrDate(format){
		if(format)
			def strdate = new Date().format(format, getTimeZone())
		else
			def strdate = newDate()
	}

	//取得當地時區
	def getTimeZone(){
		if(location==foodpark.Country.TAIWAN)
			return TimeZone.getTimeZone("GMT+8")
	}

	//將指定的UTC時間(Date)->轉換為當地時間(String) ISO8601格式 ex: 2014-04-01T14:04:01Z
	//Sun May 31 16:00:00 UTC 2015 -> 2015-06-01T00:00:00Z
	def formatWithISO8601(date){
		if(date)
			def strDate = date.format("yyyy-MM-dd'T'HH:mm:ss'Z'",getTimeZone())
		else
			null
	}

	//將指定的當地時間(String)->轉換為UTC時間(Date)
	//ex:yyyy-MM-dd HH:mm:ss 2015-06-01 00:00:00 -> Sun May 31 16:00:00 UTC 2015
	def parseToUTC(format, strDate){
		//需先將TimeZone預設時區轉換為當地時區 才可使用format轉換為UTC
		TimeZone.setDefault(getTimeZone())
		def date = Date.parse(format, strDate)
		strDate = date.format(defaultPattern, TimeZone.getTimeZone("UTC"))
		TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
		date = Date.parse(defaultPattern, strDate)
		return date
	}

	//將指定的當地時間(String) ISO8601格式->轉換為UTC時間(Date)
	//ex:2015-06-01T00:00:00Z -> Sun May 31 16:00:00 UTC 2015
	def parseISO8601ToUTC(strDate){
		return parseToUTC("yyyy-MM-dd'T'HH:mm:ss'Z'", strDate)
	}

	//將指定的當地時間(String) CST格式->轉換為UTC時間(Date)
	//ex:Sat Jul 25 2015 00:00:00 GMT+0800 (CST) -> Fri Jul 24 16:00:00 UTC 2015
	def parseCSTToUTC(strDate){
		//需先將TimeZone預設時區轉換為當地時區 才可使用format轉換為UTC
		TimeZone.setDefault(getTimeZone())
		def format = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z '(CST)'"
		def date = new SimpleDateFormat(format, Locale.US).parse(strDate)
		strDate = date.format(defaultPattern, TimeZone.getTimeZone("UTC"))
		TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
		date = Date.parse(defaultPattern, strDate)
		return date
	}
	

}