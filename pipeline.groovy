pipeline {
    agent any
    
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-cred-polyak313')
    }
    
    stages {
        stage('Checkout') {
            steps {
               // git 'https://github.com/Polyak313/docker.git'
                checkout scmGit(
    branches: [[name: 'feature/docker']],
    userRemoteConfigs: [[credentialsId:  'git@github.com:Polyak313/docker.git',url: 'https://github.com/Polyak313/docker/nginx/Dockerfile.git']])
           
            sh 'pwd'
            sh 'ls -la'
    	

            }
        }
        
        stage('Build and Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://hub.docker.com/r/polyak313/nginx', 'dockerhub-cred-polyak313') {
                        def customImage = docker.build('polyak313/nginx', '.')
                        customImage.push('latest')
                    }
                }
            }
        }
    }
}
