pipeline {
    agent {label ('slave1')}

    tools { // parametrs tools
        "org.jenkinsci.plugins.docker.commons.tools.DockerTool '24.0.7'" }

    stages {
        stage("Checkout") {
            steps {
                // clone repo //
               git([url: 'https://github.com/Polyak313/docker.git', branch: 'feature/docker'])
                    
            }
        }
        stage('Build and Push') {
            steps {
                // assembly docker //
                script {
                    def dockerImage = docker.build("polyak313/nginx:${env.BUILD_NUMBER}")
                    dockerImage.push()
                }
            }
        }
    }
}