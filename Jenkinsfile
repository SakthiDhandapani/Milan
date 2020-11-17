pipeline {
  agent any
  stages {
    stage('Development') {
      steps {
        sh 'mvn test'
        sh 'mvn clean install'
      }
    }

  }
}