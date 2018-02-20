node {
    def app

    stage('Development') {
        /* Let's make sure we have the repository cloned to our workspace */
	task 'Checkout Source'
	    checkout scm
    }
    stage('Code Analysis') {	
	task 'Static Code Analysis'
	    sh "echo code analysis tool"
    }
    stage('Unit Testing') {
	task 'Run Unit Test'
	    sh "mvn -Dtest=HomeControllerUnitTest test"
    } 
   stage('Build') {   
	task 'Build Package'
	    sh "mvn -Dmaven.test.skip=true clean package"
   }
   stage('Deploy artifact to repo') {	    
	task 'Upload artifacts to Artifactory'
	    sh "echo push to artifactory"
   }
   stage('Create Docker Image') {
    	task 'Create Docker Image'
	    app = docker.build("${repo_name}")
   }
	
    stage('Setup Selenium Grid') {
        sh 'docker pull elgalu/selenium && docker pull dosel/zalenium'
        sh 'docker run --rm -d --name zalenium -p 4444:4444 \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v /tmp/videos:/home/seluser/videos \
            --privileged dosel/zalenium start'
        sh 'sleep 15'
        //sh 'curl -sSL http://localhost:4444/wd/hub/status | jq .value.ready | grep true'
    }
	
    stage('Functional Test') {
	task 'Run Selenium Tests'
            try {
	      app.withRun ('-p 8181:8080 --name sampleapp') {c ->
                withMaven(maven: 'maven3')  {
		  sh 'mvn -Dtest=SeleniumIntegration clean test -Dwebdriver.type=remote -Dwebdriver.url=http://127.0.0.1:4444/wd/hub -Dwebdriver.cap.browserName=chrome -Dmaven.test.failure.ignore=true'
	        }
              }
            }
	    finally {
		sh 'docker stop zalenium'
		junit testResults: 'target/*.xml', allowEmptyResults: true
                archiveArtifacts 'target/**'	
            }
    }
    stage('Deploy Container Image to Repository') {
	
	task 'Push Image to Docker Registry'
	    docker.withRegistry('https://registry.hub.docker.com/', 'docker-hub-creds') {
              app.push("${env.BUILD_NUMBER}")
              app.push("latest")
        }
    }
    stage('Deploy Application') {
	
	task 'Deploy application on an EC2 instance'
	dir ('./terraform') {
	    sh "/usr/local/bin/terraform init"    
	    sh "/usr/local/bin/terraform apply -var DOCKER_IMAGE=${repo_name} -auto-approve"
	}
    }
    
}
