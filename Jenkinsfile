node {
    def app

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }
    stage('Build Source') {
        sh mvn clean package
	step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
    }
    stage('Build image') {

        app = docker.build("${repo_name}")
    }

    stage('Test image') {

        app.withRun ('-p 8181:8080') {c ->
            sh "mvn verify"
        }
    }

    stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com/', 'docker-hub-creds') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
}
