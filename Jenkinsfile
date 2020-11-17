pipeline {
  agent any
  stages {
    stage('Development') {
      parallel {
        stage('Development') {
          agent any
          steps {
            sh 'mvn test sonar:sonar -Dsonar.host.url=http://13.235.51.178:9000'
            sh 'mvn clean install'
            sh 'aws s3 cp target/demo-1.0.0.jar s3://haeron-storage'
            sh '''aws lambda update-function-code --function-name myspringboot \\
                --s3-bucket haeron-storage \\
                --s3-key demo-1.0.0.jar \\
                --region ap-south-1'''
          }
          steps {
      withSonarQubeEnv('SonarQube') {
         sh "../../../sonar-scanner-2.9.0.670/bin/sonar-scanner"   
      }

      def qualitygate = waitForQualityGate()
      if (qualitygate.status != "OK") {
         error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
      }
   }
        }

      }
    }
  
  }
}
