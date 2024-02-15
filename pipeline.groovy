pipeline {
    agent {label ('slave1')}

    tools { // parametrs tools
        dockerTool '2.23.3' // docker version
    }

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