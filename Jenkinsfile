node {
    def app

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }
    stage('Build Source') {
	sh "mvn -Dtest=HomeControllerUnitTest test"    
        sh "mvn -Dmaven.test.skip=true clean package"
	//step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
    }
    stage('Build image') {

        app = docker.build("${repo_name}")
    }

    stage('Test image') {

        app.withRun ('-p 8181:8080') {c ->
            wrap([$class: 'Xvfb', screen: '1440x900x24']) {
		sh "mvn -Dtest=SeleniumIntegrationFirefoxTest test"
	    }
        }
    }

    stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com/', 'docker-hub-creds') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
}
