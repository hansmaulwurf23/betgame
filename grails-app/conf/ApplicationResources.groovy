modules = {
    application {
        resource url:'js/application.js'
    }
	
	'custom-bootstrap' {
		dependsOn 'bootstrap'
		resource url:[dir: 'less', file: 'main.less'], attrs:[rel: "stylesheet/less", type:'css']
	}
}