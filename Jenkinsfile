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
    
    stages {
      steps("cmd"){
         withCredentials([usernamePassword(credentialsId: 'id-2', usernameVariable: 'AKIAIR35NTPMFG3AT4AQ', passwordVariable: '/wT3Z3cUhiVsucAgIrUnojbK10K0wGkfSNGyIsmC')]) {
           // available as an env variable, but will be masked if you try to print it out any which way
           sh 'echo $PASSWORD'
           // also available as a Groovy variable—note double quotes for string interpolation
           echo "$USERNAME"
         }
      }
    }
  }
    stages{
    stage('Push'){
        steps{
            sh "aws s3 cp /var/lib/jenkins/workspace/DEV-CICD-LAMBDA/target/demo-1.0.0.jar s3://${bucket}"
        }
        
    }
          

  }

}
