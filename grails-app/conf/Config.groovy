// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

grails.config.locations = [ "classpath:${appName}-config.properties",
                            "classpath:${appName}-config.groovy",
                            "file:${userHome}/.grails/${appName}-config.properties",
                            "file:${userHome}/.grails/${appName}-config.groovy"]

if (System.properties["${appName}.config.location"]) {
   grails.config.locations << "file:" + System.properties["${appName}.config.location"]
}


grails.app.context = '/'
grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml'],
    pdf:           ['text/pdf', 'application/pdf']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// grails.plugin.databasemigration.changelogFileName = 'new.groovy'

environments {
 
    development {
        grails.serverURL = "http://localhost:8280"
        grails.indexPath = "/development/index.html"
        grails.extjsIndexPath = "/development/index.html"
        grails.touchIndexPath = "/development/touch/index.html"
        grails.logging.jul.usebridge = true
        grails.converters.default.pretty.print = true

        grails.foodprint.service.server.url = "http://localhost:8080"
        grails.foodprint.service.api.url = "http://localhost:8080/api"
        grails.foodpaint.service.server.url = "http://localhost:8180"
        grails.foodpaint.service.api.url = "http://localhost:8180/api"
        // grails.aws.root = 'test'

        log4j = {
            appenders {
                file name: 'grailsfile', file: 'target/grails.log'
                file name: 'rootlog', file: 'target/root.log'
                file name: 'devfile', file: 'target/development.log'

                layout: pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2} %m%n")
            }
            root { 
                error 'stdout', 'rootlog' 
            }
            info additivity: false, grailsfile: 'org.codehaus.groovy.grails.commons'
            all additivity: false, devfile: [
                'grails.app.controllers',
                'grails.app.domain',
                'grails.app.services',
                'grails.app.taglib',
                'grails.app.conf',
                'grails.app.filters',
                'grails.app.jobs'
            ]
        }
        // grails.plugin.databasemigration.updateOnStart = true
        // grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']
    }
 
    test {

        grails.serverURL = "http://localhost:8280"
        grails.indexPath = "/test/index.html"
        grails.logging.jul.usebridge = true
        grails.resources.debug=true
        grails.converters.default.pretty.print = true

        grails.foodprint.service.server.url = "http://localhost:8080"
        grails.foodprint.service.api.url = "http://localhost:8080/api"
        grails.foodpaint.service.server.url = "http://localhost:8180"
        grails.foodpaint.service.api.url = "http://localhost:8180/api"

        log4j = {
            appenders {
                file name: 'grailsfile', file: 'target/grails.log'
                file name: 'rootlog', file: 'target/root.log'
                file name: 'testfile', file: 'target/test.log'
                
                layout: pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2} %m%n")
            }
            root { error 'stdout', 'rootlog' }
            info additivity: false, grailsfile: 'org.codehaus.groovy.grails.commons'
            all additivity: false, testfile: [
                'grails.app.controllers',
                'grails.app.domain',
                'grails.app.services',
                'grails.app.taglib',
                'grails.app.conf',
                'grails.app.filters'
            ]
     
        }
    }
    production {
        
        grails.logging.jul.usebridge = false

        grails.indexPath = "/index.html"
        grails.extjsIndexPath = "/index.html"
        grails.touchIndexPath = "/touch/index.html"
        grails.foodprint.service.server.url = "http://localhost:8080/"
        grails.foodprint.service.api.url = "http://localhost:8080/api"
        grails.foodpaint.service.server.url = "http://localhost:8080/foodpaint"
        grails.foodpaint.service.api.url = "http://localhost:8080/foodpaint/api"
        grails.serverURL = "http://localhost:8280"
        // grails.aws.root = 'attachment'

        log4j = {
            root { 
                error()
            }
        }

        grails.plugin.databasemigration.updateOnStart = true
        grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']
    }
}

extjs.version = '5.1.1'
touch.version = '2.4.2'

grails.plugin.springsecurity.userLookup.userDomainClassName = 'foodpark.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'foodpark.UserRole'
grails.plugin.springsecurity.authority.className = 'foodpark.Role'
grails.plugin.springsecurity.password.algorithm = 'bcrypt'//'SHA-256'
grails.plugin.springsecurity.password.hash.iterations = 1 //SHA-256 setting
grails.plugin.springsecurity.password.bcrypt.logrounds = 10 //bcrypt setting

grails.plugin.springsecurity.useSwitchUserFilter = true
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
   '/j_spring_security_switch_user': ['ROLE_ADMIN']
]
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.fii.rejectPublicInvocations = false


grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/login/authSucccessExtJs'
grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
grails.plugin.springsecurity.failureHandler.defaultFailureUrl = '/login/authFailExtJs?login_error=1'

// grails.upload.location.s3 = true
// grails.upload.location.local.path="${userHome}/.grails/image"

// aws.domain = ''
// aws.accessKey = ''
// aws.secretKey = ''
// aws.bucketName = ''

grails.i18nType = 'agri'
