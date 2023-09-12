pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                echo 'Building..'
                git 'https://github.com/trungthienpyf/jenkins-101.git'
            }
        } stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}