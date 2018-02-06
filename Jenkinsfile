node {
    def app

    stage('Development') {
        /* Let's make sure we have the repository cloned to our workspace */
	task 'Checkout Source'
	    checkout scm
	
	task 'Static Code Analysis'
	    sh "echo code analysis tool"
	
	task 'Unit Test'
	    sh "mvn -Dtest=HomeControllerUnitTest test"
	
    } 
   stage('Build') {   
	task 'Build Package'
	    sh "mvn -Dmaven.test.skip=true clean package"
	    
	task 'Upload artifacts to Artifactory'
	    sh "echo push to artifactory"
    }
    
    stage('Setup Selenium Grid') {
        sh 'docker pull elgalu/selenium && docker pull dosel/zalenium'
        sh 'docker run --rm -d --name zalenium -p 4444:4444 \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v /tmp/videos:/home/seluser/videos \
            --privileged dosel/zalenium start'
        
        sh 'sleep 10'
        //sh 'curl -sSL http://localhost:4444/wd/hub/status | jq .value.ready | grep true'
    }
	
    stage('Integration') {

	task 'Create Docker Image'
	    app = docker.build("${repo_name}")
	
	task 'Run Integration'
            try {
	      app.withRun ('-p 8181:8080') {c ->
                withMaven(maven: 'maven3')  {
		  sh "mvn -Dtest=SeleniumIntegrationFirefox test"
		  sh 'mvn clean test -Dwebdriver.type=remote -Dwebdriver.url=http://localhost:4444/wd/hub -Dwebdriver.cap.browserName=chrome -Dmaven.test.failure.ignore=true'
	        }
              }
            }
	    finally {
		sh 'docker stop zalenium'
		junit testResults: 'target/*.xml', allowEmptyResults: true
                archiveArtifacts 'target/**'	
            }


	
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
