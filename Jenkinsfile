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
            sh "aws s3 cp target/demo-1.0.0.jar s3://${bucket}"
        }
        
    }
     
	}     

  }

  
