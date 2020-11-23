
pipeline {
    agent any
    stages {
        stage('Clone Repo and Clean it') {
            steps {
                // Get some code from a GitHub repository
                sh 'rm -rf Milan'                
                sh 'git clone https://github.com/SakthiDhandapani/Milan.git'
            }
        }
		stage('>>>clean<<<') {
            steps {
                sh "mvn clean install"
		    def deploymentDelay=input id:'Deploy', message:'Deploye to Production?',submitter: 'milan', parameters:[choice(choices['0','1','2'],description:'Hours to Deploy', name:'deploymentDelay')]
		    sleep time: deploymentDelay.toInteger(),unit:'HOURS'
            }
        }
		
stage('SonarQube analysis') {
         steps {
       	 withSonarQubeEnv('SonarQube') {
          sh 'mvn clean package sonar:sonar'
			}
		}
	}

	    stage("Quality Gate") {
            steps {
		    sleep(11)
                timeout(time: 1, unit: 'MINUTES') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }
	stage('>>>package<<<') {
            steps {
                sh "mvn package"
            }
        }
		stage('>>>Deploy into S3<<<') {
			 when {
            branch 'dev'
        }
            steps {
                sh "aws s3 cp target/demo-1.0.0.jar s3://haeron-storage"
            }
        }
		stage('>>>Update Lambda<<<') {
			 when {
            branch 'dev'
        }
            steps {
                sh '''aws lambda update-function-code --function-name myspringboot \\
                --s3-bucket haeron-storage \\
                --s3-key demo-1.0.0.jar \\
                --region ap-south-1'''
            }
        }
    }
}
