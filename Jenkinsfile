
pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building Spring Boot application...'
                sh 'chmod +x mvnw'
                sh './mvnw -B clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh './mvnw -B test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging Spring Boot application...'
                sh './mvnw -B package -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'CI Pipeline completed successfully!'
            archiveArtifacts artifacts: 'target/*.jar',
                             fingerprint: true
        }

        failure {
            echo 'CI Pipeline failed. Check the console output.'
        }
    }
}

