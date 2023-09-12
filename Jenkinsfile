pipeline {
    agent any
    tools{
    maven 'my-maven'
    }
    stages {
        stage('Clone') {
            steps {
                echo 'Building..'
                git 'https://github.com/trungthienpyf/jenkins-101.git'
            }
          }
        stage('Build') {
                  steps {
                    sh 'mvn --version'
                    sh 'java --version'
                    sh 'mvn clean package -Dmaven.test.failure.ignore=true'
                  }
                }
    }
}