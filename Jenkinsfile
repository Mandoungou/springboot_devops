
pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

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

        stage('SonarQube Analysis') {
            steps {
                echo 'Analyzing code with SonarQube...'

                withSonarQubeEnv('SonarQube') {
                    sh '''
                        ./mvnw -B \
                        org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                        -Dsonar.projectKey=springboot-devops \
                        -Dsonar.projectName="Spring Boot DevOps"
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo 'Waiting for SonarQube Quality Gate...'

                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {

        always {
            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: true
        }

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

