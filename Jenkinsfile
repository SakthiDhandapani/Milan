def bucket = 'haeron-storage'
def functionName = 'myspringboot'
def region = 'ap-south-1'
pipeline {

    agent any
    tools {
        maven 'MAVEN_HOME' 
    }
    stages {
        stage('Compile stage') {
            steps {
                sh "mvn clean install" 
        }
    }
     
    stage('Push'){
        steps{
            sh "aws s3 cp /target/*.jar s3://${bucket}"
        }
        
    }
          

  }

}
