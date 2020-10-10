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
                sh "mvn clean compile" 
        }
    }

         stage('testing stage') {
             steps {
                sh "mvn test"
        }
    }
        stage('Push1'){
        steps{
            sh "aws s3 list"
        }
        
    }
    stage('Push2'){
        steps{
            sh "aws s3 cp /target/*.jar s3://${bucket}"
        }
        
    }
          

  }

}
