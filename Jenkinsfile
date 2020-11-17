pipeline {
  agent any
  stages {
    stage('Development') {
      parallel {
        stage('Development') {
          agent any
          steps {
            sh 'mvn test'
            sh 'mvn clean install'
            sh 'aws s3 cp target/demo-1.0.0.jar s3://haeron-storage'
            sh '''aws lambda update-function-code --function-name myspringboot \\
                --s3-bucket haeron-storage \\
                --s3-key demo-1.0.0.jar \\
                --region ap-south-1'''
          }
        }

        stage('Code Test') {
          steps {
            sh 'mvn sonar:sonar -Dsonar.host.url=http://13.235.51.178/:8081 -Dlicense.skip=true'
          }
        }

      }
    }

  }
}