pipeline {
    agent {label ('slave1')}

    stages {
        stage("Checkout") {
            steps {
                // clone repo //
                git branch: 'feature/docker'
                    url: 'https://github.com/Polyak313/docker.git'
            }
        }
        stage('Build and Push') {
            steps {
                // assembly docker //
                script {
                    def=dockerImage = docker.build('polyak313/nginx:${env.BUILD_NUMBER}')
                    dockerImage.push()
                }
            }
        }
    }
}