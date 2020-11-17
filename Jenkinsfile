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
        }

      }
    }
  stage("Quality Gate"){
          sleep(60)
      timeout(time: 1, unit: 'MINUTES') {
     def qg = waitForQualityGate()
    print "Finished waiting"
    if (qg.status != 'OK') {
        error "Pipeline aborted due to quality gate failure: ${qg.status}"
    }
}  
      }
  }
}
