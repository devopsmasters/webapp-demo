node {
    def app

    stage('Development') {
        /* Let's make sure we have the repository cloned to our workspace */
	task 'Checkout Source'
	    checkout scm
	
	task 'Code Analysis'
	    sh "echo code analysis tool"
	
	task 'Unit Test'
	    sh "mvn -Dtest=HomeControllerUnitTest test"
	    
	task 'Build Package'
	    sh "mvn -Dmaven.test.skip=true clean package"
	    
	task 'Verify Unit Test Results'
	    sh "echo verified"
	    
	task 'Upload to Artifactory'
	    sh "echo push to artifactory"
    }
    stage('Integration') {
	task 'Create Docker Image'
	    app = docker.build("${repo_name}")
	
	task 'Run Integration'
	    /*app.withRun ('-p 8181:8080') {c ->
              wrap([$class: 'Xvfb', screen: '1440x900x24']) {
		sh "mvn -Dtest=SeleniumIntegrationFirefox test"
	      }
            }*/
	
	task 'Push Image to Docker Registry'
	    docker.withRegistry('https://registry.hub.docker.com/', 'docker-hub-creds') {
              app.push("${env.BUILD_NUMBER}")
              app.push("latest")
        }
    }

    stage('Performance') {
	    
        task 'Performance Testing'
	    sh "echo Performance testing"    
    }
	
    stage('UAT') {
        
	task 'UAT Testing'
	    sh "echo UAT testing"
	    
    }
}
