pipeline {
    agent any
    
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-cred-polyak313')
    }
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Polyak313/docker.git'
                git branch: 'feature/docker'
            }
        }
        
        stage('Build and Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                        def customImage = docker.build('polyak313/nginx', '.')
                        customImage.push('latest')
                    }
                }
            }
        }
    }
}
